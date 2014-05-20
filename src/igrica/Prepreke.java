package igrica;

/**
 *
 * @author Administrator
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import static javax.swing.SpringLayout.WIDTH;


public class Prepreke extends Rectangle.Double implements GameObjects {
    
    /**
     * Širina 
     */
    public static final int w = 40;
    /**
     * Visina 
     */
    public static final int h = 135;
    public int dist=80;
    private int dx = 10;
    
    private Board board;
    private Color fillColor = Color.BLUE;
    
        public Prepreke(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        }

        
    @Override
    public void move() {
       if(x<800)
               x -=dx;		
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setPaint(fillColor);
        g2.fill(this);
    }
}
