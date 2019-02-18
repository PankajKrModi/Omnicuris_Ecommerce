package com.omnicuris.ecommerce.model.customer;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.omnicuris.ecommerce.model.order.Order;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "customer")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "id")
public class Customer {

	@Id
	@GeneratedValue
	private Long id;

	
  @Column(name="email",unique = true,nullable=false,length=32)
  private String emailId;
  
  @NotNull	
  private String fName;
  
  private String lName;
  
  @NotNull
  private String contact;

  @JsonManagedReference
  @OneToMany(targetEntity = Address.class,fetch = FetchType.LAZY,mappedBy="customer",orphanRemoval=true)
	private List<Address> addresses;

//  @JsonIgnore
//  @OneToMany(targetEntity = Order.class,fetch = FetchType.LAZY,mappedBy="customer")
//	private List<Order> orders;

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

//public List<Order> getOrders() {
//	return orders;
//}
//
//public void setOrders(List<Order> orders) {
//	this.orders = orders;
//}
  
  
}
