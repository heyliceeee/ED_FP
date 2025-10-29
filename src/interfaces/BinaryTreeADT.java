package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;

import java.util.Iterator;

public interface BinaryTreeADT<T> {
    /**
     * Retorna uma referencia do elemento da raiz da binary tree
     * @return uma referencia da raiz
     */
    T getRoot();

    /**
     * Retorna true caso a binary tree esta vazia, caso contrario retorna false
     * @return true caso a binary tree esta vazia
     */
    boolean isEmpty();

    /**
     * Retorna o numero de elementos da binary tree
     * @return o numero de elementos da binary tree
     */
    int size();

    /**
     * Retorna true se a binary tree contem um elemento que seja igual ao targetElem, caso contrario retorna false
     * @param targetElem o elemento a ser procurado na binary tree
     * @return true se conter na binary tree o targetElem
     */
    boolean contains(T targetElem);

    /**
     * Retorna uma referencia do targetElem, se for encontrado na binary tree. Caso contrario, retorna EmptyCollectionException.
     * @param targetElem o elemento a ser procurado na binary tree
     * @return uma referencia do targetElem se for encontrado na binary tree
     * @throws ElementNotFoundException caso nao encontrar uma referencia do targetElem na binary tree
     */
    T find(T targetElem) throws ElementNotFoundException;

    /**
     * Retorna uma representacao em string da binary tree
     * @return uma representacao em string da binary tree
     */
    String toString();

    /**
     * Retorna um iterador para uma travessia do inorder da binary tree
     * @return um iterador para uma travessia do inorder da binary tree
     */
    Iterator<T> iteratorInOrder();

    /**
     * Retorna um iterador para uma travessia do preorder da binary tree
     * @return um iterador para uma travessia do preorder da binary tree
     */
    Iterator<T> iteratorPreOrder();

    /**
     * Retorna um iterador para uma travessia do postorder da binary tree
     * @return um iterador para uma travessia do postorder da binary tree
     */
    Iterator<T> iteratorPostOrder();

    /**
     * Retorna um iterador para uma travessia do levelorder da binary tree
     * @return um iterador para uma travessia do levelorder da binary tree
     */
    Iterator<T> iteratorLevelOrder();
}
