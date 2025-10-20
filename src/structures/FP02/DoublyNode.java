package structures.FP02;

public class DoublyNode<T> {
    private T elem;
    private DoublyNode<T> prev, next;


    DoublyNode(){
        this.elem = null;
        this.prev = null;
        this.next = null;
    }

    public DoublyNode(T elem) {
        this.elem = elem;
        this.prev = null;
        this.next = null;
    }


    public T getElement() {
        return elem;
    }

    public void setElement(T elem) {
        this.elem = elem;
    }

    public DoublyNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoublyNode<T> prev) {
        this.prev = prev;
    }

    public DoublyNode<T> getNext() {
        return next;
    }

    public void setNext(DoublyNode<T> next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "{element = " + elem + '}';
    }
}