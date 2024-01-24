package UF2.Ejercicio.Hilos.Filosofos;

public class Filosofo extends Thread{
    Cubierto c1;
    Cubierto c2;

    public Filosofo(String nombre,Cubierto c1, Cubierto c2) {
        super(nombre);
        this.c1 = c1;
        this.c2 = c2;
    }
    public synchronized void comer() throws InterruptedException {
        c1.agarrarCubierto();
        c2.agarrarCubierto();
        Thread.sleep(((long) (Math.random()*2000)+500));
        c1.deixarCubierto();
        c2.deixarCubierto();
    }
    public synchronized void meditar() throws InterruptedException {

        Thread.sleep(((long) (Math.random()*2000)+1000));
    }

    @Override
    public void run() {
        for (;;){
            if (c1.isLliure() && c2.isLliure()){
                try {
                    comer();
                    System.out.println(getName()+" Esta comiendo");
                    Thread.sleep(((long) (Math.random()*2000)+2000));
                    meditar();
                    System.out.println(getName()+" Esta meditando tranquilamente");
                    Thread.sleep(((long) (Math.random()*5000)+5000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
