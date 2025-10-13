package structures.FP02;

import exceptions.EmptyCollectionException;

/**
 * Implementação de uma lista ligada genérica.
 * <p>
 *  Esta classe fornece funcionalidades básicas para manipulação de uma lista ligada, como adição, remoção, verificação de elementos e iteração.
 * </p>
 *
 * @param <T> O tipo de dados armazenados na lista.
 */
public class LinkedList<T> {

    private LinkedListNode<T> head; // primeiro elemento da lista
    private LinkedListNode<T> tail; // ultimo elemento da lista
    private SentinelNode<T> sentinel; // o no sentinela
    private int size; // o tamanho da lista

    /**
     * Construtor padrão que inicializa uma lista ligada vazia.
     */
    public LinkedList(){
        this.size = 0;
        this.head = null;
        sentinel = new SentinelNode<T>(null); // node especial sem dados
    }

    /**
     * adicionar no inicio da lista
     * @param elem
     */
    public void addFirst(T elem) {
        LinkedListNode<T> newNode = new LinkedListNode<T>(elem); // criar um novo node com os dados

        if (head == null) { // se a lista estiver vazia
            head = newNode; // o novo node fica na cabeça
            tail = newNode; // e também na cauda

            size++;

        } else { // se a lista NÃO estiver vazia
            newNode.setNext(head); // o novo node aponta para o antigo head
            head = newNode;        // o novo node passa a ser o head

            size++;
        }
    }

    /**
     * adicionar no início da lista, utilizando node sentinela
     * @param elem
     */
    public void addFirstSentinel(T elem) {
        SentinelNode<T> newNode = new SentinelNode<T>(elem); // criar novo node com os dados

        // o novo nó aponta para o primeiro elemento real (que pode ser null)
        newNode.setNext(sentinel.getNext());

        // o sentinela passa a apontar para o novo nó
        sentinel.setNext(newNode);

        size++;
    }

    /**
     * adicionar no fim da lista
     * @param elem
     */
    public void addLast(T elem) {
        LinkedListNode<T> newNode = new LinkedListNode<T>(elem); //criar um novo node com os dados

        if(head == null){ //se a lista estiver vazia
            head = newNode; //o novo node fica na cabeca (torna-se o primeiro)
            tail = newNode; //o novo node fica na cauda (torna-se o último)

            size++;

        } else { //se a lista NAO estiver vazia
            tail.setNext(newNode); //adicionar um novo node ao próximo elemento de tail
            tail = newNode; //tail fica com o valor do novo node

            size++;
        }
    }

    /**
     * adicionar no fim da lista, utilizando node sentinela
     * @param elem
     */
    public void addLastSentinel(T elem) {
        SentinelNode<T> newNode = new SentinelNode<T>(elem); //criar um novo node com os dados
        SentinelNode<T> current = sentinel; //elemento

        while (current.getNext() != null) //vai correr a lista até á cauda (último elemento)
            current = current.getNext(); //vai para o seguinte elemento

        current.setNext(newNode); //adicionar um novo node á cauda (último elemento)
        size++;
    }

    /**
     * Remove o primeiro elemento da lista (head).
     */
    public void removeFirst() throws EmptyCollectionException {
        if (head == null || size == 0) // lista vazia
            throw new EmptyCollectionException("List");

        head = head.getNext(); // avança a cabeça para o próximo nó
        size--;

        // se a lista ficou vazia, tail também deve ser null
        if (head == null)
            tail = null;
    }

    /**
     * Remove o primeiro elemento real da lista (logo a seguir ao sentinela).
     */
    public void removeFirstSentinel() throws  EmptyCollectionException {
        if (sentinel.getNext() == null || size == 0) // lista vazia
            throw new EmptyCollectionException("List");

        // o sentinela passa a apontar para o segundo elemento real
        sentinel.setNext(sentinel.getNext().getNext());
        size--;
    }

    /**
     * Remove o último elemento da lista (tail).
     */
    public void removeLast() throws EmptyCollectionException {
        if (head == null || size == 0) // lista vazia
            throw new EmptyCollectionException("List");

        if (head == tail) { // só existe um elemento
            head = null;
            tail = null;
            size = 0;
            return;
        }


        LinkedListNode<T> current = head;
        while (current.getNext() != tail) // percorre até ao penúltimo nó
            current = current.getNext();

        // "corta" a referência para o último nó
        current.setNext(null);
        tail = current; // atualiza a cauda
        size--;
    }

    /**
     * Remove o último elemento da lista (tail), usando nó sentinela.
     */
    public void removeLastSentinel() throws EmptyCollectionException {
        if (sentinel.getNext() == null || size == 0) // lista vazia
            throw new EmptyCollectionException("List");

        // se só existe um elemento real
        if (sentinel.getNext().getNext() == null) {
            sentinel.setNext(null); // lista fica vazia (só o sentinela sobra)
            size = 0;
            return;
        }


        SentinelNode<T> current = sentinel;
        while (current.getNext().getNext() != null) // percorre até ao penúltimo nó real
            current = current.getNext();

        // corta a referência para o último nó
        current.setNext(null);
        size--;
    }

    /**
     * Imprime todos os elementos
     */
    public void printList() {
        LinkedListNode<T> current = head; //elemento da cabeca (primeiro elemento)

        System.out.print("LinkedList [");

        while (current != null){ //corre a lista toda
            System.out.print(current.getElement() + ", ");
            current = current.getNext(); //vai para o seguinte elemento
        }

        System.out.print("]\n");
    }

    /**
     * Imprime todos os elementos, com node sentinela
     */
    public void printListSentinel() {
        SentinelNode<T> current = sentinel; //elemento da cabeca (primeiro elemento)

        System.out.print("LinkedList Sentinel [");

        while (current != null){ //corre a lista toda
            System.out.print(current.getElement() + ", ");
            current = current.getNext(); //vai para o seguinte elemento
        }

        System.out.print("]\n");
    }

    /**
     * Verifica se a lista está vazia.
     *
     * @return {@code true} se a lista estiver vazia, {@code false} caso contrário.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retorna o número de elementos na lista.
     *
     * @return O número de elementos na lista.
     */
    public int size() {
        return size;
    }
}
