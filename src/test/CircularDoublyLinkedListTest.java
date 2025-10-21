package test;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.FP06.CircularDoublyLinkedList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CircularDoublyLinkedListTest {

    private CircularDoublyLinkedList<Integer> list;
    private CircularDoublyLinkedList<String> stringList;

    @BeforeEach
    void setUp() {
        list = new CircularDoublyLinkedList<>();
        stringList = new CircularDoublyLinkedList<>();
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
    void testIteratorConcurrentModificationOnRotate() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        Iterator<Integer> iterator = list.iterator();
        list.rotateForward(); // Modificação externa

        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    // Testes para addFirst
    @Test
    void testAddFirstToEmptyList() {
        list.addFirst(10);

        assertFalse(list.isEmpty());
        assertEquals(1, list.getSize());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testAddFirstMultipleElements() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        assertEquals(3, list.getSize());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testAddFirstOrder() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        // Deve ser: 30, 20, 10
        assertEquals(30, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testAddFirstCircularity() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        // Verificar que tail aponta para head e vice-versa
        assertTrue(list.isProperlyCircular());
    }

    // Testes para addToRear
    @Test
    void testAddToRearToEmptyList() {
        list.addToRear(10);

        assertFalse(list.isEmpty());
        assertEquals(1, list.getSize());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testAddToRearMultipleElements() {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);

        assertEquals(3, list.getSize());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testAddToRearOrder() throws EmptyCollectionException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);

        // Deve ser: 10, 20, 30
        assertEquals(10, list.getHead().getElement());
        assertEquals(30, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testAddToRearCircularity() {
        list.addToRear(10);
        list.addToRear(20);

        assertTrue(list.isProperlyCircular());
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
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testRemoveFirstMaintainsCircularity() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        list.removeFirst();

        assertTrue(list.isProperlyCircular());
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
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testRemoveLastMaintainsCircularity() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        list.removeLast();

        assertTrue(list.isProperlyCircular());
        assertEquals(2, list.getSize());
    }

    // Testes para remove(T element)
    @Test
    void testRemoveElementFromEmptyList() {
        assertThrows(EmptyCollectionException.class, () -> {
            list.remove(10);
        });
    }

    @Test
    void testRemoveNonExistentElement() {
        list.addFirst(10);
        list.addFirst(20);

        assertThrows(ElementNotFoundException.class, () -> {
            list.remove(30);
        });
    }

    @Test
    void testRemoveFirstElement() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        Integer result = list.remove(30);

        assertEquals(30, result);
        assertEquals(2, list.getSize());
        assertEquals(20, list.getHead().getElement());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testRemoveMiddleElement() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        Integer result = list.remove(20);

        assertEquals(20, result);
        assertEquals(2, list.getSize());
        assertEquals(30, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testRemoveLastElement() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        Integer result = list.remove(10);

        assertEquals(10, result);
        assertEquals(2, list.getSize());
        assertEquals(20, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testRemoveOnlyElement() throws EmptyCollectionException {
        list.addFirst(42);

        Integer result = list.remove(42);

        assertEquals(42, result);
        assertTrue(list.isEmpty());
        assertEquals(0, list.getSize());
    }

    @Test
    void testRemoveMaintainsCircularity() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        list.remove(20);

        assertTrue(list.isProperlyCircular());
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

        list.remove(1);
        assertEquals(0, list.getSize());
    }

    // Testes para isProperlyCircular
    @Test
    void testIsProperlyCircularOnEmptyList() {
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testIsProperlyCircularOnSingleElement() {
        list.addFirst(10);
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testIsProperlyCircularOnMultipleElements() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testIsProperlyCircularAfterRemoval() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        list.removeFirst();
        assertTrue(list.isProperlyCircular());

        list.removeLast();
        assertTrue(list.isProperlyCircular());

        list.addToRear(40);
        list.remove(20);
        assertTrue(list.isProperlyCircular());
    }

    // Testes para rotateForward
    @Test
    void testRotateForwardOnEmptyList() {
        // Não deve lançar exceção
        list.rotateForward();
        assertTrue(list.isEmpty());
    }

    @Test
    void testRotateForwardOnSingleElement() throws EmptyCollectionException {
        list.addFirst(10);
        list.rotateForward();

        assertEquals(1, list.getSize());
        assertEquals(10, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testRotateForwardOnMultipleElements() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        // Lista: 30, 20, 10

        list.rotateForward();
        // Após rotação: 20, 10, 30

        assertEquals(20, list.getHead().getElement());
        assertEquals(30, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testMultipleRotateForward() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        // Lista: 30, 20, 10

        list.rotateForward(); // 20, 10, 30
        list.rotateForward(); // 10, 30, 20
        list.rotateForward(); // 30, 20, 10 (volta ao início)

        assertEquals(30, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    // Testes para rotateBackward
    @Test
    void testRotateBackwardOnEmptyList() {
        // Não deve lançar exceção
        list.rotateBackward();
        assertTrue(list.isEmpty());
    }

    @Test
    void testRotateBackwardOnSingleElement() throws EmptyCollectionException {
        list.addFirst(10);
        list.rotateBackward();

        assertEquals(1, list.getSize());
        assertEquals(10, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testRotateBackwardOnMultipleElements() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        // Lista: 30, 20, 10

        list.rotateBackward();
        // Após rotação: 10, 30, 20

        assertEquals(10, list.getHead().getElement());
        assertEquals(20, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testMultipleRotateBackward() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        // Lista: 30, 20, 10

        list.rotateBackward(); // 10, 30, 20
        list.rotateBackward(); // 20, 10, 30
        list.rotateBackward(); // 30, 20, 10 (volta ao início)

        assertEquals(30, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testRotateForwardAndBackward() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        // Lista: 30, 20, 10

        list.rotateForward();  // 20, 10, 30
        list.rotateBackward(); // 30, 20, 10 (volta ao original)

        assertEquals(30, list.getHead().getElement());
        assertEquals(10, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
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
        assertTrue(stringList.isProperlyCircular());
    }

    @Test
    void testStringRemove() throws EmptyCollectionException {
        stringList.addFirst("Apple");
        stringList.addToRear("Banana");
        stringList.addToRear("Cherry");

        // Verificar que contém todos os elementos antes da remoção
        assertTrue(stringList.contains("Apple"));
        assertTrue(stringList.contains("Banana"));
        assertTrue(stringList.contains("Cherry"));

        String result = stringList.remove("Banana");
        assertEquals("Banana", result);
        assertEquals(2, stringList.getSize());

        // Verificar após remoção
        assertTrue(stringList.contains("Apple"));
        assertTrue(stringList.contains("Cherry"));
        assertFalse(stringList.contains("Banana"));
        assertTrue(stringList.isProperlyCircular());
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
        assertTrue(list.isProperlyCircular());

        assertEquals(40, list.removeFirst());
        // Lista: 50, 10, 5

        assertEquals(50, list.remove(50));
        // Lista: 10, 5

        assertEquals(5, list.removeLast());
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
        assertTrue(list.isProperlyCircular());

        // Verificar que ainda é circular após muitas operações
        for (int i = 0; i < 50; i++) {
            list.removeFirst();
        }

        assertEquals(50, list.getSize());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testAllElementsSame() throws EmptyCollectionException {
        for (int i = 0; i < 5; i++)
            list.addToRear(42);

        assertEquals(5, list.getSize());
        assertEquals(42, list.getHead().getElement());
        assertEquals(42, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());

        list.remove(42); // Remove primeira ocorrência
        assertEquals(4, list.getSize());
        assertTrue(list.contains(42));
        assertTrue(list.isProperlyCircular());
    }

    // Testes para toString
    @Test
    void testToStringEmptyList() {
        assertEquals("CircularDoublyLinkedList[]", list.toString());
    }

    @Test
    void testToStringWithElements() {
        list.addFirst(10);
        list.addToRear(20);
        list.addToRear(30);

        String result = list.toString();
        assertTrue(result.contains("CircularDoublyLinkedList"));
        assertTrue(result.contains("10"));
        assertTrue(result.contains("20"));
        assertTrue(result.contains("30"));
        assertTrue(result.contains("⇄"));
    }

    @Test
    void testToStringShowsCircularInfo() {
        list.addFirst(10);
        list.addToRear(20);

        String result = list.toString();
        assertTrue(result.contains("tail→head: true"));
        assertTrue(result.contains("head←tail: true"));
    }

    // Testes específicos para comportamento circular
    @Test
    void testCircularTraversal() throws EmptyCollectionException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);

        // Após várias rotações, deve voltar ao estado original
        for (int i = 0; i < list.getSize(); i++) {
            list.rotateForward();
        }

        assertEquals(10, list.getHead().getElement());
        assertEquals(30, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    @Test
    void testSingleElementCircularity() {
        list.addFirst(10);

        // Em lista com 1 elemento, head e tail são o mesmo
        // e ambos devem apontar para si mesmos
        assertTrue(list.isProperlyCircular());
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
            assertTrue(list.isProperlyCircular());

            for (int i = 0; i < 5; i++) {
                list.removeFirst();
            }

            assertTrue(list.isEmpty());
            assertTrue(list.isProperlyCircular());
        }
    }

    @Test
    void testRotateOnEmptyList() {
        // Não deve lançar exceção
        list.rotateForward();
        list.rotateBackward();
        assertTrue(list.isEmpty());
    }

    @Test
    void testComplexRotationSequence() throws EmptyCollectionException {
        list.addToRear(1);
        list.addToRear(2);
        list.addToRear(3);
        list.addToRear(4);
        list.addToRear(5);
        // Lista: 1, 2, 3, 4, 5

        list.rotateForward();  // 2, 3, 4, 5, 1
        list.rotateForward();  // 3, 4, 5, 1, 2
        list.rotateBackward(); // 2, 3, 4, 5, 1
        list.rotateBackward(); // 1, 2, 3, 4, 5 (volta ao original)

        assertEquals(1, list.getHead().getElement());
        assertEquals(5, list.getTail().getElement());
        assertTrue(list.isProperlyCircular());
    }

    // Testes específicos para contains
    @Test
    void testContainsOnEmptyList() {
        assertFalse(list.contains(10));
    }

    @Test
    void testContainsExistingElement() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        assertTrue(list.contains(20));
        assertTrue(list.contains(10));
        assertTrue(list.contains(30));
    }

    @Test
    void testContainsNonExistingElement() {
        list.addFirst(10);
        list.addFirst(20);

        assertFalse(list.contains(30));
        assertFalse(list.contains(40));
    }

    @Test
    void testContainsNull() {
        list.addFirst(10);
        assertFalse(list.contains(null));
    }

    @Test
    void testContainsAfterRemoval() throws EmptyCollectionException {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);

        assertTrue(list.contains(20));

        list.remove(20);
        assertFalse(list.contains(20));
        assertTrue(list.contains(10));
        assertTrue(list.contains(30));
    }

    @Test
    void testContainsInCircularList() {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);

        // Testar que encontra elementos independentemente da posição
        assertTrue(list.contains(10));
        assertTrue(list.contains(20));
        assertTrue(list.contains(30));
        assertFalse(list.contains(40));

        assertTrue(list.isProperlyCircular());
    }
}