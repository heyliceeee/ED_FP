package structures.FP04;

public class QueueUtils {
    public static LinkedQueue<String> merge(LinkedQueue<String> q1, LinkedQueue<String> q2) {
        LinkedQueue<String> result = new LinkedQueue<>();

        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.first().compareTo(q2.first()) <= 0) {
                result.enqueue(q1.dequeue());
            } else {
                result.enqueue(q2.dequeue());
            }
        }
        while (!q1.isEmpty()) result.enqueue(q1.dequeue());
        while (!q2.isEmpty()) result.enqueue(q2.dequeue());

        return result;
    }
}