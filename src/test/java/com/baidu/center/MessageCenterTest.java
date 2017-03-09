package com.baidu.center;


import org.junit.Test;

import com.baidu.enums.MessageType;
import com.baidu.exception.AddMessageSenderException;
import com.baidu.exception.MessageIsNullException;
import com.baidu.exception.NoSuchMessageTypeException;
import com.baidu.exception.UnSupportMessageException;
import com.baidu.message.SmsMessage;
import com.baidu.sender.MessageSenderFactory;
import com.baidu.sender.interfance.MessageSender;

/**
 * <p>
 * Created by mayongbin01 on 2017/1/25.
 *
 * @author mayongbin01
 */
public class MessageCenterTest {

    @Test
    public void addSender() throws NoSuchMessageTypeException, AddMessageSenderException {
        MessageCenter messageCenter = MessageCenter.getInstance();
        MessageSenderFactory messageSenderFactory = new MessageSenderFactory();

        MessageSender sender = messageSenderFactory.getMessageSender(MessageType.HI);
        boolean result = messageCenter.addSender(sender);
    }

    @Test
    public void sendTest() throws MessageIsNullException, NoSuchMessageTypeException, AddMessageSenderException,
            UnSupportMessageException {
        MessageCenter messageCenter = MessageCenter.getInstance();
        MessageSenderFactory messageSenderFactory = new MessageSenderFactory();

        messageCenter.addSender(messageSenderFactory.getMessageSender(MessageType.HI));
        messageCenter.send(new SmsMessage("hahhaha"));
    }
}
