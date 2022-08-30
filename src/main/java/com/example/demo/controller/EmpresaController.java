package com.example.demo.controller;

import com.example.demo.model.Empresa;
import com.example.demo.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpresaController {

    @Autowired
    EmpresaService enterprise;

    @GetMapping("/enterprises")
    public ResponseEntity<List<Empresa>> getAllEmpresas(@RequestParam(required = false) String title) {

        ArrayList<Empresa> empresa = new ArrayList<>();
        empresa = enterprise.findAll();

        if (!empresa.isEmpty()) {
            return new ResponseEntity<>(empresa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") long id) {
        Empresa _enterprise = null;
        _enterprise = enterprise.findById(id);

        if (enterprise != null) {
            return new ResponseEntity(_enterprise, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enterprises")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        Boolean valor = enterprise.save(empresa);

        if (valor) {
            return new ResponseEntity<>(empresa, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PatchMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable("id") long id, @RequestBody Empresa empresa) {
        Boolean valor = enterprise.update(empresa);

        if (valor) {
            return new ResponseEntity<>(empresa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/enterprises/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        Boolean valor = enterprise.deleteById(id);

        if (valor) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/enterprises")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        Boolean valor = enterprise.deleteAll();

        if (valor) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
