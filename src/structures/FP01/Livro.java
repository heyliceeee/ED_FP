package structures.FP01;

public class Livro {
    private String titulo;

    public Livro(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo;
    }
}
