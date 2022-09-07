package com.example.demo.controller;

import com.example.demo.model.Empresa;
import com.example.demo.service.EmpresaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmpresaController {

    private static final  org.slf4j.Logger logger = LoggerFactory.getLogger(EmpresaController.class);

    @Autowired
    EmpresaService empresaService;

    @GetMapping("/enterprises")
    public ResponseEntity<List<Empresa>> getAllEmpresas(@RequestParam(required = false) String title) {
        ArrayList<Empresa> empresa = new ArrayList<>();
        logger.info("Try to find Enterprises");
        empresa = empresaService.findAll();

        if (!empresa.isEmpty()) {
            logger.info("successful task");
            return new ResponseEntity<>(empresa, HttpStatus.OK);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") Integer id) {
        Empresa enterprise = null;
        logger.info("Try to find Enterprises by Id");
        enterprise = empresaService.findById(id);

        if (enterprise != null) {
            logger.info("successful task");
            return new ResponseEntity(enterprise, HttpStatus.OK);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enterprises")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        logger.info("Try to create Enterprise");
        Boolean valor = empresaService.append(empresa);

        if (valor) {
            logger.info("successful task");
            return new ResponseEntity<>(empresa, HttpStatus.CREATED);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/enterprises/{id}")
    ResponseEntity<Empresa> patchEmpresa(@PathVariable("id") int id,@RequestBody Map<Object, Object> fields) {
       try{
           logger.info("Try to edit Enterprise");
           Empresa emp = empresaService.findById(id);
            fields.forEach((key, value) ->{
                Field field = ReflectionUtils.findField(Empresa.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, emp, value);
            });
            return new ResponseEntity<>(empresaService.save(id,emp),HttpStatus.OK);
       } catch (Exception e) {
           logger.error("Catch exception:: ",e);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    }

    @DeleteMapping("/enterprises/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") int id) {
        logger.info("Try to delete Enterprise by Id");
        Boolean valor = empresaService.deleteById(id);

        if (valor) {
            logger.info("successful task");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.error("Wrong task");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
