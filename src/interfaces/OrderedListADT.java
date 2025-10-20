package interfaces;

public interface OrderedListADT<T> extends ListADT<T> {
    /**
     * adiciona o elemento especifico na lista, na posicao adequada
     * @param elem o elemento a ser adicionado na lista
     */
    void add(T elem);
}
