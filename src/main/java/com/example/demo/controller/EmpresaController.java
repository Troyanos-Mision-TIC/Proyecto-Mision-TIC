package com.example.demo.controller;

import com.example.demo.model.Empresa;
import com.example.demo.service.EmpresaService;
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
    public ResponseEntity<Boolean> patchEmpleado(@PathVariable long nit, @RequestBody Map<Object, Object> fields) {
        try {
            Empresa empresa_ = enterprise.findById(nit);
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Empresa.class, (String) key);
                if (field != null) {
                    field.setAccessible(true);
                    if (!key.equals("empresa")) {
                        ReflectionUtils.setField(field, empresa_, value);
                    }
                }
            });
            return new ResponseEntity<>(enterprise.update(nit, empresa_), HttpStatus.OK);
        } catch (IndexOutOfBoundsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
