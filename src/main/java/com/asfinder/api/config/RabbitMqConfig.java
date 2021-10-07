package com.asfinder.api.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.topicexchangename}")
    String topicExchangeName;

    @Value("${spring.rabbitmq.queuename}")
    String queueName;

    @Value("${spring.rabbitmq.queuename2}")
    String queueName2;

    @Value("${spring.rabbitmq.host}")
    String host;

    @Value("${spring.rabbitmq.username}")
    String username;

    @Value("${spring.rabbitmq.password}")
    String password;

    @Value("${spring.rabbitmq.port}")
    int port;

    @Value("${spring.rabbitmq.routingkey}")
    String routingKey;

    @Value("${spring.rabbitmq.routingkey2}")
    String routingKey2;

    @Bean
    Queue queue()
    {
        return new Queue(queueName, false);
    }

    @Bean
    Queue queue2(){
        return new Queue(queueName2, false);
    }

    @Bean
    TopicExchange exchange()
    {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    Binding binding2(Queue queue2, TopicExchange exchange){
        return BindingBuilder.bind(queue2).to(exchange).with(routingKey2);
    }

    @Bean
    public ConnectionFactory connectionFactory()
    {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
                host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}
