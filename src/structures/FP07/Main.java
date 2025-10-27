package structures.FP07;

import structures.FP02.DoublyLinkedList;
import structures.FP02.LinkedList;
import structures.FP03.LinkedStack;

public class Main {
    public static void main(String[] args) {
        /**
         # Parte I
         1. Escreve um método recursivo q imprima tds os elems de uma LL, do 1o ao último elem. Deverá ser possível usar o método pra qlqr LL à sua escolha. Testa a sol desenvolvida.
         */
        LinkedList<String> nomes = new LinkedList<>();
        nomes.addLast("Alice");
        nomes.addLast("Bruno");
        nomes.addFirst("Inês"); // entra no início
        nomes.addLast("Carla");

        System.out.println("Impressão recursiva da LinkedList de nomes:");
        nomes.printRecursive();

        LinkedList<Integer> numeros = new LinkedList<>();
        numeros.addLast(10);
        numeros.addFirst(20); // entra no inicio
        numeros.addLast(30);

        System.out.println("\nImpressão recursiva da LinkedList de inteiros:");
        numeros.printRecursive();


        /**
         * 2. Escreve 2 métodos recursivos q imprima tds os elems de uma DLL, do 1o ao último elem. Um dos métodos deverá imprimir a partir de uma ponta e o outro de outra. Deverá ser possível usar os métodos pra quaisquer DLL à sua escolha. Testa a sol desenvolvida.
         */
        DoublyLinkedList<String> lista = new DoublyLinkedList<>();

        lista.addFirst("Alice");
        lista.addFirst("Bruno");
        lista.addFirst("Carla");
        lista.addFirst("Duarte");

        System.out.println("\nImpressão do início para o fim:");
        lista.printForward();

        System.out.println("\nImpressão do fim para o início:");
        lista.printBackward();


        /**
         * 3. Desenvolve um programa pra a resolução dos problemas das Torres de Hanoi. Demostra a sua utilização.
         */
        Hanoi<Integer> hanoi = new Hanoi<>();

        int n = 3; // número de discos
        LinkedStack<Integer> origem = new LinkedStack<>();
        LinkedStack<Integer> destino = new LinkedStack<>();
        LinkedStack<Integer> auxiliar = new LinkedStack<>();

        // Inicializar a stack origem (discos maiores em baixo)
        for (int i = n; i >= 1; i--)
            origem.push(i);

        System.out.println("Resolução das Torres de Hanoi com " + n + " discos:\n");
        hanoi.solveHanoi(n, origem, destino, auxiliar, 'A', 'C', 'B');

        System.out.println("\nEstado final da pilha destino: " + destino);


        /**
         * Parte II
         * 1. Quais as componentes q compõem uma definição recursiva?
         */
        // R: Uma definição recursiva é composta por caso base, passo recursivo e garantia de progresso.
        // Sem caso base → recursão infinita.
        // Sem passo recursivo → n há recursão.
        // Sem progresso → nnc se atinge o caso base.


        /**
         * 2. Devo usar smp a recursividade pra resolver os + diversos problemas?
         */
        // R: A recursividade é uma ferramenta conceptual poderosa, mas n é a sol universal. O truque é sbr qd a clareza compensa e qd a eficiência deve prevalecer.


        /**
         * 3. Acrescenta um método recursivo à implementação de DLL q devolva o conteúdo da list c a ordem dos elems invertida.
         */
        DoublyLinkedList<String> lista2 = new DoublyLinkedList<>();

        lista2.addFirst("Duarte");
        lista2.addFirst("Carla");
        lista2.addFirst("Bruno");
        lista2.addFirst("Alice");

        System.out.println("Lista normal (head → tail):");
        lista2.printForward();

        System.out.println("\nLista invertida (tail → head):");
        System.out.println("[" + lista2.toReversedString() + "]");


        /**
         * 4. Acrescenta o método void replace(T existingElem, T newElem), q funcione de forma recursiva, à sua implementação de LL q seja capaz de substituir tds as ocorrências do argumento existingElem pelo argumento newElem.
         */
        LinkedList<String> lista3 = new LinkedList<>();
        lista3.addFirst("Alice");
        lista3.addFirst("Bruno");
        lista3.addFirst("Alice");
        lista3.addFirst("Carla");

        System.out.println("Lista original: " + lista3);

        lista3.replace("Alice", "Inês");

        System.out.println("Lista após replace: " + lista3);


        /**
         * 5. Acrescenta um método recursivo à sua implementação de LL q seja capaz de inverter a ordem dos elems.
         */
        LinkedList<String> lista4 = new LinkedList<>();
        lista4.addFirst("Duarte");
        lista4.addFirst("Carla");
        lista4.addFirst("Bruno");
        lista4.addFirst("Alice");

        System.out.println("Lista original: " + lista4);

        lista4.reverse();

        System.out.println("Lista invertida: " + lista4);
    }
}
