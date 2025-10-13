package structures.FP04;

import exceptions.EmptyCollectionException;
import interfaces.QueueADT;
import structures.FP02.LinkedListNode;

/**
 * Implementação de uma fila (queue) genérica utilizando uma lista encadeada.
 * <p>
 * Esta classe implementa a interface {@code QueueADT} e oferece operações básicas
 * como inserção, remoção e consulta de elementos na fila.
 * </p>
 *
 * @param <T> O tipo de elementos armazenados na fila.
 */
public class LinkedQueue<T> implements QueueADT<T> {

    private LinkedListNode<T> front; // Nó que representa o elemento na frente da fila.
    private LinkedListNode<T> rear; // Nó que representa o elemento na traseira da fila.
    private int size; // Inteiro que representa o tamanho atual da fila.


    /**
     * Cria uma fila vazia.
     * <p>
     * A fila é inicializada com {@code front} e {@code rear} nulos e tamanho 0.
     * </p>
     */
    public LinkedQueue() {
        this.front = this.rear = null;
        this.size = 0;
    }

    /**
     * Cria uma fila com elementos predefinidos.
     *
     * @param front O nó que será o primeiro elemento da fila.
     * @param rear  O nó que será o último elemento da fila.
     * @param size  O tamanho inicial da fila.
     */
    public LinkedQueue(LinkedListNode<T> front, LinkedListNode<T> rear, int size) {
        this.front = front;
        this.rear = rear;
        this.size = size;
    }


    /**
     * Enfileira (adiciona) um elemento ao final (rear) da fila.
     *
     * @param element o elemento a ser adicionado na parte traseira da fila.
     *                Não deve ser nulo. Se for nulo, o comportamento depende da implementação.
     */
    @Override
    public void enqueue(T element) {
        LinkedListNode<T> newNode = new LinkedListNode<>(element);

        if (isEmpty()) { // Se a fila está vazia
            front = newNode;
            rear = newNode;

        } else {
            rear.setNext(newNode); // O próximo do nó traseiro é o novo nó
            rear = newNode;       // Atualiza o nó traseiro
        }

        size++;
    }

    /**
     * Desenfileira (remove) e retorna o elemento na frente (front) da fila.
     * Caso a fila esteja vazia, o comportamento depende da implementação:
     * - Poderá lançar uma exceção (por exemplo, EmptyCollectionException).
     *
     * @return o elemento que estava na frente da fila.
     * @throws RuntimeException se a fila estiver vazia (a exceção específica depende da implementação).
     */
    @Override
    public T dequeue() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue");

        T result = front.getElement(); // Obtém o elemento do nó frontal
        front = front.getNext();       // Avança o nó frontal para o próximo

        size--; // decrementa-se o tamanho do queue, porque retirou-se um elemento

        if (isEmpty()) // Se a fila está vazia após a remoção
            rear = null;

        return result;
    }

    /**
     * Retorna, sem remover, o elemento que está na frente (front) da fila.
     * Caso a fila esteja vazia, o comportamento também depende da implementação:
     * - Poderá lançar uma exceção (por exemplo, EmptyCollectionException).
     *
     * @return o primeiro elemento da fila.
     * @throws RuntimeException se a fila estiver vazia (a exceção específica depende da implementação).
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Queue");

        return front.getElement();
    }

    /**
     * Verifica se a fila está vazia, ou seja, sem elementos.
     *
     * @return true se a fila não contiver elementos, false caso contrário.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retorna o número de elementos contidos na fila.
     *
     * @return a quantidade de elementos na fila.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Retorna uma representação em string da fila.
     *
     * @return Uma string contendo os elementos da fila.
     */
    @Override
    public String toString() {
        return "LinkedQueue{" +
                "front=" + front +
                ", rear=" + rear +
                ", size=" + size +
                '}';
    }
}
