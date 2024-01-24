package UF2.Ejercicio.Hilos.Galletas;

public class PotGaletes {
    private boolean lliure;
    private int galetes;
    private int max = 25;
    public PotGaletes(){
        lliure = true;
        galetes =10;
    }
    public synchronized void agarrarPot() {
        try{
            while (!lliure) wait();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        lliure = false;
        notifyAll();
    }
    public synchronized void agarrarGaletes(int galetes){
        try{
            while (this.galetes<galetes) wait();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        this.galetes -= galetes;
        notifyAll();
    }
    public synchronized void deixarPot() {
        lliure = true;
        notifyAll();
    }
    public synchronized void omplirPot() {
        try{
            while (galetes==max)wait();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        for (int i = 0; i < max; i++) {
           if(galetes<max) galetes++;
        }
        notifyAll();
    }

    public int getGaletes() {
        return galetes;
    }

}
