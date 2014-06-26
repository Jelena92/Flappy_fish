package igrica;

/**
 *
 * @author Administrator
 */
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.ImageIcon;

public class Obstacle implements GameObjects {

    Random rnd = new Random();

    private int x;
    private final int y;
    private final int space = 150;
    private final int width = 55;
    public int speed = 6;
    private final ImageIcon imageDown = new ImageIcon(getClass().getResource("prepreke1.png"));
    private final ImageIcon imageUp = new ImageIcon(getClass().getResource("prepreke2.png"));
    private final Board board;

    private Rectangle2D.Double gornji, donji;

    public Obstacle(Board board) {
        this.board = board;
        this.x = board.PANEL_WIDTH;
        this.y = rnd.nextInt(board.PANEL_HEIGHT - 300) + 150;
    }

    /**
     * Metoda cijim pozivom se prepreke pomjeraju u lijevo, tj. vrijednost x se
     * smanjuje za speed
     */
    @Override
    public void move() {
        x -= speed;
    }

    /**
     * Metoda koja služi za iscrtavanje slika prepreka, tj. slike za gornju i
     * donju prepreku Ova metoda se poziva u klasi board
     *
     * @param g2
     */
    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(imageUp.getImage(), x, 0, width, y, null);
        g2.drawImage(imageDown.getImage(), x, y + space, width, board.PANEL_HEIGHT - y - space, null);
    }

    /**
     * Metoda u kojoj ispitujemo da li je riba udarila u prepreke, tj.da li je
     * došlo do preklapanja ribe i prepreka. Posmatraju se dva zasebna
     * pravougaonika koja imaju iste dimenzije i pozicije kao i prepreke
     *
     * @param riba
     * @return
     */
    public boolean intersectsWithFish(Fish riba) {
        gornji = new Rectangle2D.Double(x, 0, width, y);
        donji = new Rectangle2D.Double(x, y + space, width, board.PANEL_HEIGHT - y - space);
        Rectangle2D.Double ribaBounds = riba.getBounds();

        return ribaBounds.intersects(gornji) || ribaBounds.intersects(donji);
    }

    /**
     * Metoda koja vraća vrijednost x datih prepreka
     *
     * @return
     */
    public int valueX() {
        return x;
    }
}
