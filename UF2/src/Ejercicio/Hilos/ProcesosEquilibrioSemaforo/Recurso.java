package Ejercicio.Hilos.ProcesosEquilibrioSemaforo;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Recurso {

    public int contadorA;
    public int contadorB;

    static int N = 5;

    Semaphore mutex = new Semaphore(N);
    Semaphore entradaA = new Semaphore(0);

    public void proporcionar(Object proceso) throws InterruptedException {
        System.out.println("El proceso "+proceso+" esta entrando");
        //System.out.println(proceso + " se ha de esperar porque contadores "+contadorA+"-"+contadorB+"->"+N);

        if (proceso instanceof ProcesoA){
            entradaA.acquire(2);
            /*
            System.out.println("Condicion" + contadorA*2 +"<"+contadorB);
            System.out.println(proceso + " se ha de esperar porque contadores "+contadorA+"-"+contadorB+"->"+N+" --- faltan Bs");
             */
            contadorA++;
            System.out.println("ENTRADO A " + proceso + "Procesos de B: "+ contadorB);
        } else {
            contadorB++;
            System.out.println("Ha entrado un proceso B");
            entradaA.release();
        }
        mutex.acquire();
    }

    public void liberar(Object proceso){
        if (proceso instanceof ProcesoA)contadorA--;
        else if (proceso instanceof ProcesoB) contadorB--;
        mutex.release();
    }

}
