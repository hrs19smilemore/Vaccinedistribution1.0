package sr.unasat.vaccinedistribution.datastructures;

public class Stack {
    private final int SIZE = 20;
    private int[] stackArray;
    private int top;

    public Stack() {
        stackArray = new int[SIZE];
        top = -1;
    }

    public void push(int item){
        stackArray[++top] = item;
    }

    public int pop(){
        return stackArray[top--];
    }

    public int peek(){
        return stackArray[top];
    }

    public boolean isEmpty(){
        return (top == -1);
    }

    public boolean isFull(){
        return (top == SIZE-1);
    }
}
