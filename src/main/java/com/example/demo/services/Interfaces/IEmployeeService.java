package com.example.demo.services.Interfaces;

import com.example.demo.model.Empleado;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IEmployeeService {

    List<Empleado> findEmpleados();

    Optional<Empleado> findEmpleadoById(int id) throws IndexOutOfBoundsException;

    Boolean deleteEmpleadoById(int id) throws IndexOutOfBoundsException;

    Empleado saveEmpleado(Empleado empleado) throws IndexOutOfBoundsException;
}
