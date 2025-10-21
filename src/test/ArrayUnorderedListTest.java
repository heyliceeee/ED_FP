package test;

import exceptions.ElementNotFoundException;
import exceptions.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.FP05.ArrayUnorderedList;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class ArrayUnorderedListTest {

    private ArrayUnorderedList<Integer> list;
    private ArrayUnorderedList<String> stringList;

    @BeforeEach
    void setUp() {
        list = new ArrayUnorderedList<>();
        stringList = new ArrayUnorderedList<>();
    }

    // Testes para addToFront
    @Test
    void testAddToFrontToEmptyList() {
        list.addToFront(10);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    void testAddToFrontMultipleElements() {
        list.addToFront(10);
        list.addToFront(20);
        list.addToFront(30);

        assertEquals(3, list.size());
    }

    @Test
    void testAddToFrontOrder() throws EmptyCollectionException {
        list.addToFront(10);
        list.addToFront(20);
        list.addToFront(30);

        // Deve manter ordem de inserção: 30, 20, 10
        assertEquals(30, list.first());
        assertEquals(10, list.last());
    }

    @Test
    void testAddToFrontWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.addToFront(null);
        });
    }

    // Testes para addToRear
    @Test
    void testAddToRearToEmptyList() {
        list.addToRear(10);

        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }

    @Test
    void testAddToRearMultipleElements() {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);

        assertEquals(3, list.size());
    }

    @Test
    void testAddToRearOrder() throws EmptyCollectionException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);

        // Deve manter ordem de inserção: 10, 20, 30
        assertEquals(10, list.first());
        assertEquals(30, list.last());
    }

    @Test
    void testAddToRearWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            list.addToRear(null);
        });
    }

    // Testes para addAfter
    @Test
    void testAddAfterToEmptyList() {
        assertThrows(ElementNotFoundException.class, () -> {
            list.addAfter(20, 10);
        });
    }

    @Test
    void testAddAfterWithNullElement() {
        list.addToFront(10);

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAfter(null, 10);
        });
    }

    @Test
    void testAddAfterWithNullTarget() {
        list.addToFront(10);

        assertThrows(IllegalArgumentException.class, () -> {
            list.addAfter(20, null);
        });
    }

    @Test
    void testAddAfterNonExistentTarget() {
        list.addToFront(10);

        assertThrows(ElementNotFoundException.class, () -> {
            list.addAfter(30, 20);
        });
    }

    @Test
    void testAddAfterSingleElement() throws ElementNotFoundException {
        list.addToFront(10);
        list.addAfter(20, 10);

        assertEquals(2, list.size());
        assertEquals(10, list.first());
        assertEquals(20, list.last());
    }

    @Test
    void testAddAfterMiddleElement() throws ElementNotFoundException, EmptyCollectionException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);
        // Lista: 10, 20, 30

        list.addAfter(25, 20);
        // Lista: 10, 20, 25, 30

        assertEquals(4, list.size());
        assertEquals(10, list.first());
        assertEquals(30, list.last());

        // Verificar ordem
        assertEquals(10, list.removeFirst());
        assertEquals(20, list.removeFirst());
        assertEquals(25, list.removeFirst());
        assertEquals(30, list.removeFirst());
    }

    @Test
    void testAddAfterLastElement() throws ElementNotFoundException, EmptyCollectionException {
        list.addToRear(10);
        list.addToRear(20);
        // Lista: 10, 20

        list.addAfter(30, 20);
        // Lista: 10, 20, 30

        assertEquals(3, list.size());
        assertEquals(10, list.first());
        assertEquals(30, list.last());
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
        list.addToFront(42);
        Integer result = list.removeFirst();

        assertEquals(42, result);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveFirstFromMultipleElementsList() throws EmptyCollectionException {
        list.addToFront(10);
        list.addToFront(20);
        list.addToFront(30);
        // Lista: 30, 20, 10

        Integer result = list.removeFirst();

        assertEquals(30, result);
        assertEquals(2, list.size());
        assertEquals(20, list.first());
    }

    @Test
    void testRemoveFirstOrder() throws EmptyCollectionException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);
        // Lista: 10, 20, 30

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
        list.addToFront(42);
        Integer result = list.removeLast();

        assertEquals(42, result);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testRemoveLastFromMultipleElementsList() throws EmptyCollectionException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);
        // Lista: 10, 20, 30

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
        list.addToFront(10);
        list.addToFront(20);

        assertThrows(ElementNotFoundException.class, () -> {
            list.remove(30);
        });
    }

    @Test
    void testRemoveFirstElement() throws EmptyCollectionException, ElementNotFoundException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);
        // Lista: 10, 20, 30

        Integer result = list.remove(10);

        assertEquals(10, result);
        assertEquals(2, list.size());
        assertEquals(20, list.first());
    }

    @Test
    void testRemoveMiddleElement() throws EmptyCollectionException, ElementNotFoundException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);
        // Lista: 10, 20, 30

        Integer result = list.remove(20);

        assertEquals(20, result);
        assertEquals(2, list.size());
        assertEquals(10, list.first());
        assertEquals(30, list.last());
    }

    @Test
    void testRemoveLastElement() throws EmptyCollectionException, ElementNotFoundException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);
        // Lista: 10, 20, 30

        Integer result = list.remove(30);

        assertEquals(30, result);
        assertEquals(2, list.size());
        assertEquals(20, list.last());
    }

    @Test
    void testRemoveDuplicateElement() throws EmptyCollectionException, ElementNotFoundException {
        list.addToRear(10);
        list.addToRear(10);
        list.addToRear(20);
        // Lista: 10, 10, 20

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
        list.addToFront(42);
        Integer result = list.first();

        assertEquals(42, result);
        assertEquals(1, list.size());
    }

    @Test
    void testFirstOnMultipleElementsList() throws EmptyCollectionException {
        list.addToFront(10);
        list.addToFront(20);
        list.addToFront(30);
        // Lista: 30, 20, 10

        Integer result = list.first();

        assertEquals(30, result);
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
        list.addToFront(42);
        Integer result = list.last();

        assertEquals(42, result);
        assertEquals(1, list.size());
    }

    @Test
    void testLastOnMultipleElementsList() throws EmptyCollectionException {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);
        // Lista: 10, 20, 30

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
        list.addToFront(10);
        list.addToFront(20);
        list.addToFront(30);

        assertTrue(list.contains(20));
    }

    @Test
    void testContainsNonExistingElement() {
        list.addToFront(10);
        list.addToFront(20);

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
        list.addToFront(10);
        assertFalse(list.isEmpty());
    }

    @Test
    void testIsEmptyAfterRemove() throws EmptyCollectionException {
        list.addToFront(10);
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
        list.addToFront(1);
        assertEquals(1, list.size());

        list.addToRear(2);
        assertEquals(2, list.size());

        list.addToFront(3);
        assertEquals(3, list.size());
    }

    @Test
    void testSizeAfterRemoveOperations() throws EmptyCollectionException, ElementNotFoundException {
        list.addToFront(1);
        list.addToRear(2);
        list.addToFront(3);

        list.removeFirst();
        assertEquals(2, list.size());

        list.removeLast();
        assertEquals(1, list.size());

        list.remove(1);
        assertEquals(0, list.size());
    }

    // Testes para expandCapacity
    @Test
    void testExpandCapacityAutomatically() {
        ArrayUnorderedList<Integer> smallList = new ArrayUnorderedList<>(2);

        smallList.addToFront(1);
        smallList.addToFront(2);
        smallList.addToFront(3); // Deve expandir aqui

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
        list.addToFront(10);
        list.addToRear(20);

        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorNext() {
        list.addToRear(10);
        list.addToRear(20);
        list.addToRear(30);

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
        list.addToFront(10);

        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testIteratorConcurrentModification() {
        list.addToFront(10);
        list.addToRear(20);

        Iterator<Integer> iterator = list.iterator();
        list.addToFront(30); // Modificação externa

        assertThrows(ConcurrentModificationException.class, iterator::hasNext);
    }

    // Testes com strings
    @Test
    void testWithStringElements() throws EmptyCollectionException, ElementNotFoundException {
        stringList.addToFront("Hello");
        stringList.addToRear("World");
        stringList.addAfter("Test", "Hello");

        assertEquals(3, stringList.size());
        assertEquals("Hello", stringList.first());
        assertEquals("World", stringList.last());

        assertTrue(stringList.contains("Test"));
    }

    @Test
    void testStringRemove() throws EmptyCollectionException, ElementNotFoundException {
        stringList.addToRear("Apple");
        stringList.addToRear("Banana");
        stringList.addToRear("Cherry");

        String result = stringList.remove("Banana");
        assertEquals("Banana", result);
        assertEquals(2, stringList.size());
        assertFalse(stringList.contains("Banana"));
    }

    // Testes de integração - operações combinadas
    @Test
    void testMixedOperationsComplex() throws EmptyCollectionException, ElementNotFoundException {
        list.addToFront(50);
        list.addToRear(10);
        list.addAfter(30, 50);
        // Lista: 50, 30, 10

        assertEquals(50, list.removeFirst());
        // Lista: 30, 10

        list.addToFront(5);
        list.addToRear(40);
        // Lista: 5, 30, 10, 40

        assertEquals(5, list.first());
        assertEquals(40, list.last());
        assertEquals(4, list.size());

        assertEquals(5, list.removeFirst());
        // Lista: 30, 10, 40

        assertEquals(30, list.remove(30));
        // Lista: 10, 40

        assertEquals(40, list.removeLast());
        // Lista: 10

        assertEquals(10, list.removeLast());

        assertTrue(list.isEmpty());
    }

    // Testes edge cases
    @Test
    void testLargeNumberOfElements() {
        for (int i = 0; i < 1000; i++) {
            list.addToRear(i);
        }

        assertEquals(1000, list.size());
        assertEquals(0, list.first());
        assertEquals(999, list.last());
    }

    // Testes para construtor com capacidade inicial
    @Test
    void testInitialCapacityConstructor() {
        ArrayUnorderedList<Integer> customList = new ArrayUnorderedList<>(50);

        assertTrue(customList.isEmpty());
        assertEquals(0, customList.size());

        customList.addToFront(100);
        assertEquals(1, customList.size());
    }

    // Testes para toString
    @Test
    void testToStringEmptyList() {
        assertEquals("ArrayUnorderedList[]", list.toString());
    }

    @Test
    void testToStringWithElements() {
        list.addToFront(10);
        list.addToRear(20);
        list.addToRear(30);

        String result = list.toString();
        assertTrue(result.contains("ArrayUnorderedList"));
        assertTrue(result.contains("10"));
        assertTrue(result.contains("20"));
        assertTrue(result.contains("30"));
    }

    // Testes específicos para comportamento não ordenado
    @Test
    void testUnorderedBehavior() throws EmptyCollectionException {
        list.addToFront(30);
        list.addToRear(10);
        list.addToFront(20);
        // Lista: 20, 30, 10 (mantém ordem de inserção, não ordena)

        assertEquals(20, list.first());
        assertEquals(10, list.last());
        assertEquals(3, list.size());
    }

    @Test
    void testAddAfterAtBeginning() throws ElementNotFoundException, EmptyCollectionException {
        list.addToRear(10);
        list.addAfter(20, 10);
        list.addAfter(15, 10);
        // Lista: 10, 15, 20

        assertEquals(10, list.removeFirst());
        assertEquals(15, list.removeFirst());
        assertEquals(20, list.removeFirst());
    }
}
