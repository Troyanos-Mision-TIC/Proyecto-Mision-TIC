package com.example.demo.Controller;

import com.example.demo.model.Empleado;
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
public class MovimientosController {

    @GetMapping("/MovimientosDinero")
    public ResponseEntity<List<MovimientoDinero>> getAllMovimientoDinero(){
        return new ResponseEntity<>(MovimientoDineroService.getAllMovimientos(), HttpStatus.OK);
    }

    @GetMapping("/MovimientosDinero/{id}/")
    public ResponseEntity<MovimientoDinero> getMovimientoDineroById(@PathVariable("id") Integer id){
        try {
            return new ResponseEntity<>(MovimientoDineroService.getMovimiento(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/MovimientosDinero")
    public ResponseEntity<MovimientoDinero> createMovimientoDinero(@RequestBody Map<Object, Object> fields){
        MovimientoDinero nuevoMovimiento = new MovimientoDinero();
        try {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(MovimientoDinero.class, (String) key);
                if (field != null) {
                    field.setAccessible(true);
                    if (key.equals("empleado")) {
                        Empleado empleado = new Empleado("Camilo", "camilo@correo.com","EnterCom","supervisor");
                        ReflectionUtils.setField(field, nuevoMovimiento, empleado);
                    } else {
                        ReflectionUtils.setField(field, nuevoMovimiento, value);
                    }
                }
            });
            return new ResponseEntity<>(MovimientoDineroService.createMovimiento(nuevoMovimiento), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/MovimientosDinero/{id}")
    public ResponseEntity<MovimientoDinero> deleteMovimiento(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(MovimientoDineroService.removeMovimiento(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/MovimientosDinero/{id}")
    public ResponseEntity<MovimientoDinero> patchMovimiento(@PathVariable("id") Integer id, @RequestBody Map<Object, Object> fields) {
        try {
            MovimientoDinero nuevoMovimiento = MovimientoDineroService.getMovimiento(id);
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(MovimientoDinero.class, (String) key);
                if (field != null) {
                    field.setAccessible(true);
                    if (key.equals("empleado")) {
                        Empleado empleado = new Empleado("Camilo", "camilo@correo.com","EnterCom","supervisor");
                        ReflectionUtils.setField(field, nuevoMovimiento, empleado);
                    } else {
                        ReflectionUtils.setField(field, nuevoMovimiento, value);
                    }
                }
            });
            return new ResponseEntity<>(MovimientoDineroService.saveMovimiento(id, nuevoMovimiento), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
