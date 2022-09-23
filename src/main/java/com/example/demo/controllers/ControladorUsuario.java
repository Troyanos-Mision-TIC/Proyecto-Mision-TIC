package com.example.demo.controllers;

import com.example.demo.Role;
import com.example.demo.model.Empleado;
import com.example.demo.model.Empresa;
import com.example.demo.services.EmpleadoService;
import com.example.demo.services.EmpresaService;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ControladorUsuario {
    private final EmpleadoService empleadoService;
    private final EmpresaService empresaService;

    public ControladorUsuario(EmpleadoService empleadoService, EmpresaService empresaService) {
        this.empleadoService = empleadoService;
        this.empresaService = empresaService;
    }
        
        @GetMapping("/users")
    public ResponseEntity<List<Empleado>> getEmpleados() {
        return new ResponseEntity<>(empleadoService.getEmpleados(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Empleado> postEmpleado(@RequestBody Map<String, Object> fields) {
        try {
            Empleado newEmployee = new Empleado();
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Empleado.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    if (key.equals("empresa")) {
                        Optional<Empresa> empresa = empresaService.findById((long)(int) value);
                        empresa.ifPresent(empresa1 -> ReflectionUtils.setField(field, newEmployee, empresa1));
                    } else if (key.equals("rol")) {
                        Role rol = Role.fromLabel((String) value);
                        ReflectionUtils.setField(field, newEmployee, rol);
                    } else {
                        ReflectionUtils.setField(field, newEmployee, value);
                    }
                }
            });
            return new ResponseEntity<>(empleadoService.saveEmpleado(newEmployee), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Empleado> getEmpleadoPorId(@PathVariable Long id) {
        Optional<Empleado> employeeContainer = empleadoService.getEmpleado(id);
        Empleado employee = employeeContainer.get();
        System.out.println(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<Empleado> patchEmpleado(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        try {
            Empleado employee = empleadoService.getEmpleado(id).get();
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Empleado.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    if (key.equals("empresa")) {
                        Optional<Empresa> empresa = empresaService.findById((long)(int)value);
                        if (empresa.isPresent())
                            ReflectionUtils.setField(field, employee, empresa.get());
                    } else if (key.equals("rol")) {
                        Role role = Role.fromLabel((String) value);
                        ReflectionUtils.setField(field, employee, role);
                    } else {
                        ReflectionUtils.setField(field, employee, value);
                    }
                }
            });
            return new ResponseEntity<>(empleadoService.saveEmpleado(employee), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Empleado> deleteEmpleado(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(empleadoService.removeEmpleado(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
