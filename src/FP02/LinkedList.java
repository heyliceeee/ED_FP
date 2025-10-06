package FP02;

public class LinkedList<T> {
    // Node interno da list
    private static class Node<T> {
        T elem;
        Node<T> next;


        Node(T elem) {
            this.elem = elem;
            this.next = null;
        }
    }

    private Node<T> head; // 1o elem da list
    private Node<T> sentinel; // node sentinela

    public LinkedList() {
        sentinel = new Node<>(null); // node especial sem dados
    }

    // Add no início da list
    public void addFirst(T elem) {
        Node<T> newNode = new Node<>(elem);
        newNode.next = head;
        head = newNode;
    }

    // Add no fim da list
    public void addLast(T elem) {
        Node<T> newNode = new Node<>(elem);

        if (head == null) {
            head = newNode;
            return;
        }

        Node<T> current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    // Add no fim da list, com node sentinel
    public void addLastSentinel(T elem) {
        Node<T> newNode = new Node<>(elem);
        Node<T> current = sentinel;
        
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    // Remove o 1o elem
    public void removeFirst() {
        if (head == null) {
            System.out.println("Lista vazia, nada a remover.");
            return;
        }
        head = head.next;
    }

    // Remove o último elem
    public void removeLast() {
        if (head == null) {
            System.out.println("Lista vazia, nada a remover.");
            return;
        }

        if (head.next == null) {
            head = null;
            return;
        }

        Node<T> current = head;

        while (current.next.next != null) {
            current = current.next;
        }

        current.next = null;
    }

    // Imprime todos os elementos
    public void printList() {
        Node<T> current = head;

        while (current != null) {
            System.out.print(current.elem + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }
}
