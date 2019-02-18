package com.omnicuris.ecommerce.model.OrderItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.omnicuris.ecommerce.model.item.Item;
import com.omnicuris.ecommerce.model.order.Order;

@Entity
@Table(name = "ORDER_ITEM",
uniqueConstraints=
@UniqueConstraint(columnNames={"ORDER_ID", "ITEM_ID"}))

public class OrderItem {
	
	  @Id
	  @GeneratedValue
	  private Long id;
	
	  @NotNull
	  private Integer qty;
	  
	  @JsonIgnore
	  @ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "ORDER_ID") 
	  private Order order;
	  @JsonIgnore
	  @ManyToOne()
	    @JoinColumn(name = "ITEM_ID")
	  private Item item;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	  
	  

}
