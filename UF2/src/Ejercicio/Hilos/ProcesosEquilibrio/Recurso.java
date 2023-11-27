package Ejercicio.Hilos.ProcesosEquilibrio;

public class Recurso {

    public int contadorA;
    public int contadorB;

    static int N = 4;

    boolean utilizado;

    public synchronized void proporcionar(Object a) throws InterruptedException {
        while (contadorA+contadorB>N) wait();
        if (a instanceof ProcesoA){
            while (contadorB>=contadorA*2)wait();
        }
        utilizado = true;
        notifyAll();
    }

    public synchronized void liberar(){
        utilizado = false;
        notifyAll();
    }


    public void masContadorA() {
        contadorA++;
    }

    public void masContadorB() {
        contadorB++;
    }

    public void menosContadorA() {
        contadorA--;
    }

    public void menosContadorB() {
        contadorB--;
    }


}
