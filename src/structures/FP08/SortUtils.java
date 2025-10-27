package structures.FP08;

import structures.FP02.LinkedList;

public class SortUtils {

    /**
     * Selection Sort
     * @param lista elementos comparáveis
     * @param <T> Tipo genérico que implementa Comparable
     */
    public static <T extends Comparable<T>> void selectionSortLinkedList(LinkedList<T> lista) {
        int n = lista.size();

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (lista.get(j).compareTo(lista.get(minIdx)) < 0)
                    minIdx = j;
            }

            // troca
            T temp = lista.get(i);
            lista.set(i, lista.get(minIdx));
            lista.set(minIdx, temp);
        }
    }

    /**
     * Insertion Sort
     * @param lista elementos comparáveis
     * @param <T> Tipo genérico que implementa Comparable
     */
    public static <T extends Comparable<T>> void insertionSortLinkedList(LinkedList<T> lista) {
        int n = lista.size();
        for (int i = 1; i < n; i++) {
            T key = lista.get(i);
            int j = i - 1;
            while (j >= 0 && lista.get(j).compareTo(key) > 0) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, key);
        }
    }

    /**
     * Bubble Sort
     * @param lista elementos comparáveis
     * @param <T> Tipo genérico que implementa Comparable
     */
    public static <T extends Comparable<T>> void bubbleSortLinkedList(LinkedList<T> lista) {
        int n = lista.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).compareTo(lista.get(j + 1)) > 0) {
                    T temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    /**
     * Quick Sort
     * @param lista elementos comparáveis
     * @param low Índice inicial
     * @param high Índice final
     * @param <T> Tipo genérico que implementa Comparable
     */
    public static <T extends Comparable<T>> void quickSortLinkedList(LinkedList<T> lista, int low, int high) {
        if (low < high) {
            int pi = partitionLinkedList(lista, low, high);
            quickSortLinkedList(lista, low, pi - 1);
            quickSortLinkedList(lista, pi + 1, high);
        }
    }

    /**
     *  Função auxiliar para Quick Sort
     * @param lista
     * @param low
     * @param high
     * @return
     * @param <T>
     */
    private static <T extends Comparable<T>> int partitionLinkedList(LinkedList<T> lista, int low, int high) {
        T pivot = lista.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (lista.get(j).compareTo(pivot) <= 0) {
                i++;
                T temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }
        T temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(high));
        lista.set(high, temp);
        return i + 1;
    }

    /**
     * Merge Sort
     * @param lista elementos comparáveis
     * @param left Índice inicial
     * @param right Índice final
     * @param <T> Tipo genérico que implementa Comparable
     */
    public static <T extends Comparable<T>> void mergeSortLinkedList(LinkedList<T> lista, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortLinkedList(lista, left, mid);
            mergeSortLinkedList(lista, mid + 1, right);
            mergeLinkedList(lista, left, mid, right);
        }
    }

    /**
     * Função auxiliar para Merge Sort
     * @param lista
     * @param left
     * @param mid
     * @param right
     * @param <T>
     */
    private static <T extends Comparable<T>> void mergeLinkedList(LinkedList<T> lista, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Comparable[] L = new Comparable[n1];
        Comparable[] R = new Comparable[n2];

        for (int i = 0; i < n1; i++) L[i] = lista.get(left + i);
        for (int j = 0; j < n2; j++) R[j] = lista.get(mid + 1 + j);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                lista.set(k++, (T) L[i++]);
            } else {
                lista.set(k++, (T) R[j++]);
            }
        }
        while (i < n1) lista.set(k++, (T) L[i++]);
        while (j < n2) lista.set(k++, (T) R[j++]);
    }

    /**
     * Selection Sort
     * @param arr Array de elementos comparáveis
     * @param <T> Tipo genérico que implementa Comparable
     */
    public static <T extends Comparable<T>> void selectionSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { // Percorre o array até ao penúltimo elemento
            int minIdx = i; // assume que o menor elemento está na posição i

            for (int j = i + 1; j < arr.length; j++) { // Procura o menor elemento no subarray [i+1 ... fim]
                if (arr[j].compareTo(arr[minIdx]) < 0)
                    minIdx = j; // atualiza índice do menor
            }

            // Troca o elemento da posição i com o menor encontrado
            T temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    /**
     * Insertion Sort
     * @param arr Array de elementos comparáveis
     * @param <T> Tipo genérico que implementa Comparable
     */
    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) { // Começa no segundo elemento e insere-o na posição correta
            T key = arr[i]; // elemento a ser inserido
            int j = i - 1;

            while (j >= 0 && arr[j].compareTo(key) > 0) { // Desloca elementos maiores que "key" uma posição à frente
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key; // Insere "key" na posição correta
        }
    }

    /**
     * Bubble Sort
     * @param arr Array de elementos comparáveis
     * @param <T> Tipo genérico que implementa Comparable
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) { // Percorre várias vezes o array
            swapped = false; // flag para verificar se houve trocas

            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) { // Se o elemento atual for maior que o próximo, troca
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) // Se não houve trocas, o array já está ordenado → termina
                break;
        }
    }

    /**
     * Quick Sort
     * @param arr Array de elementos comparáveis
     * @param low Índice inicial
     * @param high Índice final
     * @param <T> Tipo genérico que implementa Comparable
     */
    public static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high); // Particiona o array e obtém a posição do pivô

            // Ordena recursivamente as duas metades
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Função auxiliar para Quick Sort
     * @param arr
     * @param low
     * @param high
     * @return
     * @param <T>
     */
    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        T pivot = arr[high]; // escolhe o último elemento como pivô
        int i = low - 1; // índice do menor elemento

        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) { // Se arr[j] <= pivô, troca para a esquerda
                i++;
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Coloca o pivô na posição correta
        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1; // devolve índice do pivô
    }

    /**
     * Merge Sort
     * @param arr Array de elementos comparáveis
     * @param left Índice inicial
     * @param right Índice final
     * @param <T> Tipo genérico que implementa Comparable
     */
    public static <T extends Comparable<T>> void mergeSort(T[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2; // encontra o meio

            // Ordena recursivamente as duas metades
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Junta as duas metades ordenadas
            merge(arr, left, mid, right);
        }
    }

    /**
     * Função auxiliar para Merge Sort
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param <T>
     */
    private static <T extends Comparable<T>> void merge(T[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1; // tamanho da metade esquerda
        int n2 = right - mid;    // tamanho da metade direita

        Comparable[] L = new Comparable[n1];
        Comparable[] R = new Comparable[n2];

        // Copia os elementos para arrays temporários
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) { // Junta os arrays temporários de volta em arr[]
            if (L[i].compareTo(R[j]) <= 0)
                arr[k++] = (T) L[i++];
            else
                arr[k++] = (T) R[j++];
        }

        // Copia os elementos restantes, se houver
        while (i < n1)
            arr[k++] = (T) L[i++];

        while (j < n2)
            arr[k++] = (T) R[j++];
    }
}
