package structures.FP02;

import exceptions.EmptyCollectionException;
import structures.FP05.ArrayOrderedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementação de uma lista ligada genérica.
 * <p>
 *  Esta classe fornece funcionalidades básicas para manipulação de uma lista ligada, como adição, remoção, verificação de elementos e iteração.
 * </p>
 *
 * @param <T> O tipo de dados armazenados na lista.
 */
public class LinkedList<T> {

    private LinkedListNode<T> head; // primeiro elemento da lista
    private LinkedListNode<T> tail; // ultimo elemento da lista
    private SentinelNode<T> sentinel; // o no sentinela
    private int size; // o tamanho da lista
    private int modCount = 0;

    /**
     * Construtor padrão que inicializa uma lista ligada vazia.
     */
    public LinkedList(){
        this.size = 0;
        this.head = null;
        sentinel = new SentinelNode<T>(null); // node especial sem dados
    }

    /**
     * adicionar no inicio da lista
     * @param elem
     */
    public void addFirst(T elem) {
        LinkedListNode<T> newNode = new LinkedListNode<T>(elem); // criar um novo node com os dados

        if (head == null) { // se a lista estiver vazia
            head = newNode; // o novo node fica na cabeça
            tail = newNode; // e também na cauda

            size++;

        } else { // se a lista NÃO estiver vazia
            newNode.setNext(head); // o novo node aponta para o antigo head
            head = newNode;        // o novo node passa a ser o head

            size++;
            modCount++;
        }
    }

    /**
     * adicionar no início da lista, utilizando node sentinela
     * @param elem
     */
    public void addFirstSentinel(T elem) {
        SentinelNode<T> newNode = new SentinelNode<T>(elem); // criar novo node com os dados

        // o novo nó aponta para o primeiro elemento real (que pode ser null)
        newNode.setNext(sentinel.getNext());

        // o sentinela passa a apontar para o novo nó
        sentinel.setNext(newNode);

        size++;
        modCount++;
    }

    /**
     * adicionar no fim da lista
     * @param elem
     */
    public void addLast(T elem) {
        LinkedListNode<T> newNode = new LinkedListNode<T>(elem); //criar um novo node com os dados

        if(head == null){ //se a lista estiver vazia
            head = newNode; //o novo node fica na cabeca (torna-se o primeiro)
            tail = newNode; //o novo node fica na cauda (torna-se o último)

            size++;

        } else { //se a lista NAO estiver vazia
            tail.setNext(newNode); //adicionar um novo node ao próximo elemento de tail
            tail = newNode; //tail fica com o valor do novo node

            size++;
            modCount++;
        }
    }

    /**
     * adicionar no fim da lista, utilizando node sentinela
     * @param elem
     */
    public void addLastSentinel(T elem) {
        SentinelNode<T> newNode = new SentinelNode<T>(elem); //criar um novo node com os dados
        SentinelNode<T> current = sentinel; //elemento

        while (current.getNext() != null) //vai correr a lista até á cauda (último elemento)
            current = current.getNext(); //vai para o seguinte elemento

        current.setNext(newNode); //adicionar um novo node á cauda (último elemento)
        size++;
        modCount++;
    }

    /**
     * Remove o primeiro elemento da lista (head).
     */
    public T removeFirst() throws EmptyCollectionException {
        if (head == null || size == 0) // lista vazia
            throw new EmptyCollectionException("Linked List");

        T removedElem = head.getElement(); //guarda o elemento do primeiro no antes de remover

        head = head.getNext(); // avança a cabeça para o próximo nó
        this.size--;

        // se a lista ficou vazia, tail também deve ser null
        if (head == null)
            tail = null;

        modCount++;

        return removedElem;
    }

    /**
     * Remove o primeiro elemento real da lista (logo a seguir ao sentinela).
     */
    public void removeFirstSentinel() throws  EmptyCollectionException {
        if (sentinel.getNext() == null || size == 0) // lista vazia
            throw new EmptyCollectionException("List");

        // o sentinela passa a apontar para o segundo elemento real
        sentinel.setNext(sentinel.getNext().getNext());
        size--;
        modCount++;
    }

    /**
     * Remove o último elemento da lista (tail).
     */
    public T removeLast() throws EmptyCollectionException {
        if (head == null || size == 0) // lista vazia
            throw new EmptyCollectionException("Linked List");

        T removedElem;

        if (head == tail) { // só existe um elemento
            removedElem = head.getElement();
            head = null;
            tail = null;
        } else {
            LinkedListNode<T> current = head;

            while (current.getNext() != tail) // percorre ate ao penultimo no
                current = current.getNext();

            removedElem = tail.getElement(); // guarda o elemento do ultimo no antes de remover

            // corta a referencia para o ultimo no
            current.setNext(null);
            tail = current; // atualiza a cauda
        }

        this.size--;
        modCount++;

        return removedElem;
    }

