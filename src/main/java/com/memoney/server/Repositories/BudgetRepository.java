package com.memoney.server.Repositories;

import com.memoney.server.Entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    @Override
    public Optional<Budget> findById(Long id);
}
