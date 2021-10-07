package com.asfinder.api.repository.impl;

import com.asfinder.api.model.Order;
import com.asfinder.api.repository.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl
        extends GenericRepositoryImpl<Order, Integer>
        implements OrderRepository {
}
