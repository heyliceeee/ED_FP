package FP02;


public class Main {
    public static void main(String[] args) {
    /**
    # Parte I
    1. Cria uma LL. A LL deverá possuir as seguintes operações: Add e Remove.
    Pra q seja possível verificar a integridade da LL crie uma função q imprima tds os elems da list.
    Atenção: N confundir c a classe java.util.LinkedList (disponível na plataforma de coleções do Java)
    */
        LinkedList<String> ll = new LinkedList<>();

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



    /**
    4. Criar uma DLL capaz de realizar as seguintes operações:
        ▪ Inserir um node na head.
        ▪ Remover o 1o node de uma list.
        ▪ Remover o último node de uma list.
        ▪ Indicar se a list está vazia ou n.
        ▪ Criar uma função pra percorrer e imprimir tds os elems da list.
    */



    /**
     # Parte II
     1. Responda às seguintes questões:
         ▪ Quais são os componentes básicos q compõem uma LL?
         ▪ Pra q serve um node numa LL?
         ▪ Qual a diferença entre um array e uma LL?
         ▪ Qual a diferença entre uma LL e uma DLL?
     */



    /**
     2. Criar as seguintes operações na DLL:
         ▪ Devolver um array dos elems
         ▪ Devolver um array de tds os elems até uma dada posição.
         ▪ Devolver um array de tds os elems dps de uma dada posição.
         ▪ Devolver um array de tds os elems entre um intervalo de posições.
    */



    /**
    3. Alterar a DLL de forma q esta seja capaz de devolver uma nova DLL c apenas os elems pares (smp q for instanciada c tps inteiros).
    */



    /**
    4. Alterar a DLL de forma q esta seja capaz de permitir sbr qts elems ='s a um dado elem q é passado por parâmetro estão presentes na DLL. Remover tds esses elems e manter a integridade da DLL
    */
    }
}
