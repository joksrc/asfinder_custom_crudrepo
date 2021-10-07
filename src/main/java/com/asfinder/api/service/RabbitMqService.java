package com.asfinder.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RabbitMqService {

    private final RabbitTemplate rabbitTemplate;
    /*RabbitMqService (RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }*/
    public void send(String exchange, String routingKey, Object object){
        rabbitTemplate.convertAndSend(exchange, routingKey, "hola :_ "+new Date());
    }
}
