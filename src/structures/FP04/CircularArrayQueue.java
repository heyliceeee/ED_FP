package structures.FP04;

import exceptions.EmptyCollectionException;
import interfaces.QueueADT;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class CircularArrayQueue<T> implements QueueADT<T>, Iterable<T> {
    private final int DEFAULT_CAPACITY = 100; // constante para representar a capacidade default do array
    private T[] queue; // array de elementos genéricos que representam a queue
    private T[] newQueue; // array de elementos genéricos que representam uma nova queue
    private int rear; // int que representa o elemento rear da queue
    private int front; // int que representa o elemento front da queue
    private int size; // int que representa o tamanho da queue
    private int modCount;

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
        modCount++;
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
            modCount++;
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

        modCount++;
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

    /**
     * retorna um iterador dos elementos da lista
     *
     * @return um iterador dos elementos da lista
     */
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int current;
        private int count;
        private int expectedModCount;

        /**
         * Construtor do iterador para CircularArrayQueue
         * Percorre da frente para trás (ordem FIFO) considerando a circularidade
         */
        public MyIterator() {
            this.current = front; // Começa no front da queue
            this.count = 0;
            this.expectedModCount = modCount;
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
            return count < size; // Para após percorrer todos os elementos
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

            if (!hasNext())
                throw new NoSuchElementException("No more elements in circular queue iteration");

            T element = queue[current];
            current = (current + 1) % queue.length; // Move circularmente para o próximo
            count++;

            return element;
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation). This method can be called
         * only once per call to {@link #next}.
         *
         * @throws UnsupportedOperationException se a operação remove não for suportada
         * @throws IllegalStateException se next() não foi chamado ou remove() já foi chamado
         */
        @Override
        public void remove() {
            // Em estruturas baseadas em array, remove() é complexo e geralmente não suportado
            // ou apenas suportado para o elemento atual com lógica complexa
            throw new UnsupportedOperationException("Remove operation not supported for CircularArrayQueue iterator");

            // Alternativa: Implementação complexa que requer reindexação
            // É mais seguro não suportar remove() em iteradores de array circular
        }

        /**
         * Verifica se houve modificação concorrente na queue circular
         */
        private void checkForComodification() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException("Circular queue modified during iteration");
        }
    }
}
