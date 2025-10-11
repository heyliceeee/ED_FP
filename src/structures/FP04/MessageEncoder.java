package structures.FP04;

public class MessageEncoder {
    private CircularArrayQueue<Integer> keyQueue;

    public MessageEncoder(int[] keys) {
        keyQueue = new CircularArrayQueue<>(keys.length);
        for (int k : keys) {
            keyQueue.enqueue(k);
        }
    }

    // Codifica a mensagem deslocando cada char pelo valor da chave
    public String encode(String message) {
        StringBuilder encoded = new StringBuilder();

        for (char c : message.toCharArray()) {
            int shift = keyQueue.dequeue();   // retira chave
            char newChar = (char) (c + shift); // desloca o char
            encoded.append(newChar);
            keyQueue.enqueue(shift);          // volta a colocar a chave no fim
        }

        return encoded.toString();
    }

    // Decodifica aplicando o deslocamento inverso
    public String decode(String encodedMessage) {
        StringBuilder decoded = new StringBuilder();

        for (char c : encodedMessage.toCharArray()) {
            int shift = keyQueue.dequeue();
            char newChar = (char) (c - shift); // desloca inverso
            decoded.append(newChar);
            keyQueue.enqueue(shift);
        }

        return decoded.toString();
    }
}