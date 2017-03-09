package com.baidu.sender.interfance;

import com.baidu.enums.MessageType;
import com.baidu.message.Message;

/**
 * <p>
 * Created by mayongbin01 on 2017/1/25.
 *
 * @author mayongbin01
 */
public interface MessageSender {

    /**
     * 发送消息
     *
     * @param message
     *
     * @return
     */
    boolean send(Message message);

    /**
     * 得到消息的类型
     *
     * @return
     */
    MessageType getMessageType();

    /**
     * 时间间隔
     *
     * @return
     */
    long getExecuteInterval();
}
