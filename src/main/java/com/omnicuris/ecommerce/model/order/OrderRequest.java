package com.omnicuris.ecommerce.model.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;


public class OrderRequest {
  
  @NotNull
  private Set<OrderItemsRequest> orderItems = new HashSet<OrderItemsRequest>(0); 
  
  @NotNull
  private Long customerId;
  @NotNull
  private Long addressId;
  @NotNull
  private OrderStatus status;
  
public Set<OrderItemsRequest> getOrderItems() {
	return orderItems;
}
public void setOrderItems(Set<OrderItemsRequest> orderItems) {
	this.orderItems = orderItems;
}
public Long getCustomerId() {
	return customerId;
}
public void setCustomerId(Long customerId) {
	this.customerId = customerId;
}
public Long getAddressId() {
	return addressId;
}
public void setAddressId(Long addressId) {
	this.addressId = addressId;
}
public OrderStatus getStatus() {
	return status;
}
public void setStatus(OrderStatus status) {
	this.status = status;
}
  
  
}


