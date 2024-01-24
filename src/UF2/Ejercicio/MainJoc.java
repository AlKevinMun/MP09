package UF2.Ejercicio;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainJoc {
    public static class Jugador{
        private String nom;

        private int punts;

        public void setNom(String nom) {
            this.nom = nom;
        }

        public void setPunts(int punts) {
            this.punts = punts;
        }

        public String getNom() {
            return nom;
        }

        public int getPunts() {
            return punts;
        }

        public Jugador(String nom) {
            this.nom = nom;
        }
    }

    public static class DonarPunts implements Runnable{
        Jugador jugador;
    public DonarPunts(Jugador jugador){
        this.jugador = jugador;
    }
        @Override
        public void run() {
        jugador.setPunts(jugador.getPunts()+(int)(Math.random()*10+1));
        }
    }

    public static class LlegirPunts implements Runnable{
        Jugador jugador;

        public LlegirPunts(Jugador jugador) {
            this.jugador = jugador;
        }
        @Override
        public void run() {
            System.out.println("El " + jugador.getNom()+" te un total de "+jugador.getPunts());
        }
    }

    public static void main(String[] args) {
        Jugador jugador1 = new Jugador("Jugador 1");
        Jugador jugador2 = new Jugador("Jugador 2");

        DonarPunts dJugador1 = new DonarPunts(jugador1);
        DonarPunts dJugador2 = new DonarPunts(jugador2);

        LlegirPunts lJugador1 = new LlegirPunts(jugador1);
        LlegirPunts lJugador2 = new LlegirPunts(jugador2);

        final ScheduledExecutorService schExService = Executors.newScheduledThreadPool(2);

        schExService.scheduleWithFixedDelay(dJugador1, 1, 3, TimeUnit.SECONDS);
        schExService.scheduleWithFixedDelay(dJugador2, 5, 2, TimeUnit.SECONDS);

        schExService.scheduleWithFixedDelay(lJugador1, 1, 5, TimeUnit.SECONDS);
        schExService.scheduleWithFixedDelay(lJugador2, 1, 5, TimeUnit.SECONDS);

        try {
            schExService.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        schExService.shutdownNow();
        System.out.println("Completat");

        System.out.println(jugador1.getPunts());
        System.out.println(jugador2.getPunts());


    }
}
