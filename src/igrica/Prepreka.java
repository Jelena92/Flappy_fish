package igrica;
/**
 *
 * @author Administrator
 */
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.ImageIcon;

public class Prepreka implements GameObjects {
    
    Random rnd = new Random();
    
    private int x;
    private final int y;
    private final int razmak = 150;
    private final int sirina = 55;
    public int brzina = 6;
    private final ImageIcon imageDown = new ImageIcon(getClass().getResource("prepreke1.png"));
    private final ImageIcon imageUp = new ImageIcon(getClass().getResource("prepreke2.png"));
    private final Board board;
    
    private Rectangle2D.Double gornji, donji;
    
    public Prepreka(Board board) {
        this.board = board;
        this.x = board.PANEL_WIDTH;
        this.y = rnd.nextInt(board.PANEL_HEIGHT-400)+200;
    }
    
 
    @Override
    public void move() {
        x -= brzina;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(imageUp.getImage(), x, 0, sirina, y, null);
        g2.drawImage(imageDown.getImage(), x, y + razmak, sirina, board.PANEL_HEIGHT - y - razmak, null);
    }
    
    public boolean intersectsWithFish(Riba riba) {
        gornji = new Rectangle2D.Double(x, 0, sirina, y);
        donji = new Rectangle2D.Double(x, y + razmak, sirina, board.PANEL_HEIGHT - y - razmak);
        
        Rectangle2D.Double ribaBounds = riba.getBounds();
        
        return ribaBounds.intersects(gornji) || ribaBounds.intersects(donji);
    }
    
    public int vrijednostx(){
            return x;
    }
}
