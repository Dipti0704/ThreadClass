public class NumberPrinter5 {
    public static void main(String[] args) {
        for(int i = 0; i<100; i++){
            NP5 t1 = new NP5(i);
            Thread aa = new Thread(t1);
            aa.start();
        }        
    }
}

class NP5 implements Runnable{
    int num;
    NP5(int num){
        this.num = num;
    }
    @Override
    public void run(){
        num++;
        System.out.println(num + " " + Thread.currentThread().getName());
    }
    
}