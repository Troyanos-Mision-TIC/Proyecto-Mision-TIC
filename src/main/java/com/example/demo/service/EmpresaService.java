package com.example.demo.service;

import com.example.demo.model.Empresa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmpresaService {
     private final ArrayList<Empresa> storageEnterprises;

    public EmpresaService() {
        Empresa firstEnterprise = new Empresa("FirstEnterprise","Cali - Valle","7654321","0192837465");
        storageEnterprises = new ArrayList<>();
        storageEnterprises.add(firstEnterprise);
    }

    public ArrayList<Empresa> findAll(){
        return storageEnterprises;
    }

    public Empresa findById(int id) throws IndexOutOfBoundsException {
        return storageEnterprises.get(id);
    }

    public Empresa append(Empresa enterprise) {
        storageEnterprises.add(enterprise);
        return storageEnterprises.get(storageEnterprises.size() - 1);
    }

    public Empresa save(Integer id, Empresa enterprise) throws IndexOutOfBoundsException {
        return storageEnterprises.set(id, enterprise);
    }

    public Empresa deleteById(int id) throws IndexOutOfBoundsException {
        return storageEnterprises.remove(id);
    }
}
