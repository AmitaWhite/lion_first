package com.paprika.domain.transaction.repository;

import com.paprika.domain.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 담당: D - 이동준
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByBuyerIdOrderByCreatedAtDesc(Long buyerId);

    List<Transaction> findBySellerIdOrderByCreatedAtDesc(Long sellerId);
}
