package structures.FP01;

public class Video {
    private String titulo;

    public Video(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Vídeo: " + titulo;
    }
}
