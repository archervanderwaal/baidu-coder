package com.baidu.message;

import com.baidu.enums.MessageType;

/**
 * <p>
 * Created by mayongbin01 on 2017/1/25.
 *
 * @author mayongbin01
 */
public class EmailMessage extends Message {

    /**
     * @param text
     */
    public EmailMessage(String text) {
        super(MessageType.EMAIL, text);
    }
}
