package structures.FP04;

public class MessageEncoderLinked {
    private LinkedQueue<Integer> keyQueue;
    private int[] keys; // guardar as chaves originais para reinicializar

    public MessageEncoderLinked(int[] keys) {
        this.keys = keys;
        resetQueue();
    }

    // Reinicializa a fila com as chaves originais
    private void resetQueue() {
        keyQueue = new LinkedQueue<>();
        for (int k : keys)
            keyQueue.enqueue(k);
    }

    public String encode(String message) {
        resetQueue(); // garantir que começa sempre do início
        StringBuilder encoded = new StringBuilder();

        for (char c : message.toCharArray()) {
            int shift = keyQueue.dequeue();
            char newChar = (char) (c + shift);
            encoded.append(newChar);
            keyQueue.enqueue(shift); // volta a colocar a chave no fim
        }

        return encoded.toString();
    }

    public String decode(String encodedMessage) {
        resetQueue(); // garantir que começa sempre do início
        StringBuilder decoded = new StringBuilder();

        for (char c : encodedMessage.toCharArray()) {
            int shift = keyQueue.dequeue();
            char newChar = (char) (c - shift);
            decoded.append(newChar);
            keyQueue.enqueue(shift);
        }

        return decoded.toString();
    }
}
