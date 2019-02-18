package com.omnicuris.ecommerce.service;

import com.omnicuris.ecommerce.error.ServiceResponseException;
import com.omnicuris.ecommerce.model.OrderItem.OrderItem;
import com.omnicuris.ecommerce.model.customer.Address;
import com.omnicuris.ecommerce.model.customer.Customer;
import com.omnicuris.ecommerce.model.item.Item;
import com.omnicuris.ecommerce.model.order.Order;
import com.omnicuris.ecommerce.model.order.OrderItems;
import com.omnicuris.ecommerce.model.order.OrderItemsRequest;
import com.omnicuris.ecommerce.model.order.OrderRequest;
import com.omnicuris.ecommerce.model.order.OrderStatus;
import com.omnicuris.ecommerce.model.order.Transaction;
import com.omnicuris.ecommerce.repository.AddressRepository;
import com.omnicuris.ecommerce.repository.CustomerRepository;
import com.omnicuris.ecommerce.repository.ItemRepository;
import com.omnicuris.ecommerce.repository.OrderItemRepository;
import com.omnicuris.ecommerce.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private TransactionService transactionRepository;
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private ItemRepository itemRepository;
  @Autowired
  private OrderItemRepository orderItemRepository;

  private void validateEachOrder(OrderItemsRequest orderItem) throws ServiceResponseException {

	    if (!itemRepository.existsById(orderItem.getItemId())) {
	      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST)
	          .message("Invalid item id " + orderItem.getItemId());
	    }
	    
	    Item item = itemRepository.findById(orderItem.getItemId()).get();
	    Integer inStockItems = item.getQty();

	    if (orderItem.getQty() <= 0 || orderItem.getQty() > inStockItems) {
	      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST)
	          .message(
	              "Invalid quantity for item id" + item.getId());
	    }
	  }


  public void validateRequest(OrderRequest orderRequest) throws ServiceResponseException {

    for (OrderItemsRequest orderItem : orderRequest.getOrderItems()) {
      validateEachOrder(orderItem);
    }
    
    if (!customerRepository.existsById(orderRequest.getCustomerId())) {
	      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST)
	          .message("Invalid customer id " + orderRequest.getCustomerId());
	    }
	    if (!addressRepository.existsById(orderRequest.getAddressId())) {
	      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST)
	          .message("Invalid address id " + orderRequest.getAddressId());
	    }
	   
	    if (addressRepository
	        .findById(orderRequest.getAddressId())
	        .get()
	        .getCustId().getId()
	        .equals(orderRequest.getCustomerId())==false) {
	      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST)
	          .message(
	              "Invalid address "
	                  + orderRequest.getAddressId()
	                  + " for customer "
	                  + orderRequest.getCustomerId());
	    }

      if (orderRequest.getStatus() != OrderStatus.ORDERED) {
        throw ServiceResponseException.status(HttpStatus.BAD_REQUEST)
            .message("First time order can be in ORDERED status only!");
      
    }
  }

  public Order addToCart(OrderRequest orderRequest) {
	 
    Order orders = new Order();
    int totalqty =0;
    double orderPrice =0;
    List<OrderItem> listitemOrdered = new ArrayList<OrderItem>();
    for(OrderItemsRequest orderItemReq: orderRequest.getOrderItems()) {
    	Item item = itemRepository.findById(orderItemReq.getItemId()).get();
    	
    	OrderItem o_item = new OrderItem();
    	o_item.setItem(item);
    	o_item.setQty(orderItemReq.getQty());
    	//orders.getOrders().add(o_item);
    	totalqty += orderItemReq.getQty().intValue();
    	orderPrice += item.getSell_price()*orderItemReq.getQty().intValue();
    	listitemOrdered.add(o_item);
    }
    orders.setItemQty(totalqty);
    orders.setOrderTotal(orderPrice);
    orders.setAddrId(addressRepository
	        .findById(orderRequest.getAddressId()).get());
    orders.setCustId(customerRepository.findById(orderRequest.getCustomerId()).get());
    orders.setStatus(orderRequest.getStatus());
    Order savedOrder = orderRepository.save(orders);
    listitemOrdered.forEach(order_item->{
    	order_item.setOrder(savedOrder);
    });
    orderItemRepository.saveAll(listitemOrdered);
    return savedOrder;
  }

  
  public Order validateAndSaveOrderEditRequest(Long id, OrderRequest orderRequest)
      throws ServiceResponseException {

    if (!orderRepository.existsById(id)) {
      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST)
          .message("Invalid order id " + id);
    }
    
    Transaction transaction = orderRepository.findById(id).get().getTransId();
    if (transaction != null) {
      throw ServiceResponseException.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .message("Cannot modify cart since transaction was recorded against this order!");
    }
    
    if(orderRequest.getStatus().equals(OrderStatus.CANCELLED)) {
    	Order order = orderRepository.findById(id).get();
    	orderRepository.delete(order);
    	return order;
    }
    
    //validateEachOrder(orderRequest);
    for (OrderItemsRequest orderItem : orderRequest.getOrderItems()) {
        validateEachOrder(orderItem);
      }

    Order orders = orderRepository.findById(id).get();
    //setting values to 0 
    orders.setItemQty(0);
    orders.setOrderTotal(0.0);
    int totalqty =0;
    double orderPrice =0;
   
    for (OrderItemsRequest orderRequestItem : orderRequest.getOrderItems()) {
    	
    	Optional<OrderItem> order_item = orderItemRepository.findByOrder_Item(orderRequestItem.getItemId(), id);
    	if(order_item.isPresent()) {
    		order_item.get().setQty(orderRequestItem.getQty());
    		totalqty += orderRequestItem.getQty();
    		orderPrice += itemRepository.findById(orderRequestItem.getItemId()).get().getSell_price()*orderRequestItem.getQty();
    		orderItemRepository.save(order_item.get());
    	}else {
    		Item item = itemRepository.findById(orderRequestItem.getItemId()).get();
    		
    			OrderItem o_item = new OrderItem();
    			o_item.setItem(item);
    			o_item.setQty(orderRequestItem.getQty());
    			o_item.setOrder(orders);
    			orderItemRepository.save(o_item);
    			totalqty += orderRequestItem.getQty();
        		orderPrice += item.getSell_price()*orderRequestItem.getQty();	
    		}   	
    	}
    orders.setAddrId(addressRepository
	        .findById(orderRequest.getAddressId()).get());
    orders.setItemQty(totalqty);
    orders.setOrderTotal(orderPrice);
    return orderRepository.save(orders);	
    }


  public Order findById(Long id) throws ServiceResponseException {
    return orderRepository
        .findById(id)
        .orElseThrow(
            () ->
                ServiceResponseException.status(HttpStatus.NOT_FOUND)
                    .message("No order found with id " + id));
  }
}
