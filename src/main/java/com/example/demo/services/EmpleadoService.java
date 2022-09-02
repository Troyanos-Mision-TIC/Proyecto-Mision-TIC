package com.example.demo.services;

import com.example.demo.model.Empleado;
import com.example.demo.model.Empresa;

import java.util.ArrayList;

public class EmpleadoService {
    Empleado empleado1;
    Empleado empleado2;
    Empresa empresa1;
    ArrayList<Empleado> empleados;

    public EmpleadoService(){
        this.empresa1 = new Empresa("testEnterprise", "Street 1 # 20-15 Cali", 3214567890L, 1568453218L);
        this.empleado1 = new Empleado("Daniel", "deyproj@hotmail.com", empresa1, "supervisor");
        this.empleado2 = new Empleado("Juan", "juan@hotmail.com", empresa1, "gerente");

        empleados.add(empleado1);
        empleados.add(empleado1);

    }

    public ArrayList getEmpleados() {
        return this.empleados;
    }

    public Empleado getEmpleado(int id){
        return empleados.get(id);
    }

    public Empleado addEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
        return empleados.get(empleados.size()-1);
    }

    public Empleado removeEmpleado(int id){
        return empleados.remove(id);
    }

    public Empleado saveEmpleado(int id, Empleado empleado){
        return empleados.set(id, empleado);
    }

}
