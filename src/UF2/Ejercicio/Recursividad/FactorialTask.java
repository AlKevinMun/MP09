package UF2.Ejercicio.Recursividad;

import java.sql.SQLOutput;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private int num;
    public static final int LLINDAR = 10;

    public FactorialTask(int num) {
        this.num = num;
    }

    private Long factorialSeq(){
        System.out.println("seq: " + num);
        if(num==1){
            return 1L;
        }else{
            long temp = 1;
            for (int i = 1;i<=num;i++){
                temp = temp*i;
            }
            return temp;
        }
    }

    private Long factorialReq(){

        FactorialTask f1 = new FactorialTask(num-1);
        System.out.println("req: "+(num));
        f1.fork();
        return num*f1.join();

    }

    @Override
    protected Long compute() {
        if (num < LLINDAR){
            return factorialSeq();
        }
        else return factorialReq();
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FactorialTask factorialTask = new FactorialTask(5);
        pool.invoke(factorialTask);

        long res = factorialTask.join();

        System.out.println("El resultat es: "+res);
    }

}

