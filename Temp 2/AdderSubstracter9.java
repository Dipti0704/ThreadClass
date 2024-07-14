import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AdderSubstracter9 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newFixedThreadPool(5);
        Value val = new Value(0);
        
        Adder addTask = new Adder(val);
        Subsstracter subTask = new Subsstracter(val);

        Future<Void> adderFuture = es.submit(addTask);
        Future<Void> subFuture = es.submit(subTask);

        adderFuture.get();
        subFuture.get();

        System.out.println(val.data);

    }
    
}

class Value{
    int data;
    public Value(int data){
        this.data =data;

    }
}

class Adder implements Callable<Void>{
    Value val;

    public Adder(Value val){
        this.val = val;
    }

    @Override
    public Void call() throws Exception{
        for(int i = 0; i<=100; i++){
            this.val.data += i;

        }
        return null;
    }
}

class Subsstracter implements Callable<Void>{
    Value val;

    public Subsstracter(Value val){
        this.val = val;
    }

    @Override
    public Void call() throws Exception{
        for(int i = 0; i<=100; i++){
            this.val.data -= i;

        }
        return null;
        
    }
}


// when we will execute this we will not always get 0 as the answer or same answer
/*
 * as we studied in transaction .... threads might get overlapped 
 * just like in bank account fund transfer system
 * 
 * 
 * 
 * the issue in above thread is that both threads are 
 * reading
 * performing and updating
 *                 t1        t2        final val
 * read            0          0         0
 * preform         +1         -1   
 * update          1          -1
 * 
 * 
 * 
 * 
 * this issues are called Syncronization issues
 * 
 * 
 * 
 */
