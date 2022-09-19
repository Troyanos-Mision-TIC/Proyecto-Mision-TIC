package com.example.demo.services;

import com.example.demo.repo.EmployeeRepository;
import com.example.demo.model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmpleadoService() {
    }

    public List<Empleado> findEmpleados() {
        return employeeRepository.findAll();
    }

    public Optional<Empleado> findEmpleadoById(int id) throws IndexOutOfBoundsException {
        return employeeRepository.findById(id);
    }

    public Boolean deleteEmpleadoById(int id) throws IndexOutOfBoundsException {
        employeeRepository.deleteById(id);
        return true;
    }

    public Empleado saveEmpleado(Empleado empleado) throws IndexOutOfBoundsException {
        return employeeRepository.save(empleado);

    }

}
