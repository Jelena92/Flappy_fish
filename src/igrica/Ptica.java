package igrica;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
/**
 *
 * @author Administrator
 */
public class Ptica extends Rectangle.Double implements GameObjects{

    private final int w = 30;
    private final int h = 30;
       
    private  Board board;
    private final Color fillColor = Color.RED;
    private final Color borderColor = Color.BLACK;
    
    
   // Predstavljaju intenzitet brzine po x i po y koordinati
    private int dx;
    private int dy;
    
    // Predstavljaju smjer brzine po x i po y koordinati
    private int directionX;
    private int directionY;
    

    private Rectangle.Double rectangleForDrawing;
    
    public Ptica (Board board) {
        this.board = board;
        reset();
        width = w;
        height = h;
         directionX = 1;
        directionY = 1;
    }
    
    @Override
    public void draw(Graphics2D g2) {
        
        rectangleForDrawing = new Rectangle2D.Double(x, y, w, h);        
       
        g2.setPaint(fillColor);
        g2.fill(rectangleForDrawing);
        
        g2.setPaint(borderColor);
        g2.draw(rectangleForDrawing);
        
        
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

    private void reset() {
        x = board.PANEL_WIDTH/6 + w/2;
        y = board.PANEL_HEIGHT/2 - h/2;
    }
    
    

}
