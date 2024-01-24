package Examen.MCD;

public class Main {
    public static void main(String[] args) {

        MaximComuDivisor task = new MaximComuDivisor(75, 6);
        int resultado = (int) task.compute();

        System.out.println("El resultado: " + resultado);

    }
}
