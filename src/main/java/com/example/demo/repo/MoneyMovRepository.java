package com.example.demo.repo;

import com.example.demo.model.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyMovRepository extends JpaRepository<MovimientoDinero, Integer> {

}
