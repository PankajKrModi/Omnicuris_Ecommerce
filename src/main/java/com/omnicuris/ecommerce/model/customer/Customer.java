package com.omnicuris.ecommerce.model.customer;

import com.omnicuris.ecommerce.model.order.Order;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
  private String emailId;

  private String fName;
  private String lName;
	private String contact;

  @OneToMany(targetEntity = Address.class,mappedBy="custId")
	private List<Address> addresses;

  @OneToMany(targetEntity = Order.class,mappedBy="custId")
	private List<Order> orders;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getEmailId() {
	return emailId;
}

public void setEmailId(String emailId) {
	this.emailId = emailId;
}

public String getfName() {
	return fName;
}

public void setfName(String fName) {
	this.fName = fName;
}

public String getlName() {
	return lName;
}

public void setlName(String lName) {
	this.lName = lName;
}

public String getContact() {
	return contact;
}

public void setContact(String contact) {
	this.contact = contact;
}

public List<Address> getAddresses() {
	return addresses;
}

public void setAddresses(List<Address> addresses) {
	this.addresses = addresses;
}

public List<Order> getOrders() {
	return orders;
}

public void setOrders(List<Order> orders) {
	this.orders = orders;
}
  
  
}
