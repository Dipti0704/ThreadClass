public class HelloWorldPrinter1{
    public static void main(String[] args){
        HWP t1 = new HWP();
        HWP t2 = new HWP();
        t1.start();
        t2.start();
    
    }
}
class HWP extends Thread{
    @Override
    public void run(){
        for(int i = 0; i<2; i++){
            System.out.println("Hello World " + Thread.currentThread().getName());
        }
    }
}
