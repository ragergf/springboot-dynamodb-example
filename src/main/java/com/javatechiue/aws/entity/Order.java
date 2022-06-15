package com.javatechiue.aws.entity;

import java.math.BigDecimal;
import java.util.List;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "order")
public class Order {
  /**
  * 
  */
  private static final long serialVersionUID = 1L;
  @DynamoDBHashKey(attributeName = "orderId")
  @DynamoDBAutoGeneratedKey
  public String orderId;
  @DynamoDBAttribute
  public String orderNo;
  @DynamoDBAttribute
  public String orderLocation;
  @DynamoDBAttribute
  public List<OrderDetail> orderDetail;
  @DynamoDBAttribute
  public BigDecimal total;
  
  public String getOrderId() {
    return orderId;
  }
  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }
  public String getOrderNo() {
    return orderNo;
  }
  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }
  public String getOrderLocation() {
    return orderLocation;
  }
  public void setOrderLocation(String orderLocation) {
    this.orderLocation = orderLocation;
  }
  public List<OrderDetail> getOrderDetail() {
    return orderDetail;
  }
  public void setOrderDetail(List<OrderDetail> orderDetail) {
    this.orderDetail = orderDetail;
  }
  public BigDecimal getTotal() {
    return total;
  }
  public void setTotal(BigDecimal total) {
    this.total = total;
  }
  
  
}