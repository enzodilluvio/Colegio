package ar.com.proyecto.enzo.java.entities;

public class Curso {
    private int id;
    private String titulo;
    private String profesor;
    private String dia;
    private String turno;

    public Curso() {
    }

    public Curso(String titulo, String profesor, String dia, String turno) {
        this.titulo = titulo;
        this.profesor = profesor;
        this.dia = dia;
        this.turno = turno;
    }

    public Curso(int id, String titulo, String profesor, String dia, String turno) {
        this.id = id;
        this.titulo = titulo;
        this.profesor = profesor;
        this.dia = dia;
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "id= " + id + ", titulo= " + titulo + ", profesor= " + profesor + ", dia= " + dia + ", turno= " + turno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
    
    
    
}
