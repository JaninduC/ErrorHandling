package com.example.shop.service.impl;

import com.example.shop.entity.Customer;
import com.example.shop.entity.Order;
import com.example.shop.entity.OrderItem;
import com.example.shop.entity.Product;
import com.example.shop.exception.LowStockException;
import com.example.shop.exception.NotFoundException;
import com.example.shop.model.OrderItemModel;
import com.example.shop.model.OrderModel;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.repository.OrderItemRepository;
import com.example.shop.repository.OrderRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@EnableTransactionManagement
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public boolean add(OrderModel orderModel) throws Exception {
        Optional<Customer> customerById = customerRepository.findById(orderModel.getCustomerId());
        Customer customer;
        if (customerById.isPresent()) {
            customer = customerById.get();
        } else {
            throw new NotFoundException("customer not found. customer Id: " + orderModel.getCustomerId());
        }
        Order order = orderRepository.save(Order.builder().customer(customer).orderDate(orderModel.getOrderDate()).totalAmount(orderModel.getTotalAmount()).build());
        List<OrderItemModel> orderItemModels = new ArrayList<>(orderModel.getOrderItem());
        if (!orderItemModels.isEmpty()) {

            orderItemModels.forEach(orderItemModel -> {
                Product product;
                Optional<Product> productById = productRepository.findById(orderItemModel.getProductId());
                if (productById.isPresent()) {
                    product = productById.get();
                    int productQty = product.getQty() - orderItemModel.getQty();
                    if (productQty < 0) {
                        product.setQty(productQty);
                    } else {
                        throw new RuntimeException(new LowStockException("no not enough qty in product id: " + product.getProductId()));
                    }
                } else {
                    throw new RuntimeException(new NotFoundException("product id not found. product Id : " + orderItemModel.getProductId()));
                }
//                productRepository.save(product);
                orderItemModel.setOrderId(order.getOrderId());
                orderItemRepository.save(
                        OrderItem.builder()
                                .subTotal(orderItemModel.getSubTotal())
                                .qty(orderItemModel.getQty())
                                .order(order)
                                .product(product)
                                .build()
                );
            });

        } else {
            throw new NotFoundException("order details not found ");
        }

        return true;
    }

    @Transactional
    @Override
    public boolean update(OrderModel orderModel) throws Exception {
        Order order;

        List<OrderItem> orderItems = new ArrayList<>();

        Optional<Order> orderById = orderRepository.findById(orderModel.getOrderId());
        if (orderById.isPresent()) {
            order = orderById.get();
            order.setOrderDate(orderModel.getOrderDate());
            orderModel.getOrderItem().forEach(oi -> {
                OrderItem orderItem;
                Optional<OrderItem> oiById = orderItemRepository.findById(oi.getOrderItemId());
                Product product;
                if (oiById.isPresent()) {
                    orderItem = oiById.get();
                    if (oi.getProductId() == orderItem.getProduct().getProductId()) {

                        product = orderItem.getProduct();
                        if (oi.getQty() < orderItem.getQty()) {
                            int currentQty = product.getQty() + oi.getQty();
                            if (product.getQty() > currentQty) {
                                product.setQty(currentQty);
                            } else {
                                throw new RuntimeException(new LowStockException("no not enough qty in product id: " + product.getProductId()));
                            }

                        } else {
                            product.setQty(product.getQty() - oi.getQty());
                        }
                        orderItem.setProduct(product);
                        orderItem.setQty(oi.getQty());
                        orderItem.setSubTotal(oi.getSubTotal());

                    }
                    orderItems.add(orderItem);


                } else {
                    throw new RuntimeException(new NotFoundException("order item not found id: " + oi.getOrderItemId()));
                }
                if (order.getCustomer().getId() != orderModel.getCustomerId()) {
                    Optional<Customer> customerById = customerRepository.findById(orderModel.getCustomerId());
                    if (customerById.isPresent()) {
                        order.setCustomer(customerById.get());
                    } else {
                        throw new RuntimeException(new NotFoundException("Customer not found Id : " + orderModel.getCustomerId()));
                    }
                }
                order.setOrderDate(orderModel.getOrderDate());
                order.setOrderItem(orderItems);
                orderRepository.save(order);

            });
        } else {
            throw new NotFoundException("order not found id :" + orderModel.getOrderId());
        }

        return true;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Optional<Order> orderById = orderRepository.findById(id);
        if (orderById.isPresent()) {
            Order order = orderById.get();
            order.getOrderItem().forEach(orderItem -> {
                Product product = orderItem.getProduct();
                if (product != null) {
                    product.setQty(product.getQty() + orderItem.getQty());
                    productRepository.save(product);
                }
                orderItemRepository.deleteById(orderItem.getOrderItemId());
            });
            orderRepository.deleteById(id);
        } else {
            throw new NotFoundException("Order not found Id : " + id);
        }
        return true;
    }

    @Override
    public OrderModel find(int id) throws Exception {
        OrderModel orderModel = new OrderModel();
        List<OrderItemModel> orderItemModels = new ArrayList<>();
        Optional<Order> orderById = orderRepository.findById(id);
        if (orderById.isPresent()) {
            Order order = orderById.get();
            order.getOrderItem().forEach(item -> orderItemModels.add(OrderItemModel.builder()
                    .orderId(item.getOrder().getOrderId())
                    .orderItemId(item.getOrderItemId())
                    .productId(item.getProduct().getProductId())
                    .qty(item.getQty())
                    .subTotal(item.getSubTotal())

                    .build()));
            orderModel.setCustomerId(order.getCustomer().getId());
            orderModel.setOrderId(order.getOrderId());
            orderModel.setTotalAmount(order.getTotalAmount());
            orderModel.setOrderDate(order.getOrderDate());

            orderModel.setOrderItem(orderItemModels);
        } else {
            throw new NotFoundException("order not found id :" + id);
        }
        return orderModel;
    }

    @Override
    public List<OrderModel> getAll(int start, int end) throws Exception {
        List<OrderModel> orderModels = new ArrayList<>();
        List<Order> orders = orderRepository.getAll(start, end);
        if (!orders.isEmpty()) {
            OrderModel orderModel = new OrderModel();
            orders.forEach(order -> {
                List<OrderItemModel> orderItemModels = new ArrayList<>();
                orderModel.setCustomerId(order.getCustomer().getId());
                orderModel.setOrderId(order.getOrderId());
                orderModel.setOrderDate(order.getOrderDate());
                order.getOrderItem().forEach(oi -> {
                    OrderItemModel orderItemModel = new OrderItemModel();
                    orderItemModel.setOrderItemId(oi.getOrderItemId());
                    orderItemModel.setQty(oi.getQty());
                    orderItemModel.setProductId(oi.getProduct().getProductId());
                    orderItemModel.setOrderId(oi.getOrder().getOrderId());
                    orderItemModel.setSubTotal(oi.getSubTotal());
                    orderItemModels.add(orderItemModel);
                });
                orderModel.setOrderItem(orderItemModels);
                orderModels.add(orderModel);
            });
        } else {
            throw new NotFoundException("no order to display");
        }

        return orderModels;
    }
}
