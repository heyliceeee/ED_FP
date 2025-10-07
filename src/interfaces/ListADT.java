package interfaces;

public interface ListADT<T> extends Iterable<T> {
    public void addFirst(T elem);
    public void addLast(T elem);
    public T removeFirst();
    public T removeLast();
    public boolean isEmpty();
    public int size();
}
