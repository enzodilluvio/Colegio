package ar.com.proyecto.enzo.java.repositories;

import ar.com.proyecto.enzo.java.entities.Alumno;
import ar.com.proyecto.enzo.java.util.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnoR {
    private Connection conn;

    public AlumnoR(Connection conn) {
        this.conn = conn;
    }
    
    public void save(Alumno alumno){
        if (alumno==null) return;
        try {

            PreparedStatement ps=conn.prepareStatement(
                    "insert into alumnos(nombre, apellido, edad, curso) values (?,?,?,?)",1
            ); 
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getEdad());
            ps.setInt(4, alumno.getCurso());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()) alumno.setId(rs.getInt(1)); 
            
        } catch (Exception e) {
            Log.setC(e);
        }
        }
    
        public void remove(Alumno alumno){
            if(alumno==null) return;
            try {
                conn.createStatement().execute("delete from alumnos where id="+alumno.getId()
                );
            } catch (Exception e) {
            Log.setC(e);
            }
        
        }
        public void update(Alumno alumno){
            if(alumno==null) return;
            try {
                PreparedStatement ps=conn.prepareStatement(
                "update alumnos set nombre=?, apellido=?, edad=?, curso=? "+ " where id=?"
                );
                ps.setString(1, alumno.getNombre());
                ps.setString(2, alumno.getApellido());
                ps.setInt(3, alumno.getEdad());
                ps.setInt(4, alumno.getCurso());
                ps.setInt(5, alumno.getId());
                ps.execute();
                
            } catch (Exception e) {
                Log.setC(e);
            }
        }
        public Alumno getById(int id){
           List<Alumno>lista=getByFiltro("id="+id);
           return(lista==null || lista.isEmpty()) ? null:lista.get(0);
        }
        public List<Alumno> getAll(){
            return getByFiltro("1=1");
        }
        private List<Alumno> getByFiltro(String filtro){
            List<Alumno> lista=new ArrayList();
            try {
                ResultSet rs=conn.createStatement().executeQuery(
                        "select * from alumnos where "+filtro
                );
                while(rs.next()){
                    lista.add(new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad"),
                        rs.getInt("curso")
                    ));
                }
            } catch (Exception e) {
                System.out.println(e);
                Log.setC(e);
            }
            return lista;
        }
        public List<Alumno> getByApellido(String apellido){
            return getByFiltro("apellido='"+apellido+"'");   
        }
        
        public List<Alumno>getLikeApellido(String apellido){
            return getByFiltro("apellido like '%"+apellido+"%'");   
        }
    }
        
    
    

