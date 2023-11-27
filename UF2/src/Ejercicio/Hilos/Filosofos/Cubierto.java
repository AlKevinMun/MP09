package Ejercicio.Hilos.Filosofos;

import javax.swing.table.TableRowSorter;

public class Cubierto {
    private boolean lliure;

    public Cubierto() {
        this.lliure = true;
    }

    public boolean isLliure() {
        return lliure;
    }

    public synchronized void agarrarCubierto (){
        try{
            while (!lliure) wait();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        lliure = false;
        notifyAll();
    }
    public synchronized void deixarCubierto(){
        lliure = true;
        notifyAll();
    }






}
