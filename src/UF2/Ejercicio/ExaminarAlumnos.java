package Ejercicio;

import java.util.*;

public class ExaminarAlumnos {
    public static void main(String[] args) {
        List<Alumne> listaAlumnos = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Alumne alumne = new Alumne("Alumno "+i);
            listaAlumnos.add(alumne);
        }
        for (int i = 0; i < listaAlumnos.size(); i++) {
            System.out.println(listaAlumnos.get(i).getNombre()+" "+listaAlumnos.get(i).examinar());
        }
    }
}
