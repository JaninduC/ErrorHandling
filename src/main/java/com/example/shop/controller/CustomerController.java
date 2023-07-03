package com.example.shop.controller;

import com.example.shop.exception.CustomerNotFoundException;
import com.example.shop.model.CustomerModel;
import com.example.shop.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/customer/")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<Object> getAllCustomers(@RequestParam int start,@RequestParam int end) throws Exception {
        return new ResponseEntity<>(service.getAll(start,end), HttpStatusCode.valueOf(200));
    }

    @PutMapping()
    public ResponseEntity<Object> addCustomer(@RequestBody @Valid CustomerModel customerModel) throws Exception {
        return new ResponseEntity<>(service.add(customerModel), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> updateCustomer(@RequestBody @Valid CustomerModel customerModel) throws Exception {
        return new ResponseEntity<>(service.update(customerModel), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") int id) throws CustomerNotFoundException {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findCustomerById(@PathVariable("id") int id) throws CustomerNotFoundException {
        return new ResponseEntity<>(service.find(id), HttpStatus.OK);
    }

}
