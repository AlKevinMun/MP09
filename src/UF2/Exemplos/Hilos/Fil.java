package Exemplos.Hilos;

public class Fil extends Thread{

    public Fil(String name){
        super(name);
    }
    @Override
    public void run() {
        System.out.println("Soy el hilo:"+getName());
    }
}
