package structures.FP06;

import exceptions.EmptyCollectionException;
import interfaces.SmackStackADT;
import structures.FP03.ArrayStack;

public class ArraySmackStack<T> extends ArrayStack<T> implements SmackStackADT<T> {

    /**
     * Construtor padrão - usa capacidade default do ArrayStack
     */
    public ArraySmackStack() {
        super();
    }

    /**
     * Construtor com capacidade específica
     *
     * @param initialCapacity a capacidade inicial da stack
     */
    public ArraySmackStack(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Remove e retorna o elemento do fundo da stack (base)
     * Esta operação é específica da SmackStack
     *
     * @return o elemento do fundo da stack
     * @throws EmptyCollectionException se a stack estiver vazia
     */
    @Override
    public T smack() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("ArraySmackStack");

        // Elemento do fundo está na posição 0 do array
        T bottomElement = stack[0];

        // Desloca todos os elementos uma posição para a esquerda
        for (int i = 0; i < top - 1; i++)
            stack[i] = stack[i + 1];

        // Limpa a última posição e atualiza top
        stack[top - 1] = null;
        top--;

        return bottomElement;
    }

    /**
     * Retorna o elemento do fundo da stack sem remover
     *
     * @return o elemento do fundo da stack
     * @throws EmptyCollectionException se a stack estiver vazia
     */
    public T peekBottom() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("ArraySmackStack");
        return stack[0];
    }

    /**
     * Retorna uma representação em string da SmackStack
     * Mostra tanto o topo quanto o fundo da stack
     *
     * @return string representando a stack
     */
    @Override
    public String toString() {
        if (isEmpty())
            return "ArraySmackStack[]";

        StringBuilder sb = new StringBuilder();
        sb.append("ArraySmackStack[");

        // Mostra do fundo para o topo (base to top)
        for (int i = 0; i < top; i++) {
            sb.append(stack[i]);
            if (i < top - 1)
                sb.append(" ← ");
        }

        sb.append("] (Bottom: ").append(stack[0]).append(", Top: ").append(stack[top - 1]).append(")");
        return sb.toString();
    }
}
