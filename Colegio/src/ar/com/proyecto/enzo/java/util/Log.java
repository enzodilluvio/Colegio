package ar.com.proyecto.enzo.java.util;

import java.net.InetAddress;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class Log {
    private static String file="log.csv";
    public static void set(Exception ex){
        LocalDateTime ldt=LocalDateTime.now();
        InetAddress inet=null;
        try {
            inet=InetAddress.getLocalHost();
        } catch (Exception e) {
            System.out.println(e);
        }
        String user=System.getProperty("user.name");
        String os=System.getProperty("os.name")+" "+
                System.getProperty("os.version")+" "+
                System.getProperty("os.arch");
        String registro=ldt+";"+inet+";"+user+";"+os+";"+ex;
        for(Object obj:ex.getStackTrace()) registro+=obj+";";
        new FileUtil(file).addLine(registro);
    }
    public static void setC(Exception ex){
        set(ex);
        System.out.println("Ocurrio un error!");
    }
    public static void setJ(Exception ex){
        set(ex);
        JOptionPane.showMessageDialog(null, "Ocurrio un error", "Error", 0);
    }
}
