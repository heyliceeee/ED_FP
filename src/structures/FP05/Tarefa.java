package structures.FP05;

public class Tarefa implements Comparable<Tarefa> {
    private String descricao;
    private int prioridade; // 1 = alta, 5 = baixa
    private String dataLimite;

    public Tarefa(String descricao, int prioridade, String dataLimite) {
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * @param outra the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     * @apiNote It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     */
    @Override
    public int compareTo(Tarefa outra) {
        return Integer.compare(this.prioridade, outra.prioridade);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tarefa tarefa = (Tarefa) obj;
        return prioridade == tarefa.prioridade &&
                descricao.equals(tarefa.descricao) &&
                dataLimite.equals(tarefa.dataLimite);
    }

    @Override
    public int hashCode() {
        return descricao.hashCode() + prioridade + dataLimite.hashCode();
    }

    @Override
    public String toString() {
        String nivelPrioridade;
        switch (prioridade) {
            case 1: nivelPrioridade = "ALTA"; break;
            case 2: nivelPrioridade = "MÉDIA-ALTA"; break;
            case 3: nivelPrioridade = "MÉDIA"; break;
            case 4: nivelPrioridade = "BAIXA"; break;
            case 5: nivelPrioridade = "MUITO BAIXA"; break;
            default: nivelPrioridade = "INDEFINIDA";
        }
        return String.format("Tarefa{%s, Prioridade=%s, Data=%s}",
                descricao, nivelPrioridade, dataLimite);
    }

    // Getters
    public String getDescricao() { return descricao; }
    public int getPrioridade() { return prioridade; }
    public String getDataLimite() { return dataLimite; }
}
