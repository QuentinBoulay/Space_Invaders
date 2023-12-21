package projetSpace.ex2;

import com.jogamp.opengl.GL2;
import java.util.ArrayList;

public class Cube extends GraphicalObject {

    private ArrayList<Square> faces;

    private final float leftBound = -20.0f; // Limite gauche
    private final float rightBound = 20.0f; // limite droite

    public Cube(float pX, float pY, float pZ, float scale, float r, float g, float b) {
        super(pX, pY, pZ, 0, 0, 0, scale, r, g, b); // angX, angY, angZ initialisés à 0
        faces = new ArrayList<Square>();

        // Front face
        faces.add(new Square(0, 0, 1, 0, 0, 0, 1, 0.8f, 0.2f, 0.2f));
        // Back face
        faces.add(new Square(0, 0, -1, 0, 0, 0, 1, 0.2f, 0.4f, 0.6f));
        // Right face
        faces.add(new Square(1, 0, 0, 0, 90, 0, 1, 0.1f, 0.8f, 0.2f));
        // Left face
        faces.add(new Square(-1, 0, 0, 0, -90, 0, 1, 0.6f, 0.5f, 0.2f));
        // Top face
        faces.add(new Square(0, 1, 0, 90, 0, 0, 1, 0.3f, 0.2f, 0.8f));
        // Bottom face
        faces.add(new Square(0, -1, 0, -90, 0, 0, 1, 0.3f, 0.8f, 0.7f));
    }

    public void display_normalized(GL2 gl) {
        for (Square face : faces)
            face.display(gl);
    }

    // Méthode pour déplacer le cube horizontalement
    public void move(float dx) {
        this.posX += dx;
        // Vérifie si le cube dépasse la limite gauche
        if (this.posX < leftBound) {
            this.posX = leftBound;
        }
        // Vérifie si le cube dépasse la limite droite
        else if (this.posX > rightBound) {
            this.posX = rightBound;
        }
    }

}
