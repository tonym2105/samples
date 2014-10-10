package com.mm.boot.multidb.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.boot.multidb.model.order.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
