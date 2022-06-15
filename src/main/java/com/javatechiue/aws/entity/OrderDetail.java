package com.javatechiue.aws.entity;

import java.math.BigDecimal;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DynamoDBDocument
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @DynamoDBAttribute
  public int quantity;
  @DynamoDBAttribute
  public String product;
  @DynamoDBAttribute
  public BigDecimal price;
  @DynamoDBAttribute
  public BigDecimal subtotal;
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public String getProduct() {
    return product;
  }
  public void setProduct(String product) {
    this.product = product;
  }
  public BigDecimal getPrice() {
    return price;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  public BigDecimal getSubtotal() {
    return subtotal;
  }
  public void setSubtotal(BigDecimal subtotal) {
    this.subtotal = subtotal;
  }
  
  
  
}
