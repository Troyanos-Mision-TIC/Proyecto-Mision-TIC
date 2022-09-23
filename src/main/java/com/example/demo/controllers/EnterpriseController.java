package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.Enterprise;
import com.example.demo.services.EnterpriseService;

@RestController
public class EnterpriseController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EnterpriseController.class);

    private final EnterpriseService enterpriseService;

    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @GetMapping("/enterprises")
    public ResponseEntity<List<Enterprise>> getAll() {
        return new ResponseEntity<>(enterpriseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/enterprises/{id}")
    public ResponseEntity<Enterprise> getOne(@PathVariable("id") long id) {
        try {
            logger.info("Try to find Enterprises by Id");
            return new ResponseEntity<>(enterpriseService.findById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Invalid Object", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enterprises")
    public RedirectView postEnterprise(@ModelAttribute Enterprise empresa) {
        logger.info("Enterprise created");
        enterpriseService.save(empresa);
        return new RedirectView("/enterprises-list");
    }

    @PatchMapping("/enterprises/{id}")
    public ResponseEntity<Enterprise> patchEnterprise(@PathVariable("id") long id, @RequestBody Map<String, Object> fields) {
        logger.info("Try to edit Enterprise");
        Enterprise emp = enterpriseService.findById(id).get();
        emp = enterpriseService.editEnterprise(emp, fields);
        return new ResponseEntity<>(enterpriseService.save(emp), HttpStatus.OK);
    }

    @PostMapping("/delete-enterprise/{id}")
    public RedirectView deleteEnterprise(@PathVariable("id") long id) {
        logger.info("Try to delete Enterprise by Id");
        enterpriseService.deleteById(id);
        return new RedirectView("/enterprises-list");
    }
}
