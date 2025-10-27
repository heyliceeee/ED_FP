package test;

import org.junit.jupiter.api.Test;
import structures.FP08.Carro;
import structures.FP08.SearchUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchUtilsTest {
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
