package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.Enterprise;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.EnterpriseService;

@RestController
public class EnterpriseController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EnterpriseController.class);

    private final EnterpriseService enterpriseService;
    private final EmployeeService employeeService;

    public EnterpriseController(EnterpriseService enterpriseService, EmployeeService employeeService) {
        this.enterpriseService = enterpriseService;
        this.employeeService = employeeService;
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

    @PostMapping("/patch-enterprise/{id}")
    public RedirectView patchEnterprise(@PathVariable("id") long id, @ModelAttribute Enterprise enterprise) {
        logger.info("Try to edit Enterprise");
        Enterprise emp = enterpriseService.findById(id).get();
        BeanUtils.copyProperties(enterprise, emp);
        enterpriseService.save(emp);
        return new RedirectView("/enterprises-list");
    }

    @PostMapping("/delete-enterprise/{id}")
    public RedirectView deleteEnterprise(@PathVariable("id") long id) {
        logger.info("Try to delete Enterprise by Id");
        Optional<Enterprise> enterpriseDeleted = enterpriseService.findById(id);
        enterpriseDeleted.ifPresent(e -> {
            e.getEmployees().forEach(employee -> employeeService.deleteById(employee.getId()));
        });
        enterpriseService.deleteById(id);
        return new RedirectView("/enterprises-list");
    }
}
