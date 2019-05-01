package ar.com.proyecto.enzo.java.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface I_File {
    void print();
    String getText();
    void setText(String text);
    void append(String text);
    void addLine(String line);
    List<String>getLines();
    Set<String>getSetLines();
    void removeLine(String line);
    void addLines(Collection<String>lines);
    void addLines(String... lines);
}
