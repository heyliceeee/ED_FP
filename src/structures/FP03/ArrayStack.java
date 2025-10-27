package structures.FP03;


import exceptions.EmptyCollectionException;
import interfaces.StackADT;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements StackADT<T>, Iterable<T> {

    private final int DEFAULT_CAPACITY = 100; // constante para representar a capacidade default do array
    public int top; // int que representa o número de elementos e o seguinte posição disponivel no array
    public T[] stack; // array de elementos genéricos que representam a stack
    private  int modCount;

    /**
     * cria uma stack vazia utilizando a capacidade default
     */
    public ArrayStack(){
        top = 0;
        stack = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * cria uma stack vazia utilizando uma capacidade específica
     * @param initialCapacity representa a capacidade específica
     */
    public ArrayStack(int initialCapacity){
        top = 0;
        stack = (T[]) (new Object[initialCapacity]);
    }

    /**
     * adiciona um elemento específico ao top da stack, expandindo a capacidade da stack se necessário
     * @param elem elemento genérico a ser colocado na stack
     */
    @Override
    public void push(T elem) {
        if(size() == stack.length) //se a stack já atingiu a capacidade máx.
            expandCapacity();

        stack[top] = elem; //o novo elemento fica no top
        top++; //top aponta para o elemento seguinte (vazio)
        modCount++;
    }

    /**
     * expanda a capacidade da stack
     */
    private void expandCapacity() {
        int newCapacity = stack.length == 0 ? DEFAULT_CAPACITY : stack.length * 2; //cria uma nova stack com o dobro da capacidade da stack anterior
        T[] newStack = (T[]) new Object[newCapacity];

        //copiar os elementos da stack anterior para a nova stack
        for(int i=0; i < stack.length; i++)
            newStack[i] = stack[i];

        stack = newStack; //a stack agora é a stack expandida

        modCount++;
    }

    /**
     * remove o elemento do top da stack e retorna a referência dele
     * lança uma EmptyCollectionException se a stack estiver vazia
     * @return T elemento removido do top da stack
     * @throws EmptyCollectionException se a remoção foi tentada numa stack vazia
     */
    @Override
    public T pop() throws EmptyCollectionException {
        if(isEmpty()) //se stack estiver vazia
            throw new EmptyCollectionException("Stack");

        top--; //top aponta para o elemento anterior
        T result = stack[top]; //elemento top
        stack[top] = null; //elemento top removido
        modCount++;

        return result;
    }

    /** retorna uma referência ao elemento do top da stack. o elemento não é removido da stack
     * lança uma EmptyCollectionException se a stack estiver vazia
     * @return T elemento do top da stack
     * @throws EmptyCollectionException se a observação foi tentada numa stack vazia
     */
    @Override
    public T peek() throws EmptyCollectionException {

        if(isEmpty()) //se stack estiver vazia
            throw new EmptyCollectionException("Stack");

        return stack[top - 1]; //elemento top (oq n ta vazio)
    }

    /**
     * retorna true se a stack não conter elementos
     * @return boolean dependendo se a stack está vazia
     */
    @Override
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * retorna o número de elementos da stack
     * @return int número de elementos da stack
     */
    @Override
    public int size() {
        return top;
    }


    // No ArrayStack - toString corrigido
    @Override
    public String toString() {
        if (isEmpty())
            return "ArrayStack[]";

        StringBuilder sb = new StringBuilder();
        sb.append("ArrayStack[");

        // Mostra do TOPO para a BASE (ordem LIFO correta)
        for (int i = top - 1; i >= 0; i--) {
            sb.append(stack[i]);
            if (i > 0)
                sb.append(", ");
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
        private int current;
        private int expectedModCount;

        /**
         * Construtor do iterador para ArrayStack
         * Percorre do TOPO para a BASE (ordem LIFO)
         */
        public MyIterator() {
            this.current = top - 1; // Começa no topo (último elemento adicionado)
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
            return current >= 0; // Para quando chegar à base (índice 0)
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

            T element = stack[current];
            current--; // Move para o elemento anterior (em direção à base)

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
            // Em ArrayStack, remove() é complexo porque requer reindexação do array
            // É mais seguro não suportar remove() em iteradores de array-based structures
            throw new UnsupportedOperationException("Remove operation not supported for ArrayStack iterator");

            // Alternativa: Implementação seria muito complexa pois teria que:
            // 1. Saber qual elemento foi retornado por último
            // 2. Remover esse elemento do array
            // 3. Reindexar todos os elementos após a remoção
            // 4. Atualizar top e current
            // Isso quebraria a eficiência O(1) do stack
        }

        /**
         * Verifica se houve modificação concorrente no stack
         */
        private void checkForComodification() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificationException("Stack modified during iteration");
        }
    }
}
