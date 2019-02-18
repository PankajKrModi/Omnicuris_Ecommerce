package com.omnicuris.ecommerce.repository;

import com.omnicuris.ecommerce.model.item.Item;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	@Query(value = "SELECT * FROM ITEM WHERE name = ?1", nativeQuery = true)
	Item findByName(String name);
	@Query(value = "SELECT * FROM ITEM ", nativeQuery = true)
	List<Item> findAll();
}
