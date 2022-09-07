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
@RequestMapping("/api")
public class EmpresaController {

    private static final  org.slf4j.Logger logger = LoggerFactory.getLogger(EmpresaController.class);

    @Autowired
    EmpresaService enterprise;

    @GetMapping("/enterprises")
    public ResponseEntity<List<Empresa>> getAllEmpresas(@RequestParam(required = false) String title) {

        ArrayList<Empresa> empresa = new ArrayList<>();
        logger.info("Try to find Enterprises");
        empresa = enterprise.findAll();

        if (!empresa.isEmpty()) {
            logger.info("successful task");
            return new ResponseEntity<>(empresa, HttpStatus.OK);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") long id) {
        Empresa _enterprise = null;
        logger.info("Try to find Enterprises by Id");
        _enterprise = enterprise.findById(id);

        if (enterprise != null) {
            logger.info("successful task");
            return new ResponseEntity(_enterprise, HttpStatus.OK);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enterprises")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        logger.info("Try to create Enterprise");
        Boolean valor = enterprise.save(empresa);

        if (valor) {
            logger.info("successful task");
            return new ResponseEntity<>(empresa, HttpStatus.CREATED);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/enterprises/{id}", method = RequestMethod.PATCH ,produces = {"application/vnd.api+json"} )
    ResponseEntity<Empresa> patchEmpleado(@PathVariable("id") long id,@RequestBody Empresa editedClientFromBrowser) {

        Empresa emp = enterprise.findById(id);

        logger.info("Try to edit Enterprise");
        Empresa value  = enterprise.update(emp, editedClientFromBrowser);
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
        Boolean valor = enterprise.deleteById(id);

        if (valor) {
            logger.info("successful task");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/enterprises")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        logger.info("Try to delete All Enterprises");
        Boolean valor = enterprise.deleteAll();

        if (valor) {
            logger.info("successful task");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
