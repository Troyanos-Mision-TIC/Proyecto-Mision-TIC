package com.example.demo.services;

import com.example.demo.model.MovimientoDinero;
import com.example.demo.repo.MoneyMovRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoDineroService {

    @Autowired
    MoneyMovRepository moneyMovRepository;

    public MovimientoDineroService() {

    }

    public List<MovimientoDinero> consultarTodosMovimientos() {
        return moneyMovRepository.findAll();
    }

    public Optional<MovimientoDinero> consultarMovimiento(int idmovimiento) throws IndexOutOfBoundsException {
        return moneyMovRepository.findById(idmovimiento);
    }

    public MovimientoDinero crearMovimiento(MovimientoDinero Movimiento) {
        return moneyMovRepository.save(Movimiento);
    }

    public Boolean eliminarMovimiento(int idmovimiento) throws IndexOutOfBoundsException {
        moneyMovRepository.deleteById(idmovimiento);
        return true;
    }
}
