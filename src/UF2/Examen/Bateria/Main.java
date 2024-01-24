package UF2.Examen.Bateria;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bateria bateria = new Bateria(100);
        Menu menu = new Menu(bateria);
        int opcion = 1;
        Scanner scanner = new Scanner(System.in);
        while(opcion!=0){

            menu.mostrarMenu();
            opcion = scanner.nextInt();
            menu.escogerOpcion(opcion);

        }
    }
}
