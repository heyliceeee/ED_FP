package test;

import exceptions.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.FP03.ArrayStack;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayStackTest {
    private ArrayStack<Integer> stack;
    private ArrayStack<String> stringStack;

    @BeforeEach
    void setUp() {
        stack = new ArrayStack<>();
        stringStack = new ArrayStack<>();
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

    // Testes para expandCapacity
    @Test
    void testExpandCapacityAutomatically() {
        // Criar stack com capacidade pequena para testar expansão
        ArrayStack<Integer> smallStack = new ArrayStack<>(2);

        smallStack.push(1);
        smallStack.push(2);
        smallStack.push(3); // Deve expandir aqui

        assertEquals(3, smallStack.size());
        assertEquals(3, smallStack.peek());
    }

    @Test
    void testExpandCapacityMaintainsOrder() throws EmptyCollectionException {
        ArrayStack<Integer> smallStack = new ArrayStack<>(2);

        smallStack.push(1);
        smallStack.push(2);
        smallStack.push(3); // Expande aqui

        // A ordem LIFO deve ser mantida após expansão
        assertEquals(3, smallStack.pop());
        assertEquals(2, smallStack.pop());
        assertEquals(1, smallStack.pop());
    }

    @Test
    void testMultipleExpansions() {
        ArrayStack<Integer> smallStack = new ArrayStack<>(2);

        // Adicionar muitos elementos para forçar múltiplas expansões
        for (int i = 0; i < 100; i++) {
            smallStack.push(i);
        }

        assertEquals(100, smallStack.size());
    }

    // Testes para toString
    @Test
    void testToStringEmptyStack() {
        assertEquals("ArrayStack[]", stack.toString());
    }

    @Test
    void testToStringSingleElement() {
        stack.push(42);
        assertEquals("ArrayStack[42]", stack.toString());
    }

    @Test
    void testToStringMultipleElements() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // Deve mostrar do topo para a base: [3, 2, 1]
        assertEquals("ArrayStack[3, 2, 1]", stack.toString());
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

    // Testes com construtor de capacidade inicial
    @Test
    void testInitialCapacityConstructor() {
        ArrayStack<Integer> customStack = new ArrayStack<>(50);

        assertTrue(customStack.isEmpty());
        assertEquals(0, customStack.size());

        // Testar que funciona normalmente
        customStack.push(100);
        assertEquals(1, customStack.size());
    }

    @Test
    void testInitialCapacityZero() {
        ArrayStack<Integer> zeroStack = new ArrayStack<>(0);

        // Deve conseguir expandir mesmo com capacidade inicial 0
        zeroStack.push(1);
        assertEquals(1, zeroStack.size());
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
}
