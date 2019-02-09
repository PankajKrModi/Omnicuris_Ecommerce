package com.omnicuris.ecommerce.model.item;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateItemRequest {

  @NotNull
  @NotBlank
  private String name;
  
  private String description;
  @NotNull
  @NotBlank
  private Double sell_price;
  @NotNull
  @NotBlank
  private Double purchase_price;
  
  @NotNull
  @NotBlank
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
public Double getPurchase_price() {
	return purchase_price;
}
public void setPurchase_price(Double purchase_price) {
	this.purchase_price = purchase_price;
}
public int getQtyChange() {
	return qtyChange;
}
public void setQtyChange(int qtyChange) {
	this.qtyChange = qtyChange;
}


  
}
