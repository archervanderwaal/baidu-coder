package com.baidu.sender;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.baidu.enums.MessageType;
import com.baidu.exception.NoSuchMessageTypeException;
import com.baidu.sender.impl.EmailMessageSender;
import com.baidu.sender.impl.HiMessageSender;
import com.baidu.sender.impl.SmsMessageSender;
import com.baidu.sender.interfance.MessageSender;

/**
 * <p>
 * Created by mayongbin01 on 2017/1/25.
 *
 * @author mayongbin01
 */
@Component
public class MessageSenderFactory {
    private static Logger logger = Logger.getLogger(MessageSenderFactory.class);

    /**
     * 获得消息发送器
     *
     * @param type
     *
     * @return
     */
    public MessageSender getMessageSender(MessageType type) throws NoSuchMessageTypeException {
        switch (type) {
            case HI:
                return new HiMessageSender();
            case SMS:
                return new SmsMessageSender();
            case EMAIL:
                return new EmailMessageSender();
            default:
                logger.error("no such other message type!");
                throw new NoSuchMessageTypeException("no such other message type!");
        }
    }
}
