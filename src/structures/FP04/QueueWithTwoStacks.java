package structures.FP04;

import exceptions.EmptyCollectionException;
import interfaces.QueueADT;
import structures.FP03.ArrayStack;

public class QueueWithTwoStacks<T> implements QueueADT<T> {

    private ArrayStack<T> inStack;   // onde entram os elementos
    private ArrayStack<T> outStack;  // de onde saem os elementos

    public QueueWithTwoStacks() {
        inStack = new ArrayStack<>();
        outStack = new ArrayStack<>();
    }

    @Override
    public void enqueue(T elem) {
        inStack.push(elem);
    }

    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue");

        if (outStack.isEmpty()) {
            // transferir todos os elementos de inStack para outStack
            while (!inStack.isEmpty())
                outStack.push(inStack.pop());
        }
        return outStack.pop();
    }

    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue");

        if (outStack.isEmpty()) {
            while (!inStack.isEmpty())
                outStack.push(inStack.pop());
        }
        return outStack.peek();
    }

    @Override
    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    @Override
    public int size() {
        return inStack.size() + outStack.size();
    }

    @Override
    public String toString() {
        return "QueueWithTwoStacks{size=" + size() + "}";
    }
}
