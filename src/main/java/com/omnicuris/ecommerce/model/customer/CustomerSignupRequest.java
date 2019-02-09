package com.omnicuris.ecommerce.model.customer;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;



public class CustomerSignupRequest {

  @NotNull
  @NotBlank
  private String fName;
  @Nullable 
  private String lName;
  @NotNull
  @NotBlank
  private String emailId;
  @NotNull
  @NotBlank
  private String contactNo;
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
public String getContactNo() {
	return contactNo;
}
public void setContactNo(String contactNo) {
	this.contactNo = contactNo;
}
public List<String> getAddress() {
	return address;
}
public void setAddress(List<String> address) {
	this.address = address;
}
  
  

}
