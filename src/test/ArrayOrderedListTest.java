package test;

import exceptions.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.FP02.LinkedListNode;
import structures.FP05.ArrayOrderedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayOrderedListTest {
    private ArrayOrderedList<Integer> list;
    private ArrayOrderedList<String> stringList;

    @BeforeEach
    void  setUp() {
        list = new ArrayOrderedList<>();
        stringList = new ArrayOrderedList<>();
    }

    // Testes para add
    @Test
    void testAddToEmptyList() {
        list.add(10);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    void testAddMultipleElements() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(3, list.size());
    }

    @Test
    void testAddMaintainsOrder() throws EmptyCollectionException {
        list.add(30);
        list.add(10);
        list.add(20);

        // Deve manter ordem crescente: 10, 20, 30
        assertEquals(10, list.first());
        assertEquals(30, list.last());
    }

    @Test
    void testAddWithNullElement() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.add(null);
        });
    }

    @Test
    void testAddDuplicateElements() throws EmptyCollectionException {
        list.add(10);
        list.add(10);
        list.add(20);

        assertEquals(3, list.size());
        assertEquals(10, list.first());
        assertEquals(20, list.last());
    }

    @Test
    void testAddInDescendingOrder() throws EmptyCollectionException {
        list.add(50);
        list.add(40);
        list.add(30);
        list.add(20);
        list.add(10);

        assertEquals(10, list.first());
        assertEquals(50, list.last());
        assertEquals(5, list.size());
    }

    @Test
    void testAddInAscendingOrder() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        assertEquals(10, list.first());
        assertEquals(50, list.last());
        assertEquals(5, list.size());
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
        list.add(42);
        Integer result = list.removeFirst();

        assertEquals(42, result);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveFirstFromMultipleElementsList() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer result = list.removeFirst();

        assertEquals(10, result);
        assertEquals(2, list.size());
        assertEquals(20, list.first());
    }

    @Test
    void testRemoveFirstMaintainsOrder() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(10, list.removeFirst());
        assertEquals(20, list.removeFirst());
        assertEquals(30, list.removeFirst());
        assertTrue(list.isEmpty());
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
        list.add(42);
        Integer result = list.removeLast();

        assertEquals(42, result);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveLastFromMultipleElementsList() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer result = list.removeLast();

        assertEquals(30, result);
        assertEquals(2, list.size());
        assertEquals(20, list.last());
    }

    // Testes para remove(T elem)
    @Test
    void testRemoveElementFromEmptyList() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.remove(10);
        });
    }

    @Test
    void testRemoveNonExistentElement() {
        list.add(10);
        list.add(20);

        assertThrows(EmptyCollectionException.class, () -> {
            list.remove(30);
        });
    }

    @Test
    void testRemoveFirstElement() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer result = list.remove(10);

        assertEquals(10, result);
        assertEquals(2, list.size());
        assertEquals(20, list.first());
    }

    @Test
    void testRemoveMiddleElement() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer result = list.remove(20);

        assertEquals(20, result);
        assertEquals(2, list.size());
        assertEquals(10, list.first());
        assertEquals(30, list.last());
    }

    @Test
    void testRemoveLastElement() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer result = list.remove(30);

        assertEquals(30, result);
        assertEquals(2, list.size());
        assertEquals(20, list.last());
    }

    @Test
    void testRemoveDuplicateElement() throws EmptyCollectionException {
        list.add(10);
        list.add(10);
        list.add(20);

        Integer result = list.remove(10);

        assertEquals(10, result);
        assertEquals(2, list.size());
        // Deve remover apenas a primeira ocorrência
        assertTrue(list.contains(10)); // Ainda deve conter a segunda ocorrência
    }

    // Testes para first
    @Test
    void testFirstOnEmptyList() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.first();
        });
    }

    @Test
    void testFirstOnSingleElementList() throws EmptyCollectionException {
        list.add(42);
        Integer result = list.first();

        assertEquals(42, result);
        assertEquals(1, list.size());
    }

    @Test
    void testFirstOnMultipleElementsList() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer result = list.first();

        assertEquals(10, result);
        assertEquals(3, list.size());
    }

    // Testes para last
    @Test
    void testLastOnEmptyList() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.last();
        });
    }

    @Test
    void testLastOnSingleElementList() throws EmptyCollectionException {
        list.add(42);
        Integer result = list.last();

        assertEquals(42, result);
        assertEquals(1, list.size());
    }

    @Test
    void testLastOnMultipleElementsList() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer result = list.last();

        assertEquals(30, result);
        assertEquals(3, list.size());
    }

    // Testes para contains
    @Test
    void testContainsOnEmptyList() {
        assertFalse(list.contains(10));
    }

    @Test
    void testContainsExistingElement() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertTrue(list.contains(20));
    }

    @Test
    void testContainsNonExistingElement() {
        list.add(10);
        list.add(20);

        assertFalse(list.contains(30));
    }

    @Test
    void testContainsNull() {
        assertFalse(list.contains(null));
    }

    // Testes para isEmpty
    @Test
    void testIsEmptyOnNewList() {
        assertTrue(list.isEmpty());
    }

    @Test
    void testIsEmptyAfterAdd() {
        list.add(10);
        assertFalse(list.isEmpty());
    }

    @Test
    void testIsEmptyAfterRemove() throws EmptyCollectionException {
        list.add(10);
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
        list.add(1);
        assertEquals(1, list.size());

        list.add(2);
        assertEquals(2, list.size());

        list.add(3);
        assertEquals(3, list.size());
    }

    @Test
    void testSizeAfterRemoveOperations() throws EmptyCollectionException {
        list.add(1);
        list.add(2);
        list.add(3);

        list.removeFirst();
        assertEquals(2, list.size());

        list.removeLast();
        assertEquals(1, list.size());

        list.remove(2);
        assertEquals(0, list.size());
    }

    // Testes para expandCapacity
    @Test
    void testExpandCapacityAutomatically() {
        ArrayOrderedList<Integer> smallList = new ArrayOrderedList<>(2);

        smallList.add(1);
        smallList.add(2);
        smallList.add(3); // Deve expandir aqui

        assertEquals(3, smallList.size());
        assertTrue(smallList.contains(1));
        assertTrue(smallList.contains(2));
        assertTrue(smallList.contains(3));
    }

    // Testes para Iterator
    @Test
    void testIteratorOnEmptyList() {
        Iterator<Integer> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorHasNext() {
        list.add(10);
        list.add(20);

        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorNext() {
        list.add(10);
        list.add(20);
        list.add(30);

        Iterator<Integer> iterator = list.iterator();
        assertEquals(10, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(30, iterator.next());
    }

    @Test
    void testIteratorNextOnEmptyList() {
        Iterator<Integer> iterator = list.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testIteratorNextBeyondSize() {
        list.add(10);

        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testIteratorRemove() {
        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println("Lista antes: " + list.toString());
        System.out.println("Size antes: " + list.size());

        Iterator<Integer> iterator = list.iterator();
        iterator.next(); // Avança para o primeiro elemento (10)
        iterator.remove(); // Remove o primeiro elemento

        System.out.println("Lista depois: " + list.toString());
        System.out.println("Size depois: " + list.size());
        System.out.println("First depois: " + list.first());

        assertEquals(2, list.size());
        assertEquals(20, list.first());
        assertFalse(list.contains(10));
    }

    @Test
    void testIteratorRemoveWithoutNext() {
        list.add(10);

        Iterator<Integer> iterator = list.iterator();
        assertThrows(IllegalArgumentException.class, iterator::remove);
    }

    @Test
    void testIteratorRemoveTwice() {
        list.add(10);
        list.add(20);

        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();

        assertThrows(IllegalArgumentException.class, iterator::remove);
    }

    @Test
    void testIteratorConcurrentModification() {
        list.add(10);
        list.add(20);

        Iterator<Integer> iterator = list.iterator();
        list.add(30); // Modificação externa

        assertThrows(ConcurrentModificationException.class, iterator::hasNext);
    }

    @Test
    void testIteratorRemoveMaintainsOrder() {
        list.add(10);
        list.add(20);
        list.add(30);

        Iterator<Integer> iterator = list.iterator();
        iterator.next(); // 10
        iterator.next(); // 20
        iterator.remove(); // Remove 20

        assertEquals(2, list.size());
        assertEquals(10, list.first());
        assertEquals(30, list.last());
        assertFalse(list.contains(20));
    }

    // Testes com strings
    @Test
    void testWithStringElements() throws EmptyCollectionException {
        stringList.add("Charlie");
        stringList.add("Alice");
        stringList.add("Bob");

        // Deve ordenar alfabeticamente: Alice, Bob, Charlie
        assertEquals("Alice", stringList.first());
        assertEquals("Charlie", stringList.last());
        assertEquals(3, stringList.size());
    }

    @Test
    void testStringContains() {
        stringList.add("Hello");
        stringList.add("World");

        assertTrue(stringList.contains("Hello"));
        assertTrue(stringList.contains("World"));
        assertFalse(stringList.contains("Test"));
    }

    // Testes de integração - operações combinadas
    @Test
    void testMixedOperationsComplex() throws EmptyCollectionException {
        list.add(50);
        list.add(10);
        list.add(30);
        // Lista ordenada: [10, 30, 50]

        assertEquals(10, list.removeFirst()); // Remove primeiro (10) Lista: [30, 50]

        list.add(5);
        list.add(40);
        // Lista ordenada: [5, 30, 40, 50]

        assertEquals(5, list.first());   // Primeiro = 5 ✓
        assertEquals(50, list.last());    // Último = 50 ✓
        assertEquals(4, list.size());     // Size = 4 ✓

        assertEquals(5, list.removeFirst()); // Remove primeiro (5) Lista: [30, 40, 50]

        assertEquals(30, list.remove(30)); // Remove 30 Lista: [40, 50]

        assertEquals(50, list.removeLast()); // CORREÇÃO: removeLast() remove 50 (último) Lista: [40]

        assertEquals(40, list.removeLast()); // Agora remove 40 (único elemento)

        assertTrue(list.isEmpty());
    }

    // Testes edge cases
    @Test
    void testLargeNumberOfElements() {
        for (int i = 100; i >= 1; i--) {
            list.add(i);
        }

        assertEquals(100, list.size());
        assertEquals(1, list.first());
        assertEquals(100, list.last());
    }

    @Test
    void testAllElementsSame() throws EmptyCollectionException {
        for (int i = 0; i < 5; i++) {
            list.add(42);
        }

        assertEquals(5, list.size());
        assertEquals(42, list.first());
        assertEquals(42, list.last());

        list.remove(42); // Remove primeira ocorrência
        assertEquals(4, list.size());
        assertTrue(list.contains(42));
    }

    // Testes para construtor com capacidade inicial
    @Test
    void testInitialCapacityConstructor() {
        ArrayOrderedList<Integer> customList = new ArrayOrderedList<>(50);

        assertTrue(customList.isEmpty());
        assertEquals(0, customList.size());

        customList.add(100);
        assertEquals(1, customList.size());
    }

    // Testes para toString
    @Test
    void testToStringEmptyList() {
        assertEquals("", list.toString());
    }

    @Test
    void testToStringWithElements() {
        list.add(10);
        list.add(20);
        list.add(30);

        String result = list.toString();
        assertTrue(result.contains("10"));
        assertTrue(result.contains("20"));
        assertTrue(result.contains("30"));
    }

    // Testes específicos para ordenação
    @Test
    void testOrderWithNegativeNumbers() throws EmptyCollectionException {
        list.add(-10);
        list.add(-30);
        list.add(-20);
        list.add(0);
        list.add(10);

        assertEquals(-30, list.first());
        assertEquals(10, list.last());
        assertEquals(5, list.size());
    }

    @Test
    void testOrderMaintainedAfterMultipleOperations() throws EmptyCollectionException {
        list.add(30);
        list.add(10);
        list.add(40);
        list.add(20);

        // Lista ordenada: 10, 20, 30, 40
        assertEquals(10, list.removeFirst());
        assertEquals(40, list.removeLast());

        list.add(5);
        list.add(35);

        // Nova lista ordenada: 5, 20, 30, 35
        assertEquals(5, list.first());
        assertEquals(35, list.last());
        assertEquals(4, list.size());
    }
}
