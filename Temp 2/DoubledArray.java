
// create a new list that list should have two times the value of the original list and that list should be returned by the new thread

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DoubledArray {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ArrayList<Integer> list = new ArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10; i++){
            list.add(i);
        }
        ArrayListModifier task = new ArrayListModifier(list);
        Future<ArrayList<Integer>> doubledArrayList = es.submit(task);
        System.out.println(doubledArrayList.get());
        es.shutdown();
    }
}

      
    

class ArrayListModifier implements Callable<ArrayList<Integer>>{
    ArrayList<Integer> listToDouble;
    
    public ArrayListModifier(ArrayList<Integer> list){
        this.listToDouble = list;
        
    }

    // when you are implementing callable interface you are creating a method which is  going to return the object type
    @Override
    public ArrayList<Integer> call() throws Exception{
        ArrayList<Integer> doubledList = new ArrayList<>();
        for(int i = 0; i<listToDouble.size(); i++){
            doubledList.add(listToDouble.get(i)*2);
        }
        return doubledList;

    }
}
















// 
