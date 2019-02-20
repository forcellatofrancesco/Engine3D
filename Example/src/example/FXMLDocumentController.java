package example;

import engine3d.Engine;
import engine3d.Solid;
import static engine3d.Solid.makeCube;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.MotionBlur;

/**
 *
 * @author Francesco Forcellato
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Canvas canvas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Engine en = new Engine(canvas.getHeight(), canvas.getWidth(), gc) {

            @Override
            public void beginPath(Object drawer) {
                GraphicsContext gr = (GraphicsContext) drawer;
                gr.beginPath();
            }

            @Override
            public void closePath(Object drawer) {
                GraphicsContext gr = (GraphicsContext) drawer;
                gr.stroke();
                gr.closePath();
            }

            @Override
            public void drawLine(double x0, double y0, double x1, double y1, Object drawer) {
                GraphicsContext gr = (GraphicsContext) drawer;
                gr.moveTo(x0, y0);
                gr.lineTo(x1, y1);
            }
        };
        gc.setLineWidth(2);
        MotionBlur mb = new MotionBlur();
        mb.setRadius(2f);

        double size = 100;

        Solid x = new Solid();
        x.addNode(0, 0, size);
        x.addNode(size, -size, -size);
        x.addNode(-size, -size, -size);
        x.addNode(-size, size, -size);
        x.addNode(size, size, -size);
        x.addNode(0, 0, -2 * size);

        x.addConnection(0, 1);
        x.addConnection(0, 2);
        x.addConnection(0, 3);
        x.addConnection(0, 4);

        x.addConnection(5, 1);
        x.addConnection(5, 2);
        x.addConnection(5, 3);
        x.addConnection(5, 4);

        x.addConnection(1, 2);
        x.addConnection(1, 4);
        x.addConnection(2, 3);
        x.addConnection(3, 4);

        Solid c = makeCube(200);
        AnimationTimer at = new AnimationTimer() {
            double angle = 0.001;

            @Override
            public void handle(long now) {
                gc.setEffect(null);
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                Engine.rotateX(x, angle);
                Engine.rotateY(x, -angle);
                Engine.rotateZ(x, angle * 2.0);
                Engine.rotateX(c, -angle);
                Engine.rotateY(c, angle);
                Engine.rotateZ(c, -angle * 2.0);
                mb.setAngle(-angle);
                gc.setEffect(mb);
                en.drawSolid(x);
                en.drawSolid(c);
            }
        };
        at.start();
    }    
    
}
