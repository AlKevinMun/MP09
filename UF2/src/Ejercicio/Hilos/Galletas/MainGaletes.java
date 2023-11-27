package Ejercicio.Hilos.Galletas;

public class MainGaletes {
    public static void main(String[] args) {

        PotGaletes pot = new PotGaletes();
        PadreFamilia padre = new PadreFamilia("Pepe",pot);
        HijosFamilia hijo = new HijosFamilia("Juan",pot);
        padre.start();
        hijo.start();
    }

}
