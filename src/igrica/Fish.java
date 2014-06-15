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
        imageState=0;
        
    }

    public void draw(Graphics2D g2) {
         if(imageState == 0)
            g2.drawImage(image_one, x, y, width, height, null);
        else if (imageState == 1)
            g2.drawImage(image_two, x, y, width, height, null);
        
        imageState = (imageState + 1) % 2;
        
    }

    public Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(x, y, w, h);
    }

    public void moveUp() {
        dy = -13;
    }

    /**
     * Vrši pomjeranje ribe
     */
    public void move() {
        if ((y > 0) && (y + w < board.PANEL_HEIGHT)) {
           dy += g;
            y += dy;
            
        } else {
            
            board.stopGame();
            
        }
    }

}
