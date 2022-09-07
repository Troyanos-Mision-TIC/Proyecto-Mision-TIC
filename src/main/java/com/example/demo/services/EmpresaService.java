package com.example.demo.services;

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
        Empresa firstEnterprise = new Empresa("FirstEnterprise","Cali - Valle",123165423L,321654789L);
        storageEnterprises = new ArrayList<>();
        storageEnterprises.add(firstEnterprise);
    }

    public ArrayList<Empresa> findAll(){
        ArrayList<Empresa> enterprises = new ArrayList<>();
        //Service Implement
        enterprises = getStorageEnterprises();

        return enterprises;
    }

    public Empresa findById(long id){
        Empresa enterprises = null;
        //Service Implement
        for(Empresa enterpr: getStorageEnterprises()){
            if(enterpr.getNit() == id){
                enterprises = enterpr;
                break;
            }
        }
        return enterprises;
    }

    public Boolean save(Empresa enterprise){
        //Service Implement
        int val1 = getStorageEnterprises().size();
        getStorageEnterprises().add(enterprise);
        int val2 = getStorageEnterprises().size();

        if(val2 > val1){
            return true;
        }else{
            return false;
        }
    }

    public Empresa update(Empresa actual, Empresa enterprise){
        //Service Implement
        for(Empresa enterpr: getStorageEnterprises()){
            if(enterpr.getNit() == actual.getNit()){
                if(enterprise.getNombre() != null && !enterprise.getNombre().isEmpty()){
                    enterpr.setNombre(enterprise.getNombre());}
                if(enterprise.getTelefono() != 0){
                    enterpr.setTelefono(enterprise.getTelefono());}
                if(enterprise.getDireccion() != null && !enterprise.getDireccion().isEmpty()){
                    enterpr.setDireccion(enterprise.getDireccion());}
                return enterpr;
            }
        }
        return null;
    }

    public Boolean deleteById(long id){
        //Service Implement
        for(Empresa enterpr: getStorageEnterprises()){
            if(enterpr.getNit() == id){
                getStorageEnterprises().remove(enterpr);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteAll(){
        //Service Implement
        if(!getStorageEnterprises().isEmpty()){
            getStorageEnterprises().clear();
            return true;
        }
        return false;
    }

    public ArrayList<Empresa>  getStorageEnterprises() {
        return storageEnterprises;
    }

    public void setStorageEnterprises(ArrayList<Empresa>  storageEnterprises) {
        this.storageEnterprises = storageEnterprises;
    }
}
