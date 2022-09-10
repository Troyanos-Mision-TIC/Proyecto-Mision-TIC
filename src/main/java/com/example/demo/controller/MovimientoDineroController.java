package com.example.demo.controller;

import com.example.demo.model.Empleado;
import com.example.demo.model.Empresa;
import com.example.demo.model.MovimientoDinero;
import com.example.demo.service.EmpresaService;
import com.example.demo.services.EmpleadoService;
import com.example.demo.services.MovimientoDineroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class MovimientoDineroController {
    private final MovimientoDineroService movimientoDineroService;
    private final EmpleadoService empleadoService;
    private final EmpresaService empresaService;

    public MovimientoDineroController(MovimientoDineroService movimientoDineroService, EmpleadoService empleadoService, EmpresaService empresaService) {
        this.movimientoDineroService = movimientoDineroService;
        this.empleadoService = empleadoService;
        this.empresaService = empresaService;
    }
    @GetMapping("/movements")
    public ResponseEntity<List<MovimientoDinero>> getAllMovimientoDinero(){
        return new ResponseEntity<>(movimientoDineroService.consultarTodosMovimientos(), HttpStatus.OK);
    }

    @GetMapping("/movements/{id}")
    public ResponseEntity<MovimientoDinero> getMovimientoDineroById(@PathVariable("id") int id){
        try {
            return new ResponseEntity<>(movimientoDineroService.consultarMovimiento(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/movements")
    public ResponseEntity<MovimientoDinero> postMovimientoDinero(@RequestBody Map<String, Object> fields){
        try {
            MovimientoDinero newMovement = new MovimientoDinero();
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(MovimientoDinero.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    if (key.equals("usuarioEncargado")) {
                        Empleado user = empleadoService.getEmpleado((int) value);
                        ReflectionUtils.setField(field, newMovement, user);
                    } else {
                        ReflectionUtils.setField(field, newMovement, value);
                    }
                }
            });
            return new ResponseEntity<>(movimientoDineroService.crearMovimiento(newMovement), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/movements/{id}")
    public ResponseEntity<MovimientoDinero> deleteMovimiento(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(movimientoDineroService.eliminarMovimiento(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/movements/{id}")
    public ResponseEntity<MovimientoDinero> patchMovimiento(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        try {
            MovimientoDinero modifiedMovimiento = movimientoDineroService.consultarMovimiento(id);
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(MovimientoDinero.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    if (key.equals("usuarioEncargado")) {
                        Empleado user = empleadoService.getEmpleado((int) value);
                        ReflectionUtils.setField(field, modifiedMovimiento, user);
                    } else {
                        ReflectionUtils.setField(field, modifiedMovimiento, value);
                    }
                }
            });
            return new ResponseEntity<>(movimientoDineroService.guardarMovimiento(id, modifiedMovimiento), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/enterprises/{id}/movements")
    public ResponseEntity<List<MovimientoDinero>> getEnterpriseMovements(@PathVariable int id) {
        Empresa enterprise = empresaService.findById(id);
        List<MovimientoDinero> movements = movimientoDineroService.consultarTodosMovimientos();
        List<MovimientoDinero> enterpriseMovements = new ArrayList<>();
        movements.forEach(movement -> {
            Empleado user = movement.getUsuarioEncargado();
            Empresa userEnterprise = user.getEmpresa();
            if (userEnterprise.equals(enterprise)) {
                enterpriseMovements.add(movement);
            }
        });
        return new ResponseEntity<>(enterpriseMovements, HttpStatus.OK);
    }
}
