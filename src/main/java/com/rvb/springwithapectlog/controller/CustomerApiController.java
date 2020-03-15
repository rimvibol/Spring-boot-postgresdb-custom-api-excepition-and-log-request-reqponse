package com.rvb.springwithapectlog.controller;

import com.rvb.springwithapectlog.model.Customer;
import com.rvb.springwithapectlog.repository.CustomerRepository;
import com.rvb.springwithapectlog.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest-api")
public class CustomerApiController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;




    @PostMapping(value = "/add-customer")
    public Customer addCustomer(@RequestBody Customer customer){
        Customer c = new Customer ();
//        c.setAge(customer.getAge());
//        c.setfName(customer.getfName());
//        c.setLname(customer.getLname());
//        c.setGender(customer.getGender());
        return customerService.saveCustomer(customer);

    }

    @GetMapping(value = "/get-customer-by-id/{id}")
    public Optional<Customer> customer (@PathVariable Long id){

        return customerRepository.findById(id);
    }
}
