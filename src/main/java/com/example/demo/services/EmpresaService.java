package com.example.demo.services;

import com.example.demo.model.Empresa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmpresaService {
     private final ArrayList<Empresa> storageEnterprises;

    public EmpresaService() {
        Empresa firstEnterprise = new Empresa("FirstEnterprise","Cali - Valle",123165423L,321654789L);
        storageEnterprises = new ArrayList<>();
        storageEnterprises.add(firstEnterprise);
    }

    public ArrayList<Empresa> findAll(){
        ArrayList<Empresa> enterprises;
        enterprises = getStorageEnterprises();

        return enterprises;
    }

    public Empresa findById(long id){
        Empresa enterprises = null;
        for(Empresa enterpr: getStorageEnterprises()){
            if(enterpr.getNit() == id){
                enterprises = enterpr;
                break;
            }
        }
        return enterprises;
    }

    public Boolean save(Empresa enterprise){
        return getStorageEnterprises().add(enterprise);
    }

    public Empresa update(Empresa actual, Empresa enterprise){
        for(Empresa enterpr: getStorageEnterprises()){
            if(enterpr.getNit() == actual.getNit()){
                if(enterprise.getNombre() != null && !enterprise.getNombre().isEmpty())
                    enterpr.setNombre(enterprise.getNombre());
                if(enterprise.getTelefono() != 0)
                    enterpr.setTelefono(enterprise.getTelefono());
                if(enterprise.getDireccion() != null && !enterprise.getDireccion().isEmpty())
                    enterpr.setDireccion(enterprise.getDireccion());
                return enterpr;
            }
        }
        return null;
    }

    public Boolean deleteById(long id){
        return storageEnterprises.removeIf(item -> item.getNit() == id);
    }

    public ArrayList<Empresa>  getStorageEnterprises() {
        return storageEnterprises;
    }
}
