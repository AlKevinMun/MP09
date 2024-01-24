package UF2.Exemplos.Hilos.Mando;

public class Mando {
    private boolean llibre;
    public Mando(){
        llibre=true;
    }
    public synchronized void agarrar() {
        try{
            while (!llibre) wait();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        llibre = false;
        notifyAll();
    }

    public synchronized void dejar() {
        llibre = true;
        notifyAll();
    }
}
