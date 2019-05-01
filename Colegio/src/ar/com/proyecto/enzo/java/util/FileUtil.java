package ar.com.proyecto.enzo.java.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class FileUtil implements I_File{
    
    private File file;

    public FileUtil(File file) {
        this.file = file;
    }

    public FileUtil(String file) {
        this.file=new File(file);
    }
    

    @Override
    public void print() {
        try {

            new BufferedReader(new FileReader(file))
                    .lines()
                    .forEach(item->System.out.println(item));
            
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado.");
        }catch(IOException e){
            System.out.println("Error IO");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private String texto;
    private StringBuilder sb;
    @Override
    public String getText() {

        try {
            sb=new StringBuilder();
            new BufferedReader(new FileReader(file))
                    .lines()
                    .forEach(item->sb.append(item+"\n"));
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado.");
        }catch (Exception e) {
            System.out.println(e);
        }
        return sb.toString();
    }

    @Override
    public void setText(String text) {
        try {
            BufferedWriter out=new BufferedWriter(new FileWriter(file));
            out.write(text);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void append(String text) {
        try {
            BufferedWriter out=new BufferedWriter(new FileWriter(file,true));
            out.write(text);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void addLine(String line) {
        append(line+"\n");
    }
    
    private BufferedReader in;
    public void open(){
        try{
            in=new BufferedReader(new FileReader(file));
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public String getLine(){
        try {
            return in.readLine();
        } catch (Exception e) {
            return null;
        }
    }
    public void close(){
        try{
            in.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private List<String>lista;

    @Override
    public List<String> getLines() {
        lista=new ArrayList();
        try {
            new BufferedReader(new FileReader(file)).lines().forEach(item->lista.add(item));
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    Set<String>listaSet;
    @Override 
    public Set<String> getSetLines(){
        listaSet=new LinkedHashSet();
        listaSet.addAll(getLines());
        return listaSet;
    }
    
    @Override
    public void removeLine(String line) {
        List<String>lista=getLines();
        lista.remove(line);
        setText("");
        addLines(lista);
    }

    @Override
    public void addLines(Collection<String> lines) {
        lines.forEach(item->addLine(item));
    }

    @Override
    public void addLines(String... lines) {
        for(String st:lines) addLine(st);
    }
    
}
