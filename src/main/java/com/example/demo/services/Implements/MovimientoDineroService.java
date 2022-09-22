package com.example.demo.services.Implements;

import com.example.demo.model.MovimientoDinero;
import com.example.demo.repo.ICashMoveRepository;
import com.example.demo.services.Interfaces.ICashMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoDineroService implements ICashMoveService {

    @Autowired
    ICashMoveRepository ICashMoveRepository;

    public MovimientoDineroService() {

    }

    public List<MovimientoDinero> consultarTodosMovimientos() {
        return ICashMoveRepository.findAll();
    }

    public Optional<MovimientoDinero> consultarMovimiento(int idmovimiento) throws IndexOutOfBoundsException {
        return ICashMoveRepository.findById(idmovimiento);
    }

    public MovimientoDinero crearMovimiento(MovimientoDinero Movimiento) {
        return ICashMoveRepository.save(Movimiento);
    }

    public Boolean eliminarMovimiento(int idmovimiento) throws IndexOutOfBoundsException {
        ICashMoveRepository.deleteById(idmovimiento);
        return true;
    }
}
