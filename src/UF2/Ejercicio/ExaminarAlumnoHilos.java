package UF2.Ejercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExaminarAlumnoHilos {
    static class Alumne implements Callable<Integer> {
        private String nombre;

        public String getNombre() {
            return nombre;
        }

        public Alumne(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public Integer call() throws Exception {
            return (int)(Math.random()*10+1);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        List<Alumne> listaAlumnos = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            Alumne alumne = new Alumne("Alumno "+i);
            listaAlumnos.add(alumne);
        }

        List <Future<Integer>> llistaResultats;
        llistaResultats = executor.invokeAll(listaAlumnos);

        executor.shutdown();
        for (int i = 0; i < listaAlumnos.size(); i++) {
            Future<Integer> resultat = llistaResultats.get(i);
            System.out.println(listaAlumnos.get(i).getNombre()+" "+resultat.get());
        }
    }

}
