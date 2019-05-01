package ar.com.proyecto.enzo.java.util;

import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Validator {
    private JTextField  swTxt=null;
    private TextField   fxTxt=null;

    public Validator(JTextField swTxt) {
        this.swTxt = swTxt;
    }

    public Validator(TextField fxTxt) {
        this.fxTxt = fxTxt;
    }
    
    private String getText(){
        if(swTxt!=null) return swTxt.getText();
        if(fxTxt!=null) return fxTxt.getText();
        return null;
    }
    
    private void requestFocus(){
        if(swTxt!=null) swTxt.requestFocus();
        if(fxTxt!=null) fxTxt.requestFocus();
    }
    
    public boolean isInteger(){
        try {
            Integer.parseInt(getText());
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                    "Debe ingresar un número entero.");
            requestFocus();
            return false;
        }
    }
    public boolean isInteger(int min,int max){
        if(!isInteger()) return false;
        int nro=Integer.parseInt(getText());
        if(nro>=min && nro<=max) return true;
        JOptionPane.showMessageDialog(null, 
                "Debe ingresar un número entero entre "+min+" y "+max);
        requestFocus();
        return false;
    }
    public boolean size(int size){
        if(getText().length()==size) return true;
        JOptionPane.showMessageDialog(null, 
                "Debe tener una longitud de "+size+" caracteres.");
        requestFocus();
        return false;
    }
    public boolean size(int min,int max){
        if(getText().length()>=min && getText().length()<=max) return true;
        JOptionPane.showMessageDialog(null, 
                "Debe tener una longitud entre "+min+" y "+max+" caracteres.");
        requestFocus();
        return false;
    }
}
