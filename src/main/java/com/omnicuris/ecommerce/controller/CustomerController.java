package com.omnicuris.ecommerce.controller;

import com.omnicuris.ecommerce.error.ServiceResponseException;
import com.omnicuris.ecommerce.model.customer.CustomerSignupRequest;
import com.omnicuris.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity findAll(){
	  return ResponseEntity.ok(customerService.findAll());
  }
  
  @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> signup(@RequestBody CustomerSignupRequest customerSignupRequest) {

    try {
      customerService.validateRequest(customerSignupRequest);
    } catch (ServiceResponseException e) {
      return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
    
    // duplicate email exception
    try {
    customerService.addCustomer(customerSignupRequest);
    }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate email");
    }
    return ResponseEntity.ok().body("User created!");

  }
  
  
}
