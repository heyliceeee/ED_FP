package test;

import org.junit.jupiter.api.Test;
import structures.FP03.LinkedStack;
import structures.FP07.Hanoi;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HanoiTest {
    @Test
    void testHanoiWith3Discs() {
        Hanoi<Integer> hanoi = new Hanoi<>();

        LinkedStack<Integer> origem = new LinkedStack<>();
        LinkedStack<Integer> destino = new LinkedStack<>();
        LinkedStack<Integer> auxiliar = new LinkedStack<>();

        // Inicializar origem com 3 discos (3 em baixo, 1 em cima)
        for (int i = 3; i >= 1; i--)
            origem.push(i);

        // Capturar saída do System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        hanoi.solveHanoi(3, origem, destino, auxiliar, 'A', 'C', 'B');

        // Restaurar System.out
        System.setOut(originalOut);

        // Verificar sequência de movimentos
        String expected =
                "Mover 1 de A para C\n" +
                        "Mover 2 de A para B\n" +
                        "Mover 1 de C para B\n" +
                        "Mover 3 de A para C\n" +
                        "Mover 1 de B para A\n" +
                        "Mover 2 de B para C\n" +
                        "Mover 1 de A para C\n";

        assertEquals(expected, outContent.toString());

        // Verificar estado final da pilha destino
        assertEquals(3, destino.size());   // deve ter 3 discos
        assertEquals(1, destino.pop());    // topo = disco 1
        assertEquals(2, destino.pop());    // depois disco 2
        assertEquals(3, destino.pop());    // base = disco 3
        assertTrue(destino.isEmpty());     // deve estar vazia após remover todos
    }

    @Test
    void testHanoiWith1Disc() {
        Hanoi<Integer> hanoi = new Hanoi<>();

        LinkedStack<Integer> origem = new LinkedStack<>();
        LinkedStack<Integer> destino = new LinkedStack<>();
        LinkedStack<Integer> auxiliar = new LinkedStack<>();

        origem.push(1);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        hanoi.solveHanoi(1, origem, destino, auxiliar, 'A', 'C', 'B');

        System.setOut(originalOut);

        assertEquals("Mover 1 de A para C\n", outContent.toString());
        assertEquals(1, destino.size());
        assertEquals(1, destino.pop());
    }
}
