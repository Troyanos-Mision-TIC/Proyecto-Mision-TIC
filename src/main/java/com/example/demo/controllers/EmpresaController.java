package com.example.demo.controllers;

import com.example.demo.model.Empresa;
import com.example.demo.services.EmpresaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmpresaController {

    private static final  org.slf4j.Logger logger = LoggerFactory.getLogger(EmpresaController.class);

    EmpresaService enterpriseService;

    public EmpresaController(EmpresaService empresaService) {
        this.enterpriseService = empresaService;
    }

    @GetMapping("/enterprises")
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        ArrayList<Empresa> empresa;
        logger.info("Try to find Enterprises");
        empresa = enterpriseService.findAll();

        if (empresa != null) {
            logger.info("successful task");
            return new ResponseEntity<>(empresa, HttpStatus.OK);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") long id) {
        Empresa enterprise;
        logger.info("Try to find Enterprises by Id");
        enterprise = enterpriseService.findById(id);

        if (enterprise != null) {
            logger.info("successful task");
            return new ResponseEntity<>(enterprise, HttpStatus.OK);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enterprises")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        logger.info("Try to create Enterprise");
        Boolean valor = enterpriseService.save(empresa);

        if (valor) {
            logger.info("successful task");
            return new ResponseEntity<>(empresa, HttpStatus.CREATED);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/enterprises/{id}")
    ResponseEntity<Empresa> patchEmpleado(@PathVariable("id") long id, @RequestBody Empresa editedClientFromBrowser) {
        Empresa empresa = enterpriseService.findById(id);

        logger.info("Try to edit Enterprise");
        Empresa value  = enterpriseService.update(empresa, editedClientFromBrowser);
        if(value != null){
            logger.info("successful task");
            return new ResponseEntity<>(value, HttpStatus.OK);
        }
        logger.error("Wrong task");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/enterprises/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        logger.info("Try to delete Enterprise by Id");
        Boolean removed = enterpriseService.deleteById(id);

        if (removed) {
            logger.info("successful task");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
