package Ejercicio.Hilos.ProcesosEquilibrio;

public class ProcesoA extends Thread{

    Recurso recurso;

    public ProcesoA(String nombre, Recurso recurso) {
        super(nombre);
        this.recurso=recurso;
    }

    public void solicitar() throws InterruptedException {
        recurso.proporcionar(this);
        recurso.masContadorA();
        System.out.println("A: "+recurso.contadorA);
        System.out.println("El proceso" + getName() + " esta siendo utilizado");
        sleep((long) (Math.random()*1000+3000));
        }

    public void liberar(){
        recurso.liberar();
        recurso.menosContadorA();
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
