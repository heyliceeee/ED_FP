package structures.FP05;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import interfaces.UnorderedListADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayUnorderedList<T> implements UnorderedListADT<T> {
    private final int DEFAULT_CAPACITY = 100; //constante para representar a capacidade default do array
    private T[] list; //array de elementos genéricos que representam a lista
    private int rear; // int que representa o elemento rear da lista
    private int modCount;


    public ArrayUnorderedList() {
        this.modCount = this.rear = 0;
        this.list =  (T[])(new Object[DEFAULT_CAPACITY]);
    }

    public ArrayUnorderedList(int initialCapacity) {
        this.modCount = this.rear = 0;
        this.list =  (T[])(new Object[initialCapacity]);
    }


    /**
     * adiciona o elemento especifico no inicio da lista
     *
     * @param elem o elemento a ser adicionado no inicio da lista
     */
    @Override
    public void addToFront(T elem) {
        if (elem == null)
            throw new IllegalArgumentException("Elemento não pode ser null");

        if (rear == list.length)
            expandCapacity();

        // Desloca todos os elementos para a direita
        for (int i = rear; i > 0; i--)
            list[i] = list[i - 1];

        list[0] = elem;
        rear++;
        modCount++;
    }

    /**
     * adiciona o elemento especifico no fim da lista
     *
     * @param elem o elemento a ser adicionado no fim da lista
     */
    @Override
    public void addToRear(T elem) {
        if (elem == null)
            throw new IllegalArgumentException("Elemento não pode ser null");

        if (rear == list.length)
            expandCapacity();

        list[rear] = elem;
        rear++;
        modCount++;
    }

    /**
     * adiciona o elemento especifico depois do elemento alvo
     *
     * @param elem   o elemento a ser adicionado depois do elemento alvo
     * @param target o elemento alvo em que o elemento especifico sera adicionado depois do mesmo
     */
    @Override
    public void addAfter(T elem, T target) throws ElementNotFoundException {
        if (elem == null || target == null)
            throw new IllegalArgumentException("Elemento e target não podem ser null");

        if (rear == list.length)
            expandCapacity();

        int targetIndex = findIndex(target);
        if (targetIndex == -1)
            throw new ElementNotFoundException("Elemento target não encontrado na lista");

        // Desloca elementos para a direita a partir da posição após o target
        for (int i = rear; i > targetIndex + 1; i--)
            list[i] = list[i - 1];

        list[targetIndex + 1] = elem;
        rear++;
        modCount++;
    }

    /**
     * remove e retorna o primeiro elemento da lista
     *
     * @return o primeiro elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("ArrayUnorderedList");

        T result = list[0];

        // Desloca todos os elementos para a esquerda
        for (int i = 0; i < rear - 1; i++)
            list[i] = list[i + 1];

        list[rear - 1] = null;
        rear--;
        modCount++;

        return result;
    }

    /**
     * remove e retorna o ultimo elemento da lista
     *
     * @return o ultimo elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("ArrayUnorderedList");

        T result = list[rear - 1];
        list[rear - 1] = null;
        rear--;
        modCount++;

        return result;
    }

    /**
     * remove e retorna o elemento especifico da lista
     *
     * @param elem o elemento especifico a ser removido
     * @return o elemento especifico removido da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T remove(T elem) throws EmptyCollectionException, ElementNotFoundException {
        if (isEmpty())
            throw new EmptyCollectionException("ArrayUnorderedList");

        int index = findIndex(elem);
        if (index == -1)
            throw new ElementNotFoundException("Elemento não encontrado na lista");

        T result = list[index];

        // Desloca elementos para a esquerda
        for (int i = index; i < rear - 1; i++)
            list[i] = list[i + 1];

        list[rear - 1] = null;
        rear--;
        modCount++;

        return result;
    }

    /**
     * retorna o primeiro elemento da lista
     *
     * @return o primeiro elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("ArrayUnorderedList");
        return list[0];
    }

    /**
     * retorna o ultimo elemento da lista
     *
     * @return o ultimo elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("ArrayUnorderedList");
        return list[rear - 1];
    }

    /**
     * retorna true se na lista contem o elemento especifico
     *
     * @param elem o elemento especifico a ser encontrado
     * @return true se encontra o elemento especifico, false caso contrario
     */
    @Override
    public boolean contains(T elem) {
        return findIndex(elem) != -1;
    }

    /**
     * retorna true se a lista tiver vazia
     *
     * @return true se a lista tiver vazia, false caso contrario
     */
    @Override
    public boolean isEmpty() {
        return rear == 0;
    }

    /**
     * retorna o numero de elementos da lista
     *
     * @return o numero de elementos da lista
     */
    @Override
    public int size() {
        return rear;
    }

    /**
     * retorna um iterador dos elementos da lista
     *
     * @return um iterador dos elementos da lista
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    /**
     * Retorna uma representação em string da lista
     *
     * @return string representando a lista
     */
    @Override
    public String toString() {
        if (isEmpty())
            return "ArrayUnorderedList[]";

        StringBuilder sb = new StringBuilder();
        sb.append("ArrayUnorderedList[");
        for (int i = 0; i < rear; i++) {
            sb.append(list[i]);
            if (i < rear - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Encontra o índice de um elemento na lista
     *
     * @param elem o elemento a ser encontrado
     * @return o índice do elemento, ou -1 se não encontrado
     */
    private int findIndex(T elem) {
        for (int i = 0; i < rear; i++) {
            if (elem.equals(list[i]))
                return i;
        }
        return -1;
    }

    /**
     * Expande a capacidade do array quando necessário
     */
    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        T[] larger = (T[]) new Object[list.length * 2];
        System.arraycopy(list, 0, larger, 0, list.length);
        list = larger;
    }

    private class MyIterator implements Iterator<T> {
        private int current;
        private int expectedModCount;

        public MyIterator() {
            this.current = 0;
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            checkForComodification();
            return current < rear;
        }

        @Override
        public T next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result = list[current];
            current++;
            return result;
        }

        private void checkForComodification() {
            if (expectedModCount != modCount) {
                throw new java.util.ConcurrentModificationException();
            }
        }
    }
}
