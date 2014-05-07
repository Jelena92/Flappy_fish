package igrica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
/**
 *
 * @author Administrator
 */
public class Board extends JPanel implements Runnable {
    /**
     * Širina table
     */
     /**
     * Širina table
     */
    public final int PANEL_WIDTH = 350;
    /**
     * Visina table
     */
    public final int PANEL_HEIGHT = 450;
    
    final Color BACKGROUND_COLOR = Color.CYAN;
    final Thread runner;
    
      Boolean inGame;
      
    // Objekti u igri
      Ptica ball;
   
    String message;
    
    /**
     * Podrazumjevani konstruktor. Postavlja veličinu table, boju pozadine i font,
     * inicijalizuje početni rezultat, te objekte u igri. Inicijalizuje i pokreće
     * radnu nit.
     */
    public Board() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
        setFont(getFont().deriveFont(Font.BOLD, 18f));
        setDoubleBuffered(true);
        
        inGame = false;
        message = "Flappy Bird";
        
        ball = new Ptica(this);
                
        runner = new Thread(this);
        runner.start();
    }
    
  
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
             
         Graphics2D g2 = (Graphics2D) g;
        
        if (inGame) {
            // Savjeti pri iscrtavanju
        
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // Iscrtaj teren

            g2.drawRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
          

            // Iscrtaj sve objekte

            ball.draw(g2);
                           

            // Sinhronizovanje sa grafičkom kartom
            Toolkit.getDefaultToolkit().sync();

            // Optimizacija upotrebe RAM-a, 
            g.dispose();
        } else {
            int messageWidth = getFontMetrics(getFont()).stringWidth(message);
            g2.drawString(message, PANEL_WIDTH/2 - messageWidth/2, PANEL_HEIGHT/2);
        }
    }

 private void update() {
        ball.move();
        
    }

    @Override
    public void run() {
               
        while(true) {
            update();
            repaint();
            
             try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    void startGame() {
       inGame = true;
    }
    
 
}
