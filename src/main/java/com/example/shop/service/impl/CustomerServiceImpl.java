package com.example.shop.service.impl;

import com.example.shop.entity.Customer;
import com.example.shop.model.CustomerModel;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final ModelMapper mapper;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean add(CustomerModel customerModel){
        repository.save(mapper.map(customerModel, Customer.class));
        return true;
    }

    @Override
    public boolean update(CustomerModel customerModel) {
        repository.save(mapper.map(customerModel, Customer.class));
        return true;
    }

    @Override
    public boolean delete(int id){
        repository.deleteById(id);
        return true;
    }

    @Override
    public CustomerModel find(int id) {
        //find and return values from database
        return mapper.map(repository.findById(id), CustomerModel.class);
    }

    @Override
    public List<CustomerModel> getAll() {
        //empty array dedication
        List<CustomerModel> returnModel = new ArrayList<>();

        //get all customer and ready to return
        for (Customer c :
                repository.findAll()) {
            returnModel.add(mapper.map(c, CustomerModel.class));
        }

        return returnModel;
    }
}
