package FP01;

public class Main {
    public static void main(String[] args) {
        /**
         # Parte I
         ### 1. Testa a classe TwoTypePair nos slides 26 e 27 da Aula 01 e observa o resultado.
        */
        // Criação do primeiro par (par1)
        TwoTypePair<String, Integer> par1 = new TwoTypePair<>("Alice", 25);
        System.out.println("Par1:");
        System.out.println(par1);

        // Criação do segundo par (par2) com os mesmos valores
        TwoTypePair<String, Integer> par2 = new TwoTypePair<>("Alice", 25);
        System.out.println("\nPar2:");
        System.out.println(par2);

        // Comparação inicial (par1.equals(par2))
        System.out.println("\npar1.equals(par2)? " + par1.equals(par2));

        // Alteração de par2 para "Bob", 30
        par2.setFirst("Bob");
        par2.setSecond(30);
        System.out.println("\nPar2 modificado:");
        System.out.println(par2);

        // Nova comparação (par1.equals(par2))
        System.out.println("\npar1.equals(par2)? " + par1.equals(par2));

        // Criação de par3 com o construtor vazio
        TwoTypePair<Double, String> par3 = new TwoTypePair<>();
        System.out.println("\nPar3 (construtor vazio):");
        System.out.println(par3+"\n\n");


        /**
         ### 2. Testa a classe Pair no slide 32 da Aula 01
         */
        // Exemplo com inteiros
        Pair<Integer> p1 = new Pair<>(10, 20);
        System.out.println("Maior entre 10 e 20: " + p1.max());

        // Exemplo com strings
        Pair<String> p2 = new Pair<>("Alice", "Bob");
        System.out.println("Maior entre 'Alice' e 'Bob': " + p2.max());

        // Exemplo com doubles
        Pair<Double> p3 = new Pair<>(3.14, 2.71);
        System.out.println("Maior entre 3.14 e 2.71: " + p3.max());
        System.out.println(par3+"\n\n");


        /**
         ### 3. Alterar a classe UnorderedPair no slide 37 da Aula 01 de forma q seja possível efetuar 2 operações:
              - Obter o valor do primeiro e segundo elem (2 métodos).
              - Verificar se os 2 elems introduzidos são iguais
         */
        UnorderedPair<String> up1 = new UnorderedPair<>("Alice", "Bob");
        UnorderedPair<String> up2 = new UnorderedPair<>("Bob", "Alice");
        UnorderedPair<String> up3 = new UnorderedPair<>("Alice", "Alice");

        System.out.println("Primeiro elemento de up1: " + up1.getFirstElement());
        System.out.println("Segundo elemento de up1: " + up1.getSecondElement());

        System.out.println("up1.equals(up2)? " + up1.equals(up2)); // true
        System.out.println("up1.hasEqualElements()? " + up1.hasEqualElements()); // false
        System.out.println("up3.hasEqualElements()? " + up3.hasEqualElements()+"\n\n")); // true


        /**
         ### 4. Teste o código apresentado no slide 40 da Aula 01 de forma a obter a msg de erro na compilação. Consegue perceber o pq do erro?
         */




        /**
         ### 5. Apresente uma solução pra o problema apresentado no slide 44 da Aula 01
         */



        /**
         # Parte II
         ### 1. 
         */
    }
}
