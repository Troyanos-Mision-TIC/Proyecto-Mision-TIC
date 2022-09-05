package com.example.demo.Controller;

import com.example.demo.model.MovimientoDinero;
import com.example.demo.services.MovimientoDineroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MovimientoDineroController {

    @GetMapping("/MovimientoDinero")
    public ResponseEntity getAllMovimientoDinero(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/MovimientoDinero/{id}/")
    public ResponseEntity getMovimientoDineroById(@PathVariable("id") Long id){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/MovimientoDinero", consumes = "application/json", produces = "application/json")
    public MovimientoDinero creatMovimientoDinero(@RequestBody MovimientoDinero movimientoDinero){
        return ResponseEntity(HttpStatus.CREATED);
    }

    @PatchMapping("/MovimientoDinero/{id}/{monto}")
    public MovimientoDinero uptadeMovimientoDinero(@PathVariable("id") Long id, @PathVariable("monto") Double monto){
        return ResponseEntity(HttpStatus.ACCEPTED);

    @DeleteMapping("/MovimientoDinero/{id}/")
    public ResponseEntity deleteMovimientoDinero(@PathVariable("id") Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
