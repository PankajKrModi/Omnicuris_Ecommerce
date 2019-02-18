package com.omnicuris.ecommerce.controller;

import com.omnicuris.ecommerce.error.ServiceResponseException;
import com.omnicuris.ecommerce.model.item.Item;
import com.omnicuris.ecommerce.model.item.UpdateItemRequest;
import com.omnicuris.ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

  @Autowired
  private ItemService itemService;

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addItem(@RequestBody Item item) {
    // validate the request
    try {
      itemService.validateRequest(item);
    } catch (ServiceResponseException e) {
      return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
    return ResponseEntity.ok(itemService.addItem(item));
  }

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @GetMapping(value = "/detail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getItemById(@PathVariable("id") Long id) {
    try {
      return ResponseEntity.ok(itemService.getItemById(id));
    } catch (ServiceResponseException e) {
      return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
  }

  @PreAuthorize("hasRole('ADMIN')")
  @RequestMapping(value = "/delete/{name}",method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity deleteItemByName(@PathVariable("name") String product_name) {
    try {
      itemService.deleteItemByName(product_name);
      return ResponseEntity.ok().body("Product:"+ product_name +"deletion successful");
    } catch (ServiceResponseException e) {
      return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
  }
  
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity updateItemById(@PathVariable("id") Long id,
      @RequestBody UpdateItemRequest updateItemRequest) {
    try {
      itemService.validateUpdateRequest(updateItemRequest);
      return ResponseEntity.ok(itemService.updateItem(id, updateItemRequest));
    } catch (ServiceResponseException e) {
      return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
  }

  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity findAll() {
    return ResponseEntity.ok(itemService.findAll());
  }

}
