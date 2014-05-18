   package igrica;

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
public class Riba  {

    private int x;
    private int y;
    
    private final int w = 40;
    private final int h = 40;
    private int width;
    private int height;
    private final double g=2;
    private Image image;
    Board board;
    
  
    
    
   // Predstavljaju intenzitet brzine po x i po y koordinata
    private int dy=8;
    
      
    enum MovingState { STANDING, MOVING_UP}
    private MovingState state;
    
    private Rectangle.Double rectangleForDrawing;
    

     public Riba(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.state = MovingState.STANDING;
        
        image = new ImageIcon(getClass().getResource("riba.png")).getImage();
    }
     
      /**
     * Postavlja stanje kretanja ka gore.
     */
    public void moveUp() {
        state = MovingState.MOVING_UP;
        
    }
    
    /**
     * Postavlja stanje kretanja ptice u stanje mirovabnja.
     */
    public void stopMoving() {
        state = MovingState.STANDING;
    }
    
    
        public void draw(Graphics2D g2) {
       
         g2.drawImage(image, x, y, width, height, null);
    }
        
        public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, w, h);
    }
  
   /**
     * Vrši pomjeranje ptice
     */
    
        public void move() {
        if( (y > 0) && ( y + w < 350) )
        {
            y+=g;
        }
        else{
            reset();
            board.inGame=true;
        }
            if (state == MovingState.MOVING_UP)
             y-=dy;
            
           
    }
        
        public void reset(){
           y+=g;
            board.deathMessage = "Pritisni strjelicu ka goe";
            
        }
        
}
