package UF2.Examen.Bateria;

public class DescarregarBateria extends Thread{
    Bateria bateria;

    public DescarregarBateria(Bateria bateria) {
        this.bateria = bateria;
    }

    @Override
    public void run() {
        while (bateria.getNivell()>0){
            bateria.descarregar();
            try {
                sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
