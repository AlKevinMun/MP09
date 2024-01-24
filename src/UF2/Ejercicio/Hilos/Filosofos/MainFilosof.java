package UF2.Ejercicio.Hilos.Filosofos;

public class MainFilosof {

    public static void main(String[] args) {
        Cubierto c1 = new Cubierto();
        Cubierto c2 = new Cubierto();
        Cubierto c3 = new Cubierto();
        Cubierto c4 = new Cubierto();
        Cubierto c5 = new Cubierto();

        Filosofo f1 = new Filosofo("f1",c5,c1);
        Filosofo f2 = new Filosofo("f2",c1,c2);
        Filosofo f3 = new Filosofo("f3",c2,c3);
        Filosofo f4 = new Filosofo("f4",c3,c4);
        Filosofo f5 = new Filosofo("f5",c4,c5);

        f1.start();
        f2.start();
        f3.start();
        f4.start();
        f5.start();
    }
}
