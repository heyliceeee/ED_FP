package structures.FP06;

import exceptions.EmptyCollectionException;
import interfaces.SmackStackADT;

public class Main {
    public static void main(String[] args) {
        /**
         # Parte I
         1. Defina e implemente a ED SmackStackADT recorrendo a uma das suas implementações de ArrayStack. A ED SmackStackADT tem o mm funcionamento de uma stack normal, no entanto possui + um comportamento Smack q elimina e devolve o último elem da stack. Atenção: Deverá recorrer a herança pra definir e implementar esta nova estrutura.
         */
        System.out.println("=== 🎯 DEMONSTRAÇÃO SMACKSTACK ===\n");

        // 1. Teste com ArraySmackStack
        System.out.println("1. ARRAY SMACK STACK:");
        SmackStackADT<String> arraySmack = new ArraySmackStack<>();

        // Push elementos
        arraySmack.push("Primeiro");
        arraySmack.push("Segundo");
        arraySmack.push("Terceiro");
        arraySmack.push("Quarto");

        System.out.println("Stack inicial: " + arraySmack);
        System.out.println("Topo: " + arraySmack.peek());

        // Operação Smack - remove do fundo
        System.out.println("\nOperação SMACK:");
        String smacked = arraySmack.smack();
        System.out.println("Elemento smacked: " + smacked);
        System.out.println("Stack após smack: " + arraySmack);

        // Mais operações
        System.out.println("\nMais operações:");
        arraySmack.pop();
        System.out.println("Após pop: " + arraySmack);

        arraySmack.smack();
        System.out.println("Após outro smack: " + arraySmack);

        // 3. Casos especiais
        System.out.println("\n3. CASOS ESPECIAIS:");

        // Stack vazia
        SmackStackADT<String> vazia = new ArraySmackStack<>();
        try {
            vazia.smack();
        } catch (EmptyCollectionException e) {
            System.out.println("✅ Erro esperado: " + e.getMessage());
        }

        // Um elemento
        SmackStackADT<String> umElemento = new ArraySmackStack<>();
        umElemento.push("Único");
        System.out.println("Antes do smack: " + umElemento);
        System.out.println("Smack: " + umElemento.smack());
        System.out.println("Após smack: " + umElemento);
        System.out.println("Está vazia? " + umElemento.isEmpty());

        // 4. Cenário prático - Sistema de Processamento com Prioridade
        System.out.println("\n4. CENÁRIO PRÁTICO - PROCESSAMENTO COM PRIORIDADE:");
        SmackStackADT<String> tarefas = new ArraySmackStack<>();

        tarefas.push("Tarefa Baixa Prioridade");
        tarefas.push("Tarefa Média Prioridade");
        tarefas.push("Tarefa Alta Prioridade");
        tarefas.push("Tarefa Urgente");

        System.out.println("Fila de tarefas: " + tarefas);

        // Processamento normal (LIFO) - tarefas urgentes primeiro
        System.out.println("Processando do topo (urgentes): " + tarefas.pop());

        // Smack - remover tarefa mais antiga (baixa prioridade)
        System.out.println("Removendo tarefa mais antiga: " + tarefas.smack());
        System.out.println("Fila restante: " + tarefas);


        /**
         2. Uma Queue pode ser armazenada em array usando 2 índices inteiros pra guardar a próxima posição livre na cauda e a posição do próximo elem a ser removido da cabeça. Explique o funcionamento das operações de {enqueue, dequeue, empty, first} de modo q n exista nenhum processo de shift dos elems.
         */
        // R:
        // Fila Circular - Sem Shifts:
        //  - 2 índices: front (cabeça) e rear (cauda)
        //  - Comportamento circular: qd chegam ao fim, voltam ao início
        //  - enqueue: coloca em rear e avança circularmente
        //  - dequeue: remove de front e avança circularmente
        //  - first: olha pra front sem remover
        //  - empty: verifica se front == rear (c controle de size)
        // Resultado: Tds operações O(1) - sem mover elems, só mexemos nos índices! 🎯


        /**
         3. Descreva cm é q a {stack, queue} podem ser representadas c recurso a uma LL. Qual é a principal vantagem em relação à implementação em array?
         */
        // R:
        // Stack c LL:
        //  - Topo = head da list
        //  - push = addToFront (O(1))
        //  - pop = removeFirst (O(1))
        //
        // Queue c LinkedList:
        //  - Frente = head, Cauda = tail
        //  - enqueue = addToRear (O(1))
        //  - dequeue = removeFirst (O(1))
        //
        // Principal vantagem vs array:
        //  - CRESCIMENTO DINÂMICO - sem tam fixo, sem redimensionamentos custosos 🚀


        /**
         4. Crie uma implementação de List através de uma CircularLL, podendo recorrer à herança caso seja aplicável, q funcione de forma circular, ou seja, o último elem da list deverá apontar pra a cabeça e n pra null.
         */
        System.out.println("=== 🔄 DEMONSTRAÇÃO CIRCULAR LINKED LIST ===\n");

        CircularLinkedList<String> circularList = new CircularLinkedList<>();

        // 1. Adicionar elementos
        System.out.println("1. ADIÇÃO DE ELEMENTOS:");
        circularList.addFirst("C");
        circularList.addFirst("B");
        circularList.addFirst("A");
        circularList.addToRear("D");
        circularList.addToRear("E");

        System.out.println("Lista: " + circularList);
        System.out.println("É circular? " + circularList.isCircular());
        System.out.println("Tamanho: " + circularList.getSize());

        // 2. Percursos circulares
        System.out.println("\n2. PERCURSOS CIRCULARES:");
        circularList.traverseFrom("A");
        circularList.traverseFrom("C");
        circularList.traverseFrom("E");

        // 3. Remoções
        System.out.println("\n3. REMOÇÕES:");
        System.out.println("Remove primeiro: " + circularList.removeFirst());
        System.out.println("Lista: " + circularList);

        System.out.println("Remove último: " + circularList.removeLast());
        System.out.println("Lista: " + circularList);
        System.out.println("É circular? " + circularList.isCircular());

        // 4. Casos especiais
        System.out.println("\n4. CASOS ESPECIAIS:");

        // Lista com um elemento
        CircularLinkedList<String> umElemento1 = new CircularLinkedList<>();
        umElemento1.addFirst("Único");
        System.out.println("Um elemento: " + umElemento1);
        System.out.println("É circular? " + umElemento1.isCircular());

        // Lista vazia
        CircularLinkedList<String> vazia1 = new CircularLinkedList<>();
        System.out.println("Lista vazia: " + vazia1);
        System.out.println("É circular? " + vazia1.isCircular());

        // 5. Cenário prático - Jogo de Roda
        System.out.println("\n5. CENÁRIO PRÁTICO - JOGO DA RODA:");
        CircularLinkedList<String> roda = new CircularLinkedList<>();
        roda.addToRear("João");
        roda.addToRear("Maria");
        roda.addToRear("Pedro");
        roda.addToRear("Ana");

        System.out.println("Jogadores na roda: " + roda);
        roda.traverseFrom("Maria");

        // Simular rodada
        System.out.println("Maria é eliminada!");
        // (Implementar lógica de remoção específica)
        System.out.println("Roda atualizada: " + roda);


        /**
         5. Crie uma implementação de List através de uma CircularDLL, podendo recorrer a herança caso seja aplicável, q funcione de forma circular, ou seja, a referência seguinte da cauda da list deverá referenciar a cabeça e n null, assim cm o elem anterior à cabeça deverá referenciar a cauda da list em vez de null.
         */
        System.out.println("=== 🔄 DEMONSTRAÇÃO CIRCULAR DOUBLY LINKED LIST ===\n");

        CircularDoublyLinkedList<String> circularList1 = new CircularDoublyLinkedList<>();

        // 1. Construção da lista circular
        System.out.println("1. CONSTRUÇÃO DA LISTA CIRCULAR:");
        circularList1.addFirst("C");
        circularList1.addFirst("B");
        circularList1.addFirst("A");
        circularList1.addToRear("D");
        circularList1.addToRear("E");

        System.out.println("Lista: " + circularList1);
        System.out.println("Circularidade correta? " + circularList1.isProperlyCircular());
        System.out.println("Tamanho: " + circularList1.getSize());

        // 2. Percursos bidirecionais
        System.out.println("\n2. PERCURSOS BIDIRECIONAIS:");
        circularList1.traverseForward("A");
        circularList1.traverseBackward("A");

        circularList1.traverseForward("C");
        circularList1.traverseBackward("C");

        circularList1.traverseForward("E");
        circularList1.traverseBackward("E");

        // 3. Operações de rotação
        System.out.println("\n3. ROTAÇÕES:");
        System.out.println("Antes: " + circularList1);

        circularList1.rotateForward();
        System.out.println("Rotate forward: " + circularList1);

        circularList1.rotateForward();
        System.out.println("Rotate forward: " + circularList1);

        circularList1.rotateBackward();
        System.out.println("Rotate backward: " + circularList1);

        // 4. Remoções mantendo circularidade
        System.out.println("\n4. REMOÇÕES:");
        System.out.println("Remove primeiro: " + circularList1.removeFirst());
        System.out.println("Lista: " + circularList1);
        System.out.println("Circularidade mantida? " + circularList1.isProperlyCircular());

        System.out.println("Remove último: " + circularList1.removeLast());
        System.out.println("Lista: " + circularList1);

        System.out.println("Remove 'C': " + circularList1.remove("C"));
        System.out.println("Lista: " + circularList1);

        // 5. Casos especiais
        System.out.println("\n5. CASOS ESPECIAIS:");

        // Um elemento
        CircularDoublyLinkedList<String> umElemento2 = new CircularDoublyLinkedList<>();
        umElemento2.addFirst("Solitário");
        System.out.println("Um elemento: " + umElemento2);
        System.out.println("Circularidade? " + umElemento2.isProperlyCircular());
        umElemento2.traverseForward("Solitário");
        umElemento2.traverseBackward("Solitário");

        // Vazia
        CircularDoublyLinkedList<String> vazia2 = new CircularDoublyLinkedList<>();
        System.out.println("Vazia: " + vazia2);
        System.out.println("Circularidade? " + vazia2.isProperlyCircular());

        // 6. Cenário prático - Música em Loop
        System.out.println("\n6. CENÁRIO PRÁTICO - PLAYLIST CIRCULAR:");
        CircularDoublyLinkedList<String> playlist = new CircularDoublyLinkedList<>();
        playlist.addToRear("Música 1 - Rock");
        playlist.addToRear("Música 2 - Jazz");
        playlist.addToRear("Música 3 - Pop");
        playlist.addToRear("Música 4 - Clássica");

        System.out.println("Playlist: " + playlist);

        // Simular reprodução
        System.out.println("\nReprodução em loop:");
        playlist.traverseForward("Música 1 - Rock");

        System.out.println("\nPular músicas (rotate):");
        playlist.rotateForward();
        System.out.println("Agora tocando: " + playlist.getHead());

        playlist.rotateForward();
        System.out.println("Agora tocando: " + playlist.getHead());

        // Voltar música
        playlist.rotateBackward();
        System.out.println("Voltou para: " + playlist.getHead());


        /**
         6. Desenhe uma fig da estrutura ADT {LL, CircularLL, DLL, CircularDLL} contendo os elems numéricos 20, 16, 27, 92. Atenção: N pode recorrer a nós sentinelas em nenhuma das estruturas.
         */
        // R:
        // LL
        // [20] → [16] → [27] → [92] → null
        // ↑
        //head
        //
        // CircularLL
        //     → [20] → [16] → [27] → [92]   ─┐
        //    ↑                               │
        //    └───────────────────────────────┘
        //     ↑
        //    head
        //
        // DLL
        // null ← [20] ⇄ [16] ⇄ [27] ⇄ [92] → null
        //         ↑                   ↑
        //        head               tail
        //
        // CircularDLL
        //     ┌→ [20] ⇄ [16] ⇄ [27] ⇄ [92]  ─┐
        //     │                              │
        //     └──────────────────────────────┘
        //     ↑
        //    head (20)
        //    tail (92) → next aponta para head (20)
        //    head (20) → prev aponta para tail (92)


        /**
         7. Qual é a complexidade de tempo em notação Big O das operações de {travessia, inserção de um nó no início da list, inserção de um nó no fim da list} pra as definições ADT {LL, CircularLL, DLL}?
         */
        // R:
        // Travessia: Tds O(n) - têm de percorrer tds os elems
        //
        // Inserção no início: Tds O(1) - só mexem no head
        //
        // Inserção no fim:
        //  - LL: O(n) - tem de percorrer até ao último
        //  - CircularLL: O(1) - c tail
        //  - DLL: O(1) - c tail
        //
        // DLL é a + equilibrada - td O(1) exceto travessia 🚀


        /**
         8. Implemente um validador simples de XHTML recorrendo à sua implementação da Stack. Pra o exercício considere q um documento XHTML só é válido se tds as tags abertas forem fechadas e uma tag só pode ser fechada se for a q tá aberta nesse momento (Ver ex). Coloque tds as tags (abertura e fecho) aceites numa List, e quebre a string de entrada por espaços.
         *
         * Ex válido:
         * <body>
         *      <h1> Titulo </h1>
         *      <p> Corpo com <a>link</a> </p>
         * <body>
         *
         * Ex n validos:
         * <body>
         *      <h1> Titulo </h1>
         *      <p> Corpo com <a>link</p></a>
         * </body>
         *
         * <body>
         *      <h1> Titulo </h1>
         *      <p> Corpo com <a>link</a></p>
         */
    }
}
