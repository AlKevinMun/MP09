package UF2.Ejercicio.Hilos.ProcesosEquilibrio;

import static java.lang.Thread.sleep;

public class Recurso {

    public int contadorA;
    public int contadorB;

    static int N = 4;

    boolean utilizado;

    public synchronized void proporcionar(Object proceso) throws InterruptedException {
        while (contadorA+contadorB>N) {
            System.out.println(proceso + " se ha de esperar porque contadores "+contadorA+"-"+contadorB+"->"+N);
            wait();
        }
        if (proceso instanceof ProcesoA){
            while (contadorA*2<contadorB) {
                wait();
                System.out.println("Condicion" + contadorA*2 +"<"+contadorB);
                System.out.println(proceso + " se ha de esperar porque contadores "+contadorA+"-"+contadorB+"->"+N+" --- faltan Bs");
            }
            contadorA++;
            System.out.println("ENTRADO A");
        }
        else if ( proceso instanceof ProcesoB) contadorB++;
        notifyAll();
    }

    public synchronized void liberar(Object proceso){
        if (proceso instanceof ProcesoA)contadorA--;
        else if (proceso instanceof ProcesoB) contadorB--;
        notifyAll();
    }



}
