package ar.com.proyecto.enzo.java.repositories;

import ar.com.proyecto.enzo.java.entities.Curso;
import ar.com.proyecto.enzo.java.util.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoR {
    private Connection conn;
    
    public CursoR(Connection conn){
        this.conn = conn;
    }
  

    public CursoR() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void save (Curso curso){
        try {
            PreparedStatement ps=conn.prepareStatement(
            "insert into cursos (titulo,profesor,dia,turno) values (?,?,?,?)",1
            );
            ps.setString(1, curso.getTitulo());
            ps.setString(2, curso.getProfesor());
            ps.setString(3, curso.getDia());
            ps.setString(4, curso.getTurno());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()) curso.setId(rs.getInt(1));
        } catch (Exception e) {
            Log.setC(e);
        }
    }
    public void remove (Curso curso){
        if(curso==null) return;
        try {
            conn.createStatement().execute(
                    "delete from cursos where id="+curso.getId()
            );
        } catch (Exception e) {
            Log.setC(e);
        }
    }
    public void update (Curso curso){
        if(curso==null) return;
        try {
            PreparedStatement ps=conn.prepareStatement(
            "update cursos set titulo=?, profesor=?, dia=?, turno=?"
                    + " where id=?"
            );
            ps.setString(1, curso.getTitulo());
            ps.setString(2, curso.getProfesor());
            ps.setString(3, curso.getDia());
            ps.setString(4, curso.getTurno());
            ps.setInt(5, curso.getId());
            ps.execute();
        } catch (Exception e) {
            Log.setC(e);
        }
        
    }
    public Curso getById(int id){
        List<Curso> lista=getByFiltro("id="+id);
        return (lista==null || lista.isEmpty())?null:lista.get(0);
    }
    public List<Curso> getAll(){
        return getByFiltro("1=1");
    }
    public List<Curso> getByFiltro(String filtro){
        List<Curso>lista=new ArrayList();
        try {
            ResultSet rs=conn.createStatement().executeQuery(
                    "select * from cursos where "+filtro);
            while(rs.next()){
                lista.add(
                new Curso(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("profesor"),
                rs.getString("dia"),
                rs.getString("turno")
                )
                );
            }
        } catch (Exception e) {
            Log.set(e);
        }
        return lista;
    }
    public List<Curso> getByTitulo(String titulo){
        return getByFiltro("titulo='"+titulo+"'");
    }
    
    public List<Curso> getLikeTitulo(String titulo){
        return getByFiltro("titulo like '%"+titulo+"%'");
    }
    
    public List<Curso> getByProfesor(String profesor){
        return getByFiltro("profesor='"+profesor+"'");
    }
    public List<Curso> getByDia(String dia){
        return getByFiltro("dia='"+dia+"'");
    }
    public List<Curso> getByTurno(String turno){
        return getByFiltro("turno='"+turno+"'");
    }
    
}
