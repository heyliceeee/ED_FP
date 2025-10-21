package structures.FP06;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import structures.FP02.DoublyLinkedList;
import structures.FP02.DoublyNode;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Lista duplamente ligada circular
 *  - tail.next = head
 *  - head.prev = tail
 */
public class CircularDoublyLinkedList<T> extends DoublyLinkedList<T> {
    private int modCount;

    public CircularDoublyLinkedList() {
        super();
    }

    /**
     * Adiciona elemento no início da lista circular
     */
    @Override
    public void addFirst(T element) {
        DoublyNode<T> newNode = new DoublyNode<>(element);

        if (isEmpty()) {
            // Primeiro elemento - aponta para si mesmo
            head = newNode;
            tail = newNode;
            head.setNext(head);
            head.setPrev(head);
        } else {
            // Insere no início e atualiza circularidade
            newNode.setNext(head);
            newNode.setPrev(tail);
            head.setPrev(newNode);
            tail.setNext(newNode);
            head = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Adiciona elemento no final da lista circular
     */
    public void addToRear(T element) {
        DoublyNode<T> newNode = new DoublyNode<>(element);

        if (isEmpty()) {
            // Primeiro elemento - aponta para si mesmo
            head = newNode;
            tail = newNode;
            head.setNext(head);
            head.setPrev(head);
        } else {
            // Insere no final e atualiza circularidade
            newNode.setPrev(tail);
            newNode.setNext(head);
            tail.setNext(newNode);
            head.setPrev(newNode);
            tail = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Remove o primeiro elemento mantendo a circularidade
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("CircularDoublyLinkedList");

        T removedElement = head.getElement();

        if (size == 1) {
            // Único elemento - lista fica vazia
            head = null;
            tail = null;
        } else {
            // Remove head e atualiza circularidade
            head = head.getNext();
            head.setPrev(tail);
            tail.setNext(head);
        }
        size--;
        modCount++;

        return removedElement;
    }

    /**
     * Remove o último elemento mantendo a circularidade
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("CircularDoublyLinkedList");

        T removedElement = tail.getElement();

        if (size == 1) {
            // Único elemento - lista fica vazia
            head = null;
            tail = null;
        } else {
            // Remove tail e atualiza circularidade
            tail = tail.getPrev();
            tail.setNext(head);
            head.setPrev(tail);
        }
        size--;
        modCount++;

        return removedElement;
    }

    /**
     * Remove elemento específico mantendo circularidade
     */
    public T remove(T element) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("CircularDoublyLinkedList");

        DoublyNode<T> current = head;
        int count = 0;

        // Percorre a lista circular
        while (count < size && !current.getElement().equals(element)) {
            current = current.getNext();
            count++;
        }

        if (count == size)
            throw new ElementNotFoundException("Elemento não encontrado: " + element);

        T removedElement = current.getElement();

        if (size == 1) {
            // Único elemento - lista fica vazia
            head = null;
            tail = null;
            size = 0; // ← CORREÇÃO: decrementar size
        } else if (current == head) {
            // Remove primeiro elemento
            removeFirst();
            return removedElement;
        } else if (current == tail) {
            // Remove último elemento
            removeLast();
            return removedElement;
        } else {
            // Remove do meio
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            size--;
        }

        modCount++;
        return removedElement;
    }

    /**
     * Percorre a lista circular em frente (next)
     */
    public void traverseForward(T startElement) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Circular Doubly Linked List");

        DoublyNode<T> current = findNode(startElement);
        if (current == null) {
            System.out.println("Elemento não encontrado: " + startElement);
            return;
        }

        DoublyNode<T> start = current;
        System.out.print("Traversal forward: ");
        do {
            System.out.print(current.getElement() + " → ");
            current = current.getNext();
        } while (current != start);
        System.out.println("(back to start)");
    }

    /**
     * Percorre a lista circular para trás (prev)
     */
    public void traverseBackward(T startElement) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Circular Doubly Linked List");

        DoublyNode<T> current = findNode(startElement);
        if (current == null)
            throw new EmptyCollectionException("Circular Doubly Linked List");

        DoublyNode<T> start = current;
        System.out.print("Traversal backward: ");
        do {
            System.out.print(current.getElement() + " ← ");
            current = current.getPrev();
        } while (current != start);
        System.out.println("(back to start)");
    }

