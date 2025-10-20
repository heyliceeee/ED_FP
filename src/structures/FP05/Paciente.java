package structures.FP05;

public class Paciente implements Comparable<Paciente> {
    private String nome;
    private String sintomas;
    private int prioridade; // 1 = crítico, 5 = não urgente

    public Paciente(String nome, String sintomas, int prioridade) {
        this.nome = nome;
        this.sintomas = sintomas;
        this.prioridade = prioridade;
    }

    @Override
    public int compareTo(Paciente outro) {
        // Ordena por prioridade (menor número = maior urgência)
        return Integer.compare(this.prioridade, outro.prioridade);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Paciente paciente = (Paciente) obj;
        return prioridade == paciente.prioridade && nome.equals(paciente.nome) && sintomas.equals(paciente.sintomas);
    }

    @Override
    public int hashCode() {
        return nome.hashCode() + sintomas.hashCode() + prioridade;
    }

    @Override
    public String toString() {
        String nivelUrgencia;
        switch (prioridade) {
            case 1: nivelUrgencia = "CRÍTICO"; break;
            case 2: nivelUrgencia = "URGENTE"; break;
            case 3: nivelUrgencia = "ALTA"; break;
            case 4: nivelUrgencia = "MÉDIA"; break;
            case 5: nivelUrgencia = "BAIXA"; break;
            default: nivelUrgencia = "INDEFINIDA";
        }
        return String.format("Paciente{%s, %s, %s}", nome, sintomas, nivelUrgencia);
    }

    // Getters
    public String getNome() { return nome; }
    public String getSintomas() { return sintomas; }
    public int getPrioridade() { return prioridade; }
}
