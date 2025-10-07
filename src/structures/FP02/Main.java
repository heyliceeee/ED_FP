package structures.FP02;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
    /**
    # Parte I
    1. Cria uma LL. A LL deverá possuir as seguintes operações: Add e Remove.
    Pra q seja possível verificar a integridade da LL crie uma função q imprima tds os elems da list.
    Atenção: N confundir c a classe java.util.LinkedList (disponível na plataforma de coleções do Java)
    */
        LinkedList<String> ll = new LinkedList<>();
        System.out.println("LINKED LIST");

        // add: na head ou no tail
        ll.addFirst("C++");       // head
        ll.addFirst("Java");     // head
        ll.addLast("Python");    // tail

        // imprime tds elems
        System.out.println("Lista inicial:");
        ll.printList();

        // remove: na head ou no tail
        ll.removeFirst();
        System.out.println("Depois de remover o primeiro:");
        ll.printList();

        ll.removeLast();
        System.out.println("Depois de remover o último:");
        ll.printList();


    /**
    2. Criar uma implementação de LL q use nodes sentinela. Esta Implementação deverá tbm possuir as operações Add e Remove.
    Pra q seja possível verificar a integridade da LL crie uma função q imprima tds os elems da list.
    */
        LinkedList<String> llSentinel = new LinkedList<>();
        System.out.println("LINKED LIST com sentinel nodes");

        // add: na head ou no tail sentinela
        llSentinel.addFirstSentinel("C++");       // head
        llSentinel.addFirstSentinel("Java");     // head
        llSentinel.addLastSentinel("Python");    // tail

        // imprime tds elems sentinela
        System.out.println("Lista inicial sentinela:");
        llSentinel.printListSentinel();

        // remove: na head ou no tail sentinela
        ll.removeFirstSentinel();
        System.out.println("Depois de remover o primeiro sentinela:");
        ll.printListSentinel();

        ll.removeLastSentinel();
        System.out.println("Depois de remover o último sentinela:");
        ll.printListSentinel();


    /**
    3. Csg perceber a diferença entre as 2 implementações anteriores? Observe bem as operações implementadas.
    */
    // R:
    //  Sem sentinela → + verboso, exige tratamento especial pra list vazia e pra operações na head.
    //  C sentinela → simplifica a lógica, pq o sentinela funciona cm um "guarda" fixo no início (ou até no fim, se usares 2 sentinelas).


    /**
    4. Criar uma DLL capaz de realizar as seguintes operações:
        ▪ Inserir um node na head.
        ▪ Remover o 1o node de uma list.
        ▪ Remover o último node de uma list.
        ▪ Indicar se a list está vazia ou n.
        ▪ Criar uma função pra percorrer e imprimir tds os elems da list.
    */
        DoublyLinkedList<String> dll = new DoublyLinkedList<>();
        System.out.println("DOUBLY LINKED LIST");

        // add: na head ou no tail
        dll.addFirst("C++");       // head
        dll.addFirst("Python");     // head
        dll.addFirst("Java");    // head

        // imprime tds elems
        System.out.println("Lista inicial:");
        dll.printList();

        // remove: na head ou no tail
        dll.removeFirst();
        System.out.println("Depois de remover o primeiro:");
        dll.printList();

        dll.removeLast();
        System.out.println("Depois de remover o último:");
        dll.printList();

        System.out.println("A lista está vazia? " + dll.isEmpty());


    /**
     # Parte II
     1. Responda às seguintes questões:
         ▪ Quais são os componentes básicos q compõem uma LL?
         ▪ Pra q serve um node numa LL?
         ▪ Qual a diferença entre um array e uma LL?
         ▪ Qual a diferença entre uma LL e uma DLL?
     */
    // R:
    //      ▪ nodes, head e tail
    //      ▪ guardar dados e referências pra ligar a list
    //      ▪ array tem acesso direto e tam fixo; LL é dinâmica, mas acesso é sequencial
    //      ▪ LL liga só pra frente; DLL liga pra frente e pra trás, ocupa + memória mas facilita remoções


    /**
     2. Criar as seguintes operações na DLL:
         ▪ Devolver um array dos elems
         ▪ Devolver um array de tds os elems até uma dada posição.
         ▪ Devolver um array de tds os elems dps de uma dada posição.
         ▪ Devolver um array de tds os elems entre um intervalo de posições.
    */
        DoublyLinkedList<String> dll2 = new DoublyLinkedList<>();

        dll2.addFirst("C++");
        dll2.addFirst("Python");
        dll2.addFirst("Java");
        dll2.addFirst("Rust");

        dll2.printList();

        System.out.println("Array completo: " + Arrays.toString(dll2.toArray())); // Devolver um array dos elems
        System.out.println("Até posição 2: " + Arrays.toString(dll2.toArrayUntil(2))); // Devolver um array de tds os elems até uma dada posição
        System.out.println("Depois da posição 1: " + Arrays.toString(dll2.toArrayAfter(1))); // Devolver um array de tds os elems dps de uma dada posição
        System.out.println("Entre posições 1 e 2: " + Arrays.toString(dll2.toArrayBetween(1, 2))); // Devolver um array de tds os elems entre um intervalo de posições


    /**
    3. Alterar a DLL de forma q esta seja capaz de devolver uma nova DLL c apenas os elems pares (smp q for instanciada c tps inteiros).
    */
        DoublyLinkedList<Integer> dll3 = new DoublyLinkedList<>();

        dll3.addFirst(7);
        dll3.addFirst(14);
        dll3.addFirst(23);
        dll3.addFirst(42);

        System.out.println("Elementos pares da DLL: " + dll3.getEvenElements().toString());


    /**
    4. Alterar a DLL de forma q esta seja capaz de permitir sbr qts elems ='s a um dado elem q é passado por parâmetro estão presentes na DLL. Remover tds esses elems e manter a integridade da DLL
    */
        dll3.addFirst(7);
        dll3.addFirst(14);
        dll3.addFirst(23);
        dll3.addFirst(42);
        dll3.addFirst(7);
        dll3.addFirst(14);
        dll3.addFirst(23);
        dll3.addFirst(42);
        dll3.addFirst(7);
        dll3.addFirst(14);
        dll3.addFirst(23);
        dll3.addFirst(42);
        dll3.addFirst(7);
        dll3.addFirst(14);
        dll3.addFirst(23);
        dll3.addFirst(42);

        System.out.println("ANTES: " + dll3);

        System.out.println("existe " + dll3.getManyElementsEquals(23)+ " elementos repetidos de 23");
        dll3.removeDuplicateElements(23);

        System.out.println("DEPOIS: " + dll3);
    }
}
