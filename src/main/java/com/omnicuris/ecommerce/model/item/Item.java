package com.omnicuris.ecommerce.model.item;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "item")
@EntityListeners(AuditingEntityListener.class)
public class Item {

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE)
  private Long id;

  @Column(unique = true)
  private String name;

  private String description;
  
  @NotNull
  private Integer qty;
  
//  @JsonIgnore
//  @OneToMany(mappedBy="item",fetch=FetchType.LAZY)
//  private Set<OrderItem> orderItem = new HashSet<OrderItem>();

  @NotNull
  private Double sell_price;
  
  @NotNull
  private Double buy_price;

  @Temporal(TemporalType.DATE)
  @CreatedDate
  private Date createdAt;

  @Temporal(TemporalType.DATE)
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


//
//public Set<OrderItem> getOrders() {
//	return orderItem;
//}
//
//public void setOrders(Set<OrderItem> orderItem) {
//	this.orderItem = orderItem;
//}

public Double getSell_price() {
	return sell_price;
}

public void setSell_price(Double sell_price) {
	this.sell_price = sell_price;
}

public Double getBuy_price() {
	return buy_price;
}

public void setBuy_price(Double buy_price) {
	this.buy_price = buy_price;
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
