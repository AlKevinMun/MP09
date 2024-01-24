package UF3.actividades.Tasca3;

import java.util.ArrayList;
import java.util.List;

public class Palabras {

    /* Classe que gestiona una dada de velocitat (int) aleatòria, per implementar l'exemple de Multicast:
     * ClientVelocimetre1.java
     * ClientVelocimetre2.java
     * SrvVelocitats.java
     */

    List<String> palabras = new ArrayList<>();
    public Palabras() {
        palabras.add("Alejandro");
        palabras.add("Chino");
        palabras.add("Amarillo");
        palabras.add("Negro");
        palabras.add("Blanco");
        palabras.add("Kevin");
    }

    public String recogerPalabra() {;
        return palabras.get((int)(Math.random()*5));
    }

}