package structures.FP09;



public class BinaryTreeNode<T> {
    protected T elem;
    protected BinaryTreeNode<T> left, right;


    /**
     * Cria um novo raiz da arvore com dados especificos
     * @param obj o elemento que ira fazer parte do novo raiz da arvore
     */
    public BinaryTreeNode(T obj) {
        elem = obj;
        left = right = null;
    }


    /**
     * Retorna o numero de filhos deste no
     * @return o numero de filhos deste no
     */
    public int numChildren() {
        int children = 0;

        if(left != null)
            children = 1 + left.numChildren();

        if(right != null)
            children = children + 1 + right.numChildren();

        return children;
    }
}
