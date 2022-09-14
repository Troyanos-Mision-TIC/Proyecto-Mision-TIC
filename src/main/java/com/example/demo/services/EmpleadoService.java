package com.example.demo.services;

import com.example.demo.model.Empleado;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    private final UserRepository userRepository;

    public EmpleadoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Empleado> getEmpleados() {
        return userRepository.findAll();
    }

    public Optional<Empleado> getEmpleado(long id) {
        return userRepository.findById(id);
    }

    public Optional<Empleado> removeEmpleado(long id) {
        Optional<Empleado> deletedUser = userRepository.findById(id);
        userRepository.deleteById(id);
        return deletedUser;
    }

    public Empleado saveEmpleado(Empleado empleado) {
        return userRepository.save(empleado);
    }

}
