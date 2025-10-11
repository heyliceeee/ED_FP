package structures.FP04;

public class Main {
    public static void main(String[] args) {
        /**
         # Parte I
         1. Implementar a LinkedQueue cm sugerido nos slides da aula teórica (slide 17). Demonstre a utilização da LinkedQueue pra um cenário à sua escolha.
         */
        LinkedQueue<String> lq = new LinkedQueue<>();

        // Pessoas chegam ao balcão
        lq.enqueue("Alice");
        lq.enqueue("Bruno");
        lq.enqueue("Carla");

        System.out.println(lq);

        // Atender a primeira pessoa
        System.out.println("Atendido: " + lq.dequeue());

        // Ver quem é o próximo
        System.out.println("Próximo: " + lq.first());

        System.out.println(lq);


        /**
         2. Implementar a CircularArrayQueue cm é sugerido no slide 29. Demonstre a sua utilização no mm cenário usado no exercício anterior.
         */
        CircularArrayQueue<String> fila = new CircularArrayQueue<>(5);

        // Pessoas chegam ao balcão
        fila.enqueue("Alice");
        fila.enqueue("Bruno");
        fila.enqueue("Carla");

        System.out.println(fila);

        // Atender a primeira pessoa
        System.out.println("Atendido: " + fila.dequeue());

        // Ver quem é o próximo
        System.out.println("Próximo: " + fila.first());

        // Mais pessoas chegam
        fila.enqueue("Daniel");
        fila.enqueue("Eva");

        System.out.println(fila);


        /**
         3. Oq foi necessário fzr pra adaptar o cenário do exercício 1 pra o cenário do exercício 2? Enumere tds as alterações.
         */
        // R:
        // - instanciar a fila c CircularArrayQueue em vez de LinkedQueue
        // - passar a capacidade no construtor
        // - ajustar o import


        /**
         4. Implementar o programa pra a codificação de msgs c recurso a uma CircularArrayQueue cm é sugerido no slide 13.
         */
        int[] keys = {1, 3, 2}; // Chaves de deslocamento (ciclo)
        MessageEncoder encoder = new MessageEncoder(keys);

        String encoded = encoder.encode("HELLO QUEUE");

        // criar NOVO encoder para decodificar
        MessageEncoder decoder = new MessageEncoder(keys);
        String decoded = decoder.decode(encoded);

        System.out.println("Original: HELLO QUEUE");
        System.out.println("Codificada: " + encoded);
        System.out.println("Decodificada: " + decoded);


        /**
         5. Implementar o mm programa pra a codificação de msgs (do exercício anterior) c recurso a uma LinkedQueue.
         */
        MessageEncoderLinked encoder1 = new MessageEncoderLinked(keys);

        String original = "HELLO QUEUE";
        String encoded1 = encoder1.encode(original);
        String decoded1 = encoder1.decode(encoded);

        System.out.println("Original: " + original);
        System.out.println("Codificada: " + encoded1);
        System.out.println("Decodificada: " + decoded1);


        /**
         # Parte II
         1. Pq q usamos uma implementação em array circular? Pq n um array normal cm numa stack?
         */
        // R:
        // Stack → array normal é suficiente, pq só mexes no topo.
        //Queue → array circular é necessário pra evitar deslocamentos caros e aproveitar o espaço de forma contínua.


        /**
         2. Qual o Big O pra a operação dequeue nas diferentes implementações de uma Queue: array, array circular e LL?
         */
        // R:
        // Array normal: ineficiente pra dequeue → O(n).
        //Array circular: eficiente → O(1).
        //LinkedList: tbm eficiente → O(1).


        /**
         3. Implementar um programa q dadas 2 Queues ordenadas as junte numa tbm ordenada.
         */
        LinkedQueue<Integer> fila1 = new LinkedQueue<>();
        LinkedQueue<Integer> fila2 = new LinkedQueue<>();

        // Fila 1 ordenada
        fila1.enqueue(1);
        fila1.enqueue(4);
        fila1.enqueue(7);

        // Fila 2 ordenada
        fila2.enqueue(2);
        fila2.enqueue(3);
        fila2.enqueue(8);

        LinkedQueue<Integer> merged = QueueMerger.merge(fila1, fila2);

        System.out.println("Fila resultante ordenada:");
        while (!merged.isEmpty()) {
            System.out.print(merged.dequeue() + " ");
        }


        /**
         4. Implementar uma nova Queue q obedeça à interface QueueADT através de 2 Stacks.
         */
        QueueWithTwoStacks<String> fila3 = new QueueWithTwoStacks<>();

        fila3.enqueue("Alice");
        fila3.enqueue("Bruno");
        fila3.enqueue("Carla");

        System.out.println("Primeiro da fila: " + fila3.first()); // Alice
        System.out.println("Saiu: " + fila3.dequeue());           // Alice
        System.out.println("Saiu: " + fila3.dequeue());           // Bruno

        fila3.enqueue("Daniel");
        System.out.println("Primeiro da fila: " + fila3.first()); // Carla
        System.out.println("Tamanho da fila: " + fila3.size());   // 2


        /**
         5. Dadas N strings, criar N Queues cd uma contendo uma das strings. De seguida criar uma Queue das N Queues. Repetidamente aplicar uma operação de junção ordenada às primeiras 2 Queues e reinserir a nova Queue no final. Repetir até q a Queue contenha apenas uma Queue.
         */
        // Strings iniciais
        String[] inputs = {"uva", "banana", "laranja", "maçã", "pêssego"};

        // Criar uma Queue de Queues
        LinkedQueue<LinkedQueue<String>> queueOfQueues = new LinkedQueue<>();

        // Cada string numa Queue própria
        for (String s : inputs) {
            LinkedQueue<String> q = new LinkedQueue<>();
            q.enqueue(s);
            queueOfQueues.enqueue(q);
        }

        // Enquanto houver mais do que uma Queue, juntar as duas primeiras
        while (queueOfQueues.size() > 1) {
            LinkedQueue<String> q1 = queueOfQueues.dequeue();
            LinkedQueue<String> q2 = queueOfQueues.dequeue();

            LinkedQueue<String> merged1 = QueueUtils.merge(q1, q2);

            queueOfQueues.enqueue(merged1);
        }

        // Resultado final
        LinkedQueue<String> result = queueOfQueues.dequeue();
        System.out.println("Fila final ordenada: " + result);
    }
}
