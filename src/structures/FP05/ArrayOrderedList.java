package structures.FP05;

import exceptions.EmptyCollectionException;
import interfaces.OrderedListADT;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class ArrayOrderedList<T>  implements OrderedListADT<T>, Iterable<T> {
    private final int DEFAULT_CAPACITY = 100; // constante para representar a capacidade default do array
    private T[] list; // array de elementos genericos que representam a lista
    private T[] newList; // array de elementos genericos que representam uma nova lista
    private int rear; // inteiro que representa o elemento do fim da lista
    private int front; // inteiro que representa o elemento do inicio da lista
    private int size; // inteiro que representa o tamanho da lista
    private int modCount; //

    /**
     * cria um array vazio e nulo
     */
    public ArrayOrderedList() {
        this.size = this.rear = this.front = this.modCount = 0;
        this.list = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * cria um array com um tamanho inicial definido
     * @param initialCapacity tamanho inicial definido
     */
    public ArrayOrderedList(int initialCapacity) {
        this.size = this.rear = this.front = this.modCount = 0;
        this.list = (T[]) (new Object[initialCapacity]);
    }


    /**
     * adiciona o elemento especifico na lista, na posicao adequada
     *
     * @param elem o elemento a ser adicionado na lista
     */
    @Override
    public void add(T elem) throws IllegalArgumentException {
        if (elem == null)
            throw new IllegalArgumentException("Elemento não pode ser null");

        if(!(elem instanceof Comparable)) // Verifica se o elemento implementa a interface Comparable, isso é necessário para garantir que pode ser ordenado com outros elementos
            throw new IllegalArgumentException("O elemento ou classe não é comparável");

        if(size() == list.length) // Verifica se o array interno está cheio e precisa de expansão
            expandCapacity();

        Comparable<T> temp = (Comparable<T>) elem; //elemento a ser comparado (para conseguir ser ordenado e assim adicionado na lista)
        int insertionIndex=0;

        while (insertionIndex < rear && temp.compareTo((T) list[insertionIndex]) > 0) //Percorre a lista até encontrar a posição correta para inserção
            insertionIndex++;

        for(int j=this.rear; j > insertionIndex; j--) //Desloca todos os elementos da posição i até o final para a direita para abrir espaço para o novo elemento
            list[j] = list[j-1]; // Move o elemento uma posição para a direita

        this.list[insertionIndex] = elem; //na posicao atual vai ficar o elemento adicionado

        this.rear++; // incrementa a posicao do fim da lista
        this.size++; // incrementa o tamanho da lista
        this.modCount++; // incrementa apos modificacao bem sucedida
    }

    /**
     * remove e retorna o primeiro elemento da lista
     *
     * @return o primeiro elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if(isEmpty()) // Verifica se o array está vazio
            throw new EmptyCollectionException("Array Ordered List");

        T result = list[front]; // Guarda o primeiro elemento para retornar no final
        list[front] = null; //remover primeiro elemento

        // Desloca todos os elementos uma posição para a esquerda para preencher o espaço vago pelo elemento removido
        for(int i=0; i < this.rear; i++)
            this.list[i] = this.list[i+1];

        this.rear--; // reduz a posicao do fim da lista
        this.size--; // reduz o tamanho da lista
        this.modCount++; // incrementa apos modificacao bem sucedida

        return result;
    }

    /**
     * remove e retorna o ultimo elemento da lista
     *
     * @return o ultimo elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if(isEmpty()) // Verifica se o array está vazio
            throw new EmptyCollectionException("Array Ordered List");

        this.rear--; // reduz a posicao do fim da lista

        T result = list[rear]; // Guarda o ultimo elemento para retornar no final
        list[rear] = null; //remover ultimo elemento

        this.size--; //reduz o tamanho da lista
        this.modCount++; // incrementa apos modificacao bem sucedida

        return result;
    }

    /**
     * remove e retorna o elemento especifico da lista
     *
     * @param elem o elemento especifico a ser removido
     * @return o elemento especifico removido da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T remove(T elem) throws EmptyCollectionException {
        if(isEmpty()) // Verifica se o array está vazio
            throw new EmptyCollectionException("Array Ordered List");

        if(!contains(elem)) //elemento nao existir
            throw new EmptyCollectionException("elemento nao existe");

        int pos = -1; //posicao do elemento a ser removido (-1 porque nao foi encontrado)

        for(int i=0; i < this.rear; i++) { // Procura o elemento e PARA na primeira ocorrência
            if(elem.equals(list[i])) { //encontrou elemento
                pos = i;
                break;
            }
        }

        if (pos == -1) // Verifica se o elemento foi encontrado
            throw new EmptyCollectionException("Elemento não existe");

        T result = (T) list[pos]; // Guarda o elemento especifico para retornar no final

        for(int i=pos; i < this.rear - 1; i++) //Desloca todos os elementos após a posição removida para a esquerda. Começa na posição do elemento e vai até ao penúltimo elemento
            this.list[i] = this.list[i+1];

        this.list[this.rear - 1] = null; //limpa a última posição que ficou duplicada

        this.size--; //reduz o tamanho da lista
        this.rear--;
        this.modCount++; // incrementa apos modificacao bem sucedida

        return result; // retorna o elemento que foi removido
    }

    /**
     * retorna o primeiro elemento da lista
     *
     * @return o primeiro elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T first() throws EmptyCollectionException {
        if(isEmpty()) // Verifica se o array está vazio
            throw new EmptyCollectionException("Array Ordered List");

        return list[front]; // retorna o primeiro elemento da lista
    }

    /**
     * retorna o ultimo elemento da lista
     *
     * @return o ultimo elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T last() throws EmptyCollectionException {
        if(isEmpty()) // Verifica se o array está vazio
            throw new EmptyCollectionException("Array Ordered List");

        return list[rear - 1]; // retorna o ultimo elemento da lista
    }

    /**
     * retorna true se na lista contem o elemento especifico
     *
     * @param elem o elemento especifico a ser encontrado
     * @return true se encontra o elemento especifico, false caso contrario
     */
    @Override
    public boolean contains(T elem) {
        boolean found = false;

        for(int i=0; i < this.rear; i++) {
            if(elem.equals(list[i])) //se encontrou o elemento na lista
                found = true;
        }

        return found;
    }

    /**
     * retorna true se a lista tiver vazia
     *
     * @return true se a lista tiver vazia, false caso contrario
     */
    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * retorna o numero de elementos da lista
     *
     * @return o numero de elementos da lista
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * retorna um iterador dos elementos da lista
     *
     * @return um iterador dos elementos da lista
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    /**
     * se a lista chegou ao limite de capacidade, vai se expandir
     */
    private void expandCapacity() {
        newList = (T[]) (new Object[DEFAULT_CAPACITY]); //cria uma nova lista vazia

        T[] joinLists = Stream.concat(Arrays.stream(list), Arrays.stream(newList)).toArray(size -> (T[]) Array.newInstance(list.getClass().getComponentType(), size)); //junta as 2 listas (lista atual e a newList)

        list = joinLists; // a lista atual expande de tamanho
    }

    /**
     * Remove o elemento no índice especificado. Método auxiliar para ser usado pelo iterador.
     *
     * @param index o índice do elemento a remover
     * @return o elemento que foi removido
     * @throws IndexOutOfBoundsException se o índice for inválido
     */
    private T removeAt(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= rear) // Valida o índice
            throw new IndexOutOfBoundsException("Índice inválido: " + index);

        T result = (T) list[index];// Guarda o elemento que será removido

        for (int i = index; i < rear - 1; i++)// Desloca todos os elementos após o índice removido para a esquerda
            list[i] = list[i + 1];

        // Limpa a última posição e atualiza os contadores
        list[rear - 1] = null;
        this.rear--;
        this.modCount++; // Incrementa o contador de modificações

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rear; i++) {
            if (list[i] != null) {
                sb.append(list[i]);
                if (i < rear - 1)
                    sb.append("\n");
            }
        }
        return sb.toString();
    }

    private class MyIterator<T> implements Iterator<T> {
        private int current, lastReturnedIndex, expectedModCount;
        private final int modCount;
        private final T[] items;


        public MyIterator() {
            this.current = 0;
            this.lastReturnedIndex = -1;
            this.modCount = ArrayOrderedList.this.rear;
            this.expectedModCount = ArrayOrderedList.this.size;
            this.items = (T[]) ArrayOrderedList.this.list;
        }


        /**
         * Retorna {@code true} se a iteracao tiver mais elementos
         *
         * @return {@code true} se a iteracao tiver mais elementos
         * @throws ConcurrentModificationException se a lista for modificada durante a iteracao
         */
        @Override
        public boolean hasNext() throws ConcurrentModificationException {
            if(expectedModCount != modCount) // Verifica se a lista foi modificada durante a iteração. Compara o tamanho esperado (capturado no início da iteração) com o tamanho atual da lista
                throw new ConcurrentModificationException("concorrência");

            return (this.current < rear); // Verifica se o indice atual é válido
        }

        /**
         * Retorna o elemento seguinte da iteracao
         *
         * @return o elemento seguinte da iteracao
         * @throws NoSuchElementException se a iteracao nao tiver mais elementos
         */
        @Override
        public T next() {
            if(!hasNext()) // verifica se nao ha mais elementos
                throw new NoSuchElementException("Não há mais elementos na iteração. Current: " + current + ", Rear: " + rear);

            T temp = (T) items[this.current]; // acesso ao elemento atual

            lastReturnedIndex = current; // guarda o indice do elemento que esta a ser retornado

            this.current++; // prepara para o proximo elemento

            return temp; // retorna o elemento
        }

        /**
         * Remove da lista o último elemento retornado por este iterador. Este método só pode ser chamado uma vez por cada chamada a {@link #next}.
         *
         * @throws IllegalStateException se o método {@code next} não tiver sido chamado, ou se o método {@code remove} já tiver sido chamado após a última chamada a {@code next}
         * @throws ConcurrentModificationException se a lista for modificada durante a iteração
         */
        @Override
        public void remove() throws ConcurrentModificationException {
            if(expectedModCount != modCount) // verifica se ha modificacoes concorrentes na lista
                throw new ConcurrentModificationException("concorrencia");

            if(lastReturnedIndex == -1) //verifica se next() foi chamado e se remove() não foi chamado já para este elemento
                throw new IllegalArgumentException("next() deve ser chamado antes de remove(), e remove() só pode ser chamado uma vez por elemento");

            try {
                ArrayOrderedList.this.removeAt(lastReturnedIndex); // Remove o elemento da lista, usando o indice diretamente

                if (current > lastReturnedIndex) // Se current estava após o elemento removido, decrementamos
                    current--;

                lastReturnedIndex = -1; // Reseta lastReturnedIndex para prevenir múltiplas chamadas a remove()

                expectedModCount = modCount; // Atualiza o expectedModCount porque fizemos uma modificação através do iterador

            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException("Elemento não encontrado durante remoção");
            }
        }
    }
}
