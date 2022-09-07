package com.example.demo.services;


import com.example.demo.model.Empleado;
import com.example.demo.model.Empresa;
import com.example.demo.model.MovimientoDinero;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class MovimientoDineroService {
    
    ArrayList<MovimientoDinero> movimientos;
    
    public MovimientoDineroService(){
        this.movimientos = new ArrayList<>();
        Empresa empresa1 = new Empresa("testEnterprise", "Street 1 # 20-15 Cali", 3214567890L, 1568453218L);
        Empleado empleado1 = new Empleado("Daniel", "deyproj@hotmail.com", empresa1, "supervisor");
        Empleado empleado2 = new Empleado("Juan", "juan@hotmail.com", empresa1, "gerente");
        MovimientoDinero movimiento1 = new MovimientoDinero(50000,"Egreso",empleado1);
        MovimientoDinero movimiento2 = new MovimientoDinero(25000,"Ingreso",empleado2);
        movimientos.add(movimiento1);
        movimientos.add(movimiento2);
    }
    
    public ArrayList<MovimientoDinero> consultarTodosMovimientos(){
        return movimientos;
    }
    
    public MovimientoDinero consultarMovimiento(int idmovimiento){
        return movimientos.get(idmovimiento);
    }
    
    public MovimientoDinero crearMovimiento(MovimientoDinero Movimiento){
        this.movimientos.add(Movimiento);
        return movimientos.get(movimientos.size()-1);
    }
    
    public MovimientoDinero editarMovimiento(int idmovimiento, MovimientoDinero Movimiento){
        this.movimientos.set(idmovimiento, Movimiento);
        return movimientos.get(idmovimiento);
    }
    
    public MovimientoDinero eliminarMovimiento(int idmovimiento){
        return this.movimientos.remove(idmovimiento);
    }
}
