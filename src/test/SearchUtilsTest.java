package test;

import org.junit.jupiter.api.Test;
import structures.FP02.LinkedList;
import structures.FP08.Carro;
import structures.FP08.SearchUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SearchUtilsTest {
    @Test
    void testLinearSearchFoundLinkedList() {
        LinkedList<Carro> carros = new LinkedList<>();
        carros.addLast(new Carro("11-AA-11", "Toyota", "Corolla", 2018));
        carros.addLast(new Carro("22-BB-22", "Honda", "Civic", 2020));
        carros.addLast(new Carro("33-CC-33", "Ford", "Focus", 2019));

        Carro resultado = SearchUtils.linearSearchLinkedList(carros, "22-BB-22");
        assertNotNull(resultado);
        assertEquals("22-BB-22", resultado.getMatricula());
    }

    @Test
    void testLinearSearchNotFoundLinkedList() {
        LinkedList<Carro> carros = new LinkedList<>();
        carros.addLast(new Carro("11-AA-11", "Toyota", "Corolla", 2018));
        carros.addLast(new Carro("33-CC-33", "Ford", "Focus", 2019));

        Carro resultado = SearchUtils.linearSearchLinkedList(carros, "99-ZZ-99");
        assertNull(resultado);
    }

    @Test
    void testBinarySearchFoundLinkedList() {
        LinkedList<Carro> carros = new LinkedList<>();
        carros.addLast(new Carro("11-AA-11", "Toyota", "Corolla", 2018));
        carros.addLast(new Carro("22-BB-22", "Honda", "Civic", 2020));
        carros.addLast(new Carro("33-CC-33", "Ford", "Focus", 2019));
        carros.addLast(new Carro("44-DD-44", "BMW", "320d", 2021));

        // ordenar antes da pesquisa binária
        carros.sortLinkedList();

        Carro resultado = SearchUtils.binarySearchLinkedList(carros, "33-CC-33");
        assertNotNull(resultado);
        assertEquals("33-CC-33", resultado.getMatricula());
    }

    @Test
    void testBinarySearchNotFoundLinkedList() {
        LinkedList<Carro> carros = new LinkedList<>();
        carros.addLast(new Carro("11-AA-11", "Toyota", "Corolla", 2018));
        carros.addLast(new Carro("22-BB-22", "Honda", "Civic", 2020));
        carros.addLast(new Carro("33-CC-33", "Ford", "Focus", 2019));

        carros.sortLinkedList();

        Carro resultado = SearchUtils.binarySearchLinkedList(carros, "99-ZZ-99");
        assertNull(resultado);
    }

    @Test
    void testBinarySearchFirstAndLastLinkedList() {
        LinkedList<Carro> carros = new LinkedList<>();
        carros.addLast(new Carro("11-AA-11", "Toyota", "Corolla", 2018));
        carros.addLast(new Carro("22-BB-22", "Honda", "Civic", 2020));
        carros.addLast(new Carro("33-CC-33", "Ford", "Focus", 2019));
        carros.addLast(new Carro("44-DD-44", "BMW", "320d", 2021));

        carros.sortLinkedList();

        Carro primeiro = SearchUtils.binarySearchLinkedList(carros, "11-AA-11");
        Carro ultimo = SearchUtils.binarySearchLinkedList(carros, "44-DD-44");

        assertNotNull(primeiro);
        assertEquals("11-AA-11", primeiro.getMatricula());

        assertNotNull(ultimo);
        assertEquals("44-DD-44", ultimo.getMatricula());
    }

    @Test
    void testLinearSearchFound() {
        Integer[] numeros = {10, 20, 30, 40, 50};
        int idx = SearchUtils.linearSearch(numeros, 30);
        assertEquals(2, idx); // 30 está na posição 2
    }

    @Test
    void testLinearSearchNotFound() {
        String[] nomes = {"Alice", "Bruno", "Carla"};
        int idx = SearchUtils.linearSearch(nomes, "Duarte");
        assertEquals(-1, idx); // Duarte não existe
    }

    @Test
    void testBinarySearchFound() {
        String[] nomes = {"Alice", "Bruno", "Carla", "Duarte"};
        Arrays.sort(nomes); // garantir que está ordenado
        int idx = SearchUtils.binarySearch(nomes, "Carla");
        assertTrue(idx >= 0);
        assertEquals("Carla", nomes[idx]);
    }

    @Test
    void testBinarySearchNotFound() {
        Integer[] numeros = {1, 3, 5, 7, 9};
        Arrays.sort(numeros);
        int idx = SearchUtils.binarySearch(numeros, 4);
        assertEquals(-1, idx); // 4 não existe
    }

    @Test
    void testBinarySearchWithCarro() {
        Carro[] carros = {
                new Carro("11-AA-11", "Toyota", "Corolla", 2018),
                new Carro("22-BB-22", "Honda", "Civic", 2020),
                new Carro("33-CC-33", "Ford", "Focus", 2019)
        };

        Arrays.sort(carros); // ordena por matrícula (compareTo)
        int idx = SearchUtils.binarySearch(carros, new Carro("22-BB-22", "", "", 0));
        assertTrue(idx >= 0);
        assertEquals("22-BB-22", carros[idx].getMatricula());
    }
}
