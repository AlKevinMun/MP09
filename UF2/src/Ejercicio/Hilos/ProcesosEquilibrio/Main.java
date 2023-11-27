package Ejercicio.Hilos.ProcesosEquilibrio;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static Lock lockA = new ReentrantLock();

    public static void main(String[] args) {

        int cantidadA = 2;
        int cantidadB = 4;

        Recurso recurso = new Recurso();

        ProcesoA a1 = new ProcesoA(" A1",recurso);
        ProcesoA a2 = new ProcesoA(" A2",recurso);

        ProcesoB b1 = new ProcesoB(" B1",recurso);
        ProcesoB b2 = new ProcesoB(" B2",recurso);
        ProcesoB b3 = new ProcesoB(" B3",recurso);
        ProcesoB b4 = new ProcesoB(" B4",recurso);

        a1.start();
        a2.start();

        b1.start();
        b2.start();
        b3.start();
        b4.start();

    }
}
