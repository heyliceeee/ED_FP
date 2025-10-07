package structures.FP02;

public class LinkedListNode<T> {
    private T elem;
    private LinkedListNode<T> next;


    LinkedListNode() {
        elem = null;
        next = null;
    }

    public LinkedListNode(T elem) {
        this.elem = elem;
        this.next = null;
    }


    public T getElement() {
        return elem;
    }

    public void setElement(T elem) {
        this.elem = elem;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "{element = " + elem + '}';
    }
}
