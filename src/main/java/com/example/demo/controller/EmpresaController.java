package com.example.demo.controller;

import com.example.demo.model.Empresa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpresaController {

    @GetMapping("/enterprises")
    public ResponseEntity<List<Empresa>> getAllEmpresas(@RequestParam(required = false) String title) {

        List<Empresa> empresa = new ArrayList<>();

        Boolean valor = Boolean.TRUE;
        //Implement code to service request

        if (valor) {
            return new ResponseEntity<>(empresa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") long id) {

        Boolean valor = Boolean.TRUE;
        //Implement code to service request

        if (valor) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enterprises")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {

        Boolean valor = Boolean.TRUE;
        //Implement code to service request

        if (valor) {
            return new ResponseEntity<>(empresa, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PatchMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable("id") long id, @RequestBody Empresa empresa) {
        Boolean valor = Boolean.TRUE;
        //Implement code to service request

        if (valor) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/enterprises/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        Boolean valor = Boolean.TRUE;

        if (valor) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/enterprises")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        Boolean valor = Boolean.TRUE;

        if (valor) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
