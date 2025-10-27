package structures.FP08;

import structures.FP02.LinkedList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /**
         # Parte I
         1. Cria uma classe q represente uma entidade, p.ex. um carro, assim cm algumas q ache relevantes pra o representar. Armazenar instâncias dessa entidade num array e usar os seguintes métodos de pesquisa: linear search, binary search pra encontrar um determinado elem.
         */
        Carro[] carros = {
                new Carro("11-AA-11", "Toyota", "Corolla", 2018),
                new Carro("22-BB-22", "Honda", "Civic", 2020),
                new Carro("33-CC-33", "Ford", "Focus", 2019),
                new Carro("44-DD-44", "BMW", "320d", 2021)
        };

        // Pesquisa linear
        int idxLinear = SearchUtils.linearSearch(carros, carros[2]);
        System.out.println("Linear search encontrou: " + carros[idxLinear]);

        // Pesquisa binária (precisa de array ordenado)
        Arrays.sort(carros); // ordena por matrícula
        int idxBinario = SearchUtils.binarySearch(carros, new Carro("22-BB-22", "", "", 0));
        System.out.println("Binary search encontrou: " + carros[idxBinario]);


        /**
         * 2. Cria uma classe q represente uma entidade, p.ex. um carro, assim cm algumas q ache relevantes pra o representar. Armazenar instâncias dessa entidade numa LL e usar os seguintes métodos de pesquisa: linear search, binary search pra encontrar um determinado elem.
         * Nota: Os métodos usados no exercício anterior e q se encontram nos slides respetivos à aula teórica terão de ser adaptados pra LL.
         */
        LinkedList<Carro> carros2 = new LinkedList<>();
        carros2.addFirst(new Carro("11-AA-11", "Toyota", "Corolla", 2018));
        carros2.addFirst(new Carro("22-BB-22", "Honda", "Civic", 2020));
        carros2.addFirst(new Carro("33-CC-33", "Ford", "Focus", 2019));
        carros2.addFirst(new Carro("44-DD-44", "BMW", "320d", 2021));

        System.out.println("Antes do sort: " + carros2);
        carros2.sortLinkedList();
        System.out.println("Depois do sort: " + carros2);

        // 🔹 Pesquisa linear
        System.out.println("Pesquisa linear por matrícula 33-CC-33:");
        Carro encontradoLinear = SearchUtils.linearSearchLinkedList(carros2, "33-CC-33");
        System.out.println(encontradoLinear);

        // 🔹 Pesquisa binária
        System.out.println("\nPesquisa binária por matrícula 22-BB-22:");

        Carro encontradoBinario = SearchUtils.binarySearchLinkedList(carros2, "22-BB-22");
        System.out.println(encontradoBinario);


        /**
         * 3. Criar uma classe q represente uma entidade do mundo real e q permita ser comparável pra testar os vários métodos de ordenação apresentados na aula teórica. Pra poder usar os métodos apresentados nos slides da aula teórica terá de add as várias instâncias da classe criada num array. Testar os vários algoritmos de ordenação apresentados:
         * ▪ Selection Sort
         * ▪ Insertion Sort
         * ▪ Bubble Sort
         * ▪ Quick Sort
         * ▪ Merge Sort
         * /



        /**
         * 4. Usar o cenário definido no exercício anterior, mas alterar a ED q os armazena. No exercício anterior estávamos a usar um array e neste exercício pretendemos usar uma LL. Cm tds os métodos de ordenação tão implementados pra serem usados c um array, estes terão de ser novamente implementados. Testar e implementar os vários algoritmos de ordenação pra uma LL:
         * ▪ Selection Sort
         * ▪ Insertion Sort
         * ▪ Bubble Sort
         * ▪ Quick Sort
         * ▪ Merge Sort
         */



        /**
         * Parte II
         * 1. Qual é o algoritmo de pesquisa + eficiente, a binary search ou a linear search?
         */



        /**
         * 2. Qual é o algoritmo de ordenação + eficiente, o Quick Sort ou o Merge Sort?
         */

    }
}
