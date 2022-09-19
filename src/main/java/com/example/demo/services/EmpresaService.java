package com.example.demo.services;

import com.example.demo.repo.EnterpriseRepository;
import com.example.demo.model.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    EnterpriseRepository enterpriseRepository;

    public EmpresaService() {
    }

    public List<Empresa> findAll(){
        return enterpriseRepository.findAll();
    }

    public Optional<Empresa> findById(int id) throws IndexOutOfBoundsException {
        return enterpriseRepository.findById(id);
    }

    public Empresa save(Empresa enterprise) throws IndexOutOfBoundsException {
        return enterpriseRepository.save(enterprise);
    }

    public Boolean deleteById(int id) throws IndexOutOfBoundsException {
        enterpriseRepository.deleteById(id);
        return true;
    }
}
