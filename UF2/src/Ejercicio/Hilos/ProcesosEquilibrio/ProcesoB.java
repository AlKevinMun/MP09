package Ejercicio.Hilos.ProcesosEquilibrio;

public class ProcesoB extends Thread{

    Recurso recurso;

    public ProcesoB(String nombre, Recurso recurso) {
        super(nombre);
        this.recurso=recurso;
    }

    public void solicitar() throws InterruptedException {
        recurso.proporcionar(this);
        recurso.masContadorB();
        System.out.println("B: "+recurso.contadorB);
        System.out.println("El proceso" + getName() + " esta siendo utilizado");
        sleep((long) (Math.random()*1000+3000));
    }
    public void liberar() throws InterruptedException {
        recurso.liberar();
        recurso.menosContadorB();
        System.out.println("El proceso" + getName() + " ha terminado");
        sleep((long) (Math.random()*1000+7000));
    }

    @Override
    public void run() {
        while (true){
            try {
                solicitar();
                liberar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }


    @Override
    public String toString() {
        return "ProcesoB{} " + super.toString();
    }
}
