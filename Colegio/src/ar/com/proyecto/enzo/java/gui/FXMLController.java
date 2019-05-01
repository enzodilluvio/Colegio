package ar.com.proyecto.enzo.java.gui;

import ar.com.proyecto.enzo.java.connector.Connector;
import ar.com.proyecto.enzo.java.entities.Alumno;
import ar.com.proyecto.enzo.java.entities.Curso;
import ar.com.proyecto.enzo.java.repositories.AlumnoR;
import ar.com.proyecto.enzo.java.repositories.CursoR;
import ar.com.proyecto.enzo.java.util.FxTable;
import ar.com.proyecto.enzo.java.util.Validator;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;


public class FXMLController implements Initializable {

    private Connection conn;
    private AlumnoR ar;
    private CursoR cr;
    
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtEdad;
    @FXML
    private Label lblInfo;
    @FXML
    private ComboBox<Curso> cmbCursos;
    @FXML
    private TableView<Alumno> tblAlumnos;
    @FXML
    private TextField txtBuscarApe;
    
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtProfesor;
    @FXML
    private TextField txtDia;
    @FXML
    private TextField txtTurno;
    @FXML
    private Label lblInfo1;
    @FXML
    private TextField txtBuscarCurso;
    @FXML
    private TableView<Curso> tblCurso;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn=Connector.getConnection();
        ar=new AlumnoR(conn);
        cr=new CursoR(conn);
        
        cargar();
        cargarCurso();
    }    
        
    private void cargar() {

        cmbCursos.getItems().clear();
        List<Curso>listaCursos=cr.getAll();
        if(!listaCursos.isEmpty()){
            cmbCursos.getItems().addAll(listaCursos);
            cmbCursos.setValue(listaCursos.get(0));
    }
        FxTable<Alumno> table=new FxTable();
        table.cargar(ar.getAll(), tblAlumnos);
    }
    
    @FXML
    private void Agregar(ActionEvent event) {
        if(validarAlumnos()){
        Alumno alumno=new Alumno(
                txtNombre.getText(),
                txtApellido.getText(),
                Integer.parseInt(txtEdad.getText()),
                cmbCursos.getValue().getId()
        );
        ar.save(alumno);
        lblInfo.setText("Se ingresó el alumno id = "+alumno.getId());
        limpiarAlumnos();
        cargar();
    }else{
            lblInfo.setText("No se pudo ingresar un alumno");
        }
    }
    private boolean validarAlumnos(){
        if(!new Validator(txtNombre).size(2, 20)) return false;     
        if(!new Validator(txtApellido).size(2, 20)) return false;       
        if(!new Validator(txtEdad).isInteger(18, 120)) return false;
        return true;
    }
    private void limpiarAlumnos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtNombre.requestFocus();
    }

    @FXML
    private void buscarApe(KeyEvent event) {
        FxTable<Alumno> table=new FxTable();
        table.cargar(ar.getLikeApellido(txtBuscarApe.getText()), tblAlumnos);
    }
        
        @FXML
    private void BorrarAlumno(ActionEvent event) {
        
        Alumno alumno=tblAlumnos.getSelectionModel().getSelectedItem();
        if(alumno==null) return;
        if(JOptionPane.showConfirmDialog(null, "Está seguro de borrar?","Borrar",1)==0)
            ar.remove(alumno);
            cargar();
    }
    
    private void cargarCurso() {
            
        tblCurso.getItems().clear();
        List<Curso>listaCursos=cr.getAll();
        if(!listaCursos.isEmpty()){
            tblCurso.getItems().addAll(listaCursos);
            cmbCursos.getItems().addAll(listaCursos);
            cmbCursos.setValue(listaCursos.get(0));
    }
        FxTable<Curso> table=new FxTable();
        table.cargar(cr.getAll(), tblCurso);
    }
    
    @FXML
    private void AgregarCurso(ActionEvent event) {
        if(validarCursos()){
        Curso curso=new Curso(
                txtTitulo.getText(),
                txtProfesor.getText(),
                txtDia.getText(),
                txtTurno.getText()
        );
        cr.save(curso);
        lblInfo1.setText("Se ingresó el curso id = "+curso.getId());
        limpiarCursos();
        cargarCurso();
    }else{
            lblInfo1.setText("No se pudo ingresar el curso");
        }
    }
    
    private boolean validarCursos(){
        if(!new Validator(txtTitulo).size(2, 20)) return false;     
        if(!new Validator(txtProfesor).size(2, 50)) return false;       
        if(!new Validator(txtDia).size(2, 20)) return false;
        if(!new Validator(txtTurno).size(2, 20)) return false;
        return true;
    }
    
    private void limpiarCursos(){
        txtTitulo.setText("");
        txtProfesor.setText("");
        txtDia.setText("");
        txtTurno.setText("");
        txtTitulo.requestFocus();
    }
    
    @FXML
    private void buscarCurso(KeyEvent event) {
        FxTable<Curso> table=new FxTable();
        table.cargar(cr.getLikeTitulo(txtBuscarCurso.getText()), tblCurso);
    }
    
    @FXML
    private void BorrarCurso(ActionEvent event) {

        Curso curso=tblCurso.getSelectionModel().getSelectedItem();
        if(curso==null) return;
        if(JOptionPane.showConfirmDialog(null, "Está seguro de borrar?","Borrar",1)==0)
            cr.remove(curso);
            cargarCurso();
    }
}
