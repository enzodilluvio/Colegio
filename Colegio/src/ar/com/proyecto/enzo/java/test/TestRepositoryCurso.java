package ar.com.proyecto.enzo.java.test;

import ar.com.proyecto.enzo.java.connector.Connector;
import ar.com.proyecto.enzo.java.entities.Curso;
import ar.com.proyecto.enzo.java.repositories.CursoR;

public class TestRepositoryCurso {
    public static void main(String[] args) {
        CursoR cr=new CursoR(Connector.getConnection());
        Curso curso=new Curso("Java", "Ríos", "Lunes", "Mañana");
        cr.save(curso);
        System.out.println(curso);
        
        cr.remove(cr.getById(2));
        
        curso=cr.getById(3);
        curso.setTitulo("Php");
        curso.setDia("Martes");
        cr.update(curso);
        System.out.println("------------------------------------------");
        cr.getAll().forEach(System.out::println);
    }
}
