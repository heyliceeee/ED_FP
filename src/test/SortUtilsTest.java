package test;

import org.junit.jupiter.api.Test;
import structures.FP02.LinkedList;
import structures.FP08.Carro;
import structures.FP08.SortUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortUtilsTest {
    private Carro[] criarArrayCarros() {
        return new Carro[] {new Carro("33-CC-33", "Ford", "Focus", 2019), new Carro("11-AA-11", "Toyota", "Corolla", 2018), new Carro("44-DD-44", "BMW", "320d", 2021), new Carro("22-BB-22", "Honda", "Civic", 2020)};
    }

    private String[] resultadoEsperado() {
        return new String[] {"11-AA-11", "22-BB-22", "33-CC-33", "44-DD-44"};
    }

    private String[] extrairMatriculas(Carro[] carros) {
        return Arrays.stream(carros).map(Carro::getMatricula).toArray(String[]::new);
    }

    private LinkedList<Carro> criarListaCarros() {
        LinkedList<Carro> carros = new LinkedList<>();
        carros.addFirst(new Carro("33-CC-33", "Ford", "Focus", 2019));
        carros.addFirst(new Carro("11-AA-11", "Toyota", "Corolla", 2018));
        carros.addFirst(new Carro("44-DD-44", "BMW", "320d", 2021));
        carros.addFirst(new Carro("22-BB-22", "Honda", "Civic", 2020));
        return carros;
    }

    private String[] extrairMatriculas(LinkedList<Carro> lista) {
        String[] result = new String[lista.size()];

        for (int i = 0; i < lista.size(); i++)
            result[i] = lista.get(i).getMatricula();

        return result;
    }


    @Test
    void testSelectionSortLinkedList() {
        LinkedList<Carro> carros = criarListaCarros();
        SortUtils.selectionSortLinkedList(carros);
        assertArrayEquals(resultadoEsperado(), extrairMatriculas(carros));
    }

    @Test
    void testInsertionSortLinkedList() {
        LinkedList<Carro> carros = criarListaCarros();
        SortUtils.insertionSortLinkedList(carros);
        assertArrayEquals(resultadoEsperado(), extrairMatriculas(carros));
    }

    @Test
    void testBubbleSortLinkedList() {
        LinkedList<Carro> carros = criarListaCarros();
        SortUtils.bubbleSortLinkedList(carros);
        assertArrayEquals(resultadoEsperado(), extrairMatriculas(carros));
    }

    @Test
    void testQuickSortLinkedList() {
        LinkedList<Carro> carros = criarListaCarros();
        SortUtils.quickSortLinkedList(carros, 0, carros.size() - 1);
        assertArrayEquals(resultadoEsperado(), extrairMatriculas(carros));
    }

    @Test
    void testMergeSortLinkedList() {
        LinkedList<Carro> carros = criarListaCarros();
        SortUtils.mergeSortLinkedList(carros, 0, carros.size() - 1);
        assertArrayEquals(resultadoEsperado(), extrairMatriculas(carros));
    }

    @Test
    void testSelectionSort() {
        Carro[] carros = criarArrayCarros();
        SortUtils.selectionSort(carros);
        assertArrayEquals(resultadoEsperado(), extrairMatriculas(carros));
    }

    @Test
    void testInsertionSort() {
        Carro[] carros = criarArrayCarros();
        SortUtils.insertionSort(carros);
        assertArrayEquals(resultadoEsperado(), extrairMatriculas(carros));
    }

    @Test
    void testBubbleSort() {
        Carro[] carros = criarArrayCarros();
        SortUtils.bubbleSort(carros);
        assertArrayEquals(resultadoEsperado(), extrairMatriculas(carros));
    }

    @Test
    void testQuickSort() {
        Carro[] carros = criarArrayCarros();
        SortUtils.quickSort(carros, 0, carros.length - 1);
        assertArrayEquals(resultadoEsperado(), extrairMatriculas(carros));
    }

    @Test
    void testMergeSort() {
        Carro[] carros = criarArrayCarros();
        SortUtils.mergeSort(carros, 0, carros.length - 1);
        assertArrayEquals(resultadoEsperado(), extrairMatriculas(carros));
    }
}
