
// create a new list that list should have two times the value of the original list and that list should be returned by the new thread
public class DoubledArray {
    public static void main(String[] args){

    }
    
}
class ArrayListModifier implements Callable{
    ArrayList<Integer> list;
    
    public ArrayListModifier(ArrayList<Integer> list){
        this.list = list;


    }

    // when you are implementing callable interface you are creating a method which is  going to return the object type
    @Override
    public void run(){

    }
}
















// 
