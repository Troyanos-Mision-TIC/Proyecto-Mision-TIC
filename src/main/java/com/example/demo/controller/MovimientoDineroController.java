package com.example.demo.controller;

import com.example.demo.model.MovimientoDinero;
import com.example.demo.services.MovimientoDineroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovimientoDineroController {
    @Autowired
    private MovimientoDineroService movimientoDineroService;

    public MovimientoDineroController (MovimientoDineroService movimientoDineroService) {
        this.movimientoDineroService = movimientoDineroService;
    }
    @GetMapping("/MovimientosDinero")
    public ResponseEntity<List<MovimientoDinero>> getAllMovimientoDinero(){
        return new ResponseEntity<>(movimientoDineroService.consultarTodosMovimientos(), HttpStatus.OK);
    }

    @GetMapping("/MovimientosDinero/{id}/")
    public ResponseEntity<MovimientoDinero> getMovimientoDineroById(@PathVariable("id") int id){
        try {
            return new ResponseEntity<>(movimientoDineroService.consultarMovimiento(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/MovimientosDinero")
    public ResponseEntity<MovimientoDinero> createMovimientoDinero(@RequestBody MovimientoDinero movimiento){
        try {
            return new ResponseEntity<>(movimientoDineroService.crearMovimiento(movimiento), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/MovimientosDinero/{id}")
    public ResponseEntity<MovimientoDinero> deleteMovimiento(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(movimientoDineroService.eliminarMovimiento(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/MovimientosDinero/{id}")
    public ResponseEntity<MovimientoDinero> patchMovimiento(@PathVariable("id") int id, @RequestBody MovimientoDinero movimiento) {
        try {
            return new ResponseEntity<>(movimientoDineroService.editarMovimiento(id, movimiento), HttpStatus.ACCEPTED);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
