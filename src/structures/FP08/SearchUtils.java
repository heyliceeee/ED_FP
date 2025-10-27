package structures.FP08;

public class SearchUtils {
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
