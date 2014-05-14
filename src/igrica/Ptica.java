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
   private final int g=2;
       
    private  Board board;
    private final Color fillColor = Color.RED;
    private final Color borderColor = Color.BLACK;
    
    
   // Predstavljaju intenzitet brzine po x i po y koordinati
    private int dx=1;
    private int dy=6;
    
    // Predstavljaju smjer brzine  po y koordinati
    private int directionX;
    private int directionY;
    
    enum MovingState { STANDING, MOVING_UP, MOVING_DOWN}
    private MovingState state;
    
    private Rectangle.Double rectangleForDrawing;
    
    public Ptica (Board board) {
        this.board = board;
        reset();
        width = w;
        height = h;
        directionX=1;
        directionY = 1;
    }
    
     public Ptica(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.state = MovingState.STANDING;
    }
     
      /**
     * Postavlja stanje kretanja ka gore.
     */
    public void moveUp() {
        state = MovingState.MOVING_UP;
        
    }
    
    /**
     * Postavlja stanje kretanja reketa u stajanje.
     */
    public void stopMoving() {
        state = MovingState.STANDING;
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
     * Vrši pomjeranje ptice
     */
    @Override
    public void move() {
        y+=g;
        
        if (state == MovingState.MOVING_UP)
            y-=dy;
        
        if(y+w >board.PANEL_HEIGHT)
           reset();
        
  
    }

    private void reset() {
        x = board.PANEL_WIDTH/6 + w/2;
        y = board.PANEL_HEIGHT/2 - h/2;
      
       
    }
    
    

}
