package structures.FP08;

import structures.FP02.LinkedList;

import java.util.Collections;

public class SearchUtils {
    /**
     * Linear Search em LinkedList
     * @param lista     Lista ligada de objetos Carro onde vamos procurar
     * @param matricula Matrícula do carro que queremos encontrar
     * @return          O objeto Carro correspondente, ou null se não for encontrado
     */
    public static Carro linearSearchLinkedList(LinkedList<Carro> lista, String matricula) {
        for (Carro c : lista) { // Percorre todos os elementos da lista (um a um)
            if (c.getMatricula().equals(matricula)) // Se a matrícula do carro atual for igual à matrícula procurada
                return c; // devolve o carro encontrado
        }
        return null; // Se chegar ao fim da lista sem encontrar, devolve null
    }


    /**
     * Binary Search em LinkedList
     * @param lista     Lista ligada de objetos Carro (deve estar previamente ordenada por matrícula)
     * @param matricula Matrícula do carro que queremos encontrar
     * @return          O objeto Carro correspondente, ou null se não for encontrado
     */
    public static Carro binarySearchLinkedList(LinkedList<Carro> lista, String matricula) {
        // Definimos os limites inicial e final da pesquisa
        int inicio = 0;
        int fim = lista.size() - 1;

        Carro chave = new Carro(matricula, "", "", 0); // Criamos um "carro fantasma" apenas com a matrícula, para poder comparar usando compareTo (baseado na matrícula)

        // Enquanto ainda houver intervalo válido
        while (inicio <= fim) {
            int meio = (inicio + fim) / 2; // Calcula o índice do meio

            Carro atual = lista.get(meio); // Obtém o elemento na posição 'meio' ⚠ Nota: em LinkedList, get(i) é O(n), logo esta pesquisa binária perde eficiência em comparação com arrays (onde get(i) é O(1))

            int cmp = atual.compareTo(chave); // Compara a matrícula do carro atual com a matrícula procurada

            if (cmp == 0)
                return atual; // Encontrou → devolve o carro
            else if (cmp < 0)
                inicio = meio + 1; // A matrícula procurada é "maior" → procurar na metade direita
            else
                fim = meio - 1; // A matrícula procurada é "menor" → procurar na metade esquerda
        }

        return null; // Se não encontrou nenhum carro com a matrícula indicada
    }

    /**
     * Linear Search: percorre tds os elems até encontrar o alvo. Simples, mas pode ser lento em arrays grds (𝑂(𝑛)).
     * @param array  Array genérico onde vamos procurar
     * @param target Elemento que queremos encontrar
     * @return índice do elemento se encontrado, -1 caso contrário
     * @param <T>    Tipo genérico dos elementos
     */
    public static <T> int linearSearch(T[] array, T target) {
        for (int i = 0; i < array.length; i++) { // Percorre o array do início ao fim
            if (array[i].equals(target)) // Se o elemento atual for igual ao procurado
                return i; // devolve o índice onde encontrou
        }
        return -1; // se não encontrar, devolve -1
    }

    /**
     * Binary Search: divide o array ordenado em metades sucessivas até encontrar o alvo. Mt + eficiente (𝑂(log⁡𝑛)), mas exige q o array esteja previamente ordenado e q os elems sejam comparáveis.
     * @param array  Array genérico ordenado
     * @param target Elemento que queremos encontrar
     * @return índice do elemento se encontrado, -1 caso contrário
     * @param <T>    Tipo genérico que implementa Comparable (para poder comparar)
     */
    public static <T extends Comparable<T>> int binarySearch(T[] array, T target) {
        int inicio = 0;              // limite inferior da pesquisa
        int fim = array.length - 1;  // limite superior da pesquisa

        while (inicio <= fim) { // Enquanto ainda houver intervalo válido
            int meio = (inicio + fim) / 2; // calcula o índice do meio
            int cmp = array[meio].compareTo(target); // compara elemento do meio com o procurado

            if (cmp == 0)
                return meio; // encontrou o elemento → devolve índice
            else if (cmp < 0)
                inicio = meio + 1;// target é "maior" → procurar na metade direita
            else
                fim = meio - 1; // target é "menor" → procurar na metade esquerda
        }
        return -1; // não encontrado
    }
}
