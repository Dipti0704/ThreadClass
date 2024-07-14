import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MergeSortMultithreaded {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ArrayList<Integer> list = new ArrayList<>();
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++){
            list.add(10-i);
        }
        Sorter task = new Sorter(list);
        Future<ArrayList<Integer>> sortedArrayList = es.submit(task);
       
        System.out.println(sortedArrayList.get());
      
        
    }
}
    

class Sorter implements Callable<ArrayList<Integer>>{
    ArrayList<Integer> listToSort;
    public Sorter(ArrayList<Integer> listToSort){
        this.listToSort = listToSort;
    }

    @Override
    public ArrayList<Integer> call() throws Exception {
        // TODO Auto-generated method stub
       if (listToSort.size()<=1){
        return listToSort;
       }
       int mid = listToSort.size()/2;
        ArrayList<Integer> leftToSort = getSubArray(listToSort,0,mid-1) ;
        ArrayList<Integer> rightToSort = getSubArray(listToSort,mid, listToSort.size()-1);

        // executor service to sort two halfs in parallel
        ExecutorService es = Executors.newFixedThreadPool(5);

        // create subtask with two sublist to assign them to different threads
        // to create a task we need to create the object of the sorter class
        Sorter leftSorter = new Sorter(leftToSort);
        Sorter rightSorter = new Sorter(rightToSort);

        // to execute this in multithreaded way
        // we need to submit the task to the executor service

        Future<ArrayList<Integer>> FutureleftSortedHalf = es.submit(leftSorter);
        Future<ArrayList<Integer>> FuturerightSortedHalf = es.submit(rightSorter);

        ArrayList<Integer> leftSortedList = FutureleftSortedHalf.get();
        ArrayList<Integer> rightSortedList = FuturerightSortedHalf.get();

        return merge(leftSortedList, rightSortedList);

        
        
    }


    ArrayList<Integer> merge(ArrayList<Integer> leftSortedList, ArrayList<Integer> rightSortedList){
        ArrayList<Integer> mergedList = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;
        while(leftIndex<leftSortedList.size() && rightIndex<rightSortedList.size()){
            if(leftSortedList.get(leftIndex)<rightSortedList.get(rightIndex)){
                mergedList.add(leftSortedList.get(leftIndex));
                leftIndex++;
            }else{
                mergedList.add(rightSortedList.get(rightIndex));
                rightIndex++;
            }
        }
        while(leftIndex<leftSortedList.size()){
            mergedList.add(leftSortedList.get(leftIndex));
            leftIndex++;
        }
        while(rightIndex<rightSortedList.size()){
            mergedList.add(rightSortedList.get(rightIndex));
            rightIndex++;
        }
        return mergedList;
    }

    private ArrayList<Integer> getSubArray(ArrayList<Integer> listToSort2, int start, int end) {
        
      ArrayList<Integer> subList = new ArrayList<>();
      for(int i = start; i<=end; i++){
        subList.add(listToSort2.get(i));
      }
      return subList;
    }














    // **** what we have done in this code


    /*
     *  we had created a  task called sorter 
     * when we create a task?? -->> we create a task whenever we want to assign some work to a thread
     * its job is to sort a given array
     * this sorted task is going to have a arrayList which we are going to sort
     * 
     * we are passing the arrayList here through the constructor
     * 
     * in the call function we will be writing the exact code that we want the thread to execute
     * as of now whatever we are writing inside the call function 
     * that was sequential , means it is getting executed by a single thread sequentially
     * 
     * but here we have executed the call function in the multithreaded way
     * how??
     * --->>>> we first have the base condition if the size is less that or equal to 1 
     * then the list is already sorted.
     * else
     * create two differt task having two differnt halfs of the list
     * then, create executer service we had taken 5 threads
     *  and then we have assigned the task to different  threads
     * using the executer service and those executer service will return us the sorted list
     * but will they instantly return the sorted list??
     * -->> here comes future in picture
     *      that is why it is going to return an object of future
     * 
     * now we need to merge these two list but before that we need that two list s
     *  so we will use get function to get tha two list 
     * now after getting the two sorted list , we use a simple merge function.
     * 
     * 
     * // here we did sorting the left part and sorting the right part in parallel 
     * but merging should be sequential 
     * 
     */




     /*
      * inside executor service there is task queue and thread pool

      executor service will be creating threads and it will be having aqueue of task which it will
      ve assigning to those threads

      if you say i want a executor service of 10 threads
      will it create all 10threads at a single time??
      --->>>  it will create threads based on the available tasks

      ******** WHAT IS DEADLOCK???? ********
      A deadlock in threads is a situation where two or more threads are unable to proceed with their execution because each one is waiting for a resource that the other threads have locked. This results in all the involved threads being blocked indefinitely.

A classic example of a deadlock scenario is:

Thread A locks Resource 1 and then tries to lock Resource 2.
Thread B locks Resource 2 and then tries to lock Resource 1.
If Thread A locks Resource 1 and Thread B locks Resource 2 at the same time, both threads will be stuck waiting for each other to release the resource they need, creating a deadlock.

Conditions for Deadlock
There are four necessary conditions for a deadlock to occur, often summarized by the Coffman conditions:

Mutual Exclusion: At least one resource must be held in a non-sharable mode; only one thread can use the resource at any given time.
Hold and Wait: A thread holding at least one resource is waiting to acquire additional resources that are currently being held by other threads.
No Preemption: Resources cannot be forcibly taken from threads holding them; they must be released voluntarily.
Circular Wait: There exists a set of threads 
{ T1, T2, ..., Tn }
 such that T1 is waiting for a resource held by T2, T2 is waiting for a resource held by T3, and so on until Tn is waiting for a resource held by T1.

Avoiding Deadlock
Here are a few strategies to avoid deadlocks:

Resource Ordering: Impose a global order on resource acquisition and ensure that all threads acquire resources in that order.
Deadlock Detection and Recovery: Allow the system to enter a deadlock state, detect it, and then take actions to recover, such as terminating one of the threads involved.
Timeouts: Set time limits on how long a thread can wait for a resource, forcing it to release any held resources if the timeout is reached.
Avoidance Algorithms: Use algorithms like Banker's Algorithm to allocate resources in a way that avoids deadlocks.
      */




      /*
       * why do we do merge sort in multi threaded way??
       * in large companies some part of data would be in one server... soe part of data would be in another server
       * if you want to sort this data you will sort that data in that particular server and then you will merge the data
       * in other server
       */
   

    
}