    /**
     * Encontra um nó na lista circular
     */
    private DoublyNode<T> findNode(T element) throws  EmptyCollectionException {
        if (isEmpty()) throw new EmptyCollectionException("Circular Doubly Linked List");

        DoublyNode<T> current = head;
        int count = 0;

        while (count < size) {
            if (current.getElement().equals(element))
                return current;
            current = current.getNext();
            count++;
        }

        return null;
    }

    /**
     * Verifica a integridade da circularidade
     */
    public boolean isProperlyCircular() {
        if (isEmpty()) return true;

        boolean tailPointsToHead = tail.getNext() == head;
        boolean headPointsToTail = head.getPrev() == tail;

        return tailPointsToHead && headPointsToTail;
    }

    /**
     * Roda a lista - move head para a direita
     */
    public void rotateForward() {
        if (size > 1) {
            head = head.getNext();
            tail = tail.getNext();
            modCount++;
        }
    }

    /**
     * Roda a lista - move head para a esquerda
     */
    public void rotateBackward() {
        if (size > 1) {
            head = head.getPrev();
            tail = tail.getPrev();
            modCount++;
        }
    }

    /**
     * Verifica se a lista contém um elemento específico
     * @param element o elemento a procurar
     * @return true se o elemento existir, false caso contrário
     */
    public boolean contains(T element) {
        if (isEmpty())
            return false;

        DoublyNode<T> current = head;
        int count = 0;

        // Percorre a lista circular (máximo de 'size' iterações)
        while (count < size) {
            T currentElement = current.getElement();

            // Verificação segura para null
            if (element == null) {
                if (currentElement == null)
                    return true;
            } else {
                if (element.equals(currentElement))
                    return true;
            }

            current = current.getNext();
            count++;
        }

        return false;
    }

    /**
     * Representação visual da lista circular
     */
    @Override
    public String toString() {
        if (isEmpty())
            return "CircularDoublyLinkedList[]";

        StringBuilder sb = new StringBuilder();
        sb.append("CircularDoublyLinkedList[");

        DoublyNode<T> current = head;
        int count = 0;

        while (count < size) {
            sb.append(current.getElement());
            if (count < size - 1)
                sb.append(" ⇄ ");
            current = current.getNext();
            count++;
        }

        sb.append("]");

        // Mostra conexões circulares
        if (size > 0)
            sb.append(" {tail→head: ").append(tail.getNext() == head).append(", head←tail: ").append(head.getPrev() == tail).append("}");

        return sb.toString();
    }

    /**
     * retorna um iterador dos elementos da lista
     *
     * @return um iterador dos elementos da lista
     */
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private DoublyNode<T> current;
        private int expectedModCount;
        private boolean canRemove;
        private DoublyNode<T> lastReturned;
        private int count;

        /**
         * Construtor do iterador para CircularDoublyLinkedList
         * Percorre do head para tail (uma volta completa)
         */
        public MyIterator() {
            this.current = head;
            this.expectedModCount = modCount;
            this.canRemove = false;
            this.lastReturned = null;
            this.count = 0;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            checkForComodification();
            return count < size; // Para após uma volta completa
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws java.util.NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            checkForComodification();

            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in circular list iteration");
            }

            T element = current.getElement();
            lastReturned = current;
            current = current.getNext(); // Move para o próximo nó (circular)
            count++;
            canRemove = true;

            return element;
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation). This method can be called
         * only once per call to {@link #next}.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *         operation is not supported by this iterator
         * @throws IllegalStateException if the {@code next} method has not
         *         yet been called, or the {@code remove} method has already
         *         been called after the last call to the {@code next} method
         */
        @Override
        public void remove() {
            checkForComodification();

            if (!canRemove) {
                throw new IllegalStateException("remove() can only be called once after next()");
            }

            if (lastReturned == null) {
                throw new IllegalStateException("No element to remove");
            }

            try {
                // Usa o método remove existente da lista circular
                CircularDoublyLinkedList.this.remove(lastReturned.getElement());
            } catch (EmptyCollectionException e) {
                throw new IllegalStateException("Cannot remove from empty list");
            }

            // Atualiza o estado do iterador após remoção
            expectedModCount = modCount; // remove() incrementou modCount
            canRemove = false;
            lastReturned = null;

            // Ajusta o count pois removemos um elemento
            count--;

            // Não precisamos ajustar o current porque o método remove
            // já atualizou a estrutura da lista circular
        }

        /**
         * Verifica se houve modificação concorrente na lista circular
         */
        private void checkForComodification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Circular list modified during iteration");
            }
        }
    }
}
