package com.omnicuris.ecommerce.model.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omnicuris.ecommerce.model.OrderItem.OrderItem;
import com.omnicuris.ecommerce.model.order.Order;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "item")
@EntityListeners(AuditingEntityListener.class)
public class Item {

  @Id
  @GeneratedValue
  private Long id;

  @Column(unique = true)
  @NotBlank
  private String name;

  private String description;
  
  @NotNull
  @NotBlank
  private Integer qty;
  
  @OneToMany(mappedBy="item")
  private Set<OrderItem> orderItem = new HashSet<OrderItem>();
  
  
  public Set<OrderItem> getOrders() {
	return orderItem;
}

public void setOrders(Set<OrderItem> orderItem) {
	this.orderItem = orderItem;
}

@NotBlank
  @NotNull
  private Double sell_price;
  
  @NotBlank
  @NotNull
  private Double buy_price;

  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date updatedAt;
  

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}


public Double getSell_price() {
	return sell_price;
}

public void setSell_price(Double sell_price) {
	this.sell_price = sell_price;
}

public Double getPurchase_price() {
	return buy_price;
}

public void setPurchase_price(Double purchase_price) {
	this.buy_price = purchase_price;
}


public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}

public Date getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}

public Integer getQty() {
	return qty;
}

public void setQty(Integer qty) {
	this.qty = qty;
}
  
  
}
