package test;

import exceptions.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.FP04.CircularArrayQueue;

import static org.junit.jupiter.api.Assertions.*;

public class CircularArrayQueueTest {
    private CircularArrayQueue<Integer> queue;
    private CircularArrayQueue<String> stringQueue;

    @BeforeEach
    void setUp() {
        queue = new CircularArrayQueue<>();
        stringQueue = new CircularArrayQueue<>();
    }

    // Testes para enqueue
    @Test
    void testEnqueueToEmptyQueue() {
        queue.enqueue(10);

        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
    }

    @Test
    void testEnqueueMultipleElements() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(3, queue.size());
    }

    @Test
    void testEnqueueOrder() throws EmptyCollectionException {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        // Primeiro a entrar deve ser o primeiro a sair (FIFO)
        assertEquals(1, queue.first());
        assertEquals(3, queue.size());
    }

    // Testes para dequeue
    @Test
    void testDequeueFromEmptyQueue() {
        assertThrows(EmptyCollectionException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    void testDequeueFromSingleElementQueue() throws EmptyCollectionException {
        queue.enqueue(10);
        Integer result = queue.dequeue();

        assertEquals(10, result);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void testDequeueFromMultipleElementsQueue() throws EmptyCollectionException {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        Integer result = queue.dequeue();

        assertEquals(10, result);
        assertEquals(2, queue.size());
        assertEquals(20, queue.first());
    }

    @Test
    void testDequeueFIFOOrder() throws EmptyCollectionException {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    // Testes para first
    @Test
    void testFirstOnEmptyQueue() {
        assertThrows(EmptyCollectionException.class, () -> {
            queue.first();
        });
    }

    @Test
    void testFirstOnSingleElementQueue() throws EmptyCollectionException {
        queue.enqueue(42);
        Integer result = queue.first();

        assertEquals(42, result);
        assertEquals(1, queue.size()); // Size não deve mudar
        assertFalse(queue.isEmpty());
    }

    @Test
    void testFirstOnMultipleElementsQueue() throws EmptyCollectionException {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        Integer result = queue.first();

        assertEquals(10, result);
        assertEquals(3, queue.size()); // Size não deve mudar
    }

    @Test
    void testFirstDoesNotRemoveElement() throws EmptyCollectionException {
        queue.enqueue(100);

        Integer firstCall = queue.first();
        Integer secondCall = queue.first();

        assertEquals(100, firstCall);
        assertEquals(100, secondCall);
        assertEquals(1, queue.size()); // Ainda deve ter 1 elemento
    }

    // Testes para isEmpty
    @Test
    void testIsEmptyOnNewQueue() {
        assertTrue(queue.isEmpty());
    }

    @Test
    void testIsEmptyAfterEnqueue() {
        queue.enqueue(10);
        assertFalse(queue.isEmpty());
    }

    @Test
    void testIsEmptyAfterEnqueueAndDequeue() throws EmptyCollectionException {
        queue.enqueue(10);
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void testIsEmptyAfterMultipleOperations() throws EmptyCollectionException {
        assertTrue(queue.isEmpty());

        queue.enqueue(1);
        assertFalse(queue.isEmpty());

        queue.dequeue();
        assertTrue(queue.isEmpty());

        queue.enqueue(2);
        queue.enqueue(3);
        assertFalse(queue.isEmpty());
    }

    // Testes para size
    @Test
    void testSizeOnNewQueue() {
        assertEquals(0, queue.size());
    }

    @Test
    void testSizeAfterEnqueueOperations() {
        queue.enqueue(1);
        assertEquals(1, queue.size());

        queue.enqueue(2);
        assertEquals(2, queue.size());

        queue.enqueue(3);
        assertEquals(3, queue.size());
    }

    @Test
    void testSizeAfterDequeueOperations() throws EmptyCollectionException {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        queue.dequeue();
        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());

        queue.dequeue();
        assertEquals(0, queue.size());
    }

    // Testes para isFull
    @Test
    void testIsFullOnNewQueue() {
        assertFalse(queue.isFull());
    }

    @Test
    void testIsFullAfterEnqueues() {
        // Usar uma queue pequena para testar isFull
        CircularArrayQueue<Integer> smallQueue = new CircularArrayQueue<>(3);

        smallQueue.enqueue(1);
        assertFalse(smallQueue.isFull());

        smallQueue.enqueue(2);
        assertFalse(smallQueue.isFull());

        smallQueue.enqueue(3);
        assertTrue(smallQueue.isFull());
    }

    // Testes para comportamento circular
    @Test
    void testCircularBehavior() throws EmptyCollectionException {
        // Usar uma queue pequena para testar comportamento circular
        CircularArrayQueue<Integer> smallQueue = new CircularArrayQueue<>(3);

        smallQueue.enqueue(1);
        smallQueue.enqueue(2);
        smallQueue.enqueue(3);

        // Queue cheia: [1, 2, 3]
        assertEquals(1, smallQueue.dequeue()); // Remove 1
        // Queue: [null, 2, 3], front=1, rear=0

        smallQueue.enqueue(4); // Deve ir para posição 0 (comportamento circular)
        // Queue: [4, 2, 3], front=1, rear=1

        assertEquals(2, smallQueue.dequeue());
        assertEquals(3, smallQueue.dequeue());
        assertEquals(4, smallQueue.dequeue());
        assertTrue(smallQueue.isEmpty());
    }

    @Test
    void testCircularWrapAround() throws EmptyCollectionException {
        CircularArrayQueue<Integer> smallQueue = new CircularArrayQueue<>(3);

        // Preencher e esvaziar parcialmente para forçar wrap-around
        smallQueue.enqueue(1);
        smallQueue.enqueue(2);
        smallQueue.enqueue(3);

        smallQueue.dequeue(); // Remove 1
        smallQueue.dequeue(); // Remove 2
        // Agora front está em 2, rear em 0

        smallQueue.enqueue(4); // Vai para posição 0
        smallQueue.enqueue(5); // Vai para posição 1 (wrap-around)

        assertEquals(3, smallQueue.dequeue()); // Deve ser 3 (front=2)
        assertEquals(4, smallQueue.dequeue()); // Deve ser 4 (front=0)
        assertEquals(5, smallQueue.dequeue()); // Deve ser 5 (front=1)
        assertTrue(smallQueue.isEmpty());
    }

    // Testes para expandCapacity
    @Test
    void testExpandCapacityAutomatically() throws EmptyCollectionException {
        // Criar queue com capacidade pequena para testar expansão
        CircularArrayQueue<Integer> smallQueue = new CircularArrayQueue<>(2);

        smallQueue.enqueue(1);
        smallQueue.enqueue(2);
        smallQueue.enqueue(3); // Deve expandir aqui

        assertEquals(3, smallQueue.size());
        assertEquals(1, smallQueue.first());

        // Verificar que a ordem FIFO é mantida após expansão
        assertEquals(1, smallQueue.dequeue());
        assertEquals(2, smallQueue.dequeue());
        assertEquals(3, smallQueue.dequeue());
    }

    @Test
    void testExpandCapacityWithCircularContent() throws EmptyCollectionException {
        CircularArrayQueue<Integer> smallQueue = new CircularArrayQueue<>(3);

        // Criar situação circular
        smallQueue.enqueue(1);
        smallQueue.enqueue(2);
        smallQueue.enqueue(3);
        smallQueue.dequeue(); // Remove 1, front=1
        smallQueue.enqueue(4); // rear=0 (circular)

        smallQueue.enqueue(5); // Deve expandir aqui

        // Verificar que todos os elementos estão preservados na ordem correta
        assertEquals(4, smallQueue.size());
        assertEquals(2, smallQueue.first());

        assertEquals(2, smallQueue.dequeue());
        assertEquals(3, smallQueue.dequeue());
        assertEquals(4, smallQueue.dequeue());
        assertEquals(5, smallQueue.dequeue());
    }

    // Testes com strings
    @Test
    void testWithStringElements() throws EmptyCollectionException {
        stringQueue.enqueue("Hello");
        stringQueue.enqueue("World");
        stringQueue.enqueue("Test");

        assertEquals(3, stringQueue.size());
        assertEquals("Hello", stringQueue.first());

        String result = stringQueue.dequeue();
        assertEquals("Hello", result);
        assertEquals(2, stringQueue.size());
    }

    // Testes de integração - operações combinadas
    @Test
    void testMixedOperationsComplex() throws EmptyCollectionException {
        // Sequência complexa de operações
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.dequeue());

        queue.enqueue(3);
        queue.enqueue(4);

        assertEquals(2, queue.first());
        assertEquals(3, queue.size());

        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());

        assertTrue(queue.isEmpty());
    }

    @Test
    void testEnqueueDequeueEnqueueSequence() throws EmptyCollectionException {
        queue.enqueue(10);
        queue.dequeue();
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    // Testes edge cases
    @Test
    void testLargeNumberOfOperations() throws EmptyCollectionException {
        for (int i = 0; i < 1000; i++) {
            queue.enqueue(i);
        }

        assertEquals(1000, queue.size());

        for (int i = 0; i < 1000; i++) {
            assertEquals(i, queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }

    // Testes para verificar null handling
    @Test
    void testEnqueueNull() throws EmptyCollectionException {
        queue.enqueue(null);

        assertEquals(1, queue.size());
        assertNull(queue.first());
        assertNull(queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testMixedWithNulls() throws EmptyCollectionException {
        queue.enqueue(1);
        queue.enqueue(null);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertNull(queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    // Testes com construtor de capacidade inicial
    @Test
    void testInitialCapacityConstructor() {
        CircularArrayQueue<Integer> customQueue = new CircularArrayQueue<>(50);

        assertTrue(customQueue.isEmpty());
        assertEquals(0, customQueue.size());

        // Testar que funciona normalmente
        customQueue.enqueue(100);
        assertEquals(1, customQueue.size());
    }

    @Test
    void testInitialCapacityZero() {
        // Testar comportamento com capacidade inicial 0
        CircularArrayQueue<Integer> zeroQueue = new CircularArrayQueue<>(0);

        // Deve conseguir expandir quando necessário
        zeroQueue.enqueue(1);
        assertEquals(1, zeroQueue.size());
    }

    // Testes para toString
    @Test
    void testToStringEmptyQueue() {
        String result = queue.toString();
        assertTrue(result.contains("CircularArrayQueue"));
        assertTrue(result.contains("size=0"));
    }

    @Test
    void testToStringWithElements() {
        queue.enqueue(1);
        queue.enqueue(2);

        String result = queue.toString();
        assertTrue(result.contains("CircularArrayQueue"));
        assertTrue(result.contains("size=2"));
    }

    // Testes específicos para verificar índices front e rear
    @Test
    void testFrontAndRearIndices() throws EmptyCollectionException {
        CircularArrayQueue<Integer> smallQueue = new CircularArrayQueue<>(3);

        smallQueue.enqueue(1);
        // front=0, rear=1, size=1

        smallQueue.enqueue(2);
        // front=0, rear=2, size=2

        smallQueue.enqueue(3);
        // front=0, rear=0 (circular), size=3

        smallQueue.dequeue();
        // front=1, rear=0, size=2

        smallQueue.enqueue(4);
        // front=1, rear=1, size=3

        // Verificar ordem FIFO
        assertEquals(2, smallQueue.dequeue());
        assertEquals(3, smallQueue.dequeue());
        assertEquals(4, smallQueue.dequeue());
    }

    @Test
    void testQueueBecomesEmptyAfterFull() throws EmptyCollectionException {
        CircularArrayQueue<Integer> smallQueue = new CircularArrayQueue<>(2);

        smallQueue.enqueue(1);
        smallQueue.enqueue(2);
        assertTrue(smallQueue.isFull());

        smallQueue.dequeue();
        smallQueue.dequeue();
        assertTrue(smallQueue.isEmpty());

        // Deve poder reutilizar
        smallQueue.enqueue(3);
        smallQueue.enqueue(4);
        assertEquals(3, smallQueue.dequeue());
        assertEquals(4, smallQueue.dequeue());
    }
}
