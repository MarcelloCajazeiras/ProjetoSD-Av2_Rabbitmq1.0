package com.emailsd.sistemadistribuidos.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;

@Configuration
public class RabbitMQ {

    @Bean
    public Queue userRegistrationQueue() {
        return new Queue("user.registration");
    }

    @Bean
    public Queue userUpdateQueue() {
        return new Queue("user.update");
    }

    @Bean
    public TopicExchange UserExchange() {
        return new TopicExchange("user.Exchange");
    }

    @Bean
    public Binding UserRegistrationBiding(Queue UserRegistrationQueue, TopicExchange UserExchange){
        return BindingBuilder.bind(UserRegistrationQueue).to(UserExchange).with("user.registation.#");

    }

    @Bean
    public Binding UserUpdateBiding(Queue UserUpdateQueue, TopicExchange UserExchange){
        return BindingBuilder.bind(UserUpdateQueue).to(UserExchange).with("user.update.#");
    }
}