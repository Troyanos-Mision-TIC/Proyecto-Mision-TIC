package com.example.demo.repo;

import com.example.demo.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface enterpriseRepository extends JpaRepository<Empresa, Integer>{

}
