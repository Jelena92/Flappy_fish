package igrica;

import static igrica.Board.Poruka;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author Administrator
 */
public class Riba {

    private int x;
    private int y;

    private final int w = 40;
    private final int h = 40;
    private int width;
    private int height;
    private double dy = 2;
    private final double g = 1;
    private Image image;
    Board board;

    public Riba(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;

        image = new ImageIcon(getClass().getResource("riba.png")).getImage();
    }

    public void draw(Graphics2D g2) {

        g2.drawImage(image, x, y, width, height, null);
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, w, h);
    }

    public void moveUp() {
        dy = -13;
    }

    /**
     * Vrši pomjeranje ptice
     */
    public void move() {
        if ((y > 0) && (y + w < board.PANEL_HEIGHT)) {
            dy += g;
            y += dy;
        } else {

            reset();
        }

    }

    public void reset() {
        x = board.PANEL_WIDTH / 4;
        y = board.PANEL_HEIGHT / 3;
        dy = 2;

        Poruka = " Pritisni SPACE za skakanaje";
        Timer deathTimer = new Timer(3000, new ActionListener() {

            public void actionPerformed(ActionEvent event) {

                Poruka = "";
            }
        ;
        });
        deathTimer.start();
    }
}
