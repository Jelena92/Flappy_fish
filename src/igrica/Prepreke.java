package igrica;

/**
 *
 * @author Administrator
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Prepreke extends Rectangle.Double implements GameObjects {
    
     enum MovingState { MOVING_LEFT }
    /**
     * Širina 
     */
    public static final int w = 40;
    /**
     * Visina reketa
     */
    public static final int h = 180;
    
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setPaint(fillColor);
        g2.fill(this);
    }
}
