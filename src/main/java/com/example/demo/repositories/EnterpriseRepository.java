package com.example.demo.repositories;

import com.example.demo.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository<Empresa, Long> {
}
