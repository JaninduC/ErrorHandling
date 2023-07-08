package com.example.shop.controller;

import com.example.shop.model.ProductModel;
import com.example.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductModel>> getAllProducts(@RequestParam("start") int start, @RequestParam("end") int end) throws Exception {
        return new ResponseEntity<>(productService.getAll(start, end), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") int id) throws Exception {
        return new ResponseEntity<>(productService.find(id), HttpStatus.OK);
    }

    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> SaveProduct(@RequestBody @Valid ProductModel model) throws Exception {
        return new ResponseEntity<>(productService.add(model), HttpStatus.CREATED);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> UpdateProduct(@RequestBody @Valid ProductModel model) throws Exception {
        return new ResponseEntity<>(productService.update(model), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> DeleteProductById(@PathVariable("id") int id) throws Exception {
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }
}
