package structures.FP05;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import interfaces.UnorderedListADT;
import structures.FP02.DoublyLinkedList;
import structures.FP02.DoublyNode;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedUnorderedList<T> extends DoublyLinkedList<T> implements UnorderedListADT<T> {
    private int modCount;

    public DoubleLinkedUnorderedList() {
        super();
        this.modCount = 0;
    }

    /**
     * adiciona o elemento especifico no inicio da lista
     *
     * @param elem o elemento a ser adicionado no inicio da lista
     */
    @Override
    public void addToFront(T elem) {
        if(elem == null)
            throw new IllegalArgumentException("Elemento nao pode ser null");

        super.addFirst(elem);
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

        // Implementação específica para adicionar no final
        DoublyNode<T> newNode = new DoublyNode<T>(elem);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

        size++;
        modCount++;
    }

    /**
     * adiciona o elemento especifico depois do elemento alvo
     *
     * @param elem   o elemento a ser adicionado depois do elemento alvo
     * @param target o elemento alvo em que o elemento especifico sera adicionado depois do mesmo
     * @throws ElementNotFoundException se o elemento alvo não for encontrado
     */
    @Override
    public void addAfter(T elem, T target) throws ElementNotFoundException {
        if (elem == null || target == null)
            throw new IllegalArgumentException("Elemento e target não podem ser null");

        if (isEmpty())
            throw new ElementNotFoundException("Lista vazia - elemento target não encontrado: " + target);

        DoublyNode<T> current = head;

        // Procura o elemento target
        while (current != null && !current.getElement().equals(target))
            current = current.getNext();

        if (current == null)
            throw new ElementNotFoundException("Elemento target não encontrado: " + target);

        DoublyNode<T> newNode = new DoublyNode<T>(elem);

        if (current == tail) {
            // Inserir após o último elemento
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        } else {
            // Inserir no meio
            newNode.setNext(current.getNext());
            newNode.setPrev(current);
            current.getNext().setPrev(newNode);
            current.setNext(newNode);
        }

        size++;
        modCount++;
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
            throw new EmptyCollectionException("DoubleLinkedUnorderedList");

        DoublyNode<T> current = head;

        // Procura o elemento
        while (current != null && !current.getElement().equals(elem))
            current = current.getNext();

        if (current == null)
            throw new ElementNotFoundException("Elemento não encontrado: " + elem);

        T removedElement = current.getElement();

        // Remove o nó encontrado
        if (current == head)
            return removeFirst();
        else if (current == tail)
            return removeLast();
        else {
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            size--;
            modCount++;
        }

        return removedElement;
    }

    /**
     * Remove e retorna o primeiro elemento da lista
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        T result = super.removeFirst(); // Reutiliza classe pai
        modCount++;
        return result;
    }

    /**
     * Remove e retorna o último elemento da lista
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        T result = super.removeLast(); // Reutiliza classe pai
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
        if (isEmpty()) {
            throw new EmptyCollectionException("DoubleLinkedUnorderedList");
        }
        return head.getElement();
    }

    /**
     * retorna o ultimo elemento da lista
     *
     * @return o ultimo elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("DoubleLinkedUnorderedList");
        }
        return tail.getElement();
    }

    /**
     * retorna true se na lista contem o elemento especifico
     *
     * @param elem o elemento especifico a ser encontrado
     * @return true se encontra o elemento especifico, false caso contrario
     */
    @Override
    public boolean contains(T elem) {
        DoublyNode<T> current = head;

        while (current != null) {
            if (current.getElement().equals(elem))
                return true;
            current = current.getNext();
        }

        return false;
    }

    /**
     * retorna o numero de elementos da lista
     *
     * @return o numero de elementos da lista
     */
    @Override
    public int size() {
        return size;
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

    @Override
    public String toString() {
        if (isEmpty())
            return "DoubleLinkedUnorderedList[]";

        StringBuilder sb = new StringBuilder();
        sb.append("DoubleLinkedUnorderedList[");

        DoublyNode<T> current = head;
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null)
                sb.append(" <-> ");
            current = current.getNext();
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Iterador para a lista não ordenada
     */
    private class MyIterator implements Iterator<T> {
        private DoublyNode<T> current;
        private int expectedModCount;
        private boolean canRemove;

        public MyIterator() {
            this.current = head;
            this.expectedModCount = modCount;
            this.canRemove = false;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Não há mais elementos");
            }
            T element = current.getElement();
            current = current.getNext();
            canRemove = true;
            return element;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("next() deve ser chamado antes de remove()");
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Lista modificada durante iteração");
            }

            DoublyNode<T> toRemove = current != null ? current.getPrev() : tail;

            try {
                if (toRemove == head) {
                    removeFirst();
                } else if (toRemove == tail) {
                    removeLast();
                } else {
                    DoublyNode<T> prev = toRemove.getPrev();
                    DoublyNode<T> next = toRemove.getNext();
                    prev.setNext(next);
                    next.setPrev(prev);
                    size--;
                    modCount++;
                }
                expectedModCount = modCount;
                canRemove = false;
            } catch (EmptyCollectionException e) {
                throw new IllegalStateException("Erro durante remoção: " + e.getMessage());
            }
        }
    }
}
