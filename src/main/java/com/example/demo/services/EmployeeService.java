package com.example.demo.services;

import com.example.demo.model.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository userRepository;

    public EmployeeService(EmployeeRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Employee> findAll() {
        return userRepository.findAll();
    }

    public Optional<Employee> findById(long id) {
        return userRepository.findById(id);
    }

    public Employee findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<Employee> deleteById(long id) {
        Optional<Employee> deletedUser = userRepository.findById(id);
        userRepository.deleteById(id);
        return deletedUser;
    }

    public Employee save(Employee employee) {
        return userRepository.save(employee);
    }

}
