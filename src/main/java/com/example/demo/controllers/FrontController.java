package com.example.demo.controllers;

import com.example.demo.model.Empresa;
import com.example.demo.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FrontController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/")
    public String index() {
       return "index";
    }

    @GetMapping("/empresas")
    public String empresas(Empresa empresa, Model model){
        List<Empresa> empresas = empresaService.findAll();
        model.addAttribute("empresas", empresas);
        return "empresas";
    }

    @GetMapping("/eliminarEmpresa")
    public String eliminar(long idEmpresa){
        empresaService.deleteById(idEmpresa);
        return "redirect:/empresas";
    }

    @PostMapping("/agregarEmpresa")
    public String guardar(Empresa empresa){
        empresaService.save(empresa);
        return "redirect:/empresas";
    }

}
