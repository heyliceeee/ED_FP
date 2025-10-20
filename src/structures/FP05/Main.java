package structures.FP05;

import exceptions.ElementNotFoundException;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        /**
         # Parte I
         1. Implementa uma ArrayOrderedList. Lembre-se q terá de implementar a interface adequada.
         * Preste atenção aos slides da aula teórica principalmente o 15, e veja o diagrama de interfaces. Qts classes serão necessárias pra implementar o diagrama q lá está presente?
         // R:
         // 3 interfaces (ListADT, OrderedListADT, UnorderedListADT) + 1 classe de implementacao = 4 elems
         *
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
        DoublyLinkedOrderedList<Integer> lista = new DoublyLinkedOrderedList<>();

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



        /**
         # Parte II
         1. Pq q n existe uma método add(Element) na interface ListADT?
         */



        /**
         2. Dada a versatilidade de uma list pq q n é smp a escolhida em vez de uma Stack ou de uma Queue?
         */



        /**
         3. Cria um método na DoubleLinkedOrderedList q inverta e devolva tds os elems no tp de dados abstrato q entender.
         */



        /**
         4. Suponha q apenas tem uma DoubleLinkedUnorderedList e q pretende desenvolver um programa q precisa obrigatoriamente de um simples array pra operar. Demonstre este simples cenário.
         */



        /**
         5. A fórmula 1 é a prova de automobilismo + conhecida e c + prestígio a nível mundial. O campeonato mundial é constituído por diversas provas, equipas e corredores. Cd um dos corredores mediante a classificação em cd uma das provas irá obter pontos q irão formar a tabela classificativa e no final apurar o vencedor. Pretende-se q crie um programa q faça a simulação de uma pole position.
         */

    }
}
