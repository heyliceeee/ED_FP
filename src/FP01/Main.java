package FP01;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    /**
    # Parte I
    1. Testa a classe TwoTypePair nos slides 26 e 27 da Aula 01 e observa o resultado.
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
    2. Testa a classe Pair no slide 32 da Aula 01
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
    3. Alterar a classe UnorderedPair no slide 37 da Aula 01 de forma q seja possível efetuar 2 operações:
        - Obter o valor do 1o e 2o elem (2 métodos).
        - Verificar se os 2 elems introduzidos são ='s
    */
        UnorderedPair<String> up1 = new UnorderedPair<>("Alice", "Bob");
        UnorderedPair<String> up2 = new UnorderedPair<>("Bob", "Alice");
        UnorderedPair<String> up3 = new UnorderedPair<>("Alice", "Alice");

        System.out.println("Primeiro elemento de up1: " + up1.getFirstElement());
        System.out.println("Segundo elemento de up1: " + up1.getSecondElement());

        System.out.println("up1.equals(up2)? " + up1.equals(up2)); // true
        System.out.println("up1.hasEqualElements()? " + up1.hasEqualElements()); // false
        System.out.println("up3.hasEqualElements()? " + up3.hasEqualElements()+"\n\n"); // true


    /**
    4. Teste o código apresentado no slide 40 da Aula 01 de forma a obter a msg de erro na compilação. Csg perceber o pq do erro?
    */
        Collection<String> nomes = List.of("Alice", "Bob");
        //printCollection(nomes); // ERRO de compilação

        // R: Collection<String> n é um subtp de Collection<Object>


    /**
    5. Apresente uma sol pra o problema apresentado no slide 44 da Aula 01
    */
        // Problema: drawAll(List<Shape>) está limitado apenas a lists declaradas cm List<Shape>, e n aceita List<Circle> ou
        // List<Rectangle>, mm q Circle e Rectangle sejam subclasses de Shape.
        // R: alterar a assinatura pra drawAll(List<? extends Shape>). Assim, o método pode desenhar tds as formas,
        //  independentemente de serem Shape, Circle, Rectangle ou qlqr outra subclasse.

        Canvas canvas = new Canvas();

        // List de formas
        List<Shape> shapes = Arrays.asList(new Circle(), new Rectangle());

        canvas.draw(new Circle());
        canvas.draw(new Rectangle());
        canvas.drawAll(shapes);


    /**
    # Parte II
    1. Preencha os espaços em branco:
        a) A declaração Store<T> é _uma classe generica_.
        b) Store é uma _classe generica_, e T é o _parametro de tp_.
        c) Store<String> é _uma instancia de tp (ou tp parametrizado) da classe generica Store_.
        d) O uso de um tp parametrizado é conhecido cm _instanciacao de generico (ou simplesmente uso de genericos)_.
    */


    /**
    2. Considere os seguintes fragmentos de código Java (as 1as 3 linhas são ='s em tds; apenas a última linha apresenta diferenças). Pra cd um dos fragmentos, o código compila corretamente? Caso compile, é executado sem
    erros, ou existe alguma exceção?
    */

        /*
        Point[] a = new Point[10];
        Object[] b;
        b = a; // Point[] é um subtp de Object[] → compila
        b[0] = new Point(10,20); // é válido, pq Point é compatível c Point[]
         */

        // R: executa sem erros


        /*
        Point[] a = new Point[10];
        Object[] b;
        b = a;
        b[0] = "Magical Mystery Tour"; // tenta colocar um String dentro de um Point[] → isso gera ArrayStoreException
        */

        // R: Compila, mas lança exceção em tempo de execução.


        /*
        Point[] a = new Point[10];
        Object[] b;
        b = a;
        a[0] = "Magical Mystery Tour"; // Atribuir "Magical Mystery Tour" (um String) diretamente a a[0] → erro de compilação, pq o compilador sb q a[0] deve ser um Point.
         */

        // R: N compila


    /**
    3. Oq acontece se escrevermos código análogo ao da 2ª questão, mas q faça uso de um ArrayList? P.ex:
    */

        /*
        ArrayList<Point> a = new ArrayList<Point>();
        ArrayList<Object> b;
        b = a; // tentativa de atribuicao
        b.add(new Point(10,20));
         */

        // R: ArrayList<Point> n pode ser atribuído a ArrayList<Object>, pq os genéricos em Java são invariantes.


    /**
    4. Desenvolver uma app q ordene uma list de strings pré-definidas baseadas no tam da string. Usar o método Collections.sort. Atenção: deverá fzr uso de Generics.
    */
        // Lista pré-definida de strings
        List<String> palavras = new ArrayList<>();
        palavras.add("Java");
        palavras.add("Programação");
        palavras.add("AI");
        palavras.add("Genéricos");
        palavras.add("Porto");

        System.out.println("Antes da ordenação:");
        System.out.println(palavras);

        // Ordenação usando Collections.sort e Comparator genérico
        Collections.sort(palavras, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });

        System.out.println("\nDps da ordenação (por tam):");
        System.out.println(palavras);


    /**
    5. Escrever uma classe q haja cm uma livraria pra os seguintes tps de média: livro, vídeo e CD de música. Atenção: deverá fzr uso de Generics. Add APIs adicionais pra armazenar e obter média
    */
        // Biblioteca de livros
        Library<Livro> bibliotecaLivros = new Library<>();
        bibliotecaLivros.addItem(new Livro("O Senhor dos Anéis"));
        bibliotecaLivros.addItem(new Livro("1984"));

        // Biblioteca de vídeos
        Library<Video> bibliotecaVideos = new Library<>();
        bibliotecaVideos.addItem(new Video("Matrix"));
        bibliotecaVideos.addItem(new Video("Inception"));

        // Biblioteca de CDs
        Library<CD> bibliotecaCDs = new Library<>();
        bibliotecaCDs.addItem(new CD("Abbey Road"));
        bibliotecaCDs.addItem(new CD("Dark Side of the Moon"));

        // Mostrar conteúdos
        System.out.println("Livros: " + bibliotecaLivros.getAllItems());
        System.out.println("Vídeos: " + bibliotecaVideos.getAllItems());
        System.out.println("CDs: " + bibliotecaCDs.getAllItems());
    }

    private static void printCollection(Collection<Object> c) {
        for (Object e: c){
            System.out.println(e);
        }
    }
}
