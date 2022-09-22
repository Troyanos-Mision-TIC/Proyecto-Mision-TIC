package com.example.demo.services.Interfaces;

import com.example.demo.model.MovimientoDinero;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ICashMoveService {

    List<MovimientoDinero> consultarTodosMovimientos();

    Optional<MovimientoDinero> consultarMovimiento(int idmovimiento) throws IndexOutOfBoundsException;

    MovimientoDinero crearMovimiento(MovimientoDinero Movimiento);

    Boolean eliminarMovimiento(int idmovimiento) throws IndexOutOfBoundsException;
}
