package com.example.demo.controller;

import com.example.demo.model.Empresa;
import com.example.demo.service.EmpresaService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") Integer id) {
        try {
            logger.info("Try to find Enterprises by Id");
            return new ResponseEntity<>(empresaService.findById(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            logger.error("Wrong task", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enterprises")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        try {
            logger.info("Try to create Enterprise");
            return new ResponseEntity<>(empresaService.append(empresa), HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Can't created enterprise",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> patchEmpresa(@PathVariable("id") int id, @RequestBody Map<String, Object> fields) {
       try{
            logger.info("Try to edit Enterprise");
            Empresa emp = empresaService.findById(id);
            fields.forEach((key, value) ->{
                Field field = ReflectionUtils.findField(Empresa.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, emp, value);
                }
            });
            return new ResponseEntity<>(empresaService.save(id, emp), HttpStatus.OK);
       } catch (IndexOutOfBoundsException e) {
           logger.error("Invalid Index: ", e);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       } catch (Exception e) {
           logger.error("Patch Error. " + e.getMessage());
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @DeleteMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> deleteTutorial(@PathVariable("id") int id) {
       try{
            logger.info("Try to delete Enterprise by Id");
            return new ResponseEntity<>(empresaService.deleteById(id), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e){
            logger.error("Can't delete enterprise ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
