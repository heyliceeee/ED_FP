package structures.FP05;

public class Piloto {
    private String nome;
    private String equipa;
    private int numero;

    public Piloto(String nome, String equipa, int numero) {
        this.nome = nome;
        this.equipa = equipa;
        this.numero = numero;
    }

    // Getters
    public String getNome() { return nome; }
    public String getEquipa() { return equipa; }
    public int getNumero() { return numero; }

    @Override
    public String toString() {
        return String.format("%s (#%d) - %s", nome, numero, equipa);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Piloto piloto = (Piloto) obj;
        return numero == piloto.numero && nome.equals(piloto.nome);
    }
}
