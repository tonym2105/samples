package com.mm.boot.multidb.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.boot.multidb.model.customer.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
