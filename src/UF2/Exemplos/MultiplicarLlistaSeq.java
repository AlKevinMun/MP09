package Exemplos;
import java.util.*;
public class MultiplicarLlistaSeq {

    public static void main(String[] args) {
        List<MultiplicacioSeq> llistaTasques = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            MultiplicacioSeq calcula = new MultiplicacioSeq((int)(Math.random()*10), (int)(Math.random()*10));
            llistaTasques.add(calcula);
        }

        List<Integer> llistaResultats = new ArrayList<>();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < llistaTasques.size(); i++) {
            llistaResultats.add(llistaTasques.get(i).multiplicar());
        }
        long t2 = System.currentTimeMillis();

        for (int i = 0; i < llistaResultats.size(); i++) {
            System.out.printf("Resultat %d = %d%n",i,llistaResultats.get(i));
        }
        System.out.printf("Ha tardado %d", t2-t1);
    }
}
