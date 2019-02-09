package com.omnicuris.ecommerce.service;

import com.omnicuris.ecommerce.error.ServiceResponseException;
import com.omnicuris.ecommerce.model.OrderItem.OrderItem;
import com.omnicuris.ecommerce.model.item.Item;
import com.omnicuris.ecommerce.model.order.Order;
import com.omnicuris.ecommerce.model.order.OrderStatus;
import com.omnicuris.ecommerce.model.order.Transaction;
import com.omnicuris.ecommerce.model.order.TransactionRequest;
import com.omnicuris.ecommerce.model.order.TransactionStatus;
import com.omnicuris.ecommerce.repository.ItemRepository;
import com.omnicuris.ecommerce.repository.OrderRepository;
import com.omnicuris.ecommerce.repository.TransactionRepository;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private ItemRepository itemRepository;

  public void validateRequest(TransactionRequest transactionRequest)
      throws ServiceResponseException {

    if (transactionRequest.getOrderId()==null||!orderRepository.existsById(transactionRequest.getOrderId())) {
      throw ServiceResponseException.status(HttpStatus.BAD_REQUEST).message("Invalid order id");
    }

      Long orderId = transactionRequest.getOrderId();
      Order order = orderRepository.findById(orderId).get();
      if (!order.getStatus().equals(OrderStatus.ORDERED)) {
        throw ServiceResponseException.status(HttpStatus.BAD_REQUEST)
            .message("Transaction not allowed if status NOT ORDERED");
      }
      if (order.getTransId() != null
          && order.getTransId().getStatus().equals(TransactionStatus.SUCCESS)) {
        throw ServiceResponseException.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .message(
                "Order id " + orderId + " has a successful trnsaction with id " + order.getTransId()
                    .getId() + "!");
      }
    }


  public Transaction buyCartItems(TransactionRequest transactionRequest) {
	  
    Order order = orderRepository.findById(transactionRequest.getOrderId()).get();
    
    //Decreasing itemCount from Item table for items  as transaction completes
    updateItems(order);
    Transaction transaction = new Transaction();
    transaction.setOrder(order);
    transaction.setStatus(transactionRequest.getStatus());
    return transactionRepository.save(transaction);

  }
  
  public void updateItems(Order order){
	  
	 Set<OrderItem> orderItems = order.getOrders();
	 for(OrderItem orderItem: orderItems) {
		orderItem.getItem().setQty(orderItem.getItem().getQty()-orderItem.getQty());
	 }
  }
}
