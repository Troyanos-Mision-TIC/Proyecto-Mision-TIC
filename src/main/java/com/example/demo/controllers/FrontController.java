package com.example.demo.controllers;

import com.example.demo.model.Enterprise;
import com.example.demo.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FrontController {
    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/")
    public String index() {
       return "index";
    }

    @GetMapping("/enterprises-list")
    public String enterprises(Enterprise enterprise, Model model){
        List<Enterprise> enterprises = enterpriseService.findAll();
        model.addAttribute("empresas", enterprises);
        return "enterprises";
    }
}
