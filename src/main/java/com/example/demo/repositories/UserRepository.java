package com.example.demo.repositories;

import com.example.demo.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Empleado, Long> {
}
