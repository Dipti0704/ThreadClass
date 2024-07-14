import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumberPrinter6 {
    public static void main(String[] args) {
    ExecutorService es = Executors.newFixedThreadPool(10);
    for(int i = 0; i<20; i++){
        es.submit(new NP6(i));
    }
    }
    
}
class NP6 implements Runnable{
    int num;
    NP6(int num){
        this.num = num;
    }
    @Override
    public void run(){
        num++;
        System.out.println(num + " " + Thread.currentThread().getName());
    }
    
}