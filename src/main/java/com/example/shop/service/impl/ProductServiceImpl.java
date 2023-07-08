package com.example.shop.service.impl;

import com.example.shop.entity.Product;
import com.example.shop.exception.NotFoundException;
import com.example.shop.model.ProductModel;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;

    @Override
    public boolean add(ProductModel productModel) throws Exception {
        repository.save(new ModelMapper().map(productModel, Product.class));
        return true;
    }

    @Override
    public boolean update(ProductModel productModel) throws Exception {
        if (repository.findById(productModel.getProductId()).isPresent()) {
            repository.save(new ModelMapper().map(productModel, Product.class));
        } else {
            throw new NotFoundException("product not found to update . id " + productModel.getProductId());
        }
        return true;
    }

    @Override
    public boolean delete(int id) throws Exception {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("product not found to delete . id " + id);
        }
        return true;
    }

    @Override
    public ProductModel find(int id) throws Exception {
        Optional<Product> byId = repository.findById(id);
        if (byId.isPresent()) {
            return new ModelMapper().map(byId.get(), ProductModel.class);
        } else {
            throw new NotFoundException("product not found id :" + id);
        }

    }

    @Override
    public List<ProductModel> getAll(int start, int end) throws Exception {
        List<ProductModel> productModels = new ArrayList<>();
        List<Product> products = repository.getAll(start, end);
        if (!products.isEmpty()) {
            products.forEach(e -> productModels.add(new ModelMapper().map(e, ProductModel.class)));
        } else {
            throw new NotFoundException("not product to display");
        }
        return productModels;
    }
}
