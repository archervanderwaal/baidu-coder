package com.baidu.client;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.baidu.center.MessageCenter;
import com.baidu.config.MyConfig;
import com.baidu.enums.MessageType;
import com.baidu.exception.AddMessageSenderException;
import com.baidu.exception.MessageIsNullException;
import com.baidu.exception.NoSuchMessageTypeException;
import com.baidu.exception.UnSupportMessageException;
import com.baidu.message.EmailMessage;
import com.baidu.message.HiMessage;
import com.baidu.message.SmsMessage;
import com.baidu.sender.MessageSenderFactory;

/**
 *         <p>
 *         Created by mayongbin01 on 2017/1/25.
 *
 *  @author mayongbin01
 */
public class ClientDemo {

    private static Logger logger = Logger.getLogger(ClientDemo.class);

    public static void main(String[] args) {

        /**
         * get spring Ioc container
         */
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        /**
         * get bean
         */
        MessageCenter messageCenter = context.getBean(MessageCenter.class);

        MessageSenderFactory messageSenderFactory = context.getBean(MessageSenderFactory.class);

        try {
            messageCenter.addSender(messageSenderFactory.getMessageSender(MessageType.EMAIL));
            messageCenter.addSender(messageSenderFactory.getMessageSender(MessageType.HI));
            messageCenter.addSender(messageSenderFactory.getMessageSender(MessageType.SMS));
        } catch (AddMessageSenderException e) {
            e.printStackTrace();
        } catch (NoSuchMessageTypeException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <= 50; i++) {
            try {
                messageCenter.send(new HiMessage("I am a Hi Message"));
                messageCenter.send(new SmsMessage("I am a Sms Message"));
                messageCenter.send(new EmailMessage("I am a Email Message"));
            } catch (MessageIsNullException e) {
                e.printStackTrace();
            } catch (UnSupportMessageException e) {
                e.printStackTrace();
            }
        }
    }
}
