package structures.FP06;

import exceptions.EmptyCollectionException;
import structures.FP02.DoublyLinkedList;
import structures.FP02.DoublyNode;

/**
 * Lista circular duplamente ligada
 * O último elemento aponta para o primeiro e vice-versa
 */
public class CircularLinkedList<T> extends DoublyLinkedList<T> {
    public CircularLinkedList() {
        super();
    }

    /**
     * Adiciona elemento no início da lista circular
     */
    @Override
    public void addFirst(T element) {
        super.addFirst(element);

        // Torna a lista circular
        if (size == 1) {
            // Único elemento - aponta para si mesmo
            head.setNext(head);
            head.setPrev(head);
        } else {
            // Atualiza ponteiros para manter circularidade
            head.setPrev(tail);
            tail.setNext(head);
        }
    }

    /**
     * Adiciona elemento no final da lista circular
     */
    public void addToRear(T element) {
        DoublyNode<T> newNode = new DoublyNode<>(element);

        if (isEmpty()) {
            // Lista vazia - novo nó é head e tail
            head = newNode;
            tail = newNode;
            head.setNext(head);
            head.setPrev(head);
        } else {
            // Insere no final e atualiza ponteiros circulares
            tail.setNext(newNode);
            newNode.setPrev(tail);
            newNode.setNext(head);
            head.setPrev(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * Remove o primeiro elemento da lista circular
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("CircularLinkedList");

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
     * Remove o último elemento da lista circular
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("CircularLinkedList");

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
     * Percorre a lista circular a partir de qualquer nó
     */
    public void traverseFrom(T startElement) throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("CircularLinkedList");

        // Encontra o nó de início
        DoublyNode<T> current = head;
        while (current != null && !current.getElement().equals(startElement)) {
            current = current.getNext();
            if (current == head) break; // Deu volta completa
        }

        if (current == null || !current.getElement().equals(startElement))
            throw new EmptyCollectionException("CircularLinkedList");

        // Percorre a lista circular
        DoublyNode<T> start = current;
        System.out.print("Traversal circular: ");
        do {
            System.out.print(current.getElement() + " → ");
            current = current.getNext();
        } while (current != start);
        System.out.println("(volta ao início)");
    }

    /**
     * Verifica se a lista é circular (sempre true nesta implementação)
     */
    public boolean isCircular() {
        if (isEmpty()) return true;

        // Verifica se o último aponta para o primeiro
        return tail.getNext() == head && head.getPrev() == tail;
    }

    /**
     * Retorna uma representação da lista circular
     */
    @Override
    public String toString() {
        if (isEmpty())
            return "CircularLinkedList[]";

        StringBuilder sb = new StringBuilder();
        sb.append("CircularLinkedList[");

        DoublyNode<T> current = head;
        int count = 0;

        // Percorre até voltar ao início ou até o tamanho da lista
        while (count < size) {
            sb.append(current.getElement());
            if (count < size - 1)
                sb.append(" ⇄ ");
            current = current.getNext();
            count++;
        }

        sb.append("]");
        return sb.toString();
    }
}
