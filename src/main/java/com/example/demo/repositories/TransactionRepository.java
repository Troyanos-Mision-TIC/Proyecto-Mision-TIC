package com.example.demo.repositories;

import com.example.demo.model.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<MovimientoDinero, Long> {
}
