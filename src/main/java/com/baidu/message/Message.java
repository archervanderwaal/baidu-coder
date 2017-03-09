package com.baidu.message;

import com.baidu.enums.MessageType;

/**
 * <p>
 * Created by mayongbin01 on 2017/1/25.
 *
 * @author mayongbin01
 */
public class Message {

    private MessageType type;

    private String text;

    /**
     * @param type
     * @param text
     */
    Message(MessageType type, String text) {
        this.type = type;
        this.text = text;
    }

    /**
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return
     */
    public MessageType getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(MessageType type) {
        this.type = type;
    }
}
