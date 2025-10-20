package interfaces;

public interface UnorderedListADT<T> extends ListADT<T> {
    /**
     * adiciona o elemento especifico no inicio da lista
     * @param elem o elemento a ser adicionado no inicio da lista
     */
    void addToFront(T elem);

    /**
     * adiciona o elemento especifico no fim da lista
     * @param elem o elemento a ser adicionado no fim da lista
     */
    void addToRear(T elem);

    /**
     * adiciona o elemento especifico depois do elemento alvo
     * @param elem o elemento a ser adicionado depois do elemento alvo
     * @param target o elemento alvo em que o elemento especifico sera adicionado depois do mesmo
     */
    void addAfter(T elem, T target);
}
