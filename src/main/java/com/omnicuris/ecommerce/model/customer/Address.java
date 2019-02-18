package com.omnicuris.ecommerce.model.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id")
public class Address {

  @Id
  @GeneratedValue
  private Long id;

  
  @ManyToOne(targetEntity = Customer.class)
  private Customer customer;

  @NotNull
  private String address;
  
//  @OneToOne(targetEntity = Order.class,mappedBy="addr")
//  private Order order;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Customer getCustId() {
	return customer;
}

public void setCustId(Customer custId) {
	this.customer = custId;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}


//public Order getOrderId() {
//	return order;
//}
//
//public void setOrderId(Order orderId) {
//	this.order = orderId;
//}
  
  
}
