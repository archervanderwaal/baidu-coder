package com.baidu.message;

import com.baidu.enums.MessageType;

/**
 * <p>
 * Created by mayongbin01 on 2017/1/25.
 *
 * @author mayongbin01
 */
public class SmsMessage extends Message {

    /**
     * @param text
     */
    public SmsMessage(String text) {
        super(MessageType.SMS, text);
    }
}
