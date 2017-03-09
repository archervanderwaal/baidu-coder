package com.baidu.message;

import com.baidu.enums.MessageType;

/**
 * <p>
 * Created by mayongbin01 on 2017/1/25.
 *
 * @author mayongbin01
 */
public class HiMessage extends Message {

    /**
     * @param text
     */
    public HiMessage(String text) {
        super(MessageType.HI, text);
    }
}
