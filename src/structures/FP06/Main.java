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



        /**
         3. Descreva cm é q a {stack, queue} podem ser representadas c recurso a uma LL. Qual é a principal vantagem em relação à implementação em array?
         */



        /**
         4. Crie uma implementação de List através de uma CircularLL, podendo recorrer à herança caso seja aplicável, q funcione de forma circular, ou seja, o último elem da list deverá apontar pra a cabeça e n pra null.
         */



        /**
         5. Crie uma implementação de List através de uma CircularDLL, podendo recorrer a herança caso seja aplicável, q funcione de forma circular, ou seja, a referência seguinte da cauda da list deverá referenciar a cabeça e n null, assim cm o elem anterior à cabeça deverá referenciar a cauda da list em vez de null.
         */



        /**
         6. Desenhe uma fig da estrutura ADT {LL, CircularLL, DLL, CircularDLL} contendo os elems numéricos 20, 16, 27, 92. Atenção: N pode recorrer a nós sentinelas em nenhuma das estruturas.
         */



        /**
         7. Qual é a complexidade de tempo em notação Big O das operações de {travessia, inserção de um nó no início da list, inserção de um nó no fim da list} pra as definições ADT {LL, CircularLL, DLL}?
         */



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
