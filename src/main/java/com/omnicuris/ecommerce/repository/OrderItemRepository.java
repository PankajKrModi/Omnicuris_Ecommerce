package com.omnicuris.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.omnicuris.ecommerce.model.OrderItem.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{
	@Query(value = "SELECT * FROM ORDER_ITEM WHERE ORDER_ID = ?1", nativeQuery = true)
	   List<OrderItem> findByOrderId(Long order_Id);
	@Query(value = "SELECT * FROM ORDER_ITEM WHERE ITEM_ID = ?1", nativeQuery = true)
	   List<OrderItem> findByItemId(Long item_Id);
}
