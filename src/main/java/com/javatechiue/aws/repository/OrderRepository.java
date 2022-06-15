package com.javatechiue.aws.repository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.javatechiue.aws.entity.Order;

@Repository
public class OrderRepository {
  @Autowired
  private DynamoDBMapper mapper;


  public Order addOrder(Order order) {
    if (order.getOrderNo() == null) {
      SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.HOUR_OF_DAY, 0);
      calendar.set(Calendar.MINUTE, 0);
      calendar.set(Calendar.SECOND, 0);

      Date dateObj = calendar.getTime();
      Date current = new Date();
      order.setOrderNo(String.valueOf(current.getTime() - dateObj.getTime()));
    }

    if (order.getOrderDetail() != null) {
      order.getOrderDetail().stream().forEach((o) -> {
        if(o.getPrice() == null)
          o.setPrice(BigDecimal.ZERO);
        if(o.getQuantity() < 0)
          o.setQuantity(0);
        o.setSubtotal(o.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
      });

      BigDecimal total = order.getOrderDetail().stream().map(x -> x.getSubtotal())
          .reduce(BigDecimal.ZERO, BigDecimal::add);
      order.setTotal(total);
    }

    mapper.save(order);
    return order;
  }

  public Order findPersonByOrderId(String orderId) {
    return mapper.load(Order.class, orderId);
  }

  public PaginatedScanList<Order> findOrders() {
    return mapper.scan(Order.class, new DynamoDBScanExpression());
  }

  public String deleteOrder(Order order) {
    mapper.delete(order);
    return "person removed !!";
  }

  public String editOrder(Order order) {
    
    System.out.println("edit..");
    
    if (order.getOrderDetail() != null) {
      order.getOrderDetail().stream().forEach((o) -> {
        if(o.getPrice() == null)
          o.setPrice(BigDecimal.ZERO);
        if(o.getQuantity() < 0)
          o.setQuantity(0);
        o.setSubtotal(o.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));
      });

      BigDecimal total = order.getOrderDetail().stream().map(x -> x.getSubtotal())
          .reduce(BigDecimal.ZERO, BigDecimal::add);
      order.setTotal(total);
    }
    mapper.save(order, buildExpression(order));
    return "record updated ...";
  }

  private DynamoDBSaveExpression buildExpression(Order order) {
    DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
    Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
    expectedMap.put("orderId",
        new ExpectedAttributeValue(new AttributeValue().withS(order.getOrderId())));
    dynamoDBSaveExpression.setExpected(expectedMap);
    return dynamoDBSaveExpression;
  }
}
