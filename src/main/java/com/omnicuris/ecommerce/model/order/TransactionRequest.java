package com.omnicuris.ecommerce.model.order;

import java.util.List;
import javax.validation.constraints.NotNull;


public class TransactionRequest {

  @NotNull
  private Long orderId;
  @NotNull
  private TransactionStatus status;
public Long getOrderId() {
	return orderId;
}
public void setOrderId(Long orderId) {
	this.orderId = orderId;
}
public TransactionStatus getStatus() {
	return status;
}
public void setStatus(TransactionStatus status) {
	this.status = status;
}
  
  

}
