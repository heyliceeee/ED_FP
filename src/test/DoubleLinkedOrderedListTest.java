package test;

import exceptions.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.FP03.ArrayStack;
import structures.FP03.LinkedStack;
import structures.FP04.LinkedQueue;
import structures.FP05.DoubleLinkedOrderedList;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import static org.junit.jupiter.api.Assertions.*;

class DoubleLinkedOrderedListTest {

    private DoubleLinkedOrderedList<Integer> list;
    private DoubleLinkedOrderedList<String> stringList;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedOrderedList<>();
        stringList = new DoubleLinkedOrderedList<>();
    }

    // Testes para add - ordenação
    @Test
    void testAddToEmptyList() {
        list.add(10);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertTrue(list.isOrdered());
    }

    @Test
    void testAddMultipleElementsInOrder() {
        list.add(10);
        list.add(20);
        list.add(30);

        assertEquals(3, list.size());
        assertTrue(list.isOrdered());
    }

    @Test
    void testAddMultipleElementsReverseOrder() {
        list.add(30);
        list.add(20);
        list.add(10);

        assertEquals(3, list.size());
        assertTrue(list.isOrdered());
    }

    @Test
    void testAddMultipleElementsRandomOrder() {
        list.add(50);
        list.add(10);
        list.add(30);
        list.add(20);
        list.add(40);

        assertEquals(5, list.size());
        assertTrue(list.isOrdered());
    }

    @Test
    void testAddWithNullElement() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.add(null);
        });
    }

    @Test
    void testAddDuplicateElements() {
        list.add(10);
        list.add(10);
        list.add(20);

        assertEquals(3, list.size());
        assertTrue(list.isOrdered());
    }

    @Test
    void testAddAtBeginning() throws EmptyCollectionException {
        list.add(20);
        list.add(10); // Deve ir para o início

        assertEquals(10, list.first());
        assertEquals(20, list.last());
        assertTrue(list.isOrdered());
    }

    @Test
    void testAddAtEnd() throws EmptyCollectionException {
        list.add(10);
        list.add(20); // Deve ir para o final

        assertEquals(10, list.first());
        assertEquals(20, list.last());
        assertTrue(list.isOrdered());
    }

    @Test
    void testAddInMiddle() throws EmptyCollectionException {
        list.add(10);
        list.add(30);
        list.add(20); // Deve ir para o meio

        assertEquals(10, list.first());
        assertEquals(30, list.last());
        assertTrue(list.isOrdered());
    }

    // Testes para remove(T elem)
    @Test
    void testRemoveFromEmptyList() {
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
        assertTrue(list.isOrdered());
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
        assertTrue(list.isOrdered());
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
        assertTrue(list.isOrdered());
    }

    @Test
    void testRemoveDuplicateElement() throws EmptyCollectionException {
        list.add(10);
        list.add(10);
        list.add(20);

        Integer result = list.remove(10);

        assertEquals(10, result);
        assertEquals(2, list.size());
        assertTrue(list.contains(10)); // Ainda deve conter a segunda ocorrência
        assertTrue(list.isOrdered());
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
        list.remove(10);
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

        list.remove(1);
        assertEquals(2, list.size());

        list.remove(2);
        assertEquals(1, list.size());

        list.remove(3);
        assertEquals(0, list.size());
    }

    // Testes para isOrdered
    @Test
    void testIsOrderedOnEmptyList() {
        assertTrue(list.isOrdered());
    }

    @Test
    void testIsOrderedOnSingleElement() {
        list.add(10);
        assertTrue(list.isOrdered());
    }

    @Test
    void testIsOrderedAfterMultipleAdds() {
        list.add(50);
        list.add(10);
        list.add(30);
        list.add(20);
        list.add(40);

        assertTrue(list.isOrdered());
    }

    @Test
    void testIsOrderedAfterRemove() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        list.remove(20);

        assertTrue(list.isOrdered());
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
        assertThrows(EmptyCollectionException.class, iterator::next);
    }

    @Test
    void testIteratorNextBeyondSize() {
        list.add(10);

        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        assertThrows(EmptyCollectionException.class, iterator::next);
    }

    @Test
    void testIteratorRemove() {
        list.add(10);
        list.add(20);
        list.add(30);

        Iterator<Integer> iterator = list.iterator();
        iterator.next(); // Avança para o primeiro elemento
        iterator.remove(); // Remove o primeiro elemento

        assertEquals(2, list.size());
        assertEquals(20, list.first());
        assertTrue(list.isOrdered());
    }

    @Test
    void testIteratorRemoveWithoutNext() {
        list.add(10);

        Iterator<Integer> iterator = list.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    void testIteratorRemoveTwice() {
        list.add(10);
        list.add(20);

        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();

        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    void testIteratorConcurrentModification() {
        list.add(10);
        list.add(20);

        Iterator<Integer> iterator = list.iterator();
        list.add(30); // Modificação externa

        assertThrows(ConcurrentModificationException.class, iterator::hasNext);
    }

    // Testes para reverseToStack
    @Test
    void testReverseToStackEmptyList() {
        LinkedStack<Integer> stack = list.reverseToStack();

        assertTrue(stack.isEmpty());
    }

    @Test
    void testReverseToStackSingleElement() {
        list.add(10);
        LinkedStack<Integer> stack = list.reverseToStack();

        assertEquals(1, stack.size());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testReverseToStackMultipleElements() {
        list.add(10);
        list.add(20);
        list.add(30);

        LinkedStack<Integer> stack = list.reverseToStack();

        assertEquals(3, stack.size());
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }

    // Testes para reverseToArrayStack
    @Test
    void testReverseToArrayStackEmptyList() {
        ArrayStack<Integer> stack = list.reverseToArrayStack();

        assertTrue(stack.isEmpty());
    }

    @Test
    void testReverseToArrayStackMultipleElements() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        ArrayStack<Integer> stack = list.reverseToArrayStack();

        assertEquals(3, stack.size());
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }

    // Testes para reverseToQueue
    @Test
    void testReverseToQueueEmptyList() {
        LinkedQueue<Integer> queue = list.reverseToQueue();

        assertTrue(queue.isEmpty());
    }

    @Test
    void testReverseToQueueMultipleElements() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        LinkedQueue<Integer> queue = list.reverseToQueue();

        assertEquals(3, queue.size());
        assertEquals(30, queue.dequeue());
        assertEquals(20, queue.dequeue());
        assertEquals(10, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    // Testes para reverseInPlace
    @Test
    void testReverseInPlaceEmptyList() {
        list.reverseInPlace();

        assertTrue(list.isEmpty());
        assertTrue(list.isOrdered());
    }

    @Test
    void testReverseInPlaceSingleElement() throws EmptyCollectionException {
        list.add(10);
        list.reverseInPlace();

        assertEquals(1, list.size());
        assertEquals(10, list.first());
        assertEquals(10, list.last());
        assertTrue(list.isOrdered());
    }

    @Test
    void testReverseInPlaceMultipleElements() throws EmptyCollectionException {
        list.add(10);
        list.add(20);
        list.add(30);

        list.reverseInPlace();

        assertEquals(3, list.size());
        assertEquals(30, list.first());
        assertEquals(10, list.last());
        
        // reverseInPlace() inverte a ordem física, mas quebra a ordenação lógica
        assertFalse(list.isOrdered());
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
        assertTrue(stringList.isOrdered());
    }

    @Test
    void testStringContains() {
        stringList.add("Apple");
        stringList.add("Banana");
        stringList.add("Cherry");

        assertTrue(stringList.contains("Banana"));
        assertTrue(stringList.contains("Cherry"));
        assertFalse(stringList.contains("Date"));
    }

    @Test
    void testStringReverseToStack() throws EmptyCollectionException {
        stringList.add("Apple");
        stringList.add("Banana");
        stringList.add("Cherry");

        LinkedStack<String> stack = stringList.reverseToStack();

        assertEquals(3, stack.size());
        assertEquals("Cherry", stack.pop());
        assertEquals("Banana", stack.pop());
        assertEquals("Apple", stack.pop());
    }

    // Testes de integração - operações combinadas
    @Test
    void testMixedOperationsComplex() throws EmptyCollectionException {
        list.add(50);
        list.add(10);
        list.add(30);
        // Lista ordenada: 10, 30, 50

        assertEquals(10, list.remove(10));
        // Lista: 30, 50

        list.add(5);
        list.add(40);
        // Lista ordenada: 5, 30, 40, 50

        assertEquals(5, list.first());
        assertEquals(50, list.last());
        assertEquals(4, list.size());
        assertTrue(list.isOrdered());

        assertEquals(5, list.remove(5));
        // Lista: 30, 40, 50

        assertEquals(30, list.remove(30));
        // Lista: 40, 50

        assertEquals(50, list.remove(50));
        // Lista: 40

        assertEquals(40, list.remove(40));

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
        assertTrue(list.isOrdered());
    }

    @Test
    void testAllElementsSame() throws EmptyCollectionException {
        for (int i = 0; i < 5; i++) {
            list.add(42);
        }

        assertEquals(5, list.size());
        assertEquals(42, list.first());
        assertEquals(42, list.last());
        assertTrue(list.isOrdered());

        list.remove(42); // Remove primeira ocorrência
        assertEquals(4, list.size());
        assertTrue(list.contains(42));
        assertTrue(list.isOrdered());
    }

    // Testes para toString
    @Test
    void testToStringEmptyList() {
        assertEquals("DoublyLinkedOrderedList[]", list.toString());
    }

    @Test
    void testToStringWithElements() {
        list.add(10);
        list.add(20);
        list.add(30);

        String result = list.toString();
        assertTrue(result.contains("DoublyLinkedOrderedList"));
        assertTrue(result.contains("10"));
        assertTrue(result.contains("20"));
        assertTrue(result.contains("30"));
        assertTrue(result.contains("->"));
    }

    // Testes específicos para ordenação com números negativos
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
        assertTrue(list.isOrdered());
    }

    @Test
    void testOrderMaintainedAfterMultipleOperations() throws EmptyCollectionException {
        list.add(30);
        list.add(10);
        list.add(40);
        list.add(20);

        // Lista ordenada: 10, 20, 30, 40
        assertEquals(10, list.remove(10));
        assertEquals(40, list.remove(40));

        list.add(5);
        list.add(35);

        // Nova lista ordenada: 5, 20, 30, 35
        assertEquals(5, list.first());
        assertEquals(35, list.last());
        assertEquals(4, list.size());
        assertTrue(list.isOrdered());
    }
}