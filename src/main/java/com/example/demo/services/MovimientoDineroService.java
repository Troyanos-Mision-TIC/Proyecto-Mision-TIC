package com.example.demo.services;

import com.example.demo.model.Empleado;
import com.example.demo.model.Empresa;
import com.example.demo.model.MovimientoDinero;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovimientoDineroService {

    MovimientoDinero movimiento1;
    MovimientoDinero movimiento2;
    Empleado empleado1;
    Empleado empleado2;
    Empresa empresa1;
    ArrayList<MovimientoDinero> movimientos;

    public MovimientoDineroService() {
        this.movimiento1 = new MovimientoDinero(500000,"ingreso",empleado1);
        this.movimiento2 = new MovimientoDinero(-2311123,"Retiro",empleado2);
        this.empleado1 = new Empleado("Daniel", "deyproj@hotmail.com", empresa1, "supervisor");
        this.empleado2 = new Empleado("Juan", "juan@hotmail.com", empresa1, "gerente");
        this.movimientos = new ArrayList<>();

        movimientos.add(movimiento1);
        movimientos.add(movimiento2);
    }

    public MovimientoDinero saveMovimiento(Integer id, MovimientoDinero movimiento) {
        return movimientos.set(id, movimiento);
    }

    public MovimientoDinero removeMovimiento(Integer id) {
        return movimientos.remove(id);
    }

    public  MovimientoDinero getMovimiento(Integer id) {
        return movimientos.get(id);
    }

    public  ArrayList getAllMovimientos() {
        return this.movimientos;
    }

    public  MovimientoDinero createMovimiento(MovimientoDinero movimiento) {
        this.movimientos.add(movimiento);
        return movimientos.get(movimientos.size()-1);
    }

}
