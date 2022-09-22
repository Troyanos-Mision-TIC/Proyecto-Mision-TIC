package com.example.demo.services.Implements;

import com.example.demo.model.Empresa;
import com.example.demo.repo.IEnterpriseRepository;
import com.example.demo.services.Interfaces.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService implements IEnterpriseService {
    @Autowired
    IEnterpriseRepository IEnterpriseRepository;

    public EmpresaService() {
    }

    public List<Empresa> findAll() {
        return IEnterpriseRepository.findAll();
    }

    public Optional<Empresa> findById(int id) throws IndexOutOfBoundsException {
        return IEnterpriseRepository.findById(id);
    }

    public Empresa save(Empresa enterprise) throws IndexOutOfBoundsException {
        return IEnterpriseRepository.save(enterprise);
    }

    public Boolean deleteById(int id) throws IndexOutOfBoundsException {
        IEnterpriseRepository.deleteById(id);
        return true;
    }
}
