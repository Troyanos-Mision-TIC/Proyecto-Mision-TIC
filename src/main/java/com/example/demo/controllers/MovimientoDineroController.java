package com.example.demo.controllers;

import com.example.demo.model.Empleado;
import com.example.demo.model.Empresa;
import com.example.demo.model.MovimientoDinero;
import com.example.demo.services.EmpresaService;
import com.example.demo.services.MovimientoDineroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class MovimientoDineroController {
    private final MovimientoDineroService movimientoDineroService;
    private final EmpresaService empresaService;

    public MovimientoDineroController(MovimientoDineroService movimientoDineroService, EmpresaService empresaService) {
        this.movimientoDineroService = movimientoDineroService;
        this.empresaService = empresaService;
    }

    @GetMapping("/movements")
    public ResponseEntity<List<MovimientoDinero>> getAllMovimientoDinero(){
        return new ResponseEntity<>(movimientoDineroService.consultarTodosMovimientos(), HttpStatus.OK);
    }

    @GetMapping("/movements/{id}")
    public ResponseEntity<MovimientoDinero> getMovimientoDineroById(@PathVariable("id") long id){
        try {
            Optional<MovimientoDinero> optionalTransaction = movimientoDineroService.consultarMovimiento(id);
            MovimientoDinero transaction = optionalTransaction.get();
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/movements")
    public ResponseEntity<MovimientoDinero> postMovimientoDinero(@RequestBody Map<String, Object> fields){
        try {
            MovimientoDinero newMovement = new MovimientoDinero();
            newMovement = movimientoDineroService.editarMovimiento(newMovement, fields);
            return new ResponseEntity<>(movimientoDineroService.guardarMovimiento(newMovement), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/movements/{id}")
    public ResponseEntity<MovimientoDinero> deleteMovimiento(@PathVariable("id") long id) {
        try {
            movimientoDineroService.eliminarMovimiento(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/movements/{id}")
    public ResponseEntity<MovimientoDinero> patchMovimiento(@PathVariable long id, @RequestBody Map<String, Object> fields) {
        try {
            Optional<MovimientoDinero> optionalTransaction = movimientoDineroService.consultarMovimiento(id);
            MovimientoDinero transaction = optionalTransaction.get();
            transaction = movimientoDineroService.editarMovimiento(transaction, fields);
            return new ResponseEntity<>(movimientoDineroService.guardarMovimiento(transaction), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enterprises/{id}/movements")
    public ResponseEntity<List<MovimientoDinero>> getEnterpriseMovements(@PathVariable long id) {
        Optional<Empresa> optionalEnterprise = empresaService.findById(id);
        Empresa enterprise = optionalEnterprise.get();
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
