// print numbers from 1 to 10 using different threads


public class NumberPrinter2 {
    public static void main(String[] args) {
        NWP2 t1 = new NWP2();
        NWP2 t2 = new NWP2();
        t1.start();
        t2.start();
    }
  
    
}
class NWP2 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + " " + Thread.currentThread().getName());
        }
    }
}

