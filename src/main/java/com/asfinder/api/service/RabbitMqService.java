package com.asfinder.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqService {

    private final RabbitTemplate rabbitTemplate;

    public void send(String exchange, String routingKey, Object payload){

        rabbitTemplate.convertAndSend(exchange, routingKey, payload);
    }
}
