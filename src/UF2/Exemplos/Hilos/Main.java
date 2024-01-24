package Exemplos.Hilos;

public class Main {
    public static void main(String[] args) {
        Fil fil1 = new Fil("f1");
        Fil fil2 = new Fil("f2");
        fil1.start();
        fil2.start();

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread anonimo");
            }
        });
        th1.start();

        Thread th2 = new Thread(() -> System.out.println("th2 anònim"));
        th2.start();
    }
}
