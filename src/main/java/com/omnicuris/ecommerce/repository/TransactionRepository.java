package com.omnicuris.ecommerce.repository;

import com.omnicuris.ecommerce.model.order.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
