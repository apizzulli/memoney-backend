package com.budgeter.server.Repositories;

import com.budgeter.server.Entities.Budget;
import com.budgeter.server.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
