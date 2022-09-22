package com.example.demo.services.Implements;

import com.example.demo.model.Empleado;
import com.example.demo.repo.IEmployeeRepository;
import com.example.demo.services.Interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService implements IEmployeeService {

    @Autowired
    IEmployeeRepository IEmployeeRepository;

    public EmpleadoService() {
    }

    public List<Empleado> findEmpleados() {
        return IEmployeeRepository.findAll();
    }

    public Optional<Empleado> findEmpleadoById(int id) throws IndexOutOfBoundsException {
        return IEmployeeRepository.findById(id);
    }

    public Boolean deleteEmpleadoById(int id) throws IndexOutOfBoundsException {
        IEmployeeRepository.deleteById(id);
        return true;
    }

    public Empleado saveEmpleado(Empleado empleado) throws IndexOutOfBoundsException {
        return IEmployeeRepository.save(empleado);
    }

}
