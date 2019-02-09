package com.omnicuris.ecommerce.model.order;

import javax.validation.constraints.NotNull;

public class OrderItemsRequest{
	@NotNull
	  private Long itemId;
	  @NotNull
	  private Integer qty;
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	  
}
