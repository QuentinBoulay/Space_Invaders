package projetSpace.ex2;

import com.jogamp.opengl.GL2;

public class Projectile extends GraphicalObject {
    // Vitesse du projectile
    private float speed;

    public Projectile(float x, float y, float z, float speed) {
        super(x, y, z, 0, 0, 0, 2f, 1, 0, 0);
        this.speed = speed;
    }

    @Override
    public void display_normalized(GL2 gl) {
        gl.glBegin(GL2.GL_QUADS); // Début du dessin du cube

        // Front Face
        gl.glColor3f(1.0f, 0.0f, 0.0f); // Rouge
        gl.glVertex3f(-0.1f, -0.1f,  0.1f);
        gl.glVertex3f( 0.1f, -0.1f,  0.1f);
        gl.glVertex3f( 0.1f,  0.1f,  0.1f);
        gl.glVertex3f(-0.1f,  0.1f,  0.1f);

        // Back Face
        gl.glVertex3f(-0.1f, -0.1f, -0.1f);
        gl.glVertex3f(-0.1f,  0.1f, -0.1f);
        gl.glVertex3f( 0.1f,  0.1f, -0.1f);
        gl.glVertex3f( 0.1f, -0.1f, -0.1f);

        // Top Face
        gl.glVertex3f(-0.1f,  0.1f, -0.1f);
        gl.glVertex3f(-0.1f,  0.1f,  0.1f);
        gl.glVertex3f( 0.1f,  0.1f,  0.1f);
        gl.glVertex3f( 0.1f,  0.1f, -0.1f);

        // Bottom Face
        gl.glVertex3f(-0.1f, -0.1f, -0.1f);
        gl.glVertex3f( 0.1f, -0.1f, -0.1f);
        gl.glVertex3f( 0.1f, -0.1f,  0.1f);
        gl.glVertex3f(-0.1f, -0.1f,  0.1f);

        // Right face
        gl.glVertex3f( 0.1f, -0.1f, -0.1f);
        gl.glVertex3f( 0.1f,  0.1f, -0.1f);
        gl.glVertex3f( 0.1f,  0.1f,  0.1f);
        gl.glVertex3f( 0.1f, -0.1f,  0.1f);

        // Left Face
        gl.glVertex3f(-0.1f, -0.1f, -0.1f);
        gl.glVertex3f(-0.1f, -0.1f,  0.1f);
        gl.glVertex3f(-0.1f,  0.1f,  0.1f);
        gl.glVertex3f(-0.1f,  0.1f, -0.1f);

        gl.glEnd(); // Fin du dessin du cube
    }

    public void move() {
        this.posY += speed; // Déplace le projectile vers l'avant
    }

}