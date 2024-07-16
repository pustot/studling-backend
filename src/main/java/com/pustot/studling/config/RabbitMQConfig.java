package com.pustot.studling.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue userQueue() {
        return new Queue("user.queue");
    }

    @Bean
    public Queue anotherServiceQueue() {
        return new Queue("another.service.queue");
    }

    @Bean
    public Binding userBinding(Queue userQueue, TopicExchange exchange) {
        return BindingBuilder.bind(userQueue).to(exchange).with("user.#");
    }

    @Bean
    public Binding anotherServiceBinding(Queue anotherServiceQueue, TopicExchange exchange) {
        return BindingBuilder.bind(anotherServiceQueue).to(exchange).with("another.service.#");
    }
}
