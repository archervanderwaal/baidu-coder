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
public class SmsMessageSender implements MessageSender {

    private static Logger logger = Logger.getLogger(SmsMessageSender.class);

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

        logger.info("send Sms Message --- " + message.getText() + " message successfully! content:" + message.getText
                ());
        return true;
    }

    /**
     * @return
     *
     * @描述：时间间隔
     */
    @Override
    public long getExecuteInterval() {
        return 100L;
    }

    /**
     * 得到消息的类型
     *
     * @return
     */
    @Override
    public MessageType getMessageType() {
        return MessageType.SMS;
    }
}
