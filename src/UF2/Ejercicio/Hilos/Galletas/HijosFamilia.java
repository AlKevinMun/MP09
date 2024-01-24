package UF2.Ejercicio.Hilos.Galletas;

public class HijosFamilia extends Thread {
    PotGaletes pot;

    public HijosFamilia(String nombre, PotGaletes pot) {
        super(nombre);
        this.pot = pot;
    }

    @Override
    public void run() {
        for (;;){
            try {
                pot.agarrarPot();
                System.out.println(getName()+" Ha agafat galetes fins deixar "+ pot.getGaletes());
                pot.agarrarGaletes((int) (Math.random()*10)+1);
                Thread.sleep((long) (Math.random()*800)+200);
                pot.deixarPot();
                Thread.sleep((long) (Math.random()*2000)+500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
