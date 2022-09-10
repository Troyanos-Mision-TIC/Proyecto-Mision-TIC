package com.example.demo.services;

import com.example.demo.model.Empleado;
import com.example.demo.model.Empresa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmpleadoService {
    Empleado empleado1;
    Empleado empleado2;
    Empresa empresa1;
    ArrayList<Empleado> empleados;

    public EmpleadoService(){
        this.empleados = new ArrayList<>();
        this.empleado1 = new Empleado("Daniel", "deyproj@hotmail.com", null, "supervisor");
        this.empleado2 = new Empleado("Juan", "juan@hotmail.com", null, "gerente");

        empleados.add(empleado1);
        empleados.add(empleado2);
    }

    public ArrayList<Empleado> getEmpleados() {
        return this.empleados;
    }

    public Empleado getEmpleado(int id) throws IndexOutOfBoundsException {
        return empleados.get(id);
    }

    public Empleado addEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
        return empleados.get(empleados.size()-1);
    }

    public Empleado removeEmpleado(int id) throws IndexOutOfBoundsException {
        return empleados.remove(id);
    }

    public Empleado saveEmpleado(int id, Empleado empleado) throws IndexOutOfBoundsException {
        return empleados.set(id, empleado);
    }

}
