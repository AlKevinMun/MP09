package Ejercicio.Hilos;

public class CompteEstalvi {
    private int saldo;
    public synchronized void ingresar(int diners){
        saldo +=diners;
    }
    public synchronized void retirar(int diners){
        saldo -=diners;
    }

    public CompteEstalvi(int saldo) {
        this.saldo = saldo;
    }

    public synchronized int getSaldo() {
        return saldo;
    }

    public static void main(String[] args) {
        CompteEstalvi compteEstalvi = new CompteEstalvi(1000);
        for (int i = 0; i < 1000; i++) {
            Thread thIngresar = new Thread(new Runnable() {
                @Override
                public void run() {
                        compteEstalvi.ingresar(10);
                }
            });
            Thread thRetirar = new Thread(new Runnable() {
                @Override
                public void run() {
                    compteEstalvi.retirar(5);
                }
            });
            thIngresar.run();
            thRetirar.run();
        }
        System.out.println(compteEstalvi.getSaldo());
    }

}
