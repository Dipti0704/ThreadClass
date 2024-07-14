public class NumberPrinter4 {
    public static void main(String[] args) {
        for(int i =0;i<10; i++){
            NWP3 t1 = new NWP3(i);
            t1.start();
        }
    }
}

class NWP4 implements Runnable {
    static int num ;
    public NWP4(int num){
        this.num = num;
    }

    @Override
    public void run() {
        num++;
        System.out.println(num + " " + Thread.currentThread().getName());
        
        
    }
}
