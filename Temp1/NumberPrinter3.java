// print numbers from 1 to 10 with 10 different threads
public class NumberPrinter3 {
    public static void main(String[] args) {
        for(int i =0;i<10; i++){
            NWP3 t1 = new NWP3(i);
            t1.start();
        }
    }
    
}
class NWP3 extends Thread {
    int num ;
    public NWP3(int num){
        this.num = num;
    }

    @Override
    public void run() {
        num++;
        System.out.println(num + " " + Thread.currentThread().getName());
        
        
    }
}
