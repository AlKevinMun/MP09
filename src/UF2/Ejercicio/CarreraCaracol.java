package Ejercicio;

import Exemplos.TascaProgramada;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CarreraCaracol {
    static class Caracol implements Runnable{
        private int metros;

        public int getMetros() {
            return metros;
        }

        @Override
        public void run() {
            metros +=(int)(Math.random()*10+1);
        }
    }

    public static class MonitorCargol implements Runnable{
        private Caracol Caracol;
        public MonitorCargol(Caracol cargol){
            this.Caracol = cargol;
        }
        @Override
        public void run() {
            System.out.printf("%s porta %d metres acumulats %n","Cargol1",Caracol.getMetros());
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Caracol caracol1 = new Caracol();
        Caracol piedra = new Caracol();
        MonitorCargol mCaracol1 = new MonitorCargol(caracol1);
        MonitorCargol mPiedra = new MonitorCargol(piedra);

        final ScheduledExecutorService schExService = Executors.newScheduledThreadPool(2);

        schExService.scheduleWithFixedDelay(caracol1, 3, 5, TimeUnit.SECONDS);

        // Programa Fil, s'inicia als 2 segons i després es va executant cada 3 segons
        schExService.scheduleWithFixedDelay(piedra, 4, 4, TimeUnit.SECONDS);

        schExService.scheduleWithFixedDelay(mCaracol1, 1, 10, TimeUnit.SECONDS);
        schExService.scheduleWithFixedDelay(mPiedra, 1, 10, TimeUnit.SECONDS);

        schExService.awaitTermination(30, TimeUnit.SECONDS);
        // shutdown .
        schExService.shutdownNow();
        System.out.println("Completat");
        System.out.println(caracol1.getMetros());
        System.out.println(piedra.getMetros());
    }
}
