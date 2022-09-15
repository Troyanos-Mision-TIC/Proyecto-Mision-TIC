package com.example.demo.repo;

import com.example.demo.model.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface moneyMovRepository extends JpaRepository<MovimientoDinero, Integer> {

}
