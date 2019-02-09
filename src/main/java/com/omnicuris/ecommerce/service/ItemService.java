package com.omnicuris.ecommerce.service;

import com.omnicuris.ecommerce.error.ServiceResponseException;
import com.omnicuris.ecommerce.model.item.Item;
import com.omnicuris.ecommerce.model.item.UpdateItemRequest;
import com.omnicuris.ecommerce.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  @Autowired
  private ItemRepository itemRepository;

  public void validateRequest(Item item) throws ServiceResponseException {
	  
    
    if (item.getSell_price() == null || item.getSell_price() <= 0 )  {
      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST).message("Price is invalid"+item.getSell_price());
    }
    
    if(item.getPurchase_price() == null || item.getPurchase_price() > item.getSell_price()) {
    	throw ServiceResponseException.status(HttpStatus.BAD_REQUEST).message("Buy Price is invalid or Greater than Selling Price"+item.getPurchase_price());
    }
    //On adding new item check qty
    if(item.getQty()== null || item.getQty() <= 0) {
    	throw ServiceResponseException.status(HttpStatus.BAD_REQUEST).message("Invalid Quantity "+item.getQty());
    }
  }

  public void validateUpdateRequest(UpdateItemRequest updateItemRequest)
      throws ServiceResponseException {
    
    if (updateItemRequest.getSell_price() == null || updateItemRequest.getSell_price() <= 0)
    	throw ServiceResponseException.status(HttpStatus.BAD_REQUEST).message("Selling Price is invalid");
    if (updateItemRequest.getPurchase_price() == null || updateItemRequest.getPurchase_price() <updateItemRequest.getSell_price() ) {
      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST).message("Purchase Price is invalid or lesser than Selling Price");
    }
    //no check on qty as Admin can decrease or increase the item in Stock
  }

  public Item addItem(Item item) {
    return itemRepository.save(item);
  }

  public Item getItemById(Long id) throws ServiceResponseException {

    return itemRepository
        .findById(id)
        .orElseThrow(
            () ->
                ServiceResponseException.status(HttpStatus.BAD_REQUEST)
                    .message("Failed to find Item with id " + id));
  }

  public Item updateItem(Long id, UpdateItemRequest updateItemRequest)
      throws ServiceResponseException {
    Optional<Item> itemOptional = itemRepository.findById(id);
    if (!itemOptional.isPresent()) {
      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST).message("Invalid item " + id);
    }

    Item item = itemOptional.get();
    item.setName(updateItemRequest.getName());
    item.setDescription(updateItemRequest.getDescription());
    item.setSell_price(updateItemRequest.getSell_price());
    item.setPurchase_price(updateItemRequest.getPurchase_price());
    item.setQty(updateItemRequest.getQtyChange() + item.getQty()); //change in qty
    return itemRepository.save(item);
  }
  
  public void deleteItemById(Long id, UpdateItemRequest updateItemRequest)
	      throws ServiceResponseException {
	    Optional<Item> itemOptional = itemRepository.findById(id);
	    if (!itemOptional.isPresent()) {
	      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST).message("Invalid item " + id);
	    }

	    Item item = itemOptional.get();
	    itemRepository.delete(item);
	  }
  
  public List<Item> findAll() {
    return itemRepository.findAll();
  }
}
