package test;

import exceptions.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.FP02.DoublyLinkedList;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList<>();
    }

    // ===== TESTES BÁSICOS =====

    @Test
    void testNewListIsEmpty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.getSize());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test
    void testAddFirstToEmptyList() {
        list.addFirst(10);

        assertFalse(list.isEmpty());
        assertEquals(1, list.getSize());
        assertEquals(10, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
        assertNull(list.getHead().getNext());
        assertNull(list.getHead().getPrev());
    }

    @Test
    void testAddFirstMultipleElements() {
        list.addFirst(30);
        list.addFirst(20);
        list.addFirst(10);

        assertEquals(3, list.getSize());
        assertEquals(10, list.getHead().getElement());
        assertEquals(30, list.getTail().getElement());

        // Verifica ligações
        assertEquals(20, list.getHead().getNext().getElement());
        assertEquals(10, list.getHead().getNext().getPrev().getElement());
    }

    // ===== TESTES REMOÇÃO =====

    @Test
    void testRemoveFirstFromEmptyListThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.removeFirst();
        });
    }

    @Test
    void testRemoveFirstSingleElement() throws EmptyCollectionException {
        list.addFirst(42);
        int removed = list.removeFirst();

        assertEquals(42, removed);
        assertTrue(list.isEmpty());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test
    void testRemoveFirstMultipleElements() throws EmptyCollectionException {
        list.addFirst(30);
        list.addFirst(20);
        list.addFirst(10);

        assertEquals(10, list.removeFirst());
        assertEquals(2, list.getSize());
        assertEquals(20, list.getHead().getElement());
        assertEquals(30, list.getTail().getElement());

        assertEquals(20, list.removeFirst());
        assertEquals(1, list.getSize());
        assertEquals(30, list.getHead().getElement());
        assertEquals(30, list.getTail().getElement());
    }

    @Test
    void testRemoveLastFromEmptyListThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.removeLast();
        });
    }

    @Test
    void testRemoveLastSingleElement() throws EmptyCollectionException {
        list.addFirst(100);
        int removed = list.removeLast();

        assertEquals(100, removed);
        assertTrue(list.isEmpty());
    }

    @Test
    void testRemoveLastMultipleElements() throws EmptyCollectionException {
        list.addFirst(30);
        list.addFirst(20);
        list.addFirst(10);

        assertEquals(30, list.removeLast());
        assertEquals(2, list.getSize());
        assertEquals(10, list.getHead().getElement());
        assertEquals(20, list.getTail().getElement());

        assertEquals(20, list.removeLast());
        assertEquals(1, list.getSize());
        assertEquals(10, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
    }

    // ===== TESTES TO ARRAY =====

    @Test
    void testToArrayEmptyList() {
        Object[] result = list.toArray();

        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    void testToArrayWithElements() {
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        Object[] result = list.toArray();

        assertEquals(3, result.length);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        assertEquals(3, result[2]);
    }

    @Test
    void testToArrayUntil() {
        list.addFirst(5);
        list.addFirst(4);
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        Object[] result = list.toArrayUntil(3);

        assertEquals(3, result.length);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        assertEquals(3, result[2]);
    }

    @Test
    void testToArrayUntilWithPositionGreaterThanSize() {
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        Object[] result = list.toArrayUntil(10);

        assertEquals(3, result.length); // Deve limitar ao tamanho da lista
    }

    @Test
    void testToArrayUntilWithInvalidPositionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.toArrayUntil(-1);
        });
    }

    @Test
    void testToArrayAfter() {
        list.addFirst(5);
        list.addFirst(4);
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        Object[] result = list.toArrayAfter(2);

        assertEquals(3, result.length);
        assertEquals(3, result[0]);
        assertEquals(4, result[1]);
        assertEquals(5, result[2]);
    }

    @Test
    void testToArrayAfterWithPositionBeyondSize() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        Object[] result = list.toArrayAfter(5);

        assertEquals(0, result.length); // Array vazio
    }

    @Test
    void testToArrayBetween() {
        list.addFirst(6);
        list.addFirst(5);
        list.addFirst(4);
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        Object[] result = list.toArrayBetween(1, 4);

        assertEquals(4, result.length);
        assertEquals(2, result[0]);
        assertEquals(3, result[1]);
        assertEquals(4, result[2]);
        assertEquals(5, result[3]);
    }

    @Test
    void testToArrayBetweenWithInvalidParametersThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.toArrayBetween(-1, 2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            list.toArrayBetween(3, 1); // start > end
        });
    }

    // ===== TESTES OPERAÇÕES ESPECIAIS =====

    @Test
    void testGetEvenElementsWithIntegerList() throws EmptyCollectionException {
        DoublyLinkedList<Integer> intList = new DoublyLinkedList<>();
        intList.addFirst(1);
        intList.addFirst(2);
        intList.addFirst(3);
        intList.addFirst(4);
        intList.addFirst(5);

        DoublyLinkedList<Integer> evenList = intList.getEvenElements();

        assertEquals(2, evenList.getSize());
        assertEquals(4, evenList.getHead().getElement());
        assertEquals(2, evenList.getTail().getElement());
    }

    @Test
    void testGetEvenElementsWithNonIntegerListThrowsException() {
        DoublyLinkedList<String> stringList = new DoublyLinkedList<>();
        stringList.addFirst("not integer");

        assertThrows(UnsupportedOperationException.class, () -> {
            stringList.getEvenElements();
        });
    }

    @Test
    void testGetManyElementsEquals() throws EmptyCollectionException {
        DoublyLinkedList<Integer> intList = new DoublyLinkedList<>();
        intList.addFirst(1);
        intList.addFirst(2);
        intList.addFirst(1);
        intList.addFirst(3);
        intList.addFirst(1);

        int count = intList.getManyElementsEquals(1);

        assertEquals(3, count);
    }

    @Test
    void testRemoveDuplicateElements() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(1);
        list.addFirst(3);
        list.addFirst(1);
        list.addFirst(4);

        list.removeDuplicateElements(1);

        Object[] result = list.toArray();

        assertEquals(4, result.length); // A lista final deve ter 4 elementos

        // A ordem correta é [4, 1, 3, 2]
        assertEquals(4, result[0]);
        assertEquals(1, result[1]);
        assertEquals(3, result[2]);
        assertEquals(2, result[3]);
    }

    // ===== TESTES STRING REPRESENTATION =====

    @Test
    void testToStringEmptyListThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.toString();
        });
    }

    @Test
    void testToStringWithElements() throws EmptyCollectionException {
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        String result = list.toString();

        assertTrue(result.contains("1 <-> 2 <-> 3 <-> null"));
    }

    @Test
    void testPrintListEmptyListThrowsException() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.printList();
        });
    }

    // ===== TESTES CASOS LIMITE =====

    @Test
    void testComplexOperationsSequence() throws EmptyCollectionException {
        // Sequência complexa de operações
        list.addFirst(10);
        list.addFirst(20);
        list.removeFirst();
        list.addFirst(30);
        list.removeLast();
        list.addFirst(40);

        assertEquals(2, list.getSize());
        assertEquals(40, list.getHead().getElement());
        assertEquals(30, list.getTail().getElement());
    }

    @Test
    void testBoundaryConditions() throws EmptyCollectionException {
        // Teste com um único elemento
        list.addFirst(100);
        assertEquals(100, list.removeFirst());
        assertTrue(list.isEmpty());

        // Adiciona e remove múltiplas vezes
        list.addFirst(200);
        list.addFirst(300);
        list.removeLast();
        list.removeFirst();
        assertTrue(list.isEmpty());
    }

    @Test
    void testGetManyElementsEqualsWithNoMatches() throws EmptyCollectionException {
        DoublyLinkedList<Integer> intList = new DoublyLinkedList<>();
        intList.addFirst(1);
        intList.addFirst(2);
        intList.addFirst(3);

        int count = intList.getManyElementsEquals(999);

        assertEquals(0, count);
    }

    @Test
    void testRemoveDuplicateElementsWhenNoDuplicates() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        list.removeDuplicateElements(1);

        assertEquals(3, list.getSize()); // Nada deve ser removido
    }
}
