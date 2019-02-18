package com.omnicuris.ecommerce.model.order;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.omnicuris.ecommerce.model.OrderItem.OrderItem;
import com.omnicuris.ecommerce.model.customer.Address;
import com.omnicuris.ecommerce.model.customer.Customer;
import com.omnicuris.ecommerce.model.item.Item;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "t_order")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id")
public class Order {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(mappedBy="order",orphanRemoval=true)
  private Set<OrderItem> orderItem = new HashSet<OrderItem>();
  
  @JsonIgnore
  @OneToOne(targetEntity = Address.class)
  private Address addr;

  @JsonIgnore
  @ManyToOne(targetEntity = Customer.class)
  private Customer customer;

  @Enumerated(value = EnumType.STRING)
  private OrderStatus status;

  @OneToOne(targetEntity = Transaction.class)
  private Transaction transaction;

  private Integer itemQty;
  private Double orderTotal;


  @CreatedDate
  @Temporal(TemporalType.DATE)
  private Date orderedAt;

  
  public Set<OrderItem> getOrders() {
	return orderItem;
}

public void setOrders(Set<OrderItem> orderItem) {
	this.orderItem = orderItem;
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public Address getAddrId() {
	return addr;
}


public void setAddrId(Address addrId) {
	this.addr = addrId;
}


public Customer getCustId() {
	return customer;
}


public void setCustId(Customer custId) {
	this.customer = custId;
}


public OrderStatus getStatus() {
	return status;
}


public void setStatus(OrderStatus status) {
	this.status = status;
}


public Transaction getTransId() {
	return transaction;
}


public void setTransId(Transaction transId) {
	this.transaction = transId;
}


public Integer getItemQty() {
	return itemQty;
}


public void setItemQty(Integer itemQty) {
	this.itemQty = itemQty;
}


public Double getOrderTotal() {
	return orderTotal;
}


public void setOrderTotal(Double orderTotal) {
	this.orderTotal = orderTotal;
}


public Date getOrderedAt() {
	return orderedAt;
}


public void setOrderedAt(Date orderedAt) {
	this.orderedAt = orderedAt;
}
  
  
}
