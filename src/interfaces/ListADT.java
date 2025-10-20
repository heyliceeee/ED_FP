package interfaces;

import exceptions.EmptyCollectionException;

import java.util.Iterator;

public interface ListADT<T> extends Iterable<T> {
    /**
     * remove e retorna o primeiro elemento da lista
     * @return o primeiro elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    T removeFirst() throws EmptyCollectionException;

    /**
     * remove e retorna o ultimo elemento da lista
     * @return o ultimo elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    T removeLast() throws EmptyCollectionException;

    /**
     * remove e retorna o elemento especifico da lista
     * @param elem o elemento especifico a ser removido
     * @return o elemento especifico removido da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    T remove(T elem) throws EmptyCollectionException;

    /**
     * retorna o primeiro elemento da lista
     * @return o primeiro elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    T first() throws EmptyCollectionException;

    /**
     * retorna o ultimo elemento da lista
     * @return o ultimo elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    T last() throws EmptyCollectionException;

    /**
     * retorna true se na lista contem o elemento especifico
     * @param elem o elemento especifico a ser encontrado
     * @return true se encontra o elemento especifico, false caso contrario
     */
    boolean contains(T elem);

    /**
     * retorna true se a lista tiver vazia
     * @return true se a lista tiver vazia, false caso contrario
     */
    boolean isEmpty();

    /**
     * retorna o numero de elementos da lista
     * @return o numero de elementos da lista
     */
    int size();

    /**
     * retorna um iterador dos elementos da lista
     * @return um iterador dos elementos da lista
     */
    Iterator<T> iterator();

    /**
     * retorna em string a lista
     * @return em string a lista
     */
    String toString();
}
