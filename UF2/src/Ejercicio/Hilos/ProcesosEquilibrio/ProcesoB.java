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
    public void liberar(){
        recurso.liberar();
        recurso.menosContadorB();
        System.out.println("El proceso" + getName() + " ha terminado");
    }

    @Override
    public void run() {
        while (true){
            try {
                solicitar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            liberar();
        }
    }
}
