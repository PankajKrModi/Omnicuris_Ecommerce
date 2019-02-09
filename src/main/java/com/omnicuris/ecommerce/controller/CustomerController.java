package com.omnicuris.ecommerce.controller;

import com.omnicuris.ecommerce.error.ServiceResponseException;
import com.omnicuris.ecommerce.model.customer.CustomerSignupRequest;
import com.omnicuris.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> signup(@RequestBody CustomerSignupRequest customerSignupRequest) {

    try {
      customerService.validateRequest(customerSignupRequest);
    } catch (ServiceResponseException e) {
      return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }

    customerService.addCustomer(customerSignupRequest);
    return ResponseEntity.ok().body("User created!");

  }
}
