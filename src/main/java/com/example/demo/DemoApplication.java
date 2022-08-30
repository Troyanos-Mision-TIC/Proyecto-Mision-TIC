package com.example.demo;

import com.example.demo.model.Empleado;
import com.example.demo.model.Empresa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        /*ArrayList<Empresa> empresas = new ArrayList<>();

        Empresa empresa1 = new Empresa("testEnterprise", "Street 1 # 20-15 Cali", 3214567890L, 1568453218L);
        Empresa empresa2 = new Empresa("testEnterprise2", "Street 2 # 20-15 Cali", 2123562354L, 546543211L);
        empresas.add(empresa1);
        empresas.add(empresa2);

        int contador1 = 1;
        for (Empresa empresa : empresas) {
            System.out.println("<<----------------------------------------------->>");
            System.out.println("Enterprise parameters No." + contador1);
            System.out.println("Nombre: " + empresa.getNombre());
            System.out.println("Dirección:" + empresa.getDireccion());
            System.out.println("Teléfono: " + empresa.getTelefono());
            System.out.println("NIT: " + empresa.getNit());

            empresa.setDireccion("Calle 5 No. 30 -25  Medellin");
            empresa.setTelefono(3201478523L);
            empresa.setNIT(546543212L);
            empresa.setNombre("EnterpriseProdu");
            System.out.println("<<**************************************************>>");

            System.out.println("Enterprise parameters No." + contador1 + " Editados");
            System.out.println("Nombre: " + empresa.getNombre());
            System.out.println("Dirección:" + empresa.getDireccion());
            System.out.println("Teléfono: " + empresa.getTelefono());
            System.out.println("NIT: " + empresa.getNit());
            System.out.println("<<----------------------------------------------->>");
            contador1++;
        }

        ArrayList<Empleado> empleados = new ArrayList<>();

        Empleado empleado1 = new Empleado("Daniel", "deyproj@hotmail.com", empresa1, "supervisor");
        Empleado empleado2 = new Empleado("Juan", "juan@hotmail.com", empresa1, "gerente");
        empleados.add(empleado1);
        empleados.add(empleado2);

        int contador2 = 1;
        for (Empleado empleado : empleados) {
            System.out.println("<<----------------------------------------------->>");
            System.out.println("Employee parameters No." + contador2);
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Correo:" + empleado.getCorreo());
            System.out.println("Empresa:" + (empleado.getEmpresa()).getNombre());
            System.out.println("Rol: " + empleado.getRol());

            empleado.setNombre("Alejandro");
            empleado.setCorreo("cambio@hotmail.com");
            empleado.setEmpresa(empresa2);
            empleado.setRol("asesor");

            System.out.println("<<**************************************************>>");

            System.out.println("Employee parameters No." + contador2 + " Editados");
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Correo:" + empleado.getCorreo());
            System.out.println("Empresa:" + (empleado.getEmpresa()).getNombre());
            System.out.println("Rol: " + empleado.getRol());
            System.out.println("<<----------------------------------------------->>");
            contador2++;
        }*/
    }
}
