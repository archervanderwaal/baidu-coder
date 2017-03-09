package com.baidu.sender.impl;

import org.apache.log4j.Logger;

import com.baidu.enums.MessageType;
import com.baidu.message.Message;
import com.baidu.sender.interfance.MessageSender;

/**
 * <p>
 * Created by mayongbin01 on 2017/1/25.
 *
 * @author mayongbin01
 */
public class EmailMessageSender implements MessageSender {

    private static final long MINUTE_EMAILMESSAGE_COUNT = 200;

    private static final Logger logger = Logger.getLogger(EmailMessageSender.class);

    /**
     * 发送消息
     *
     * @param message
     *
     * @return
     */
    @Override
    public boolean send(Message message) {
        if (message == null) {
            logger.error("send message error: message == null");
            return false;
        }

        logger.info("send Email message --- " + message.getText() + " message successfully! content:" + message.getText
                ());
        return true;
    }

    /**
     * 得到消息类型
     *
     * @return
     */
    @Override
    public MessageType getMessageType() {
        return MessageType.EMAIL;
    }

    /**
     * @return
     *
     * @描述 得到时间间隔
     */
    @Override
    public long getExecuteInterval() {
        return 60 * 1000 / MINUTE_EMAILMESSAGE_COUNT;
    }
}
