package structures.FP03;

public class Main {
    public static void main(String[] args) {
        /**
         # Parte I
         1. Implementar a ArrayStack cm sugerido nos slides da aula teórica. Demonstre a utilização da ArrayStack pra um cenário à sua escolha.
         */
        ArrayStack<String> undoStack = new ArrayStack<>(5);

        // Simulação de ações do utilizador
        undoStack.push("Escreveu: Olá");
        undoStack.push("Escreveu: Mundo");
        undoStack.push("Apagou: Mundo");

        System.out.println(undoStack);

        // Última ação (undo)
        System.out.println("Desfazer -> " + undoStack.pop());

        // Ver ação atual no topo
        System.out.println("Topo -> " + undoStack.peek());

        System.out.println(undoStack);


        /**
         2. Implementar a LinkedStack cm é sugerido no Slide 32
         */
        LinkedStack<String> history = new LinkedStack<>();

        // Utilizador visita páginas
        history.push("google.com");
        history.push("github.com");
        history.push("stackoverflow.com");

        System.out.println(history);

        // Voltar atrás (pop)
        System.out.println("Voltar de: " + history.pop());

        // Página atual (peek)
        System.out.println("Página atual: " + history.peek());

        System.out.println(history);


        /**
         3. Implementar as operações: push e pop pra a LinkedStack. Demonstre o comportamento das operações implementadas.
         */
        // foi implementado no exercicio anterior


        /**
         4. Implementar as operações restantes pra a LinkedStack cm é sugerido no Slide 38. Demonstre a utilização da LinkedStack pra um cenário à sua escolha.
         */
        // foi implementado no exercicio anterior


        /**
         5. Observa a classe java.util.Stack e identifica as diferenças em relação à classe implementada nos exercícios anteriores.
         */
        // R:
        // - java.util.Stack herda de Vector, é sincronizada, cresce dinamicamente e tem métodos extra cm search.
        // - ArrayStack → array fixo, simples, n sincronizada.
        // - LinkedStack → linkedlist, ilimitada, n sincronizada.


        /**
         # Parte II
         1. Implementa uma calculadora postfix c recurso a um ArrayStack.
         */



        /**
         2. Substituir a ArrayStack pela LinkedStack na implementação da calculadora posfix
         */



        /**
         3. Q alterações teve de fzr na implementação da calculadora pra q esta apresentasse o mm comportamento. Enumere cd uma das alterações.
         */

    }
}
