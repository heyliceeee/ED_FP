package structures.FP05;

import exceptions.ElementNotFoundException;
import structures.FP03.ArrayStack;
import structures.FP03.LinkedStack;
import structures.FP04.LinkedQueue;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        /**
         # Parte I
         1. Implementa uma ArrayOrderedList. Lembre-se q terá de implementar a interface adequada.
         * Preste atenção aos slides da aula teórica principalmente o 15, e veja o diagrama de interfaces. Qts classes serão necessárias pra implementar o diagrama q lá está presente?
         */
         // R:
         // 3 interfaces (ListADT, OrderedListADT, UnorderedListADT) + 1 classe de implementacao = 4 elems


        /**
         * Outro problema mt importante c q se irá debater será na criação do iterador. Pra este exercício n considere as modificações concorrentes à ED subjacente.
         * Demonstre a utilização da ArrayOrderedList pra um cenário à sua escolha.
         */
        ArrayOrderedList<Paciente> filaEmergencia = new ArrayOrderedList<>();// Criar lista ordenada de pacientes por prioridade

        System.out.println("=== SISTEMA DE TRIAGEM HOSPITALAR ===\n");

        // 1. Chegada de pacientes à emergência
        System.out.println("1. CHEGADA DE PACIENTES:");
        filaEmergencia.add(new Paciente("João Silva", "Fratura exposta", 2));
        filaEmergencia.add(new Paciente("Maria Santos", "Dor de cabeça", 4));
        filaEmergencia.add(new Paciente("Pedro Costa", "Ataque cardíaco", 1));
        filaEmergencia.add(new Paciente("Ana Oliveira", "Febre alta", 3));
        filaEmergencia.add(new Paciente("Carlos Lima", "Corte superficial", 5));

        System.out.println("Fila de emergência (ordenada por prioridade):");
        System.out.println(filaEmergencia);
        System.out.println("Total de pacientes: " + filaEmergencia.size());

        // 2. Atendimento por ordem de prioridade
        System.out.println("\n2. ATENDIMENTO POR PRIORIDADE:");
        System.out.println("Próximo paciente: " + filaEmergencia.first());

        Paciente emAtendimento = filaEmergencia.removeFirst();
        System.out.println("Em atendimento: " + emAtendimento);
        System.out.println("Pacientes restantes: " + filaEmergencia.size());

        // 3. Chega um caso mais urgente
        System.out.println("\n3. CHEGADA DE CASO URGENTE:");
        Paciente casoUrgente = new Paciente("Sofia Rodrigues", "Acidente vascular", 1);
        filaEmergencia.add(casoUrgente);
        System.out.println("Adicionado: " + casoUrgente);
        System.out.println("Nova fila: " + filaEmergencia);

        // 4. Processar toda a fila
        System.out.println("\n4. PROCESSAMENTO DA FILA COMPLETA:");
        System.out.println("Atendendo todos os pacientes por ordem de prioridade:");

        int atendidos = 1;
        while (!filaEmergencia.isEmpty()) {
            Paciente paciente = filaEmergencia.removeFirst();
            System.out.println(atendidos + ". " + paciente);
            atendidos++;
        }

        System.out.println("Fila esvaziada: " + filaEmergencia.isEmpty());

        // 5. Demonstração do iterador com remoção seletiva
        System.out.println("\n5. DEMONSTRAÇÃO DO ITERADOR COM REMOÇÃO SELETIVA:");

        // Recriar fila para demonstração
        filaEmergencia.add(new Paciente("Luís Fernandes", "Queimadura grave", 2));
        filaEmergencia.add(new Paciente("Marta Alves", "Virose", 4));
        filaEmergencia.add(new Paciente("Rui Gomes", "Trauma craniano", 1));
        filaEmergencia.add(new Paciente("Catarina Sousa", "Dor abdominal", 3));

        System.out.println("Fila inicial: " + filaEmergencia);

        // Usar iterador para remover pacientes não urgentes (prioridade > 3)
        Iterator<Paciente> iterator = filaEmergencia.iterator();
        while (iterator.hasNext()) {
            Paciente p = iterator.next();
            if (p.getPrioridade() > 3) {
                System.out.println("Removendo caso não urgente: " + p);
                iterator.remove();
            }
        }

        System.out.println("Fila após remoção de casos não urgentes: " + filaEmergencia);

        // 6. Operações específicas da lista
        System.out.println("\n6. OPERAÇÕES ESPECÍFICAS DA LISTA:");
        System.out.println("Primeiro paciente: " + filaEmergencia.first());
        System.out.println("Último paciente: " + filaEmergencia.last());

        // Remover paciente específico
        Paciente paraRemover = new Paciente("Luís Fernandes", "Queimadura grave", 2);
        Paciente removido = filaEmergencia.remove(paraRemover);
        System.out.println("Paciente removido: " + removido);
        System.out.println("Fila final: " + filaEmergencia);


        /**
         2. Implementa uma DoubleLinkedOrderedList. Demonstre a utilização da DoubleLinkedOrderedList pra um cenário à sua escolha.
         */
        DoubleLinkedOrderedList<Integer> lista = new DoubleLinkedOrderedList<>();

        // ✅ Funciona - usa o método add() específico da lista ordenada
        lista.add(5);
        lista.add(2);
        lista.add(8);
        lista.add(1);

        System.out.println(lista); // [1 -> 2 -> 5 -> 8] - ordenado!

        // ✅ Funciona - métodos herdados
        System.out.println("Primeiro: " + lista.first()); // 1
        System.out.println("Último: " + lista.last());    // 8

        // ✅ Funciona - remoções
        lista.removeFirst(); // Remove 1
        lista.remove(5);     // Remove 5

        System.out.println(lista); // [2 -> 8]


        /**
         3. Os cenários usados nos exercícios anteriores (1 e 2) foram adds elems n comparáveis? Oq aconteceu?
         */
        // R:
        // N foram adds elems n comparáveis. Tds as classes (Paciente, Livro, Tarefa) implementavam Comparable. Por isso, n houve erros e td funcionou corretamente


        /**
         4. Define a interface UnorderedListADT e implementa uma ArrayUnorderedList. Demonstre a utilização da ArrayUnorderedList pra um cenário à sua escolha.
         */
        System.out.println("=== SISTEMA DE HISTÓRICO DE NAVEGAÇÃO WEB ===\n");

        // Criar histórico de navegação
        ArrayUnorderedList<String> historico = new ArrayUnorderedList<>();

        // 1. Navegação inicial - páginas visitadas
        System.out.println("1. NAVEGAÇÃO INICIAL:");
        historico.addToRear("https://www.google.com");
        historico.addToRear("https://www.github.com");
        historico.addToRear("https://stackoverflow.com");

        System.out.println("Histórico após navegação inicial:");
        System.out.println(historico);
        System.out.println("Total de páginas: " + historico.size());

        // 2. Acesso rápido a página inicial
        System.out.println("\n2. ACESSO RÁPIDO À PÁGINA INICIAL:");
        historico.addToFront("https://www.meusite.com");
        System.out.println("Histórico após adicionar página inicial:");
        System.out.println(historico);

        // 3. Navegação após pesquisa específica
        System.out.println("\n3. NAVEGAÇÃO APÓS PESQUISA:");
        try {
            historico.addAfter("https://docs.oracle.com/javase", "https://www.google.com");
            System.out.println("Adicionada documentação Java após Google");
            System.out.println("Histórico atual: " + historico);
        } catch (ElementNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // 4. Consulta do histórico
        System.out.println("\n4. CONSULTA DO HISTÓRICO:");
        System.out.println("Primeira página visitada: " + historico.first());
        System.out.println("Última página visitada: " + historico.last());
        System.out.println("Contém GitHub? " + historico.contains("https://www.github.com"));

        // 5. Navegação para trás (remover última página)
        System.out.println("\n5. NAVEGAÇÃO PARA TRÁS:");
        String ultima = historico.removeLast();
        System.out.println("Página removida (back): " + ultima);
        System.out.println("Histórico após back: " + historico);

        // 6. Voltar ao início (remover primeira página)
        System.out.println("\n6. VOLTAR AO INÍCIO:");
        String primeira = historico.removeFirst();
        System.out.println("Página removida (home): " + primeira);
        System.out.println("Histórico atual: " + historico);

        // 7. Remoção de página específica
        System.out.println("\n7. REMOÇÃO DE PÁGINA ESPECÍFICA:");
        try {
            String removida = historico.remove("https://www.github.com");
            System.out.println("Página removida: " + removida);
            System.out.println("Histórico final: " + historico);
        } catch (ElementNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // 8. Iteração pelo histórico
        System.out.println("\n8. ITERAÇÃO PELO HISTÓRICO:");
        System.out.println("Páginas no histórico:");
        for (String pagina : historico) {
            System.out.println(" - " + pagina);
        }

        // 9. Cenário de erro - elemento não encontrado
        System.out.println("\n9. CENÁRIO DE ERRO:");
        try {
            historico.addAfter("https://www.invalida.com", "https://www.inexistente.com");
        } catch (ElementNotFoundException e) {
            System.out.println("❌ Erro esperado: " + e.getMessage());
        }

        // 10. Histórico vazio
        System.out.println("\n10. LIMPAR HISTÓRICO:");

        while (!historico.isEmpty())
            historico.removeFirst(); // Remove todos os elementos

        System.out.println("Histórico vazio? " + historico.isEmpty());
        System.out.println("Total de páginas: " + historico.size());


        /**
         5. Implementa e testa uma DoubleLinkedUnorderedList.
         */
        System.out.println("=== TESTE DA DOUBLELINKEDUNORDEREDLIST ===\n");

        DoubleLinkedUnorderedList<String> lista1 = new DoubleLinkedUnorderedList<>();

        // Testar addToFront e addToRear
        lista1.addToFront("B");
        lista1.addToFront("A");
        lista1.addToRear("D");

        System.out.println("Após addToFront/Rear: " + lista1);

        // Testar addAfter
        lista1.addAfter("C", "B");
        System.out.println("Após addAfter: " + lista1);

        // Testar métodos de consulta
        System.out.println("Primeiro: " + lista1.first());
        System.out.println("Último: " + lista1.last());
        System.out.println("Tamanho: " + lista1.size());
        System.out.println("Contém 'C'? " + lista1.contains("C"));
        System.out.println("Está vazia? " + lista1.isEmpty());

        // Testar iterador
        System.out.println("Elementos via iterador:");
        for (String elem : lista1)
            System.out.println(" - " + elem);

        // Testar remoções
        lista1.removeFirst();
        System.out.println("Após removeFirst: " + lista1);

        lista1.remove("C");
        System.out.println("Após remove 'C': " + lista1);

        lista1.removeLast();
        System.out.println("Após removeLast: " + lista1);

        // Testar cenário de erro
        try {
            lista1.addAfter("X", "Inexistente");
        } catch (ElementNotFoundException e) {
            System.out.println("✅ Erro esperado: " + e.getMessage());
        }

        System.out.println("Lista final: " + lista1);


        /**
         # Parte II
         1. Pq q n existe uma método add(Element) na interface ListADT?
         */
        // R: Cd interface tem os métodos q fazem sentido pra o seu tp de list.


        /**
         2. Dada a versatilidade de uma list pq q n é smp a escolhida em vez de uma Stack ou de uma Queue?
         */
        // R:
        // Cd estrutura comunica intenção ≠:
        //  - Stack: "Só preciso do último" (LIFO)
        //  - Queue: "Processar por ordem de chegada" (FIFO)
        //  - List: "Preciso de acesso livre a tds"

        // List é canivete suíço - + flexível mas:
        //  - - segura (permite operações erradas)
        //  - - performance pra casos específicos
        //  - - clara a intenção
        // Usa a ferramenta certa pra o trabalho - restrições evitam erros.


        /**
         3. Cria um método na DoubleLinkedOrderedList q inverta e devolva tds os elems no tp de dados abstrato q entender.
         */
        System.out.println("=== DEMONSTRAÇÃO DE INVERSÃO COM LINKEDSTACK ===\n");

        DoubleLinkedOrderedList<Integer> listaOrdenada = new DoubleLinkedOrderedList<>();

        // Adicionar elementos ordenados
        listaOrdenada.add(10);
        listaOrdenada.add(20);
        listaOrdenada.add(30);
        listaOrdenada.add(40);

        System.out.println("Lista original (ordenada): " + listaOrdenada);

        // 1. Inverter para LinkedStack (LIFO)
        System.out.println("\n1. INVERSÃO PARA LINKEDSTACK:");
        LinkedStack<Integer> linkedStack = listaOrdenada.reverseToStack();
        System.out.println("LinkedStack: " + linkedStack);
        System.out.println("Pop: " + linkedStack.pop() + " → " + linkedStack.pop());
        System.out.println("Stack após pops: " + linkedStack);

        // 2. Inverter para ArrayStack
        System.out.println("\n2. INVERSÃO PARA ARRAYSTACK:");
        ArrayStack<Integer> arrayStack = listaOrdenada.reverseToArrayStack();
        System.out.println("ArrayStack: " + arrayStack);
        System.out.println("Top: " + arrayStack.peek());

        // 3. Inverter para Queue
        System.out.println("\n3. INVERSÃO PARA QUEUE:");
        LinkedQueue<Integer> queue = listaOrdenada.reverseToQueue();
        System.out.println("LinkedQueue (ordem inversa): " + queue);
        System.out.println("Dequeue: " + queue.dequeue() + " → " + queue.dequeue());

        // 4. Inversão in-place
        System.out.println("\n4. INVERSÃO IN-PLACE:");
        System.out.println("Antes: " + listaOrdenada);
        listaOrdenada.reverseInPlace();
        System.out.println("Depois: " + listaOrdenada);

        // 5. Teste com lista vazia e um elemento
        System.out.println("\n5. CASOS ESPECIAIS:");
        DoubleLinkedOrderedList<String> listaVazia = new DoubleLinkedOrderedList<>();
        DoubleLinkedOrderedList<String> listaUmElemento = new DoubleLinkedOrderedList<>();
        listaUmElemento.add("Único");

        System.out.println("Lista vazia: " + listaVazia.reverseToStack().isEmpty());
        System.out.println("Lista com um elemento: " + listaUmElemento.reverseToStack());


        /**
         4. Suponha q apenas tem uma DoubleLinkedUnorderedList e q pretende desenvolver um programa q precisa obrigatoriamente de um simples array pra operar. Demonstre este simples cenário.
         */
        System.out.println("=== SISTEMA DE PROCESSAMENTO DE IMAGENS ===\n");

        // 1. Dados originais em DoubleLinkedUnorderedList (flexível para adições)
        System.out.println("1. DADOS ORIGINAIS NA LISTA:");
        DoubleLinkedUnorderedList<Integer> listaPixels = new DoubleLinkedUnorderedList<>();

        // Simular valores de pixels RGB
        listaPixels.addToFront(255);  // Pixel 1 - Vermelho
        listaPixels.addToRear(128);   // Pixel 2 - Verde
        listaPixels.addToRear(64);    // Pixel 3 - Azul
        listaPixels.addAfter(200, 255); // Pixel adicional
        listaPixels.addToRear(100);   // Pixel final

        System.out.println("Pixels na lista: " + listaPixels);
        System.out.println("Total de pixels: " + listaPixels.size());

        // 2. CONVERSÃO PARA ARRAY (obrigatório para certas operações)
        System.out.println("\n2. CONVERSÃO PARA ARRAY SIMPLES:");
        Object[] arrayPixels = listaPixels.toArray();

        System.out.println("Array criado: " + java.util.Arrays.toString(arrayPixels));
        System.out.println("Tipo do array: " + arrayPixels.getClass().getSimpleName());
        System.out.println("Tamanho do array: " + arrayPixels.length);

        // 3. OPERAÇÕES QUE EXIGEM ARRAY
        System.out.println("\n3. OPERAÇÕES EXCLUSIVAS DE ARRAY:");

        // A. Acesso aleatório por índice (O(1))
        System.out.println("A. ACESSO ALEATÓRIO:");
        System.out.println("Pixel na posição 2: " + arrayPixels[2]);
        System.out.println("Pixel na posição 0: " + arrayPixels[0]);

        // B. Ordenação nativa com Arrays.sort()
        System.out.println("\nB. ORDENAÇÃO NATIVA:");
        Integer[] pixelsOrdenados = Arrays.copyOf(arrayPixels, arrayPixels.length, Integer[].class);
        Arrays.sort(pixelsOrdenados);
        System.out.println("Pixels ordenados: " + Arrays.toString(pixelsOrdenados));

        // C. Busca binária (requer array ordenado)
        System.out.println("\nC. BUSCA BINÁRIA:");
        int posicao = Arrays.binarySearch(pixelsOrdenados, 128);
        System.out.println("Valor 128 encontrado na posição: " + posicao);

        // D. Processamento em lote com for-loop otimizado
        System.out.println("\nD. PROCESSAMENTO EM LOTE:");
        int soma = 0;
        for (int i = 0; i < arrayPixels.length; i++) {
            soma += (Integer) arrayPixels[i];
            System.out.println("Processando pixel " + i + ": " + arrayPixels[i]);
        }
        System.out.println("Soma total dos pixels: " + soma);

        // E. Operações matemáticas com arrays
        System.out.println("\nE. OPERAÇÕES MATEMÁTICAS:");
        double media = (double) soma / arrayPixels.length;
        System.out.println("Média dos pixels: " + media);

        // 4. CONVERSÃO DE VOLTA PARA LISTA (se necessário)
        System.out.println("\n4. CONVERSÃO DE VOLTA PARA LISTA:");
        DoubleLinkedUnorderedList<Integer> listaProcessada = new DoubleLinkedUnorderedList<>();
        for (Object pixel : arrayPixels)
            listaProcessada.addToRear((Integer) pixel);
        System.out.println("Lista reconstruída: " + listaProcessada);

        // 5. VANTAGENS COMPARATIVAS
        System.out.println("\n5. VANTAGENS DE CADA ESTRUTURA:");
        System.out.println("DoubleLinkedUnorderedList:");
        System.out.println("  ✅ Inserções O(1) no início/fim");
        System.out.println("  ✅ Flexível para modificações");
        System.out.println("  ❌ Acesso O(n) por posição");

        System.out.println("Array Simples:");
        System.out.println("  ✅ Acesso O(1) por índice");
        System.out.println("  ✅ Ordenação/busca nativa");
        System.out.println("  ✅ Cache-friendly");
        System.out.println("  ❌ Tamanho fixo");
        System.out.println("  ❌ Inserções O(n)");


        /**
         5. A fórmula 1 é a prova de automobilismo + conhecida e c + prestígio a nível mundial. O campeonato mundial é constituído por diversas provas, equipas e corredores. Cd um dos corredores mediante a classificação em cd uma das provas irá obter pontos q irão formar a tabela classificativa e no final apurar o vencedor. Pretende-se q crie um programa q faça a simulação de uma pole position.
         */
        System.out.println("=== 🏎️ SIMULAÇÃO POLE POSITION - FÓRMULA 1 ===\n");

        // 1. Criar lista de pilotos participantes
        System.out.println("1. 📋 LISTA DE PILOTOS INSCRITOS:");
        DoubleLinkedUnorderedList<Piloto> listaPilotos = new DoubleLinkedUnorderedList<>();

        // Adicionar pilotos (ordem de inscrição)
        listaPilotos.addToRear(new Piloto("Max Verstappen", "Red Bull Racing", 1));
        listaPilotos.addToRear(new Piloto("Lewis Hamilton", "Mercedes", 44));
        listaPilotos.addToRear(new Piloto("Charles Leclerc", "Ferrari", 16));
        listaPilotos.addToRear(new Piloto("Fernando Alonso", "Aston Martin", 14));
        listaPilotos.addToRear(new Piloto("Lando Norris", "McLaren", 4));
        listaPilotos.addToRear(new Piloto("Carlos Sainz", "Ferrari", 55));
        listaPilotos.addToRear(new Piloto("George Russell", "Mercedes", 63));
        listaPilotos.addToRear(new Piloto("Sergio Perez", "Red Bull Racing", 11));

        System.out.println("Pilotos inscritos (" + listaPilotos.size() + "):");
        int pos = 1;
        for (Piloto piloto : listaPilotos) {
            System.out.println("  " + pos + ". " + piloto);
            pos++;
        }

        // 2. Simular tempos de qualificação
        System.out.println("\n2. ⏱️  SIMULAÇÃO DE TEMPOS DE QUALIFICAÇÃO:");
        DoubleLinkedUnorderedList<VoltaQualificacao> voltas = simularQualificacao(listaPilotos);

        System.out.println("Melhores voltas de cada piloto:");
        for (VoltaQualificacao volta : voltas) {
            System.out.println("  " + volta);
        }

        // 3. Converter para array para ordenação
        System.out.println("\n3. 🏁 ORDENAÇÃO PARA POLE POSITION:");
        Object[] arrayVoltas = voltas.toArray();
        VoltaQualificacao[] voltasArray = Arrays.copyOf(arrayVoltas, arrayVoltas.length, VoltaQualificacao[].class);

        // Ordenar por tempo (menor tempo = melhor)
        Arrays.sort(voltasArray);

        System.out.println("Classificação final da Qualificação:");
        for (int i = 0; i < voltasArray.length; i++) {
            System.out.println("  P" + (i + 1) + " - " + voltasArray[i].getPiloto().getNome() +
                    " - " + voltasArray[i].getTempoFormatado());
        }

        // 4. Anunciar Pole Position
        System.out.println("\n4. 🏆 POLE POSITION!");
        VoltaQualificacao pole = voltasArray[0];
        System.out.println("🎉 " + pole.getPiloto().getNome().toUpperCase() +
                " conquista a POLE POSITION!");
        System.out.println("   Equipa: " + pole.getPiloto().getEquipa());
        System.out.println("   Tempo: " + pole.getTempoFormatado());
        System.out.println("   Número: " + pole.getPiloto().getNumero());

        // 5. Grid de largada
        System.out.println("\n5. 🚦 GRID DE LARGADA:");
        System.out.println("Pos | Piloto               | Equipa           | Tempo");
        System.out.println("----|----------------------|------------------|----------");
        for (int i = 0; i < Math.min(10, voltasArray.length); i++) {
            VoltaQualificacao volta = voltasArray[i];
            Piloto piloto = volta.getPiloto();
            System.out.printf("%-3d | %-20s | %-16s | %s\n",
                    i + 1, piloto.getNome(), piloto.getEquipa(), volta.getTempoFormatado());
        }

        // 6. Estatísticas
        System.out.println("\n6. 📊 ESTATÍSTICAS DA QUALIFICAÇÃO:");
        calcularEstatisticas(voltasArray);
    }

    /**
     * Simula os tempos de qualificação para todos os pilotos
     */
    private static DoubleLinkedUnorderedList<VoltaQualificacao> simularQualificacao(DoubleLinkedUnorderedList<Piloto> pilotos) {
        DoubleLinkedUnorderedList<VoltaQualificacao> voltas = new DoubleLinkedUnorderedList<>();
        Random random = new Random();

        for (Piloto piloto : pilotos) {
            // Simular 3 tentativas e ficar com a melhor
            double melhorTempo = Double.MAX_VALUE;

            for (int tentativa = 1; tentativa <= 3; tentativa++) {
                // Tempo base + variação aleatória + skill do piloto
                double tempoBase = 85.0; // 1:25.000
                double variacao = (random.nextDouble() - 0.5) * 2.0; // ±1 segundo
                double skill = (10 - piloto.getNumero() % 10) * 0.05; // pilotos com números menores = mais skill

                double tempoVolta = tempoBase + variacao - skill;
                melhorTempo = Math.min(melhorTempo, tempoVolta);
            }

            voltas.addToRear(new VoltaQualificacao(piloto, melhorTempo));
        }

        return voltas;
    }

    /**
     * Calcula estatísticas da qualificação
     */
    private static void calcularEstatisticas(VoltaQualificacao[] voltas) {
        if (voltas.length == 0) return;

        double melhorTempo = voltas[0].getTempo();
        double piorTempo = voltas[voltas.length - 1].getTempo();
        double soma = 0;

        for (VoltaQualificacao volta : voltas)
            soma += volta.getTempo();

        double media = soma / voltas.length;
        double diferenca = piorTempo - melhorTempo;

        System.out.printf("Melhor tempo: %s\n", voltas[0].getTempoFormatado());
        System.out.printf("Pior tempo: %s\n", voltas[voltas.length - 1].getTempoFormatado());
        System.out.printf("Tempo médio: %.3f segundos\n", media);
        System.out.printf("Diferença pole/last: %.3f segundos\n", diferenca);
        System.out.printf("Número de pilotos: %d\n", voltas.length);

        // Análise por equipa
        System.out.println("\n🏁 Análise por equipa:");
        Map<String, Integer> polesPorEquipa = new HashMap<>();
        for (int i = 0; i < Math.min(3, voltas.length); i++) {
            String equipa = voltas[i].getPiloto().getEquipa();
            polesPorEquipa.put(equipa, polesPorEquipa.getOrDefault(equipa, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : polesPorEquipa.entrySet())
            System.out.printf("  %s: %d pilotos no top-3\n", entry.getKey(), entry.getValue());
    }
}
