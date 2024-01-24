package UF2.Examen.Bateria;

public class CarrgarBateria extends Thread {
    Bateria bateria;

    public CarrgarBateria(Bateria bateria) {
        this.bateria = bateria;
    }

    @Override
    public void run() {
        while (bateria.getNivell() <= 100) {
            bateria.carregar();
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
