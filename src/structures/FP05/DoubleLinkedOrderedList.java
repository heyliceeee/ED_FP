package structures.FP05;

import exceptions.EmptyCollectionException;
import interfaces.OrderedListADT;
import structures.FP02.DoublyLinkedList;
import structures.FP02.DoublyNode;
import structures.FP03.ArrayStack;
import structures.FP03.LinkedStack;
import structures.FP04.LinkedQueue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class DoubleLinkedOrderedList<T extends Comparable<T>> extends DoublyLinkedList<T> implements OrderedListADT<T>, Iterable<T> {
    private int modCount;

    public DoubleLinkedOrderedList() {
        super();
        this.modCount = 0;
    }


    /**
     * adiciona o elemento especifico na lista, na posicao adequada
     *
     * @param elem o elemento a ser adicionado na lista
     */
    @Override
    public void add(T elem) {
        if(elem == null)
            throw new IllegalArgumentException("Elemento não pode ser null");

        // Caso 1: Lista vazia - usa o método da classe pai
        if (isEmpty())
            super.addFirst(elem);  // ✅ CORREÇÃO: usar addFirst() em vez de addToFront()

        // Caso 2: Inserir no início (elemento é o menor)
        else if (elem.compareTo(first()) <= 0)
            super.addFirst(elem);  // ✅ CORREÇÃO: usar addFirst()

        // Caso 3: Inserir no final (elemento é o maior)
        else if (elem.compareTo(last()) > 0)
            addToEnd(elem);  // ✅ CORREÇÃO: método auxiliar pois não há addToRear()

        // Caso 4: Inserir no meio
        else
            addInOrder(elem);

        modCount++;
    }

    /**
     * Adiciona um elemento na posição ordenada correta no meio da lista
     */
    private void addInOrder(T elem) {
        DoublyNode<T> current = head.getNext(); // Começa do segundo elemento
        DoublyNode<T> newNode = new DoublyNode<T>(elem);

        // Encontra a posição correta para inserção
        while (current != null && elem.compareTo(current.getElement()) > 0)
            current = current.getNext();

        // Insere antes do nó current
        DoublyNode<T> previous = current.getPrev();

        previous.setNext(newNode);
        newNode.setPrev(previous);
        newNode.setNext(current);
        current.setPrev(newNode);

        this.size++;
    }

    /**
     * Método auxiliar para adicionar no final (equivalente a addToRear)
     */
    private void addToEnd(T elem) {
        DoublyNode<T> newNode = new DoublyNode<T>(elem);

        if (tail == null) { // Lista vazia
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }

        this.size++;
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
        DoublyNode<T> current = head;

        while (current != null) {
            if (current.getElement().equals(elem)) {
                if (current == head)
                    return removeFirst();

                else if (current == tail)
                    return removeLast();

                else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());

                    this.size--;
                    this.modCount++;

                    return current.getElement();
                }
            }
            current = current.getNext();
        }

        throw new EmptyCollectionException("Doubly Linked Ordered List removed UNSUCCESSFULLY");
    }

    /**
     * retorna o primeiro elemento da lista
     *
     * @return o primeiro elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T first() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Double Linked Ordered List get first UNSUCCESSFULLY");

        return head.getElement();
    }

    /**
     * retorna o ultimo elemento da lista
     *
     * @return o ultimo elemento da lista
     * @throws EmptyCollectionException se a lista estiver vazia
     */
    @Override
    public T last() throws EmptyCollectionException {
        if (isEmpty())
            throw new EmptyCollectionException("Double Linked Ordered List get last UNSUCCESSFULLY");

        return tail.getElement();
    }

    /**
     * retorna true se na lista contem o elemento especifico
     *
     * @param elem o elemento especifico a ser encontrado
     * @return true se encontra o elemento especifico, false caso contrario
     */
    @Override
    public boolean contains(T elem) {
        DoublyNode<T> current = head;

        while (current != null) {
            if (current.getElement().equals(elem))
                return true;

            current = current.getNext();
        }

        return false;
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
     * Verifica se a lista está ordenada
     *
     * @return true se a lista estiver ordenada, false caso contrário
     */
    public boolean isOrdered() {
        if (isEmpty() || size == 1)
            return true;

        DoublyNode<T> current = head;
        while (current.getNext() != null) {
            if (current.getElement().compareTo(current.getNext().getElement()) > 0)
                return false;

            current = current.getNext();
        }
        return true;
    }

    /**
     * retorna um iterador dos elementos da lista
     *
     * @return um iterador dos elementos da lista
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DoublyLinkedOrderedList[");

        DoublyNode<T> current = head;
        while (current != null) {
            sb.append(current.getElement());
            if (current.getNext() != null)
                sb.append(" -> ");
            current = current.getNext();
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Inverte a lista ordenada e devolve os elementos num LinkedStack (LIFO)
     * Stack é ideal porque naturalmente inverte a ordem - último elemento adicionado é o primeiro a ser removido
     *
     * @return LinkedStack com os elementos em ordem inversa
     */
    public LinkedStack<T> reverseToStack() {
        LinkedStack<T> stack = new LinkedStack<>();

        // Percorre a lista e empilha os elementos (resulta em ordem inversa)
        DoublyNode<T> current = head;
        while (current != null) {
            stack.push(current.getElement());
            current = current.getNext();
        }

        modCount++;

        return stack;
    }


    /**
     * Inverte a lista ordenada e devolve os elementos num ArrayStack (LIFO)
     * Alternativa usando array-based stack
     *
     * @return ArrayStack com os elementos em ordem inversa
     */
    public ArrayStack<T> reverseToArrayStack() {
        ArrayStack<T> stack = new ArrayStack<>();

        DoublyNode<T> current = head;
        while (current != null) {
            stack.push(current.getElement());
            current = current.getNext();
        }

        modCount++;

        return stack;
    }

    /**
     * Inverte a lista ordenada e devolve os elementos numa LinkedQueue (FIFO) mas em ordem inversa à original
     *
     * @return LinkedQueue com os elementos em ordem inversa
     */
    public LinkedQueue<T> reverseToQueue() {
        LinkedQueue<T> queue = new LinkedQueue<>();

        // Percorre do fim para o início para manter ordem inversa na queue
        DoublyNode<T> current = tail;
        while (current != null) {
            queue.enqueue(current.getElement());
            current = current.getPrev();
        }

        modCount++;

        return queue;
    }

    /**
     * Inverte a própria lista (modifica a instância atual)
     * Algoritmo in-place que troca os elementos dos nós
     */
    public void reverseInPlace() {
        if (isEmpty() || size == 1)
            return; // Não precisa inverter

        DoublyNode<T> current = head;
        DoublyNode<T> temp = null;

        // Troca head e tail
        head = tail;
        tail = current;

        // Inverte os ponteiros de todos os nós
        while (current != null) {
            temp = current.getPrev();
            current.setPrev(current.getNext());
            current.setNext(temp);
            current = current.getPrev(); // Move para o próximo (antigo anterior)
        }

        modCount++;
    }

    private class MyIterator implements Iterator<T> {
        private DoublyNode<T> current; // Ponteiro para o nó atual
        private int expectedModCount;
        private boolean canRemove;

        public MyIterator() {
            this.current = head; // Começa no nó da cabeça
            this.expectedModCount = modCount;
            this.canRemove = false;
        }

        @Override
        public boolean hasNext() {
            checkForComodification();
            return current != null; // Verifica se há próximo nó
        }

        private void checkForComodification() {
            if(expectedModCount != modCount)
                throw new ConcurrentModificationException("Lista modificada durante a iteracao");
        }

        @Override
        public T next() {
            checkForComodification();

            if (!hasNext())
                throw new EmptyCollectionException("Nao ha mais elementos na iteracao"); // Lança exceção se não houver próximo elemento

            T element = current.getElement(); // Obtém o elemento atual
            current = current.getNext(); // Move para o próximo nó
            canRemove = true;

            return element;
        }

        @Override
        public void remove() {
            checkForComodification();

            if (!canRemove)
                throw new IllegalStateException("next() deve ser chamado antes de remove()");

            DoublyNode<T> toRemove = current != null ? current.getPrev() : tail;// O nó a remover é o anterior ao current

            // Remove usando os métodos da classe pai
            if (toRemove == head) {
                try {
                    removeFirst();
                } catch (EmptyCollectionException e) {
                    // Não deve acontecer pois verificamos canRemove
                }
            } else if (toRemove == tail) {
                try {
                    removeLast();
                } catch (EmptyCollectionException e) {
                    // Não deve acontecer pois verificamos canRemove
                }
            } else {
                DoublyNode<T> previous = toRemove.getPrev();
                DoublyNode<T> next = toRemove.getNext();

                previous.setNext(next);
                next.setPrev(previous);

                size--;
                modCount++;
            }

            this.expectedModCount = modCount;
            this.canRemove = false;
        }
    }
}
