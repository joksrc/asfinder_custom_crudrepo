package com.asfinder.api.repository;

import com.asfinder.api.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {


}
