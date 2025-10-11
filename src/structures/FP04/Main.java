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



        /**
         5. Implementar o mm programa pra a codificação de msgs (do exercício anterior) c recurso a uma LinkedQueue.
         */



        /**
         # Parte II
         1. Pq q usamos uma implementação em array circular? Pq n um array normal cm numa stack?
         */



        /**
         2. Qual o Big O pra a operação dequeue nas diferentes implementações de uma Queue: array, array circular e LL?
         */



        /**
         3. Implementar um programa q dadas 2 Queues ordenadas as junte numa tbm ordenada.
         */



        /**
         4. Implementar uma nova Queue q obedeça à interface QueueADT através de 2 Stacks.
         */



        /**
         5. Dadas N strings, criar N Queues cd uma contendo uma das strings. De seguida criar uma Queue das N Queues. Repetidamente aplicar uma operação de junção ordenada às primeiras 2 Queues e reinserir a nova Queue no final. Repetir até q a Queue contenha apenas uma Queue.
         */
    }
}
