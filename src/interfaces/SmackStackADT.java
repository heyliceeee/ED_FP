package interfaces;

import exceptions.EmptyCollectionException;

/**
 * Interface SmackStackADT - Stack com operação Smack adicional
 * Smack: remove e retorna o elemento do fundo da stack (base)
 *
 * @param <T> o tipo de elementos na stack
 */
public interface SmackStackADT<T> extends StackADT<T> {
    /**
     * Remove e retorna o elemento do fundo da stack (base)
     * Esta operação é específica da SmackStack
     *
     * @return o elemento do fundo da stack
     * @throws EmptyCollectionException se a stack estiver vazia
     */
    T smack() throws EmptyCollectionException;
}
