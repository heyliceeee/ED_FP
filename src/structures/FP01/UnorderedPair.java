package structures.FP01;

public class UnorderedPair<T extends Comparable<T>> extends Pair<T> {
    public UnorderedPair() {
        setFirst(null);
        setSecond(null);
    }

    public UnorderedPair(T firstItem, T secondItem) {
        setFirst(firstItem);
        setSecond(secondItem);
    }

    // Métodos para obter os valores
    public T getFirstElement() {
        return getFirst();
    }

    public T getSecondElement() {
        return getSecond();
    }

    // Método para verificar se os dois elementos são iguais
    public boolean hasEqualElements() {
        if (getFirst() == null || getSecond() == null) {
            return false; // evita NullPointerException
        }
        return getFirst().equals(getSecond());
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;

        } else if (getClass() != otherObject.getClass()) {
            return false;

        } else {
            UnorderedPair<T> otherPair = (UnorderedPair<T>) otherObject;

            return (getFirst().equals(otherPair.getFirst()) && getSecond().equals(otherPair.getSecond()))
                    ||
                    (getFirst().equals(otherPair.getSecond()) && getSecond().equals(otherPair.getFirst()));
        }
    }
}
