package structures.FP03;

import exceptions.EmptyCollectionException;

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
         * 2. Implementar a LinkedStack cm é sugerido no Slide 32
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
        String expr1 = "3 4 + 2 *";   // (3+4)*2 = 14
        String expr2 = "10 2 8 * + 3 -"; // 10 + (2*8) - 3 = 23

        System.out.println("Expressão: " + expr1 + " = " + evaluate(expr1));
        System.out.println("Expressão: " + expr2 + " = " + evaluate(expr2));


        /**
         2. Substituir a ArrayStack pela LinkedStack na implementação da calculadora posfix
         */
        System.out.println("Expressão: " + expr1 + " = " + evaluateLinked(expr1));
        System.out.println("Expressão: " + expr2 + " = " + evaluateLinked(expr2));


        /**
         3. Q alterações teve de fzr na implementação da calculadora pra q esta apresentasse o mm comportamento. Enumere cd uma das alterações.
         */
        // R: As únicas mudanças foram substituir a ED (ArrayStack → LinkedStack) e ajustar os imports/declaração da stack. Td a lógica de cálculo postfix manteve-se idêntica, pq ambas as stacks implementam a mm interface de
        // operações fundamentais (push, pop, peek, isEmpty).
    }

    /**
     * Avalia uma expressão em notação postfix (RPN).
     * Exemplo: "3 4 + 2 *" → (3+4)*2 = 14
     *
     * @param expr expressão em postfix separada por espaços
     * @return resultado inteiro da expressão
     */
    public static int evaluate(String expr) throws EmptyCollectionException {

        ArrayStack<Integer> stack = new ArrayStack<>();
        String[] tokens = expr.split("\\s+");

        for (String token : tokens) {
            if (isNumber(token)) {
                // Se for número, empilha
                stack.push(Integer.parseInt(token));
            } else {
                // Se for operador, desempilha dois operandos
                int b = stack.pop();
                int a = stack.pop();
                int result = applyOperator(a, b, token);
                stack.push(result);
            }
        }

        return stack.pop(); // resultado final
    }

    /**
     * verifica se a string é número
     * @param s string verificada
     * @return true se for um numero, false caso contrario
     */
    private static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * aplicar o operador da calculadora postfix
     * @param a
     * @param b
     * @param op
     * @return calculo
     */
    private static int applyOperator(int a, int b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Operador inválido: " + op);
        };
    }


    /**
     * Avalia uma expressão em notação postfix, usando linkedstack.
     * Exemplo: "3 4 + 2 *" → (3+4)*2 = 14
     *
     * @param expr expressão em postfix separada por espaços
     * @return resultado inteiro da expressão
     */
    public static int evaluateLinked(String expr) throws EmptyCollectionException {

        LinkedStack<Integer> stack = new LinkedStack<>();
        String[] tokens = expr.split("\\s+");

        for (String token : tokens) {
            if (isNumber(token)) {
                // Se for número, empilha
                stack.push(Integer.parseInt(token));
            } else {
                // Se for operador, desempilha dois operandos
                int b = stack.pop();
                int a = stack.pop();
                int result = applyOperator(a, b, token);
                stack.push(result);
            }
        }

        return stack.pop(); // resultado final
    }
}
