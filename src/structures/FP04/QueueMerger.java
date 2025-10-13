package structures.FP04;

public class QueueMerger {

    /**
     * Junta duas filas ordenadas numa só fila também ordenada.
     * @param q1 primeira fila ordenada
     * @param q2 segunda fila ordenada
     * @return nova fila ordenada com todos os elementos
     */
    public static LinkedQueue<Integer> merge(LinkedQueue<Integer> q1, LinkedQueue<Integer> q2) {
        LinkedQueue<Integer> result = new LinkedQueue<>();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.first() <= q2.first())
                result.enqueue(q1.dequeue());
            else
                result.enqueue(q2.dequeue());
        }

        // Se ainda restarem elementos em alguma fila, adiciona todos
        while (!q1.isEmpty())
            result.enqueue(q1.dequeue());

        while (!q2.isEmpty())
            result.enqueue(q2.dequeue());

        return result;
    }
}