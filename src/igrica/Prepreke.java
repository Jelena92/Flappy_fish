package igrica;

/**
 *
 * @author Administrator
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Prepreke extends Rectangle.Double implements GameObjects {
    /**
     * Širina 
     */
    public static final int w = 100;
    /**
     * Visina reketa
     */
    public static final int h = 20;
    
    private int dx = 10;
    
    private Board board;

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics2D g2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
