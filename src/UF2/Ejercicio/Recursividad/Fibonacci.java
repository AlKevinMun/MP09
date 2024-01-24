package UF2.Ejercicio.Recursividad;

import java.util.concurrent.ForkJoinPool;

public class Fibonacci {

    public Fibonacci() {}

    public static void main(String[] args) {
        int n=7;
        ForkJoinPool pool = new ForkJoinPool();

        FibonacciTask tasca = new FibonacciTask(n);

        // crida a la tasca i esperar que es completin
        pool.invoke(tasca);
        long result = tasca.join();

        System.out.println("Resultat: " + result);

    }

}
