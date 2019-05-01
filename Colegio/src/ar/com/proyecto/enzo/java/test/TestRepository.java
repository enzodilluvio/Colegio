package ar.com.proyecto.enzo.java.test;

import ar.com.proyecto.enzo.java.connector.Connector;
import ar.com.proyecto.enzo.java.entities.Alumno;
import ar.com.proyecto.enzo.java.repositories.AlumnoR;

public class TestRepository {
    public static void main(String[] args) {
        AlumnoR ar=new AlumnoR(Connector.getConnection());
        Alumno alumno=new Alumno("Patricio","Rey",22,1);
        ar.save(alumno);
        System.out.println(alumno);
        
        System.out.println("----------------------------------");
        
        ar.getAll().forEach(System.out::println);   
        
        System.out.println("----------------------------------");
        
        ar.getByApellido("Rey").forEach(System.out::println);

        System.out.println("----------------------------------");
        alumno=ar.getById(3);
        System.out.println(alumno);
        
        System.out.println("----------------------------------");
        
        ar.remove(ar.getById(4));
        
        alumno=ar.getById(2);
        alumno.setNombre("Cecilia");
        alumno.setApellido("Molina");
        ar.update(alumno);
    }
}
