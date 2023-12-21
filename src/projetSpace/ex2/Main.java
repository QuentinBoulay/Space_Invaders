package projetSpace.ex2;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;

public class Main extends GLCanvas implements GLEventListener, KeyListener {
    private Cube bigCube;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Cube> enemyCubes = new ArrayList<>();

    public static void main(String[] args) {
        GLCanvas canvas = new Main();
        canvas.setPreferredSize(new Dimension(800, 600));
        final JFrame frame = new JFrame("OpenGL Space Invaders");
        frame.getContentPane().add(canvas);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        Animator animator = new Animator(canvas);
        animator.start();
    }

    public Main() {
        this.addGLEventListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // BUTTON3 est généralement le bouton gauche
                    shootProjectile();
                }
            }
        });

        // Initialisation du bigCube
        this.bigCube = new Cube(0, -15, -50, 2, 1, 0, 0);
        initEnemyCubes();
    }

    private void initEnemyCubes() {
        float startX = -17; // Position de départ X
        float gap = 5; // Espace entre chaque cube
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 3; j++) {
                Cube enemyCube = new Cube(startX + i * gap, 10 + j * gap, -50, 2, 1, 0, 0);
                enemyCubes.add(enemyCube);
            }
        }
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        // Affichage du bigCube
        bigCube.display(gl);

        for (Projectile projectile : projectiles) {
            projectile.move();
            projectile.display(gl);
        }

        // Mise à jour et affichage des cubes ennemis
        updateEnemyCubes();
        checkCollisions();

        for (Cube enemyCube : enemyCubes) {
            enemyCube.display(gl);
        }
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU glu = new GLU();
        glu.gluPerspective(45.0, (float)width/height, 0.1, 100.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    private void updateEnemyCubes() {
        float movementSpeed = -0.01f; // Vitesse de déplacement vers le bas, ajustez selon vos besoins

        for (Cube enemyCube : enemyCubes) {
            enemyCube.translate(0, movementSpeed, 0); // Déplace chaque cube ennemi vers le bas

            if(enemyCube.posY < -15) {
                JOptionPane.showMessageDialog(this, "Game Over");
                System.exit(0);
            }
        }

        shootEnemyProjectile();

        for(Cube enemyCube : enemyCubes) {
            if(distance(enemyCube, bigCube)) {
                JOptionPane.showMessageDialog(this, "Game Over");
                System.exit(0);
            }
        }
    }

    // Implémentation des méthodes de KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            bigCube.move(-0.3f); // Déplacer vers la gauche
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            bigCube.move(0.3f); // Déplacer vers la droite
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void shootProjectile() {
        Projectile newProjectile = new Projectile(bigCube.posX, bigCube.posY+5.0f, bigCube.posZ, 0.5f);
        projectiles.add(newProjectile);

        ArrayList<Cube> enemiesRemove = new ArrayList<>();
        ArrayList<Projectile> projectilesRemove = new ArrayList<>();

        for (Projectile projectile : projectiles) {
            for (Cube enemyCube : enemyCubes) {
                if (distance(projectile, enemyCube)) {
                    enemiesRemove.add(enemyCube);
                    projectilesRemove.add(projectile);
                }
            }
        }
        enemyCubes.removeAll(enemiesRemove);
        projectiles.removeAll(projectilesRemove);

        if(enemyCubes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bravo, vous avez gagné");
            System.exit(0);
        }
    }

    private void shootEnemyProjectile() {
        for (Cube enemyCube : enemyCubes) {
            if (Math.random() < 0.001) {
                Projectile enemyProjectile = new Projectile(enemyCube.posX, enemyCube.posY, enemyCube.posZ, -0.05f);
                projectiles.add(enemyProjectile);
            }
        }
    }

    private boolean distance(GraphicalObject projectile, Cube enemyCube) {
        return Math.pow(projectile.posX - enemyCube.posX, 2) < 10 &&
                Math.pow(projectile.posY - enemyCube.posY, 2) < 10;
    }

    private void checkCollisions() {
        for (Projectile projectile : projectiles) {
            if (distance(projectile, bigCube)) {
                JOptionPane.showMessageDialog(this, "Game Over - Vous avez été touché!");
                System.exit(0);
            }
        }
    }
}
