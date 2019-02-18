package com.omnicuris.ecommerce.model.item;

import javax.validation.constraints.NotNull;

public class UpdateItemRequest {

  @NotNull
  private String name;
  
  private String description;
  
  @NotNull
  private Double sell_price;
  
  @NotNull
  private Double buy_price;
  
  @NotNull
  private int qtyChange;
  
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
public Double getBuy_price() {
	return buy_price;
}
public void setBuy_price(Double buy_price) {
	this.buy_price = buy_price;
}
public int getQtyChange() {
	return qtyChange;
}
public void setQtyChange(int qtyChange) {
	this.qtyChange = qtyChange;
}


  
}
