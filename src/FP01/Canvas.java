package FP01;

import java.util.List;

public class Canvas {

    public void draw(Shape shape) {
    }

    void drawAll(List<? extends Shape> shapes) {
        for (Shape s : shapes) {
            s.draw(this); // desenha no Canvas
        }
    }
}