    /**
     * Remove o último elemento da lista (tail), usando nó sentinela.
     */
    public void removeLastSentinel() throws EmptyCollectionException {
        if (sentinel.getNext() == null || size == 0) // lista vazia
            throw new EmptyCollectionException("List");

        // se só existe um elemento real
        if (sentinel.getNext().getNext() == null) {
            sentinel.setNext(null); // lista fica vazia (só o sentinela sobra)
            size = 0;
            return;
        }


        SentinelNode<T> current = sentinel;
        while (current.getNext().getNext() != null) // percorre até ao penúltimo nó real
            current = current.getNext();

        // corta a referência para o último nó
        current.setNext(null);
        size--;
        modCount++;
    }

    /**
     * Imprime todos os elementos
     */
    public void printList() {
        LinkedListNode<T> current = head; //elemento da cabeca (primeiro elemento)

        System.out.print("LinkedList [");

        while (current != null){ //corre a lista toda
            System.out.print(current.getElement() + ", ");
            current = current.getNext(); //vai para o seguinte elemento
        }

        System.out.print("]\n");
    }

    /**
     * Imprime todos os elementos, com node sentinela
     */
    public void printListSentinel() {
        SentinelNode<T> current = sentinel; //elemento da cabeca (primeiro elemento)

        System.out.print("LinkedList Sentinel [");

        while (current != null){ //corre a lista toda
            System.out.print(current.getElement() + ", ");
            current = current.getNext(); //vai para o seguinte elemento
        }

        System.out.print("]\n");
    }

    /**
     * Imprime recursivamente
     */
    public void printRecursive(){
        printRecursive(head);
    }

    /**
     * Imprime recursivamente os elementos
     * @param node no atual
     */
    private void printRecursive(LinkedListNode<T> node){
        if(node == null) // caso base
            return;

        System.out.print(node.getElement() + " ");
        printRecursive(node.getNext()); // chamada recursiva
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        LinkedListNode<T> current = head;

        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Verifica se a lista está vazia.
     *
     * @return {@code true} se a lista estiver vazia, {@code false} caso contrário.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retorna o número de elementos na lista.
     *
     * @return O número de elementos na lista.
     */
    public int size() {
        return size;
    }

    /**
     * percorre a lista e substitui todas as ocorrencias de existingElem por newElem
     * @param existingElem elemento existente
     * @param newElem elemento novo
     */
    public void replace(T existingElem, T newElem) {
        replaceRecursive(head, existingElem, newElem);
    }

    /**
     * percorre a lista e substitui todas as ocorrencias de existingElem por newElem
     * @param node no atual
     * @param existingElem elemento existente
     * @param newElem elemento novo
     */
    private void replaceRecursive(LinkedListNode<T> node, T existingElem, T newElem) {
        if (node == null) // caso base: fim da lista
            return;

        if (node.getElement().equals(existingElem)) // encontrou ocorrencia
            node.setElement(newElem); // substitui pelo novo elemento

        replaceRecursive(node.getNext(), existingElem, newElem); // chamada recursiva
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
        private LinkedListNode<T> current;
        private LinkedListNode<T> lastReturned;
        private int expectedModCount;
        private boolean canRemove;

        /**
         * Construtor do iterador
         */
        public MyIterator() {
            this.current = head;
            this.lastReturned = null;
            this.expectedModCount = modCount;
            this.canRemove = false;
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
            return current != null;
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
                throw new NoSuchElementException("No more elements in iteration");

            T element = current.getElement();
            lastReturned = current;
            current = current.getNext();
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

            if (!canRemove)
                throw new IllegalStateException("remove() can only be called once after next()");

            if (lastReturned == null)
                throw new IllegalStateException("No element to remove");

            // Remove o nó lastReturned da lista
            if (lastReturned == head) {
                try {
                    removeFirst();
                } catch (EmptyCollectionException e) {
                    throw new IllegalStateException("Cannot remove from empty list");
                }
            } else {
                // Encontra o nó anterior ao lastReturned
                LinkedListNode<T> prev = head;
                while (prev != null && prev.getNext() != lastReturned)
                    prev = prev.getNext();

                if (prev != null) {
                    // Remove o nó do meio ou do final
                    prev.setNext(lastReturned.getNext());

                    // Se era o último nó, atualiza tail
                    if (lastReturned == tail)
                        tail = prev;
                    size--;
                    modCount++;
                }
            }

            // Atualiza o expectedModCount pois modificámos a lista
            expectedModCount = modCount;
            canRemove = false;
            lastReturned = null;
        }

        /**
         * Verifica se houve modificação concorrente na lista
         */
        private void checkForComodification() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException("List modified during iteration");
        }
    }
}
