package com.baidu.center;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.baidu.enums.MessageType;
import com.baidu.exception.AddMessageSenderException;
import com.baidu.exception.MessageIsNullException;
import com.baidu.exception.UnSupportMessageException;
import com.baidu.message.Message;
import com.baidu.sender.interfance.MessageSender;

/**
 * <p>
 * Created by mayongbin01 on 2017/1/25.
 *
 * @author mayongbin01
 */
@Component
public class MessageCenter {

    /**
     * logging
     */
    private static final Logger logger = Logger.getLogger(MessageCenter.class);

    /**
     * message queue map
     */
    private Map<MessageType, LinkedBlockingQueue<Message>> messageQueueMap;

    /**
     * thread pool
     */
    private ScheduledExecutorService executorService;
    private static final int MAX_POOL_SIZE = 3;

    /**
     * support sender
     */
    private List<MessageSender> messageSenderList;

    private static volatile MessageCenter instance;

    private MessageCenter() {
        this.messageQueueMap = new ConcurrentHashMap<>();
        this.executorService = Executors.newScheduledThreadPool(MAX_POOL_SIZE);
        this.messageSenderList = new ArrayList<>();
    }

    public static MessageCenter getInstance() {
        if (instance == null) {
            synchronized (MessageCenter.class) {
                if (instance == null) {
                    instance = new MessageCenter();
                }
            }
        }
        return instance;
    }

    /**
     * add sender to messageCenter
     *
     * @param messageSender
     *
     * @return
     */
    public boolean addSender(MessageSender messageSender) throws AddMessageSenderException {
        if (messageSender == null) {
            logger.error("add sender to messageCenter error: sender == null !");
            throw new AddMessageSenderException("add sender to messageCenter error: sender == null !");
        } else {
            this.messageSenderList.add(messageSender);
            /**
             * start execute task
             */
            executeScheduleTask(messageSender);
            return true;
        }
    }

    /**
     * execute task
     *
     * @param sender
     *
     * @return
     */
    public boolean executeScheduleTask(final MessageSender sender) {

        if (sender == null) {
            logger.error("sender is null! ");
            return false;
        }

        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                /**
                 * get message queue for message queue map
                 */
                LinkedBlockingQueue<Message> messageQueue = messageQueueMap.get(sender.getMessageType());

                if (messageQueue == null || messageQueue.isEmpty()) {
                    return;
                }

                Message message = messageQueue.peek();

                if (sender.send(message)) {
                    messageQueue.poll();
                    return;
                } else {
                    logger.info(message + "send failed!");
                    return;
                }
            }
        }, 0, sender.getExecuteInterval(), TimeUnit.MILLISECONDS);
        return true;
    }

    /**
     * add message to message queue
     *
     * @param message
     *
     * @return
     *
     * @throws MessageIsNullException
     */
    public boolean send(Message message) throws MessageIsNullException, UnSupportMessageException {
        if (message == null) {
            logger.error("send message but message == null !");
            throw new MessageIsNullException("send message but message == null !");
        }

        boolean isSupport = false;
        for (MessageSender messageSender : messageSenderList) {
            if (messageSender.getMessageType().equals(message.getType())) {
                isSupport = true;
            }
        }

        if (!isSupport) {
            throw new UnSupportMessageException("sorry, your Message type is unsupport!");
        }

        /**
         * get message queue
         */
        LinkedBlockingQueue<Message> messageQueue = messageQueueMap.get(message.getType());

        if (messageQueue == null) {
            /**
             * init
             */
            messageQueue = new LinkedBlockingQueue<>();
        }

        try {
            messageQueue.put(message);
            messageQueueMap.put(message.getType(), messageQueue);
            return true;
        } catch (InterruptedException e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * shutdown
     */
    public void destory() {
        executorService.shutdown();
    }
}
