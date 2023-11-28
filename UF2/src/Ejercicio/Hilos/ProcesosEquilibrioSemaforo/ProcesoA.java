package Ejercicio.Hilos.ProcesosEquilibrioSemaforo;

public class ProcesoA extends Thread{

    Recurso recurso;

    public ProcesoA(String nombre, Recurso recurso) {
        super(nombre);
        this.recurso=recurso;
    }

    public void solicitar() throws InterruptedException {
        recurso.proporcionar(this);
        System.out.println("A: "+recurso.contadorA);
        System.out.println("El proceso" + getName() + " esta siendo utilizado");
        sleep((long) (Math.random()*1000+1000));
        }

    public void liberar() throws InterruptedException {
        recurso.liberar(this);
        System.out.println("El proceso" + getName() + " ha terminado");
        sleep((long) (Math.random()*1000+3000));
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

}
