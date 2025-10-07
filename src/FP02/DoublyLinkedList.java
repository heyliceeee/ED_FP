package FP02;

public class DoublyLinkedList<T> {
    // Classe interna Node
    private static class Node<T> {
        T elem;
        Node<T> next;
        Node<T> prev;

        Node(T elem) {
            this.elem = elem;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> head; // 1o node
    private Node<T> tail; // último node

    // Verifica se a list está vazia
    public boolean isEmpty() {
        return head == null;
    }

    // Inserir no início (head)
    public void addFirst(T elem) {
        Node<T> newNode = new Node<>(elem);

        if (isEmpty()) {
            head = tail = newNode;

        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Remover o 1o node
    public void removeFirst() {
        if (isEmpty()) {
            System.out.println("Lista vazia, nada a remover.");
            return;
        }

        if (head == tail) { // só 1 elem
            head = tail = null;

        } else {
            head = head.next;
            head.prev = null;
        }
    }

    // Remover o último node
    public void removeLast() {
        if (isEmpty()) {
            System.out.println("Lista vazia, nada a remover.");
            return;
        }

        if (head == tail) { // só 1 elem
            head = tail = null;

        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }

    // Percorrer e imprimir tds os elems
    public void printList() {
        if (isEmpty()) {
            System.out.println("Lista vazia.");
            return;
        }

        Node<T> current = head;

        while (current != null) {
            System.out.print(current.elem + " <-> ");
            current = current.next;
        }

        System.out.println("null");
    }
}
