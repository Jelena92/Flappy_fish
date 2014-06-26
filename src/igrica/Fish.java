package igrica;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public class Fish {

    int x;
    private int y;
    private final String message = "";

    private final int w = 50;
    private final int h = 50;
    private final int width;
    private final int height;
    private double dy = 2;
    private final double g = 1;
    private final Image image_one, image_two;
    Board board;
    private int imageState;

    public Fish(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;

        //Postavljanje dvije slike da bi se mogla napraviti animacija
        image_one = new ImageIcon(getClass().getResource("riba.png")).getImage();
        image_two = new ImageIcon(getClass().getResource("riba1.png")).getImage();
        //Stanje slike postavljeno na nula
        imageState = 0;

    }

    /**
     * Meotoda koja sluzi za crtanje animacije Kada se riba krece, i njen rep se
     * krece. To se postize naizmjenicnim iscrtavanjem dvije slike
     *
     * @param g2
     */
    public void draw(Graphics2D g2) {
        /*
         Ako je stanje slik jednako 0, iscrtava se prva slika, u suprotnom
         iscrtava se druga slika
         */
        if (imageState == 0) {
            g2.drawImage(image_one, x, y, width, height, null);
        } else if (imageState == 1) {
            g2.drawImage(image_two, x, y, width, height, null);
        }
        //mjenjanje stanja slika 
        imageState = (imageState + 1) % 2;

    }

    /**
     * Metoda koja vraća pravugaonik koji predstavlja ribu
     *
     * @return
     */
    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x, y + 10, w, h - 20);
    }

    /**
     * Metoda koja se poziva prilikom mijenjanja brzine ribe
     */
    public void moveUp() {
        dy = -13;
    }

    /**
     * Metoda koja vrši pomjeranje ribe. Ako prilikom kretanja riba udari o dno
     * ili vrh prozora, igra se zaustavlja
     */
    public void move() {
        if ((y > 0) && (y + w < board.PANEL_HEIGHT)) {
            //ubrzanje kretanja ribe
            dy += g;
            y += dy;

        } else {

            board.stopGame();

        }
    }

}
