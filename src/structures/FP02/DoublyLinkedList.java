package structures.FP02;

public class DoublyLinkedList<T> {
    protected DoublyNode<T> head, tail;
    protected int size;


    public DoublyLinkedList() {
        this.size = 0;
        this.head = this.tail = null;
    }

    /**
     * adiciona na cabeça
     * @param elem
     */
    public void addFirst(T elem) {
        DoublyNode<T> newNode = new DoublyNode<T>(elem); //elemento

        if (head == null) { //caso a lista esteja vazia, o novo node fica tanto no head como no tail
            head = newNode;
            tail = newNode;

            size++;

        } else {
            newNode.setNext(head); //o próximo do novo node aponta para o head do node atual
            head.setPrev(newNode); //o anterior do node da head aponta para o novo node
            head = newNode; //o node atual da head é o novo node

            size++;
        }
    }

    /**
     * remover node da head
     *
     */
    public void removeFirst() {
        if (head == null || size == 0) { //lista vazia
            System.out.println("Lista vazia, nada a remover.");
            return;
        }

        DoublyNode<T> removedNode = head; //elemento que está na head e q vai ser removido

        if (head == tail) { //se o node head é o único da lista
            head = tail = null;
            size--;

        } else {
            head = head.getNext(); //a head fica com o valor do proximo node
            head.setPrev(null); //o elemento anterior ao head não existe
            size--;
        }
    }

    /**
     * remover node da tail
     *
     */
    public void removeLast() {
        if (tail == null || size == 0) { //lista vazia
            System.out.println("Lista vazia, nada a remover.");
            return;
        }

        DoublyNode<T> removedNode = tail; //elemento que está na tail e q vai ser removido

        if (head == tail) { //se o node tail é o único da lista
            head = tail = null;
            size--;

        } else {
            tail = tail.getPrev(); //a tail fica com o valor do proximo node
            tail.setNext(null); //o elemento seguinte ao tail não existe
            size--;
        }
    }

    /**
     * Imprimir todos os elementos
     */
    public void printList() {
        if (isEmpty()) {
            System.out.println("Lista vazia.");
            return;
        }

        DoublyNode<T> current = head;

        while (current != null) {
            System.out.print(current.getElement() + " <-> ");
            current = current.getNext();
        }

        System.out.println("null");
    }

    /**
     * Devolver um array dos elementos
     * @return array
     */
    public Object[] toArray() {
        int i = 0;
        DoublyNode<T> current = head; //elemento da cabeca (primeiro elemento)

        while (current != null) {
            i++;
            current = current.getNext(); //vai para o seguinte elemento
        }

        // Crie um array do tamanho apropriado
        Object[] result = new Object[i];

        // Preencha o array com os dados dos nós
        current = head;
        int index = 0;

        while (current != null) {
            result[index] = current.getElement();
            index++;
            current = current.getNext();
        }

        return result;
    }

    /**
     * Devolver um array de todos os elementos até uma dada posição (inclusive)
     * @param pos
     * @return array
     */
    public Object[] toArrayUntil(int pos) {
        if (pos < 0) {
            throw new IllegalArgumentException("a posicao deve ser nao negativa");
        }

        DoublyNode<T> current = head;

        while (current != null) {
            current = current.getNext();
        }

        int effectiveSize = Math.min(pos, size); //se a position for maior do que o tamanho da lista, limite-a ao tamanho

        Object[] result = new Object[effectiveSize]; //criar um array do tamanho apropriado

        //preencha o array com os dados dos nodes até a posicao
        current = head;
        int i = 0;

        while (current != null && i < effectiveSize) {
            result[i] = current.getElement();
            i++;
            current = current.getNext();
        }

        return result;
    }

    /**
     * Devolver um array de todos os elementos depois de uma dada posição
     * @param pos
     * @return array
     */
    public Object[] toArrayAfter(int pos) {
        if (pos < 0) {
            throw new IllegalArgumentException("a posicao deve ser nao negativa");
        }

        DoublyNode<T> current = head;
        int y = 0;

        while (current != null && y < pos) {
            y++;
            current = current.getNext();
        }

        //se a position for >= doq o tamanho da list, retorne um array vazio
        if (current == null) {
            return new Object[0];
        }

        int effectiveSize = 0;
        DoublyNode<T> temp = current;

        while (temp != null) {
            effectiveSize++;
            temp = temp.getNext();
        }

        Object[] result = new Object[effectiveSize]; //criar um array do tamanho apropriado

        //preencha o array c os dados dos nodes até a position
        int i = 0;

        while (current != null) {
            result[i] = current.getElement();
            i++;
            current = current.getNext();
        }

        return result;
    }

    /**
     * Devolver um array de todos os elementos entre um intervalo de posições [start, end]
     * @param start posicao inicial
     * @param end posicao final
     * @return array
     */
    public Object[] toArrayBetween(int start, int end) {
        if (start < 0 || end < 0 || start > end) {
            throw new IllegalArgumentException("a posicao deve ser nao negativa");
        }

        DoublyNode<T> current = head;

        //correr até a start
        int j = 0;
        while (current != null && j < start) {
            j++;
            current = current.getNext();
        }

        //se a start for >= ao tamanho da lista, retorne um array vazio
        if (current == null) {
            return new Object[0];
        }

        int length = 0;
        DoublyNode<T> temp = current;

        while (temp != null && j <= end) {
            length++;
            j++;
            temp = temp.getNext();
        }

        // Crie um array do tamanho calculado
        Object[] result = new Object[length];

        // Preencha o array com os dados dos nós entre as posições especificadas
        int i = 0;
        while (current != null && i < length) {
            result[i] = current.getElement();
            i++;
            current = current.getNext();
        }

        return result;
    }

    /**
     * verificar se a lista está vazia
     *
     * @return true caso esteja vazia, false caso contrario
     */
    public boolean isEmpty() {
        return size <= 0;
    }
}
