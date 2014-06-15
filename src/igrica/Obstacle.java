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
        this.y = rnd.nextInt(board.PANEL_HEIGHT-300)+150;
    }
    
 
    @Override
    public void move() {
        x -= speed;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(imageUp.getImage(), x, 0, width, y, null);
        g2.drawImage(imageDown.getImage(), x, y + space, width, board.PANEL_HEIGHT - y - space, null);
    }
    
    public boolean intersectsWithFish(Fish riba) {
        gornji = new Rectangle2D.Double(x, 0, width, y);
        donji = new Rectangle2D.Double(x, y + space, width, board.PANEL_HEIGHT - y - space);
        
        Rectangle2D.Double ribaBounds = riba.getBounds();
        
        return ribaBounds.intersects(gornji) || ribaBounds.intersects(donji);
    }
    
    public int valueX(){
            return x;
    }
}
