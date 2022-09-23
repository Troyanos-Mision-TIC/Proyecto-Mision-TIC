package com.example.demo.services;

import com.example.demo.model.Enterprise;
import com.example.demo.repositories.EnterpriseRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EnterpriseService {
    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    public List<Enterprise> findAll() {
        return enterpriseRepository.findAll();
    }

    public Optional<Enterprise> findById(long id) {
        return enterpriseRepository.findById(id);
    }

    public Enterprise save(Enterprise enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    public Enterprise editEnterprise(Enterprise enterprise, Map<String, Object> fields) {
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Enterprise.class, key);
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
