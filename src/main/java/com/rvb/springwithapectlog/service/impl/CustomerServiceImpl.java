package com.rvb.springwithapectlog.service.impl;

import com.rvb.springwithapectlog.aop_config.EnableSaveLog;
import com.rvb.springwithapectlog.model.Customer;
import com.rvb.springwithapectlog.repository.CustomerRepository;
import com.rvb.springwithapectlog.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @EnableSaveLog
    @Override
    public Customer saveCustomer(Customer customer) {
        customerRepository.save(customer);

        return customer;
    }
}
