package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Empresa;
import com.example.demo.services.EmpresaService;

@RestController
public class EmpresaController {
    private static final  org.slf4j.Logger logger = LoggerFactory.getLogger(EmpresaController.class);

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/enterprises")
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        return new ResponseEntity<>(empresaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") long id) {
        try {
            logger.info("Try to find Enterprises by Id");
            return new ResponseEntity<>(empresaService.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Invalid Object", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enterprises")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        logger.info("Enterprise created");
        return new ResponseEntity<>(empresaService.save(empresa), HttpStatus.CREATED);
    }

    @PatchMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> patchEmpresa(@PathVariable("id") long id, @RequestBody Map<String, Object> fields) {
        logger.info("Try to edit Enterprise");
        Empresa emp = empresaService.findById(id).get();
        emp = empresaService.editEnterprise(emp, fields);
        return new ResponseEntity<>(empresaService.save(emp), HttpStatus.OK);
    }

    @DeleteMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> deleteTutorial(@PathVariable("id") long id) {
        logger.info("Try to delete Enterprise by Id");
        empresaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
