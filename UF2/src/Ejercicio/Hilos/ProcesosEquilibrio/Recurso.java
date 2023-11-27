package Ejercicio.Hilos.ProcesosEquilibrio;

public class Recurso {

    public int contadorA;
    public int contadorB;

    static int N = 4;

    boolean utilizado;

    public synchronized void proporcionar(Object a) throws InterruptedException {
        System.out.println(a+" pide entrar");
        while (contadorA+contadorB>N) {
            System.out.println(a + " se ha de esperar porque contadores "+contadorA+"-"+contadorB+"->"+N);
           wait();
        }
        if (a instanceof ProcesoA){
            while (contadorA*2<contadorB) {
                wait();
                System.out.println("Condicion" + contadorA*2 +"<"+contadorB);
                System.out.println(a + " se ha de esperar porque contadores "+contadorA+"-"+contadorB+"->"+N+" --- faltan Bs");
            }
            System.out.println("ENTRADO A");
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
