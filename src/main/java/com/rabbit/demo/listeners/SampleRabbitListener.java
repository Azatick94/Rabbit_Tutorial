package com.rabbit.demo.listeners;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.rabbit.demo.config.RabbitMQConfiguration.QUEUE1;
import static com.rabbit.demo.config.RabbitMQConfiguration.QUEUE2;

@Component
public class SampleRabbitListener {

    // listening for messages from 2 Queues
    @RabbitListener(queues = {QUEUE1, QUEUE2})
    public void onMessage(Message message) {
        System.out.println("Got message from '" + message.getMessageProperties().getConsumerQueue() + "'" +
                ", message: '" + new String(message.getBody()) + "'");
    }
}


