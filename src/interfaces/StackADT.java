package interfaces;

import exceptions.EmptyCollectionException;

public interface StackADT<T> {
    /**
     * adiciona um elemento específico ao top da stack, expandindo a capacidade da stack se necessário
     * @param elem elemento genérico a ser colocado na stack
     */
    void push(T elem);

    /**
     * remove o elemento do top da stack e retorna a referência dele
     * lança uma EmptyCollectionException se a stack estiver vazia
     * @return T elemento removido do top da stack
     * @throws EmptyCollectionException se a remoção foi tentada numa stack vazia
     */
    T pop();

    /** retorna uma referência ao elemento do top da stack. o elemento não é removido da stack
     * lança uma EmptyCollectionException se a stack estiver vazia
     * @return T elemento do top da stack
     * @throws EmptyCollectionException se a observação foi tentada numa stack vazia
     */
    T peek();

    /**
     * retorna true se a stack não conter elementos
     * @return boolean dependendo se a stack está vazia
     */
    boolean isEmpty();

    /**
     * retorna o número de elementos da stack
     * @return int número de elementos da stack
     */
    int size();


    @Override
    String toString();
}
