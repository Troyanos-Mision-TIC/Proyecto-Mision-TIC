package com.example.demo.services.Interfaces;

import com.example.demo.model.Empresa;

import java.util.List;
import java.util.Optional;

public interface IEnterpriseService {

    List<Empresa> findAll();

    Optional<Empresa> findById(int id) throws IndexOutOfBoundsException;

    Empresa save(Empresa enterprise) throws IndexOutOfBoundsException;

    Boolean deleteById(int id) throws IndexOutOfBoundsException;
}
