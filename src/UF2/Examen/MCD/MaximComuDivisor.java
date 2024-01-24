package UF2.Examen.MCD;

import java.util.concurrent.RecursiveTask;

public class MaximComuDivisor extends RecursiveTask {

    public static int maximo = 20;

    int valor1;
    int valor2;

    public MaximComuDivisor(int valor1, int valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public int MCDValor(int valor1, int valor2){
        while (valor2!=0){
            int t = valor2; // t es una variable temporal.
            valor2 = valor2%valor1;
            valor1 = t;
        }
        return valor1;
    }


    @Override
    protected Object compute() {
        if (valor1 <= maximo || valor2 <= maximo) {
            return MCDValor(valor1, valor2);
        } else {
            return new MaximComuDivisor(valor2, valor1 % valor2).fork().join();
        }

    }
}
