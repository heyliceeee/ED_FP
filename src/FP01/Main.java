package FP01;

public class Main {
    public static void main(String[] args) {
        /// Parte I
        /// 1. Testa a classe TwoTypePair nos slides 26 e 27 da Aula 01 e observa o resultado.

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
        System.out.println(par3);
    }
}
