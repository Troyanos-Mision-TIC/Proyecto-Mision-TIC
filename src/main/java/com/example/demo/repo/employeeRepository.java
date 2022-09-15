package com.example.demo.repo;

import com.example.demo.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface employeeRepository extends JpaRepository<Empleado, Integer>{

}
