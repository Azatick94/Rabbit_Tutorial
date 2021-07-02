package com.rabbit.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String QUEUE1 = "testQueue";
    public static final String QUEUE2 = "testQueue2";
    public static final String EXCHANGE = "testExchange";
    public static final String ROUTING_KEY = "testKey";

    // Setting RabbitMQ connection Properties
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        // setting connection properties
        cachingConnectionFactory.setHost("localhost");
        cachingConnectionFactory.setPort(5672);
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    // Creating 2 Queues Programmatically
    @Bean
    public Queue queue1() {
        return new Queue(QUEUE1, true);
    }

    // ALTERNATIVE WAY to create Queues
//    @Bean
//    public Queue queue1() {
//        return QueueBuilder
//                .durable(QUEUE1)
//                .autoDelete()
//                .build();
//    }

    @Bean
    public Queue queue2() {
        return new Queue(QUEUE2, true);
    }

    // CREATING EXCHANGES
    @Bean
    public Exchange testExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    // ALTERNATIVE WAY TO create Exchanges
//    @Bean
//    public Exchange testExchange() {
//        return ExchangeBuilder.directExchange(EXCHANGE)
//                .durable(true)
//                .build();
//    }

    // Creating BINDINGS between Exchanges <-> Queues
    @Bean
    public Binding binding1(@Qualifier("queue1") Queue queue, @Qualifier("testExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }

    // Another way
//    @Bean
//    public Binding binding1() {
//        return new Binding(QUEUE1, Binding.DestinationType.QUEUE, EXCHANGE, ROUTING_KEY, null);
//    }

    @Bean
    public Binding binding2(@Qualifier("queue2") Queue queue, @Qualifier("testExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }

}
