package structures.FP04;

import exceptions.EmptyCollectionException;
import interfaces.QueueADT;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

public class CircularArrayQueue<T> implements QueueADT<T> {
    private final int DEFAULT_CAPACITY = 100; // constante para representar a capacidade default do array
    private T[] queue; // array de elementos genéricos que representam a queue
    private T[] newQueue; // array de elementos genéricos que representam uma nova queue
    private int rear; // int que representa o elemento rear da queue
    private int front; // int que representa o elemento front da queue
    private int size; // int que representa o tamanho da queue


    /**
     * cria um array vazia e nula
     */
    public CircularArrayQueue() {
        this.size = this.front = this.rear = 0;
        this.queue = (T[]) (new Object[this.DEFAULT_CAPACITY]);
    }

    /**
     * cria um array
     */
    public CircularArrayQueue(int initialCapacity) {
        this.size = this.front = this.rear = 0;
        int actualCapacity = Math.max(initialCapacity, 1);
        this.queue = (T[]) (new Object[actualCapacity]);
    }


    /**
     * Enfileira (adiciona) um elemento ao final (rear) da fila.
     *
     * @param element o elemento a ser adicionado na parte traseira da fila.
     *                Não deve ser nulo. Se for nulo, o comportamento depende da implementação.
     */
    @Override
    public void enqueue(T element) {
        if(isFull()) //se a queue tiver cheia
            expandCapacity(); //expande

        queue[rear] = element; //rear = novo elemento no final
        rear = (rear + 1) % queue.length; //rear volta á posicao inicial
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
        T result;

        if(isEmpty()) //se queue estiver vazia
            throw new EmptyCollectionException("Queue");

        else {
            result = queue[front]; //elemento front
            queue[front] = null; //elemento front passa a ser nulo (foi removido)

            front = (front + 1) % queue.length; //front volta á posicao inicial

            size--;
        }

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
        if(isEmpty()) //se queue estiver vazia
            throw new EmptyCollectionException("Queue");

        return queue[front];
    }

    /**
     * Verifica se a fila está vazia, ou seja, sem elementos.
     *
     * @return true se a fila não contiver elementos, false caso contrário.
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
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
     * retorna true consoante se a queue tiver cheia
     * @return true consoante se a queue tiver cheia
     */
    public boolean isFull()
    {
        return (size == queue.length);
    }

    /**
     * expande o array
     */
    private void expandCapacity() {
        newQueue = (T[]) (new Object[queue.length == 0 ? DEFAULT_CAPACITY : queue.length * 2]); // Dobrar a capacidade

        for (int i = 0; i < size; i++) // Copiar elementos mantendo a ordem FIFO considerando a circularidade
            newQueue[i] = queue[(front + i) % queue.length];

        queue = newQueue;
        front = 0;    // Reset front para início
        rear = size;  // Rear aponta para próxima posição vazia
    }

    @Override
    public String toString() {
        return "CircularArrayQueue{" +
                "DEFAULT_CAPACITY=" + DEFAULT_CAPACITY +
                ", queue=" + Arrays.toString(queue) +
                ", newQueue=" + Arrays.toString(newQueue) +
                ", rear=" + rear +
                ", front=" + front +
                ", size=" + size +
                '}';
    }
}
