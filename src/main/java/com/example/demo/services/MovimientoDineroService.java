package com.example.demo.services;

import com.example.demo.model.MovimientoDinero;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class MovimientoDineroService {

    ArrayList<MovimientoDinero> movimientos;

    public MovimientoDineroService() {
        this.movimientos = new ArrayList<>();
    }

    public ArrayList<MovimientoDinero> consultarTodosMovimientos() {
        return movimientos;
    }
    
    public MovimientoDinero consultarMovimiento(int idmovimiento) throws IndexOutOfBoundsException {
        return movimientos.get(idmovimiento);
    }
    
    public MovimientoDinero crearMovimiento(MovimientoDinero Movimiento) {
        this.movimientos.add(Movimiento);
        return movimientos.get(movimientos.size()-1);
    }
    
    public MovimientoDinero guardarMovimiento(int idmovimiento,MovimientoDinero Movimiento){
        this.movimientos.set(idmovimiento, Movimiento);
        return movimientos.get(idmovimiento);
    }
    
    public MovimientoDinero eliminarMovimiento(int idmovimiento) throws IndexOutOfBoundsException {
        return this.movimientos.remove(idmovimiento);
    }
}
