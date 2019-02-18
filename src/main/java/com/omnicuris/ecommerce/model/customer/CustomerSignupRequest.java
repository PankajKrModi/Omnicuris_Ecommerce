package com.omnicuris.ecommerce.model.customer;

import java.util.List;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;



public class CustomerSignupRequest {

  @NotNull
  private String fName;
  @Nullable 
  private String lName;
  @NotNull
  private String emailId;
  @NotNull
  private String contact;
  private List<String> address;
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
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getContact() {
	return contact;
}
public void setContact(String contactNo) {
	this.contact = contactNo;
}
public List<String> getAddress() {
	return address;
}
public void setAddress(List<String> address) {
	this.address = address;
}
  
  

}
