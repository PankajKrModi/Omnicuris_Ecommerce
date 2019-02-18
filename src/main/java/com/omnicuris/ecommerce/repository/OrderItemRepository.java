package com.omnicuris.ecommerce.repository;

import java.util.List;
import java.util.Optional;

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
	@Query(value = "SELECT * FROM ORDER_ITEM WHERE ITEM_ID = ?1 AND ORDER_ID= ?2", nativeQuery = true)
	   Optional<OrderItem> findByOrder_Item(Long item_Id,Long order_id);
}
