package com.example.demo.controllers;

import com.example.demo.model.Empleado;
import com.example.demo.model.Empresa;
import com.example.demo.services.EmpleadoService;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class ControladorUsuario {
    private final EmpleadoService empleadoService;

    public ControladorUsuario(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<Empleado>> getEmpleados() {
        return new ResponseEntity<>(empleadoService.getEmpleados(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Empleado> postEmpleado(@RequestBody Empleado newEmployee) {
        return new ResponseEntity<>(empleadoService.addEmpleado(newEmployee), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Empleado> getEmpleadoPorId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(empleadoService.getEmpleado(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<Empleado> patchEmpleado(@PathVariable Integer id, @RequestBody Map<Object, Object> fields) {
        try {
            Empleado nuevoEmpleado = empleadoService.getEmpleado(id);
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Empleado.class, (String) key);
                if (field != null) {
                    field.setAccessible(true);
                    if (key.equals("empresa")) {
                        Empresa empresa = new Empresa("EnterCom", "Street 1-1", 151561561L, 156156L);
                        ReflectionUtils.setField(field, nuevoEmpleado, empresa);
                    } else {
                        ReflectionUtils.setField(field, nuevoEmpleado, value);
                    }
                }
            });
            return new ResponseEntity<>(empleadoService.saveEmpleado(id, nuevoEmpleado), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Empleado> deleteEmpleado(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(empleadoService.removeEmpleado(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
