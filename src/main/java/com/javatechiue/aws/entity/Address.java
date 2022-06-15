package com.javatechiue.aws.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@DynamoDBDocument
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @DynamoDBAttribute
  private String city;
  @DynamoDBAttribute
  private String state;
  @DynamoDBAttribute
  private long pinCode;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public long getPinCode() {
    return pinCode;
  }

  public void setPinCode(long pinCode) {
    this.pinCode = pinCode;
  }


}
