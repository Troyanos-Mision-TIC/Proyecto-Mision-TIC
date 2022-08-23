package com.example.demo;

import com.example.demo.model.Empleado;
import com.example.demo.model.Empresa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		System.out.println("Hello World");

		ArrayList<Empresa> enterprise  = new ArrayList<>();

		Empresa emp =  new Empresa("testEnterprise" , "Street 1 # 20-15 Cali", 3214567890L, 1568453218L);
		Empresa emp2 =  new Empresa("testEnterprise2" , "Street 2 # 20-15 Cali", 2123562354L, 546543211L);
		enterprise.add(emp);
		enterprise.add(emp2);

		int counter = 1;
		for(Empresa e : enterprise){
			System.out.println("<<----------------------------------------------->>");
			System.out.println("Enterprise parameters No."+ counter);
			System.out.println("Nombre: "+e.getNombre());
			System.out.println("Dirección:"+e.getDireccion());
			System.out.println("Telefono: "+e.getTelefono());
			System.out.println("NIT: "+e.getNIT());

			e.setDireccion("Calle 5 No. 30 -25  Medellin");
			e.setTelefono(3201478523L);
			e.setNIT(546543212L);
			e.setNombre("EnterpriseProdu");
			System.out.println("<<**************************************************>>");

			System.out.println("Enterprise parameters No."+counter+" Editados");
			System.out.println("Nombre: "+e.getNombre());
			System.out.println("Dirección:"+e.getDireccion());
			System.out.println("Telefono: "+e.getTelefono());
			System.out.println("NIT: "+e.getNIT());
			System.out.println("<<----------------------------------------------->>");
			counter++;
		}

		ArrayList<Empleado> employee  = new ArrayList<>();
                
        Empleado empleado = new Empleado("Daniel", "deyproj@hotmail.com", emp, "supervisor");
        Empleado empleado2 = new Empleado("Juan", "juan@hotmail.com", emp, "gerente");
        employee.add(empleado);
        employee.add(empleado2);
                
                
                int counter2 = 1;
		for(Empleado e : employee){
			System.out.println("<<----------------------------------------------->>");
			System.out.println("Employee parameters No."+ counter2);
			System.out.println("Nombre: "+e.getNombre());
			System.out.println("Correo:"+e.getCorreo());
			System.out.println("Empresa:"+(e.getEmpresa()).getNombre());
			System.out.println("Rol: "+e.getRol());
                        
            e.setNombre("Alejandro");
			e.setCorreo("cambio@hotmail.com");
			e.setEmpresa(emp2);
			e.setRol("asesor");
                        
			System.out.println("<<**************************************************>>");


			System.out.println("Employee parameters No."+counter2+" Editados");
			System.out.println("Nombre: "+e.getNombre());
			System.out.println("Correo:"+e.getCorreo());
			System.out.println("Empresa:"+(e.getEmpresa()).getNombre());
			System.out.println("Rol: "+e.getRol());
			System.out.println("<<----------------------------------------------->>");
			counter2++;
		}            
	}
}
