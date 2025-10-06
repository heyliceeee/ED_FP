package FP01;

public class CD {
    private String titulo;

    public CD(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "CD: " + titulo;
    }
}
