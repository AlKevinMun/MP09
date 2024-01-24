package UF2.Ejercicio.Hilos.Galletas;

public class PadreFamilia extends Thread {
    PotGaletes pot;

    public PadreFamilia(String nombre, PotGaletes pot) {
        super(nombre);
        this.pot = pot;
    }

    @Override
    public void run() {
        for (;;){
            try{
                pot.agarrarPot();
                pot.omplirPot();
                System.out.println(getName()+" Ha agregat galetes fins deixar " + pot.getGaletes());
                Thread.sleep((long) (Math.random()*800)+200);
                pot.deixarPot();
                Thread.sleep((long) (Math.random()*2000)+500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
