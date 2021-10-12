package com.asfinder.api.service;

import com.asfinder.api.model.Order;
import com.asfinder.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShippingService {


    private final OrderRepository orderRepository;
    private final RabbitMqService rabbitMqService;

    @Transactional
    public void toOrder(Order order){
        order.setCreationDate(new Date());
        orderRepository.save(order);
        rabbitMqService.send("asfinder_qex","sales_amazon", order);

        //rabbitMqService.send("asfinder_qex","sales_amazon", new Gson().toJson(order));

    }
}
