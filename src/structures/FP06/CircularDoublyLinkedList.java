package structures.FP06;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import structures.FP02.DoublyLinkedList;
import structures.FP02.DoublyNode;

/**
 * Lista duplamente ligada circular
 *  - tail.next = head
 *  - head.prev = tail
 */
public class CircularDoublyLinkedList<T> extends DoublyLinkedList<T> {
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
            // Único elemento
            head = null;
            tail = null;
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
        }
    }

    /**
     * Roda a lista - move head para a esquerda
     */
    public void rotateBackward() {
        if (size > 1) {
            head = head.getPrev();
            tail = tail.getPrev();
        }
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
}
