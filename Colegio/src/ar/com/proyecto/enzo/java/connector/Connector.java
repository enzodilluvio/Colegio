package ar.com.proyecto.enzo.java.connector;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {
    private static String driver="com.mysql.jdbc.Driver";
    private static String vendor="mysql";
    private static String server="localhost";
    private static String port="3306";
    private static String base="colegio";
    private static String user="root";
    private static String pass="";
    
    private static String url="jdbc:"+vendor+"://"+server+":"+port+"/"+base;
    
    private static Connection conn=null;
    
    public static Connection getConnection(){
        if(conn==null){
            try {
                Class.forName(driver);
                conn=DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return conn;
    }
}
