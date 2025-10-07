package structures.FP02;

public class SentinelNode<T> {
    private T elem;
    private SentinelNode<T> next;


    SentinelNode(T elem) {
        this.elem = elem;
        this.next = null;
    }


    public T getElement() {
        return elem;
    }

    public void setElement(T elem) {
        this.elem = elem;
    }

    public SentinelNode<T> getNext() {
        return next;
    }

    public void setNext(SentinelNode<T> next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "{element = " + elem + '}';
    }
}