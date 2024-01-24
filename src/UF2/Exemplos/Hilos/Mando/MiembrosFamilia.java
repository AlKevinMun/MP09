package UF2.Exemplos.Hilos.Mando;

public class MiembrosFamilia extends Thread {
    Mando mando;
    static int canal;

    public MiembrosFamilia(String nombre, Mando mando) {
        super(nombre);
        this.mando = mando;
    }

    @Override
    public void run() {
        for (;;){
            mando.agarrar();
            int canal2 = (int) (Math.random()*10) +1;
            System.out.println(getName()+" mirando la tele y el canal "+canal + " y cambio al " + canal2);
            canal = canal2;
            try {
                Thread.sleep((long) (Math.random()*800)+200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mando.dejar();
            try {
                Thread.sleep((long) (Math.random()*1000)+500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
