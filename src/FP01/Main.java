package FP01;

public class Main {
    public static void main(String[] args) {
        /// Parte I
        /// 1. Testar a classe TwoTypePair nos slides 26 e 27 da Aula 01 e observar o resultado.

        // Criar um par de String e Integer
        TwoTypePair<String, Integer> par1 = new TwoTypePair<>("Alice", 25);
        System.out.println("Par1:");
        System.out.println(par1);

        // Criar outro par igual
        TwoTypePair<String, Integer> par2 = new TwoTypePair<>("Alice", 25);
        System.out.println("\nPar2:");
        System.out.println(par2);

        // Criar um par diferente
        TwoTypePair<String, Integer> par3 = new TwoTypePair<>("Bob", 30);
        System.out.println("\nPar3:");
        System.out.println(par3);

        // Testar equals
        System.out.println("\nComparações:");
        System.out.println("par1.equals(par2) → " + par1.equals(par2)); // true
        System.out.println("par1.equals(par3) → " + par1.equals(par3)); // false

        // Alterar valores com setters
        par3.setFirst("Alice");
        par3.setSecond(25);
        System.out.println("\nPar3 modificado:");
        System.out.println(par3);

        System.out.println("par1.equals(par3) → " + par1.equals(par3)); // true agora
    }
}
