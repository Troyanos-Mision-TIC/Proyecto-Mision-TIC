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
@RequestMapping("/")
public class EmpresaController {

    private static final  org.slf4j.Logger logger = LoggerFactory.getLogger(EmpresaController.class);

    @Autowired
    EmpresaService empresaService;

    @GetMapping("/enterprises")
    public ResponseEntity<List<Empresa>> getAllEmpresas(@RequestParam(required = false) String title) {
        try{
            logger.info("Try to find Enterprises");
            return new ResponseEntity<>(empresaService.findAll(), HttpStatus.OK);
        } catch (Exception e){
            logger.error("Wrong task");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/enterprises/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("id") Integer id) {
        try{
            logger.info("Try to find Enterprises by Id");
            return new ResponseEntity(empresaService.findById(id), HttpStatus.OK);
        }catch (IndexOutOfBoundsException e){
            logger.error("Wrong task", e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enterprises")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        try{
            logger.info("Try to create Enterprise");
            return new ResponseEntity<>(empresaService.append(empresa), HttpStatus.CREATED);
        } catch (Exception e){
            logger.error("Can't created enterprise",e);
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
