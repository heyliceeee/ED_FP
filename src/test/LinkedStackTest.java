package test;

import structures.FP02.LinkedListNode;
import structures.FP03.LinkedStack;
import exceptions.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedStackTest {
    private LinkedStack<Integer> stack;
    private LinkedStack<String> stringStack;

    @BeforeEach
    public void setUp() {
        stack = new LinkedStack<>();
        stringStack = new LinkedStack<>();
    }

    @Test
    void testIteratorConcurrentModification() {
        stack.push(10);
        stack.push(20);

        Iterator<Integer> iterator = stack.iterator();
        stack.push(30); // Modificação externa

        assertThrows(ConcurrentModificationException.class, iterator::hasNext);
    }

    @Test
    void testIteratorConcurrentModificationOnPop() throws EmptyCollectionException {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        Iterator<Integer> iterator = stack.iterator();
        stack.pop(); // Modificação externa

        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    // Testes para push
    @Test
    void testPushToEmptyStack() {
        stack.push(10);

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
    }

    @Test
    void testPushMultipleElements() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size());
    }

    @Test
    void testPushOrder() throws EmptyCollectionException {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Último a entrar deve ser o primeiro a sair (LIFO)
        assertEquals(3, stack.peek());
        assertEquals(3, stack.size());
    }

    // Testes para pop
    @Test
    void testPopFromEmptyStack() {
        assertThrows(EmptyCollectionException.class, () -> {
            stack.pop();
        });
    }

    @Test
    void testPopFromSingleElementStack() throws EmptyCollectionException {
        stack.push(10);
        Integer result = stack.pop();

        assertEquals(10, result);
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void testPopFromMultipleElementsStack() throws EmptyCollectionException {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        Integer result = stack.pop();

        assertEquals(30, result);
        assertEquals(2, stack.size());
        assertEquals(20, stack.peek());
    }

    @Test
    void testPopLIFOOrder() throws EmptyCollectionException {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPopUpdatesTopCorrectly() throws EmptyCollectionException {
        stack.push(100);
        stack.push(200);

        assertEquals(200, stack.pop());
        assertEquals(100, stack.peek()); // Top deve agora ser 100

        assertEquals(100, stack.pop());
        assertTrue(stack.isEmpty());
    }

    // Testes para peek
    @Test
    void testPeekOnEmptyStack() {
        assertThrows(EmptyCollectionException.class, () -> {
            stack.peek();
        });
    }

    @Test
    void testPeekOnSingleElementStack() throws EmptyCollectionException {
        stack.push(42);
        Integer result = stack.peek();

        assertEquals(42, result);
        assertEquals(1, stack.size()); // Size não deve mudar
        assertFalse(stack.isEmpty());
    }

    @Test
    void testPeekOnMultipleElementsStack() throws EmptyCollectionException {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        Integer result = stack.peek();

        assertEquals(30, result);
        assertEquals(3, stack.size()); // Size não deve mudar
    }

    @Test
    void testPeekDoesNotRemoveElement() throws EmptyCollectionException {
        stack.push(100);

        Integer firstPeek = stack.peek();
        Integer secondPeek = stack.peek();

        assertEquals(100, firstPeek);
        assertEquals(100, secondPeek);
        assertEquals(1, stack.size()); // Ainda deve ter 1 elemento
    }

    // Testes para isEmpty
    @Test
    void testIsEmptyOnNewStack() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void testIsEmptyAfterPush() {
        stack.push(10);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testIsEmptyAfterPushAndPop() throws EmptyCollectionException {
        stack.push(10);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void testIsEmptyAfterMultipleOperations() throws EmptyCollectionException {
        assertTrue(stack.isEmpty());

        stack.push(1);
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());

        stack.push(2);
        stack.push(3);
        assertFalse(stack.isEmpty());
    }

    // Testes para size
    @Test
    void testSizeOnNewStack() {
        assertEquals(0, stack.size());
    }

    @Test
    void testSizeAfterPushOperations() {
        stack.push(1);
        assertEquals(1, stack.size());

        stack.push(2);
        assertEquals(2, stack.size());

        stack.push(3);
        assertEquals(3, stack.size());
    }

    @Test
    void testSizeAfterPopOperations() throws EmptyCollectionException {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        stack.pop();
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());

        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    void testSizeAfterMixedOperations() throws EmptyCollectionException {
        assertEquals(0, stack.size());

        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());

        stack.push(3);
        stack.push(4);
        assertEquals(3, stack.size());

        stack.pop();
        stack.pop();
        assertEquals(1, stack.size());
    }

    // Testes com strings
    @Test
    void testWithStringElements() throws EmptyCollectionException {
        stringStack.push("Hello");
        stringStack.push("World");
        stringStack.push("Test");

        assertEquals(3, stringStack.size());
        assertEquals("Test", stringStack.peek());

        String result = stringStack.pop();
        assertEquals("Test", result);
        assertEquals(2, stringStack.size());
    }

    @Test
    void testStringStackOrder() throws EmptyCollectionException {
        stringStack.push("First");
        stringStack.push("Second");
        stringStack.push("Third");

        assertEquals("Third", stringStack.pop());
        assertEquals("Second", stringStack.pop());
        assertEquals("First", stringStack.pop());
    }

    // Testes de integração - operações combinadas
    @Test
    void testMixedOperationsComplex() throws EmptyCollectionException {
        // Sequência complexa de operações
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());

        stack.push(3);
        stack.push(4);

        assertEquals(4, stack.peek());
        assertEquals(3, stack.size());

        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());

        assertTrue(stack.isEmpty());
    }

    @Test
    void testPushPopPushSequence() throws EmptyCollectionException {
        stack.push(10);
        stack.pop();
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testAlternatingPushPop() throws EmptyCollectionException {
        stack.push(1);
        assertEquals(1, stack.pop());

        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());

        stack.push(4);
        assertEquals(4, stack.pop());
        assertEquals(2, stack.pop());

        assertTrue(stack.isEmpty());
    }

    // Testes edge cases
    @Test
    void testLargeNumberOfOperations() throws EmptyCollectionException {
        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }

        assertEquals(1000, stack.size());

        for (int i = 999; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertTrue(stack.isEmpty());
    }

    @Test
    void testRepeatedPushAndPop() throws EmptyCollectionException {
        for (int i = 0; i < 100; i++) {
            stack.push(i);
            assertEquals(i, stack.pop());
            assertTrue(stack.isEmpty());
        }
    }

    // Testes para verificar null handling
    @Test
    void testPushNull() throws EmptyCollectionException {
        stack.push(null);

        assertEquals(1, stack.size());
        assertNull(stack.peek());
        assertNull(stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testMixedWithNulls() throws EmptyCollectionException {
        stack.push(1);
        stack.push(null);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertNull(stack.pop());
        assertEquals(1, stack.pop());
    }

    // Testes para o construtor com parâmetros
    @Test
    void testParameterizedConstructor() throws EmptyCollectionException {
        LinkedListNode<Integer> initialNode = new LinkedListNode<>(100);
        LinkedStack<Integer> customStack = new LinkedStack<>(initialNode, 1);

        assertFalse(customStack.isEmpty());
        assertEquals(1, customStack.size());
        assertEquals(100, customStack.peek());

        customStack.push(200);
        assertEquals(2, customStack.size());
        assertEquals(200, customStack.peek());
    }

    @Test
    void testParameterizedConstructorWithNullTop() {
        LinkedStack<Integer> customStack = new LinkedStack<>(null, 0);

        assertTrue(customStack.isEmpty());
        assertEquals(0, customStack.size());

        // Deve funcionar normalmente
        customStack.push(50);
        assertEquals(1, customStack.size());
    }

    // Testes para toString
    @Test
    void testToStringEmptyStack() {
        String result = stack.toString();
        assertTrue(result.contains("LinkedStack"));
        assertTrue(result.contains("size=0"));
    }

    @Test
    void testToStringWithElements() {
        stack.push(1);
        stack.push(2);

        String result = stack.toString();
        assertTrue(result.contains("LinkedStack"));
        assertTrue(result.contains("size=2"));
    }

    // Testes de performance/stress
    @Test
    void testStackHandlesManyElements() throws EmptyCollectionException {
        // Testar que a stack não tem limites de capacidade (ao contrário do ArrayStack)
        for (int i = 0; i < 10000; i++) {
            stack.push(i);
        }

        assertEquals(10000, stack.size());

        for (int i = 9999; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

        assertTrue(stack.isEmpty());
    }

    // Testes para verificar encadeamento correto
    @Test
    void testStackMaintainsCorrectLinks() throws EmptyCollectionException {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Verificar que pop atualiza o top corretamente
        assertEquals(3, stack.pop());
        assertEquals(2, stack.peek());

        assertEquals(2, stack.pop());
        assertEquals(1, stack.peek());

        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testStackAfterMultiplePops() throws EmptyCollectionException {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        stack.pop();
        stack.pop();

        // Ainda deve funcionar corretamente
        assertEquals(10, stack.peek());
        stack.push(40);
        assertEquals(40, stack.pop());
        assertEquals(10, stack.pop());
    }
}
