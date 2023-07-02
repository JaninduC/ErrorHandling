package com.example.shop.controller;

import com.example.shop.model.CustomerModel;
import com.example.shop.model.error.ErrorModel;
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
    public ResponseEntity<Object> getAllCustomers() {
        try {
            return new ResponseEntity<>(service.getAll(), HttpStatusCode.valueOf(200));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<Object> addCustomer(@RequestBody @Valid CustomerModel customerModel) {
        try {
            return new ResponseEntity<>(service.add(customerModel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> updateCustomer(@RequestBody CustomerModel customerModel) {
        try {
            return new ResponseEntity<>(service.update(customerModel), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> findCustomerById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(service.find(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
