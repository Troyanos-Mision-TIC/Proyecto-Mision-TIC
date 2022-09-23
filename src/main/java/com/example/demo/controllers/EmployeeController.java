package com.example.demo.controllers;

import com.example.demo.Role;
import com.example.demo.model.Employee;
import com.example.demo.model.Enterprise;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.EnterpriseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EnterpriseService enterpriseService;

    public EmployeeController(EmployeeService employeeService, EnterpriseService enterpriseService) {
        this.employeeService = employeeService;
        this.enterpriseService = enterpriseService;
    }
        
    @GetMapping("/users")
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Employee> postEmployee(@RequestBody Map<String, Object> fields) {
        try {
            Employee newEmployee = new Employee();
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Employee.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    if (key.equals("enterprise")) {
                        Optional<Enterprise> enterprise = enterpriseService.findById((long)(int) value);
                        enterprise.ifPresent(enterprise1 -> ReflectionUtils.setField(field, newEmployee, enterprise1));
                    } else if (key.equals("role")) {
                        Role role = Role.fromLabel((String) value);
                        ReflectionUtils.setField(field, newEmployee, role);
                    } else {
                        ReflectionUtils.setField(field, newEmployee, value);
                    }
                }
            });
            return new ResponseEntity<>(employeeService.save(newEmployee), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Employee> getOne(@PathVariable long id) {
        Optional<Employee> employeeContainer = employeeService.findById(id);
        Employee employee = employeeContainer.get();
        System.out.println(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<Employee> patchEmployee(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        try {
            Employee employee = employeeService.findById(id).get();
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Employee.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    if (key.equals("enterprise")) {
                        Optional<Enterprise> enterprise = enterpriseService.findById((long)(int)value);
                        if (enterprise.isPresent())
                            ReflectionUtils.setField(field, employee, enterprise.get());
                    } else if (key.equals("role")) {
                        Role role = Role.fromLabel((String) value);
                        ReflectionUtils.setField(field, employee, role);
                    } else {
                        ReflectionUtils.setField(field, employee, value);
                    }
                }
            });
            return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable long id) {
        try {
            return new ResponseEntity<>(employeeService.deleteById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
