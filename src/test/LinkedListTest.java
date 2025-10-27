package test;

import exceptions.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.FP02.LinkedList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {
    private LinkedList<Integer> list;
    private LinkedList<String> stringList;

    @BeforeEach
    void setUp() {
        list = new LinkedList<>();
        stringList = new LinkedList<>();
    }

    @Test
    void testPrintRecursive() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("A");
        list.addFirst("B");
        list.addLast("C");

        // Capturar saída do System.out
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        list.printRecursive();

        System.setOut(System.out); // Restaurar System.out

        String expected = "B A C ";
        assertEquals(expected, outContent.toString());
    }

    @Test
    void testIteratorConcurrentModification() {
        list.addFirst(10);
        list.addFirst(20);

        Iterator<Integer> iterator = list.iterator();
        list.addFirst(30); // Modificação externa

        assertThrows(ConcurrentModificationException.class, iterator::hasNext);
    }

    @Test
    void testIteratorConcurrentModificationOnRemove() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        Iterator<Integer> iterator = list.iterator();
        list.removeFirst(); // Modificação externa

        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    // Testes para addFirst
    @Test
    void testAddFirstToEmptyList() {
        list.addFirst(10);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    void testAddFirstToNonEmptyList() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        assertEquals(3, list.size());
    }

    @Test
    void testAddFirstOrder() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        // A lista deve ser [3, 2, 1]
        assertEquals(3, list.size());
    }

    // Testes para addLast
    @Test
    void testAddLastToEmptyList() {
        list.addLast(10);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    void testAddLastToNonEmptyList() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertEquals(3, list.size());
    }

    @Test
    void testAddLastOrder() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        // A lista deve ser [1, 2, 3]
        assertEquals(3, list.size());
    }

    // Testes para removeFirst
    @Test
    void testRemoveFirstFromEmptyList() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.removeFirst();
        });
    }

    @Test
    void testRemoveFirstFromSingleElementList() throws EmptyCollectionException {
        list.addFirst(10);
        Integer removed = list.removeFirst();

        assertEquals(10, removed);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveFirstFromMultipleElementsList() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        Integer removed = list.removeFirst();

        assertEquals(30, removed);
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveFirstOrder() throws EmptyCollectionException {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        Integer first = list.removeFirst();
        Integer second = list.removeFirst();

        assertEquals(1, first);
        assertEquals(2, second);
        assertEquals(1, list.size());
    }

    // Testes para removeLast
    @Test
    void testRemoveLastFromEmptyList() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.removeLast();
        });
    }

    @Test
    void testRemoveLastFromSingleElementList() throws EmptyCollectionException {
        list.addFirst(10);
        Integer removed = list.removeLast();

        assertEquals(10, removed);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveLastFromMultipleElementsList() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        Integer removed = list.removeLast();

        assertEquals(10, removed);
        assertEquals(2, list.size());
    }

    @Test
    void testRemoveLastOrder() throws EmptyCollectionException {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        Integer last = list.removeLast();
        Integer secondLast = list.removeLast();

        assertEquals(3, last);
        assertEquals(2, secondLast);
        assertEquals(1, list.size());
    }

    // Testes para métodos sentinela
    @Test
    void testAddFirstSentinel() {
        list.addFirstSentinel(10);
        list.addFirstSentinel(20);

        assertEquals(2, list.size());
    }

    @Test
    void testAddLastSentinel() {
        list.addLastSentinel(10);
        list.addLastSentinel(20);
        list.addLastSentinel(30);

        assertEquals(3, list.size());
    }

    @Test
    void testRemoveFirstSentinelFromEmptyList() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.removeFirstSentinel();
        });
    }

    @Test
    void testRemoveFirstSentinel() throws EmptyCollectionException {
        list.addFirstSentinel(10);
        list.addFirstSentinel(20);

        list.removeFirstSentinel();

        assertEquals(1, list.size());
    }

    @Test
    void testRemoveLastSentinelFromEmptyList() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.removeLastSentinel();
        });
    }

    @Test
    void testRemoveLastSentinel() throws EmptyCollectionException {
        list.addLastSentinel(10);
        list.addLastSentinel(20);
        list.addLastSentinel(30);

        list.removeLastSentinel();

        assertEquals(2, list.size());
    }

    // Testes para isEmpty
    @Test
    void testIsEmptyOnNewList() {
        assertTrue(list.isEmpty());
    }

    @Test
    void testIsEmptyAfterAdd() {
        list.addFirst(10);
        assertFalse(list.isEmpty());
    }

    @Test
    void testIsEmptyAfterRemove() throws EmptyCollectionException {
        list.addFirst(10);
        list.removeFirst();
        assertTrue(list.isEmpty());
    }

    // Testes para size
    @Test
    void testSizeOnNewList() {
        assertEquals(0, list.size());
    }

    @Test
    void testSizeAfterAddOperations() {
        list.addFirst(1);
        list.addLast(2);
        list.addFirst(3);

        assertEquals(3, list.size());
    }

    @Test
    void testSizeAfterRemoveOperations() throws EmptyCollectionException {
        list.addFirst(1);
        list.addLast(2);
        list.addFirst(3);

        list.removeFirst();
        list.removeLast();

        assertEquals(1, list.size());
    }

    // Testes de integração - operações combinadas
    @Test
    void testMixedOperations() throws EmptyCollectionException {
        // Adiciona elementos
        list.addFirst(1);
        list.addLast(2);
        list.addFirst(0);

        assertEquals(3, list.size());

        // Remove e verifica
        Integer first = list.removeFirst();
        Integer last = list.removeLast();

        assertEquals(0, first);
        assertEquals(2, last);
        assertEquals(1, list.size());

        // Adiciona mais elementos
        list.addLast(3);
        list.addFirst(-1);

        assertEquals(3, list.size());
    }

    // Testes com strings
    @Test
    void testWithStringElements() throws EmptyCollectionException {
        stringList.addFirst("Hello");
        stringList.addLast("World");
        stringList.addFirst("Test");

        assertEquals(3, stringList.size());

        String first = stringList.removeFirst();
        assertEquals("Test", first);

        String last = stringList.removeLast();
        assertEquals("World", last);

        assertEquals(1, stringList.size());
    }

    // Testes edge cases
    @Test
    void testMultipleAddRemoveOperations() throws EmptyCollectionException {
        for (int i = 0; i < 100; i++)
            list.addFirst(i);
        assertEquals(100, list.size());

        for (int i = 0; i < 50; i++)
            list.removeFirst();
        assertEquals(50, list.size());

        for (int i = 0; i < 50; i++)
            list.removeLast();
        assertTrue(list.isEmpty());
    }

    @Test
    void testSingleElementListOperations() throws EmptyCollectionException {
        list.addFirst(42);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());

        Integer element = list.removeFirst();
        assertEquals(42, element);
        assertTrue(list.isEmpty());

        // Testar com removeLast também
        list.addLast(43);
        element = list.removeLast();
        assertEquals(43, element);
        assertTrue(list.isEmpty());
    }
}
