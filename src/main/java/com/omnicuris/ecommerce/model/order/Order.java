package com.omnicuris.ecommerce.model.order;

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
@Table(name = "order")
public class Order {

  @Id
  @GeneratedValue
  private Long id;

  @OneToMany(mappedBy="order")
  private Set<OrderItem> orderItem = new HashSet<OrderItem>();
  
  
  public Set<OrderItem> getOrders() {
	return orderItem;
}

public void setOrders(Set<OrderItem> orderItem) {
	this.orderItem = orderItem;
}

  @OneToOne(targetEntity = Address.class)
  private Address addrId;

  @ManyToOne(targetEntity = Customer.class)
  private Customer custId;

  @Enumerated(value = EnumType.STRING)
  private OrderStatus status;

  @OneToOne(targetEntity = Transaction.class)
  private Transaction transId;

  private Integer itemQty;
  private Double orderTotal;


  @Column(nullable = false, updatable = false)
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date orderedAt;


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public Address getAddrId() {
	return addrId;
}


public void setAddrId(Address addrId) {
	this.addrId = addrId;
}


public Customer getCustId() {
	return custId;
}


public void setCustId(Customer custId) {
	this.custId = custId;
}


public OrderStatus getStatus() {
	return status;
}


public void setStatus(OrderStatus status) {
	this.status = status;
}


public Transaction getTransId() {
	return transId;
}


public void setTransId(Transaction transId) {
	this.transId = transId;
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
