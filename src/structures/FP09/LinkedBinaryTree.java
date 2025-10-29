package structures.FP09;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import interfaces.BinaryTreeADT;
import structures.FP02.LinkedList;
import structures.FP04.LinkedQueue;
import structures.FP05.ArrayUnorderedList;

import java.util.Iterator;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {
    protected int count;
    protected BinaryTreeNode<T> root;


    /**
     * Cria uma binary tree vazia
     */
    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    /**
     * Cria uma binary tree com um elemento especifico como raiz
     * @param elem o elemento que ira ser a raiz da nova binary tree
     */
    public LinkedBinaryTree(T elem) {
        count = 1;
        root = new BinaryTreeNode<T> (elem);
    }

    /**
     * Retorna uma referencia do elemento da raiz da binary tree
     *
     * @return uma referencia da raiz
     */
    @Override
    public T getRoot() {
        return null;
    }

    /**
     * Retorna true caso a binary tree esta vazia, caso contrario retorna false
     *
     * @return true caso a binary tree esta vazia
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Retorna o numero de elementos da binary tree
     *
     * @return o numero de elementos da binary tree
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Retorna true se a binary tree contem um elemento que seja igual ao targetElem, caso contrario retorna false
     *
     * @param targetElem o elemento a ser procurado na binary tree
     * @return true se conter na binary tree o targetElem
     */
    @Override
    public boolean contains(T targetElem) {
        return false;
    }

    /**
     * Retorna uma referencia do targetElem, se for encontrado na binary tree. Caso contrario, retorna EmptyCollectionException.
     *
     * @param targetElem o elemento a ser procurado na binary tree
     * @return uma referencia do targetElem se for encontrado na binary tree
     * @throws EmptyCollectionException caso nao encontrar uma referencia do targetElem na binary tree
     */
    @Override
    public T find(T targetElem) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElem, root); // metodo recursivo

        if(current == null) // se nao encontrar o elemento, lanca a excecao
            throw new ElementNotFoundException("Binary Tree");

        return current.elem; // retorna o elemento do no encontrado
    }

    /**
     * Retorna uma referencia do targetElem, se for encontrado na binary tree
     * @param targetElem o elemento a ser procurado na binary tree
     * @param next o elemento a partir do qual se inicia a pesquisa
     * @return uma referencia do targetElem se for encontrado na binary tree
     */
    private BinaryTreeNode<T> findAgain(T targetElem, BinaryTreeNode<T> next) {

        if(next == null) // se o no atual for nulo, retorna nulo
            return null;

        if(next.elem.equals(targetElem)) // se o elemento do no for igual ao procurado, retorna o no
            return next;

        BinaryTreeNode<T> temp = findAgain(targetElem, next.left); // caso contrario, procura recursivamente na esquerda; se nao encontrar, procura na direita

        if(temp == null) // se nao encontrar no na esquerda, procura recursivamente na direita
            temp = findAgain(targetElem, next.right);

        return temp; // retorna o no encontrado
    }

    /**
     * Retorna um iterador para uma travessia do inorder da binary tree
     *
     * @return um iterador para uma travessia do inorder da binary tree
     */
    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();

        inorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Retorna uma travessia inorder recursiva
     * @param node o no que vai ser usado como raiz nesta travessia
     * @param tempList a lista temporaria que vai ser usada nesta travessia
     */
    protected void inorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if(node != null) { // se o no nao for nulo
            inorder(node.left, tempList); // chama recursivamente inorder para o filho esquerdo
            tempList.addToRear(node.elem); // adiciona o elemento do no atual ao fim da lista
            inorder(node.right, tempList); // chama recursivamente inorder para o filho direito
        }
    }


    /**
     * Retorna um iterador para uma travessia do preorder da binary tree
     *
     * @return um iterador para uma travessia do preorder da binary tree
     */
    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();

        preorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Retorna uma travessia preorder recursiva
     * @param node o no que vai ser usado como raiz nesta travessia
     * @param tempList a lista temporaria que vai ser usada nesta travessia
     */
    protected void preorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if(node != null) { // se o no nao for nulo
            tempList.addToRear(node.elem); // adiciona o elemento do no atual ao fim da lista
            preorder(node.left, tempList); // chama recursivamente preorder para o filho esquerdo
            preorder(node.right, tempList); // chama recursivamente preorder para o filho direito
        }
    }

    /**
     * Retorna um iterador para uma travessia do postorder da binary tree
     *
     * @return um iterador para uma travessia do postorder da binary tree
     */
    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();

        postorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Retorna uma travessia postorder recursiva
     * @param node o no que vai ser usado como raiz nesta travessia
     * @param tempList a lista temporaria que vai ser usada nesta travessia
     */
    protected void postorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if(node != null) { // se o no nao for nulo
            postorder(node.left, tempList); // chama recursivamente postorder para o filho esquerdo
            postorder(node.right, tempList); // chama recursivamente postorder para o filho direito
            tempList.addToRear(node.elem); // adiciona o elemento do no atual ao fim da lista
        }
    }

    /**
     * Retorna um iterador para uma travessia do levelorder da binary tree
     *
     * @return um iterador para uma travessia do levelorder da binary tree
     */
    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();

        levelorder(root, tempList);

        return tempList.iterator();
    }

    /**
     * Retorna uma travessia levelorder recursiva
     * @param node o no que vai ser usado como raiz nesta travessia
     * @param tempList a lista temporaria que vai ser usada nesta travessia
     */
    protected void levelorder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if(node == null)
            return;

        LinkedQueue<BinaryTreeNode<T>> queue = new LinkedQueue<>();
        queue.enqueue(node); // enfileira a raiz

        while (!queue.isEmpty()) { // enquanto a queue nao tiver vazia
            try {
                BinaryTreeNode<T> current = queue.dequeue(); // desenfileira o no da frente
                tempList.addToRear(current.elem); // adiciona o elemento no fim da lista

                if (current.left != null) // se existir filhos a esquerda
                    queue.enqueue(current.left); // enfileira os filhos esquerdos

                if (current.right != null) // se existir filhos a direita
                    queue.enqueue(current.right); // enfileira os filhos direitos

            } catch (EmptyCollectionException e) {
                return; // não deve acontecer, mas se acontecer apenas termina
            }
        }
    }
}
