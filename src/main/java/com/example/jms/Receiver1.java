package com.example.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver1.class);

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        LOGGER.info(email.toString());
    }
}
