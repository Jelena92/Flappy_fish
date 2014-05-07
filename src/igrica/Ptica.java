package igrica;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
/**
 *
 * @author Administrator
 */
public class Ptica extends Rectangle.Double implements GameObjects{

    private final int w = 40;
    private final int h = 20;
       
    private  Board board;
    private final Color fillColor = Color.RED;
    private final Color borderColor = Color.BLACK;
    
    
   // Predstavljaju intenzitet brzine po x i po y koordinati
    private int dx;
    private int dy;
    
    // Predstavljaju smjer brzine po x i po y koordinati
    private int directionX;
    private int directionY;
    
    private Ellipse2D.Double ellipseForDrawing;
    
    
    public Ptica (Board board) {
        this.board = board;
        width = w;
        height = h;
         directionX = 1;
        directionY = 1;
    }
    
    @Override
    public void draw(Graphics2D g2) {
        ellipseForDrawing = new Ellipse2D.Double(80, 200, w, h);
        
        Ellipse2D.Double rex = new Ellipse2D.Double(100, 200,10, 10);
        
        g2.setPaint(fillColor);
        g2.fill(ellipseForDrawing);
        
        g2.setPaint(borderColor);
        g2.draw(ellipseForDrawing);
        
        g2.fill(rex);
        g2.draw(rex);
    }
    
    /**
     * Mijenja smijer lopte po y osi.
     */
    public void bouceVertical() {
        directionY = -directionY;
    }

   /**
     * Vrši pomjeranje lopte.
     */
    @Override
    public void move() {
       
    }
    
    

}
