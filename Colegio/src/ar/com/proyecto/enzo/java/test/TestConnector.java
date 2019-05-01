package ar.com.proyecto.enzo.java.test;

import ar.com.proyecto.enzo.java.connector.Connector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnector {
    public static void main(String[] args) throws Exception{
        Connection conn=Connector.getConnection();
        String query="insert into alumnos (nombre, apellido, edad, curso) values"
                +"('Gabriela','Guilon',22,1)";
        
        Statement stm=conn.createStatement();
        stm.execute(query);
        
        Connector.getConnection().createStatement().execute(
                "insert into alumnos (nombre, apellido, edad, curso) values"
                    +"('Mariela','Casas',31,1)"
        );

        int x=Connector.getConnection().createStatement().executeUpdate(
                "insert into alumnos (nombre, apellido, edad, curso) values"
                +"('Karina','Vazques',38,2)"
        );
        System.out.println("Se insertó "+x+" alumno.");

        x=Connector.getConnection().createStatement().executeUpdate(
                "delete from alumnos where id=1"
        );
        System.out.println("Se borró "+x+" alumno.");

        query="select * from alumnos";
        ResultSet rs=Connector.getConnection().createStatement()
                .executeQuery(query);
        
        while(rs.next()){
            System.out.println(
                    rs.getInt("id")+" "+
                    rs.getString("nombre")+" "+
                    rs.getString("apellido")+" "+
                    rs.getInt("edad")+" "+
                    rs.getInt("curso")
            );
        }
        
        
    }
    
}
