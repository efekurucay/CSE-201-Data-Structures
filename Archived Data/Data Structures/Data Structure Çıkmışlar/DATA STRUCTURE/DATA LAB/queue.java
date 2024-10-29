public class queue{
 public static void main(String[] args) {
    Queue queue = new Queue();
    
 }   
}
class Queue implements IQueue{
    private int [] arr = new int [10];
    private int r;
    private int f = 0;
    private int sz = 0;
    @Override
    public int size() {
        return sz;
    }

    @Override
    public boolean isEmpty() {
        return sz==0;
    }

    @Override
    public void Enqueue(Object o) {
        if(size() == arr.length){
            throw new IllegalStateException();
        }else{
            r = (f+size()) % arr.length;
            arr[r] = (int) o;
            sz++;
        }
        
    }

    @Override
    public Object Dequque() {
        if (isEmpty())
            return null;

        Object o = arr[f];
        f = (f+1)%arr.length;
        sz--;
        
        return o;
    }
}

interface IQueue{
    int size();
    boolean isEmpty();
    void Enqueue(Object o);
    Object Dequque();
}
