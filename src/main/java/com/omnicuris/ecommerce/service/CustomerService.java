package com.omnicuris.ecommerce.service;

import com.omnicuris.ecommerce.error.ServiceResponseException;
import com.omnicuris.ecommerce.model.customer.Address;
import com.omnicuris.ecommerce.model.customer.Customer;
import com.omnicuris.ecommerce.model.customer.CustomerSignupRequest;
import com.omnicuris.ecommerce.repository.AddressRepository;
import com.omnicuris.ecommerce.repository.CustomerRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private AddressRepository addressRepository;

  public void validateRequest(CustomerSignupRequest customerSignupRequest)
      throws ServiceResponseException {

    if (!ValidationUtil.validateEmail(customerSignupRequest.getEmailId())) {
      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST)
          .message("Invalid email " + customerSignupRequest.getEmailId());
    }

    if (!ValidationUtil.validateContact(customerSignupRequest.getContact())) {
      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST)
          .message("Invalid contact number " + customerSignupRequest.getContact());
    }
  }

  public void addCustomer(CustomerSignupRequest customerSignupRequest) throws Exception{
	  
    Customer customer = new Customer();
    customer.setfName(customerSignupRequest.getfName());
    customer.setlName(customerSignupRequest.getlName());
    customer.setContact(customerSignupRequest.getContact());
    customer.setEmailId(customerSignupRequest.getEmailId());
    
    Customer savedCustomer = customerRepository.save(customer);

    customerSignupRequest.getAddress().forEach(
            addr -> {
              Address address = new Address();
              address.setAddress(addr);
              address.setCustId(savedCustomer);
              // save address
              addressRepository.save(address);
            });
  }
  
  public List<Customer> findAll(){
	  return customerRepository.findAll();
  }
}
