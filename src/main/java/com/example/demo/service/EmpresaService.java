package com.example.demo.service;

import com.example.demo.model.Empresa;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.BreakIterator;
import java.util.ArrayList;

@Service
public class EmpresaService {
     private ArrayList<Empresa> storageEnterprises;

    @PostConstruct
    private void EmpresaService() {
        Empresa firstEnterprise = new Empresa("FirstEnterprise","Cali - Valle",123165423L,321654789);
        storageEnterprises = new ArrayList<>();
        storageEnterprises.add(firstEnterprise);
    }

    public ArrayList<Empresa> findAll(){
        return getStorageEnterprises();
    }

    public Empresa findById(int id){
        return storageEnterprises.get(id);
    }

    public Boolean append(Empresa enterprise){
        try {
            getStorageEnterprises().add(enterprise);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Empresa save(Integer id, Empresa enterprise){
        return storageEnterprises.set(id, enterprise);
    }

    public Boolean deleteById(int id){
        try{
            getStorageEnterprises().remove(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public ArrayList<Empresa>  getStorageEnterprises() {
        return storageEnterprises;
    }

    public void setStorageEnterprises(ArrayList<Empresa>  storageEnterprises) {
        this.storageEnterprises = storageEnterprises;
    }
}
