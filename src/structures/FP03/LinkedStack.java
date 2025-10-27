package structures.FP03;

import exceptions.EmptyCollectionException;
import interfaces.StackADT;
import structures.FP02.LinkedListNode;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements StackADT<T>, Iterable<T> {

    private LinkedListNode<T> top; // node que representa o elemento top da stack
    private int size; // int que representa o tamanho da stack
    private int modCount;

    /**
     * cria uma stack vazia e nula
     */
    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * cria uma stack
     * @param top representa o elemento top da stack
     * @param size representa o tamanho da stack
     */
    public LinkedStack(LinkedListNode<T> top, int size) {
        this.top = top;
        this.size = size;
    }


    /**
     * adiciona um elemento específico ao top da stack, expandindo a capacidade da stack se necessário
     *
     * @param elem elemento genérico a ser colocado na stack
     */
    @Override
    public void push(T elem) {
        LinkedListNode<T> newNode = new LinkedListNode<T>(elem); //novo elemento a ser adicionado

        newNode.setNext(top); //o elemento seguinte ao novo elemento = top
        top = newNode; //top = novo elemento

        size++;
        modCount++;
    }

    /**
     * remove o elemento do top da stack e retorna a referência dele
     * lança uma EmptyCollectionException se a stack estiver vazia
     *
     * @return T elemento removido do top da stack
     * @throws EmptyCollectionException se a remoção foi tentada numa stack vazia
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if(isEmpty()) //se stack estiver vazia
            throw new EmptyCollectionException("Stack");

        T result = top.getElement(); //elemento top
        top = top.getNext(); //top = elemento seguinte ao removido

        size--;
        modCount++;

        return result;
    }

    /**
     * retorna uma referência ao elemento do top da stack. o elemento não é removido da stack
     * lança uma EmptyCollectionException se a stack estiver vazia
     *
     * @return T elemento do top da stack
     * @throws EmptyCollectionException se a observação foi tentada numa stack vazia
     */
    @Override
    public T peek() throws EmptyCollectionException {
        if(isEmpty()) //se stack estiver vazia
            throw new EmptyCollectionException("Stack");

        return top.getElement(); //elemento top
    }

    /**
     * retorna true se a stack não conter elementos
     *
     * @return boolean dependendo se a stack está vazia
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * retorna o número de elementos da stack
     *
     * @return int número de elementos da stack
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        LinkedListNode<T> current = top;

        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }

        sb.append("]");
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
        private LinkedListNode<T> current;
        private int expectedModCount;
        private boolean canRemove;
        private LinkedListNode<T> lastReturned;

        /**
         * Construtor do iterador para LinkedStack
         * Percorre do topo para a base (ordem LIFO)
         */
        public MyIterator() {
            this.current = top; // Começa no topo da stack
            this.expectedModCount = modCount;
            this.canRemove = false;
            this.lastReturned = null;
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
                throw new NoSuchElementException("No more elements in stack iteration");

            T element = current.getElement();
            lastReturned = current;
            current = current.getNext(); // Move para o próximo nó (em direção à base)
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

            // Remoção em stack é complexa - geralmente não suportada ou limitada
            // Implementação básica que remove apenas se for o topo
            if (lastReturned == top) {
                try {
                    pop(); // Remove o topo usando o método existente
                } catch (EmptyCollectionException e) {
                    throw new IllegalStateException("Cannot remove from empty stack");
                }
            } else
                throw new UnsupportedOperationException("Can only remove top element from stack iterator");

            // Atualiza o estado do iterador
            expectedModCount = modCount; // pop() incrementou modCount
            canRemove = false;
            lastReturned = null;

            // O current já foi atualizado pelo pop() se era o topo
            // Se não era o topo, mantém o current atual
        }

        /**
         * Verifica se houve modificação concorrente na stack
         */
        private void checkForComodification() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException("Stack modified during iteration");
        }
    }
}
