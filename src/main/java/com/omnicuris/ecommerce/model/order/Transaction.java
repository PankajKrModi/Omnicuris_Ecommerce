package com.omnicuris.ecommerce.model.order;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "transaction")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne(targetEntity = Order.class)
  private Order order;

  private String mode = "UPI";

  private TransactionStatus status;

  @Column(nullable = false, updatable = false)
  @CreatedDate
  @Temporal(TemporalType.DATE)
  private Date createdDate;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Order getOrder() {
	return order;
}

public void setOrder(Order order) {
	this.order = order;
}

public String getMode() {
	return mode;
}

public void setMode(String mode) {
	this.mode = mode;
}

public TransactionStatus getStatus() {
	return status;
}

public void setStatus(TransactionStatus status) {
	this.status = status;
}

public Date getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}
  
  
}
