package sr.unasat.vaccinedistribution.datastructures;

public class PriorityQueue {
    private final int SIZE = 20;
    private int[] queueArray;
    private  int nItems;

    public PriorityQueue() {
        queueArray = new int[SIZE];
        nItems = 0;
    }

    public void insert(int item){
        int end;

        if (nItems==0)
            queueArray[nItems++] = item;
        else{
            for (end=nItems-1; end>=0; end--){
                if (item > queueArray[end])
                    queueArray[end+1] = queueArray[end];
                else
                    break;
            }
            queueArray[end+1] = item;
            nItems++;
        }
    }

    public int remove(){
        return queueArray[--nItems];
    }

    public int peekMin(){
        return queueArray[nItems-1];
    }

    public boolean isEmpty(){
        return (nItems==0);
    }

    public boolean isFull(){
        return (nItems == SIZE);
    }
}
