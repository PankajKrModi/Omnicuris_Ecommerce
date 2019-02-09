package com.omnicuris.ecommerce.model.customer;

import com.omnicuris.ecommerce.model.order.Order;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "address")
public class Address {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(targetEntity = Customer.class)
  private Customer custId;

  @NotNull
  @NotBlank
  private String address;
  
  @OneToOne(targetEntity = Order.class,mappedBy="addrId")
  private Order orderId;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Customer getCustId() {
	return custId;
}

public void setCustId(Customer custId) {
	this.custId = custId;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}


public Order getOrderId() {
	return orderId;
}

public void setOrderId(Order orderId) {
	this.orderId = orderId;
}
  
  
}
