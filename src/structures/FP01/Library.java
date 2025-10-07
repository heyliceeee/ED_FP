package structures.FP01;

import java.util.ArrayList;
import java.util.List;

public class Library<T> {
    private List<T> items = new ArrayList<>();

    // Adiciona um item
    public void addItem(T item) {
        items.add(item);
    }

    // Obtém um item pelo índice
    public T getItem(int index) {
        return items.get(index);
    }

    // Retorna todos os itens
    public List<T> getAllItems() {
        return items;
    }

    // Número de itens
    public int size() {
        return items.size();
    }
}
