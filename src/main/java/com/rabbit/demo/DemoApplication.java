package com.rabbit.demo;

import com.rabbit.demo.model.Person;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.rabbit.demo.config.RabbitMQConfiguration.EXCHANGE;
import static com.rabbit.demo.config.RabbitMQConfiguration.ROUTING_KEY;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application Started");

        Person person = new Person("Azat", 26, "demo@mail.ru");

        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, "Hello RabbitMQ");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, person);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, person.toString());

        System.out.println("Application Finished");
    }
}
