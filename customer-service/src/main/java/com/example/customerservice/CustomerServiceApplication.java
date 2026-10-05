package com.example.customerservice;


import com.example.customerservice.entities.Customer;
import com.example.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerService customerService) {
        return args -> {
            List.of("Mohamed", "Imane", "Yassine").forEach(name -> {
                Customer customer = Customer.builder()
                        .name(name)
                        .email(name.toLowerCase() + "@gmail.com")
                        .build();
                customerService.saveCustomer(customer);
            });
        };
    }
}
