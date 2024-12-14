package com.example.triet.Controller;

import com.example.triet.Model.Orders;
import com.example.triet.Repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    // GET: Returns a list of all Order
    @GetMapping
    public List<Orders> getAllOrder() {
        return orderRepository.findAll();
    }

    // POST: Add a new order
    @PostMapping
    public void createOrderProduct(@RequestBody Orders Orders) {
        orderRepository.save(Orders);
    }

    // GET: Returns the details of an order based on id
    @GetMapping("/{id}")
    public Orders getOrderProductById(@PathVariable Long id) {
        return orderRepository.findById(id).get();
    }

    // PUT: Update an other's informations by id
    @PutMapping("/{id}")
    public Orders updateOrderProductById(@PathVariable Long id, @RequestBody Orders Orders) {
        return orderRepository.findById(id)
                .map(ordersProduct -> {
                    if (ordersProduct.getProduct_list() != null) {
                        ordersProduct.setTotal_price(Orders.getTotal_price());
                    }
                    ordersProduct.setProduct_list(Orders.getProduct_list());
                    return orderRepository.save(ordersProduct);
        })
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id " + id));
    }

    // DELETE: Delete Order based on id
    @DeleteMapping("/{id}")
    public void deleteOrderProductById(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}