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
public class HiMessageSender implements MessageSender {

    private static Logger logger = Logger.getLogger(MessageSender.class);

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

        logger.info("send Hi Message --- " + message.getText() + " message successfully! content:" + message.getText());
        return true;
    }

    /**
     * 得到消息的类型
     *
     * @return
     */
    @Override
    public MessageType getMessageType() {
        return MessageType.HI;
    }

    /**
     * @return
     *
     * @描述：时间间隔
     */
    @Override
    public long getExecuteInterval() {
        return 500L;
    }
}
