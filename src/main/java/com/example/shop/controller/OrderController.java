package com.example.shop.controller;

import com.example.shop.model.OrderModel;
import com.example.shop.service.OrderService;
import com.example.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderModel>> getAllOrders(@RequestParam("start") int start, @RequestParam(name = "end") int end) throws Exception {
        return new ResponseEntity<>(orderService.getAll(start, end), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderModel> getOrderById(@PathVariable("id") int id) throws Exception {
        return new ResponseEntity<>(orderService.find(id), HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createOrder(@RequestBody @Valid OrderModel orderModel) throws Exception {
        return new ResponseEntity<>(orderService.add(orderModel), HttpStatus.CREATED);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> updateOrder(@RequestBody @Valid OrderModel orderModel) throws Exception {
        return new ResponseEntity<>(orderService.update(orderModel), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteOrder(@PathVariable("id") int id) throws Exception {
        return new ResponseEntity<>(orderService.delete(id), HttpStatus.OK);
    }


}
