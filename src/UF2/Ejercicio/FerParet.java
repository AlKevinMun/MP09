package UF2.Ejercicio;
import java.util.concurrent.*;

public class FerParet implements Runnable {

    public static void main(String[] args) {
        int numMaons = 10, ti, te;
        int numPaletes = 5;

        //instanciem els paletes
        Paleta[] P = new Paleta[numPaletes];
        // Creem un executor
        ExecutorService executor = Executors.newFixedThreadPool(numPaletes);

        //comencem a contar el temps
        ti = (int) System.currentTimeMillis();
        //Donem nom als paletes i els posem a fer fer la paret
        for (int i=0;i<numPaletes;i++) {
            P[i] = new Paleta("Paleta-"+i);
            P[i].posaMaons(numMaons);
            executor.execute(P[i]);
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
            // While per a que tots el demons terminen.
        }

        //Han acabat i agafem el temps final
        te = (int) System.currentTimeMillis();

        System.out.println("Han trigat: " + (te - ti)/1000 + " segons");

    }

    @Override
    public void run() {

    }
}