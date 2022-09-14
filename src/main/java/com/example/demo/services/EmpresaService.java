package com.example.demo.services;

import com.example.demo.model.Empresa;
import com.example.demo.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmpresaService {
     private final EnterpriseRepository enterpriseRepository;

    public EmpresaService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    public List<Empresa> findAll() {
        return enterpriseRepository.findAll();
    }

    public Optional<Empresa> findById(long id) {
        return enterpriseRepository.findById(id);
    }

    public Empresa save(Empresa enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    public Empresa editEnterprise(Empresa enterprise, Map<String, Object> fields) {
        fields.forEach((key, value) ->{
            Field field = ReflectionUtils.findField(Empresa.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, enterprise, value);
            }
        });
        return enterprise;
    }

    public void deleteById(long id) {
        enterpriseRepository.deleteById(id);
    }
}
