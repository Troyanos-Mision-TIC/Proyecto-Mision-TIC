package com.example.demo.controller;

import com.example.demo.model.MovimientoDinero;
import com.example.demo.services.MovimientoDineroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
public class MovimientoDineroController {
    private final MovimientoDineroService movimientoDineroService;

    public MovimientoDineroController(MovimientoDineroService movimientoDineroService) {
        this.movimientoDineroService = movimientoDineroService;
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
    public ResponseEntity<MovimientoDinero> postMovimientoDinero(@RequestBody MovimientoDinero movimiento){
        return new ResponseEntity<>(movimientoDineroService.crearMovimiento(movimiento), HttpStatus.OK);
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
                    ReflectionUtils.setField(field, modifiedMovimiento, value);
                }
            });
            return new ResponseEntity<>(movimientoDineroService.guardarMovimiento(id, modifiedMovimiento), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
