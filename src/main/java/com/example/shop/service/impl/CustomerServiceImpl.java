package com.example.shop.service.impl;

import com.example.shop.entity.Customer;
import com.example.shop.exception.InvalidParameter;
import com.example.shop.exception.NotFoundException;
import com.example.shop.model.CustomerModel;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final ModelMapper mapper;

    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean add(CustomerModel customerModel) {
        repository.save(mapper.map(customerModel, Customer.class));
        return true;
    }

    @Override
    public boolean update(CustomerModel customerModel) throws Exception {
        Optional<Customer> customerById = repository.findById(customerModel.getId());
        if (customerById.isEmpty()) {
            throw new NotFoundException("Customer not found. Id :" + customerModel.getId());
        }
        repository.save(mapper.map(customerModel, Customer.class));

        return true;
    }

    @Override
    public boolean delete(int id) throws NotFoundException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            throw new NotFoundException("customer not found with id : " + id);
        }
    }

    @Override
    public CustomerModel find(int id) throws NotFoundException {
        //find and return values from database
        Optional<Customer> byId = repository.findById(id);
        if (byId.isPresent()) {
            return mapper.map(repository.findById(id), CustomerModel.class);
        } else {
            throw new NotFoundException("customer not found with id: " + id);
        }
    }

    /**
     * @return List<CustomerModel> list of customers
     * @throws InvalidParameter,NotFoundException same as original method
     */

    @Override
    public List<CustomerModel> getAll() throws Exception {
        return getAll(0, 10);
    }

    /**
     * @param start start value
     * @param end   end value
     * @return List<CustomerModel> list of customers
     * @throws InvalidParameter,NotFoundException invalidParameter exception for when enter invalid data, CustomerNotFoundException for if their not customers
     */
    @Override
    public List<CustomerModel> getAll(int start, int end) throws Exception {
        //empty array dedication
        List<Customer> returnModel = repository.getAll(start - 1, end + 1);
        // check parameter more than 0
        if (start <= 0) {
            throw new InvalidParameter("start: must enter grater than 0", "int");
        }
        //check parameter grater than 0
        if (end < 0) {
            throw new InvalidParameter("end: must enter  0 or more", "int");
        }
        // check empty and throw exception
        if (returnModel.isEmpty()) {
            throw new NotFoundException("no customers to display");
        }

        return returnModel.stream()
                .map(customer -> new ModelMapper()
                        .map(customer, CustomerModel.class))
                .collect(Collectors.toList());
    }
}
