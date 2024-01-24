package UF2.Exemplos;
import java.util.*;
import java.util.concurrent.*;

public class MultiplicaLlista {

    static class Multiplicacio implements Callable<Integer> {
        private int operador1;
        private int operador2;
        public Multiplicacio(int operador1, int operador2) {
            this.operador1 = operador1;
            this.operador2 = operador2;
        }
        @Override
        public Integer call() throws Exception {
            return operador1 * operador2;
        }
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        List<Multiplicacio> llistaTasques= new ArrayList<Multiplicacio>();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Multiplicacio calcula = new Multiplicacio((int)(Math.random()*10), (int)(Math.random()*10));
            llistaTasques.add(calcula);
        }
        long t2 = System.currentTimeMillis();
        List <Future<Integer>> llistaResultats;
        llistaResultats = executor.invokeAll(llistaTasques);

        executor.shutdown();
        System.out.printf("Ha tardado %d", t2-t1);
        for (int i = 0; i < llistaResultats.size(); i++) {
            Future<Integer> resultat = llistaResultats.get(i);
            try {
                System.out.println("Resultat tasca "+i+ " és:" + resultat.get());
            } catch (InterruptedException | ExecutionException e) {
            }
        }

    }
}
