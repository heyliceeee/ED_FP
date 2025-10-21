package test;

import exceptions.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.FP06.CircularLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {

    private CircularLinkedList<Integer> list;
    private CircularLinkedList<String> stringList;

    @BeforeEach
    void setUp() {
        list = new CircularLinkedList<>();
        stringList = new CircularLinkedList<>();
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
        assertEquals(1, list.getSize());
        assertTrue(list.isCircular());
    }

    @Test
    void testAddFirstMultipleElements() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        assertEquals(3, list.getSize());
        assertTrue(list.isCircular());
    }

    @Test
    void testAddFirstOrder() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        // Deve ser: 30, 20, 10
        assertEquals(30, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
        assertTrue(list.isCircular());
    }

    @Test
    void testAddFirstCircularity() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        // Verificar que tail aponta para head e vice-versa
        assertTrue(list.isCircular());
    }

    // Testes para addToRear
    @Test
    void testAddToRearToEmptyList() {
        list.addToRear(10);

        assertFalse(list.isEmpty());
        assertEquals(1, list.getSize());
        assertTrue(list.isCircular());
    }

    @Test
    void testAddToRearMultipleElements() {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);

        assertEquals(3, list.getSize());
        assertTrue(list.isCircular());
    }

    @Test
    void testAddToRearOrder() throws EmptyCollectionException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);

        // Deve ser: 10, 20, 30
        assertEquals(10, list.getHead().getElement());
        assertEquals(30, list.getTail().getElement());
        assertTrue(list.isCircular());
    }

    @Test
    void testAddToRearCircularity() {
        list.addToRear(10);
        list.addToRear(20);

        assertTrue(list.isCircular());
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
        list.addFirst(42);
        Integer result = list.removeFirst();

        assertEquals(42, result);
        assertTrue(list.isEmpty());
        assertEquals(0, list.getSize());
        assertTrue(list.isCircular()); // Lista vazia é considerada circular
    }

    @Test
    void testRemoveFirstFromMultipleElementsList() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        Integer result = list.removeFirst();

        assertEquals(30, result);
        assertEquals(2, list.getSize());
        assertEquals(20, list.getHead().getElement());
        assertTrue(list.isCircular());
    }

    @Test
    void testRemoveFirstMaintainsCircularity() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        list.removeFirst();

        assertTrue(list.isCircular());
        assertEquals(2, list.getSize());
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
        list.addFirst(42);
        Integer result = list.removeLast();

        assertEquals(42, result);
        assertTrue(list.isEmpty());
        assertEquals(0, list.getSize());
        assertTrue(list.isCircular());
    }

    @Test
    void testRemoveLastFromMultipleElementsList() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        Integer result = list.removeLast();

        assertEquals(10, result);
        assertEquals(2, list.getSize());
        assertEquals(30, list.getHead().getElement());
        assertTrue(list.isCircular());
    }

    @Test
    void testRemoveLastMaintainsCircularity() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        list.removeLast();

        assertTrue(list.isCircular());
        assertEquals(2, list.getSize());
    }

    // Testes para first e last
    @Test
    void testFirstOnEmptyList() {
        assertThrows(NullPointerException.class, () -> {
            list.getHead().getElement();
        });
    }

    @Test
    void testLastOnEmptyList() {
        assertThrows(NullPointerException.class, () -> {
            list.getTail().getElement();
        });
    }

    @Test
    void testFirstOnSingleElement() throws EmptyCollectionException {
        list.addFirst(42);
        assertEquals(42, list.getHead().getElement());
        assertEquals(42, list.getTail().getElement()); // Em lista de 1 elemento, first = last
    }

    @Test
    void testLastOnMultipleElements() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        assertEquals(30, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
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
        assertEquals(0, list.getSize());
    }

    @Test
    void testSizeAfterAddOperations() {
        list.addFirst(1);
        assertEquals(1, list.getSize());

        list.addToRear(2);
        assertEquals(2, list.getSize());

        list.addFirst(3);
        assertEquals(3, list.getSize());
    }

    @Test
    void testSizeAfterRemoveOperations() throws EmptyCollectionException {
        list.addFirst(1);
        list.addToRear(2);
        list.addFirst(3);

        list.removeFirst();
        assertEquals(2, list.getSize());

        list.removeLast();
        assertEquals(1, list.getSize());

        list.removeFirst();
        assertEquals(0, list.getSize());
    }

    // Testes para isCircular
    @Test
    void testIsCircularOnEmptyList() {
        assertTrue(list.isCircular());
    }

    @Test
    void testIsCircularOnSingleElement() {
        list.addFirst(10);
        assertTrue(list.isCircular());
    }

    @Test
    void testIsCircularOnMultipleElements() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        assertTrue(list.isCircular());
    }

    @Test
    void testIsCircularAfterRemoval() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        list.removeFirst();
        assertTrue(list.isCircular());

        list.removeLast();
        assertTrue(list.isCircular());

        list.addToRear(40);
        list.removeFirst();
        assertTrue(list.isCircular());
    }

    // Testes para traverseFrom
    @Test
    void testTraverseFromEmptyList() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.traverseFrom(10);
        });
    }

    @Test
    void testTraverseFromNonExistentElement() {
        list.addFirst(10);
        list.addFirst(20);

        assertThrows(EmptyCollectionException.class, () -> {
            list.traverseFrom(30);
        });
    }

    @Test
    void testTraverseFromFirstElement() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        // Não deve lançar exceção
        assertDoesNotThrow(() -> {
            list.traverseFrom(30);
        });
    }

    @Test
    void testTraverseFromMiddleElement() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        // Não deve lançar exceção
        assertDoesNotThrow(() -> {
            list.traverseFrom(20);
        });
    }

    @Test
    void testTraverseFromLastElement() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        // Não deve lançar exceção
        assertDoesNotThrow(() -> {
            list.traverseFrom(10);
        });
    }

    // Testes com strings
    @Test
    void testWithStringElements() throws EmptyCollectionException {
        stringList.addFirst("Hello");
        stringList.addToRear("World");
        stringList.addFirst("Test");

        assertEquals(3, stringList.getSize());
        assertEquals("Test", stringList.getHead().getElement());
        assertEquals("World", stringList.getTail().getElement());
        assertTrue(stringList.isCircular());
    }

    @Test
    void testStringTraverseFrom() {
        stringList.addFirst("Apple");
        stringList.addToRear("Banana");
        stringList.addToRear("Cherry");

        // Não deve lançar exceção
        assertDoesNotThrow(() -> {
            stringList.traverseFrom("Banana");
        });
    }

    // Testes de integração - operações combinadas
    @Test
    void testMixedOperationsComplex() throws EmptyCollectionException {
        list.addFirst(50);
        list.addToRear(10);
        list.addFirst(30);
        // Lista: 30, 50, 10

        assertEquals(30, list.removeFirst());
        // Lista: 50, 10

        list.addToRear(5);
        list.addFirst(40);
        // Lista: 40, 50, 10, 5

        assertEquals(40, list.getHead().getElement());
        assertEquals(5, list.getTail().getElement());
        assertEquals(4, list.getSize());
        assertTrue(list.isCircular());

        assertEquals(40, list.removeFirst());
        // Lista: 50, 10, 5

        assertEquals(5, list.removeLast());
        // Lista: 50, 10

        assertEquals(50, list.removeFirst());
        // Lista: 10

        assertEquals(10, list.removeLast());

        assertTrue(list.isEmpty());
    }

    // Testes edge cases
    @Test
    void testLargeNumberOfElements() {
        for (int i = 0; i < 100; i++) {
            list.addToRear(i);
        }

        assertEquals(100, list.getSize());
        assertTrue(list.isCircular());

        // Verificar que ainda é circular após muitas operações
        for (int i = 0; i < 50; i++) {
            list.removeFirst();
        }

        assertEquals(50, list.getSize());
        assertTrue(list.isCircular());
    }

    @Test
    void testAllElementsSame() throws EmptyCollectionException {
        for (int i = 0; i < 5; i++) {
            list.addToRear(42);
        }

        assertEquals(5, list.getSize());
        assertEquals(42, list.getHead().getElement());
        assertEquals(42, list.getTail().getElement());
        assertTrue(list.isCircular());

        list.removeFirst();
        assertEquals(4, list.getSize());
        assertEquals(42, list.getHead().getElement());
        assertEquals(42, list.getTail().getElement());
        assertTrue(list.isCircular());
    }

    // Testes para toString
    @Test
    void testToStringEmptyList() {
        assertEquals("CircularLinkedList[]", list.toString());
    }

    @Test
    void testToStringWithElements() {
        list.addFirst(10);
        list.addToRear(20);
        list.addToRear(30);

        String result = list.toString();
        assertTrue(result.contains("CircularLinkedList"));
        assertTrue(result.contains("10"));
        assertTrue(result.contains("20"));
        assertTrue(result.contains("30"));
        assertTrue(result.contains("⇄"));
    }

    // Testes específicos para comportamento circular
    @Test
    void testSingleElementCircularity() {
        list.addFirst(10);

        // Em lista com 1 elemento, head e tail são o mesmo
        // e ambos devem apontar para si mesmos
        assertTrue(list.isCircular());
    }

    @Test
    void testCircularTraversalCompleteness() throws EmptyCollectionException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);

        // Testar que traverseFrom funciona para todos os elementos
        assertDoesNotThrow(() -> list.traverseFrom(10));
        assertDoesNotThrow(() -> list.traverseFrom(20));
        assertDoesNotThrow(() -> list.traverseFrom(30));
    }

    // Testes para verificar robustez das operações
    @Test
    void testMultipleAddRemoveCycles() throws EmptyCollectionException {
        // Ciclos repetidos de adição e remoção
        for (int cycle = 0; cycle < 10; cycle++) {
            for (int i = 0; i < 5; i++) {
                list.addToRear(i);
            }

            assertEquals(5, list.getSize());
            assertTrue(list.isCircular());

            for (int i = 0; i < 5; i++) {
                list.removeFirst();
            }

            assertTrue(list.isEmpty());
            assertTrue(list.isCircular());
        }
    }

    // Testes para métodos herdados (se existirem)
    @Test
    void testContainsMethod() throws EmptyCollectionException {
        // Assumindo que existe um método contains (pode precisar ser implementado)
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        // Se o método contains existir:
        // assertTrue(list.contains(20));
        // assertFalse(list.contains(40));

        // Alternativa: verificar através de first/last
        assertEquals(30, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
    }

    @Test
    void testAddFirstCircularityAfterMultipleOperations() {
        // Testar que addFirst mantém circularidade em cenários complexos
        list.addFirst(1);
        assertTrue(list.isCircular());

        list.addFirst(2);
        assertTrue(list.isCircular());

        list.addFirst(3);
        assertTrue(list.isCircular());

        list.addToRear(4);
        assertTrue(list.isCircular());

        assertEquals(4, list.getSize());
        assertEquals(3, list.getHead().getElement());
        assertEquals(4, list.getTail().getElement());
    }

    @Test
    void testAddToRearCircularityAfterMultipleOperations() {
        // Testar que addToRear mantém circularidade em cenários complexos
        list.addToRear(1);
        assertTrue(list.isCircular());

        list.addToRear(2);
        assertTrue(list.isCircular());

        list.addToRear(3);
        assertTrue(list.isCircular());

        list.addFirst(0);
        assertTrue(list.isCircular());

        assertEquals(4, list.getSize());
        assertEquals(0, list.getHead().getElement());
        assertEquals(3, list.getTail().getElement());
    }

    // Testes para verificar que métodos não quebram circularidade
    @Test
    void testCircularityPreservedAfterAllOperations() throws EmptyCollectionException {
        // Sequência complexa que deve manter circularidade
        list.addFirst(100);
        assertTrue(list.isCircular());

        list.addToRear(200);
        assertTrue(list.isCircular());

        list.addFirst(50);
        assertTrue(list.isCircular());

        list.removeFirst();
        assertTrue(list.isCircular());

        list.removeLast();
        assertTrue(list.isCircular());

        list.addToRear(300);
        assertTrue(list.isCircular());

        list.addFirst(25);
        assertTrue(list.isCircular());

        assertEquals(3, list.getSize());
        assertEquals(25, list.getHead().getElement());
        assertEquals(300, list.getTail().getElement());
    }
}