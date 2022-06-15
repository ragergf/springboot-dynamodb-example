package com.javatechiue.aws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.javatechiue.aws.entity.Order;
import com.javatechiue.aws.repository.OrderRepository;

@RestController
public class OrderController {
  
  @Autowired
  private OrderRepository repository;
  
  @PostMapping("/saveOrder")
  public Order savePOrder(@RequestBody Order person) {
    return repository.addOrder(person);
  }

  @GetMapping("/getOrder/{personId}")
  public Order findOrder(@PathVariable String orderId) {
    return repository.findPersonByOrderId(orderId);
  }

  @GetMapping("/getOrders")
  public PaginatedScanList<Order> findOrder() {
    return repository.findOrders();
  }

  @DeleteMapping("/deleteOrder")
  public String deleteOrder(@RequestBody Order order) {
    return repository.deleteOrder(order);
  }

  @PutMapping("/editOrder")
  public String updateOrder(@RequestBody Order order) {
    return repository.editOrder(order);
  }

}
