package test;

import exceptions.EmptyCollectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import structures.FP02.LinkedListNode;
import structures.FP04.LinkedQueue;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedQueueTest {
    private LinkedQueue<Integer> queue;
    private LinkedQueue<String> stringQueue;

    @BeforeEach
    public void setUp() {
        queue = new LinkedQueue<>();
        stringQueue = new LinkedQueue<>();
    }

    // Testes para enqueue
    @Test
    void testEnqueueToEmptyQueue(){
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

    @Test
    void testDequeueUpdatesFrontCorrectly() throws EmptyCollectionException {
        queue.enqueue(100);
        queue.enqueue(200);

        assertEquals(100, queue.dequeue());
        assertEquals(200, queue.first()); // Front deve agora ser 200

        assertEquals(200, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testDequeueUpdatesRearWhenBecomingEmpty() throws EmptyCollectionException {
        queue.enqueue(50);

        assertEquals(50, queue.dequeue());
        assertTrue(queue.isEmpty());
        // Rear deve ser null quando a queue fica vazia
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

    @Test
    void testSizeAfterMixedOperations() throws EmptyCollectionException {
        assertEquals(0, queue.size());

        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());

        queue.enqueue(3);
        queue.enqueue(4);
        assertEquals(3, queue.size());

        queue.dequeue();
        queue.dequeue();
        assertEquals(1, queue.size());
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

    @Test
    void testStringQueueOrder() throws EmptyCollectionException {
        stringQueue.enqueue("First");
        stringQueue.enqueue("Second");
        stringQueue.enqueue("Third");

        assertEquals("First", stringQueue.dequeue());
        assertEquals("Second", stringQueue.dequeue());
        assertEquals("Third", stringQueue.dequeue());
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

    @Test
    void testAlternatingEnqueueDequeue() throws EmptyCollectionException {
        queue.enqueue(1);
        assertEquals(1, queue.dequeue());

        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(2, queue.dequeue());

        queue.enqueue(4);
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());

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

    @Test
    void testRepeatedEnqueueAndDequeue() throws EmptyCollectionException {
        for (int i = 0; i < 100; i++) {
            queue.enqueue(i);
            assertEquals(i, queue.dequeue());
            assertTrue(queue.isEmpty());
        }
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

    // Testes para o construtor com parâmetros
    @Test
    void testParameterizedConstructor() throws EmptyCollectionException {
        LinkedListNode<Integer> frontNode = new LinkedListNode<>(100);
        LinkedListNode<Integer> rearNode = frontNode;
        LinkedQueue<Integer> customQueue = new LinkedQueue<>(frontNode, rearNode, 1);

        assertFalse(customQueue.isEmpty());
        assertEquals(1, customQueue.size());
        assertEquals(100, customQueue.first());

        customQueue.enqueue(200);
        assertEquals(2, customQueue.size());
        assertEquals(100, customQueue.first()); // Front deve manter-se
    }

    @Test
    void testParameterizedConstructorWithNullNodes() {
        LinkedQueue<Integer> customQueue = new LinkedQueue<>(null, null, 0);

        assertTrue(customQueue.isEmpty());
        assertEquals(0, customQueue.size());

        // Deve funcionar normalmente
        customQueue.enqueue(50);
        assertEquals(1, customQueue.size());
    }

    // Testes para toString
    @Test
    void testToStringEmptyQueue() {
        String result = queue.toString();
        assertTrue(result.contains("LinkedQueue"));
        assertTrue(result.contains("size=0"));
    }

    @Test
    void testToStringWithElements() {
        queue.enqueue(1);
        queue.enqueue(2);

        String result = queue.toString();
        assertTrue(result.contains("LinkedQueue"));
        assertTrue(result.contains("size=2"));
    }

    // Testes de performance/stress
    @Test
    void testQueueHandlesManyElements() throws EmptyCollectionException {
        // Testar que a queue não tem limites de capacidade
        for (int i = 0; i < 10000; i++) {
            queue.enqueue(i);
        }

        assertEquals(10000, queue.size());

        for (int i = 0; i < 10000; i++) {
            assertEquals(i, queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }

    // Testes específicos para LinkedQueue (encadeamento)
    @Test
    void testQueueMaintainsCorrectLinks() throws EmptyCollectionException {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        // Verificar que dequeue atualiza o front corretamente
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.first());

        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.first());

        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testQueueAfterMultipleDequeues() throws EmptyCollectionException {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        queue.dequeue();
        queue.dequeue();

        // Ainda deve funcionar corretamente
        assertEquals(30, queue.first());
        queue.enqueue(40);
        assertEquals(30, queue.dequeue());
        assertEquals(40, queue.dequeue());
    }

    @Test
    void testSingleElementQueueBehavior() throws EmptyCollectionException {
        queue.enqueue(42);

        // Front e rear devem apontar para o mesmo nó
        assertEquals(42, queue.first());
        assertEquals(42, queue.dequeue());
        assertTrue(queue.isEmpty());

        // Deve poder adicionar novamente
        queue.enqueue(43);
        assertEquals(43, queue.first());
        assertEquals(1, queue.size());
    }

    // Testes para verificar rear é atualizado corretamente
    @Test
    void testRearUpdatedOnEnqueue() throws EmptyCollectionException {
        queue.enqueue(1);
        // Front e rear devem ser o mesmo

        queue.enqueue(2);
        // Front deve ser 1, rear deve ser 2

        assertEquals(1, queue.dequeue());
        // Front deve ser 2, rear deve ser 2 (mesmo nó)

        assertEquals(2, queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}
