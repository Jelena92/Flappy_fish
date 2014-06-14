package igrica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author Administrator
 */
public class Board extends JPanel implements Runnable {

    /**
     * Širina table
     */
    public final int PANEL_WIDTH = 600;
    /**
     * Visina table
     */
    public final int PANEL_HEIGHT = 600;

    final Color BACKGROUND_COLOR = Color.CYAN;
    final Thread runner;
    static String Poruka = "";
    private Image image;
    Boolean inGame;
    Boolean paused;
    static int score =0;
    
    long frames;

    // Objekti u igri
    Riba riba;
    ArrayList<Prepreka> prepreke;

    /**
     * Podrazumjevani konstruktor. Postavlja veličinu table, boju pozadine i
     * font, inicijalizuje početni rezultat, te objekte u igri. Inicijalizuje i
     * pokreće radnu nit.
     */
    public Board() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
        setFont(getFont().deriveFont(Font.BOLD, 18f));
        setDoubleBuffered(true);

        inGame = false;
        paused = true;

        riba = new Riba(this, PANEL_WIDTH / 4, PANEL_HEIGHT / 3);
        
        prepreke = new ArrayList<>();

        addKeyListener(new GameKeyAdapter());

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

            // Slika u pozadini
            image = new ImageIcon(getClass().getResource("background.PNG")).getImage();
            g2.drawImage(image, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);

            // Iscrtaj sve objekte
            riba.draw(g2);
            
            int d = prepreke.size();
            for (int i = 0; i < d; i++) {
                prepreke.get(i).draw(g2);
            }

            //Font kojim se ispisuje text na ekranu           
            g2.setFont(new Font("comicsans", Font.BOLD, 20));
            	g2.drawString("" + score, PANEL_WIDTH/ 2 - 20, 100);          
            g2.drawString(Poruka, PANEL_WIDTH * 4 / 20, PANEL_HEIGHT / 10);
            
            

            // Sinhronizovanje sa grafičkom kartom
            Toolkit.getDefaultToolkit().sync();

            // Optimizacija upotrebe RAM-a, 
            g.dispose();
        } else {
            image = new ImageIcon(getClass().getResource("flappy_fish.png")).getImage();
            g2.drawImage(image, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
        }
    }

    private void update() {
        riba.move();
        
        int d = prepreke.size();
        for (int i = 0; i < d; i++) {
            prepreke.get(i).move();
        }
        for (int i = 0; i < d; i++) {
            if(prepreke.get(i).vrijednostx()==riba.x)
                score+=1;
    }
    
    }
    
    private void addObstacle() {
        prepreke.add(new Prepreka(this));
    }
    
    private void detectCollision() {
        int d = prepreke.size();
        for (int i = 0; i < d; i++) {
            if(prepreke.get(i).intersectsWithFish(riba))
                stopGame();
        }
        
        
    }


    @Override
    public void run() {

        while (true) {
            if (inGame && !paused) {
                frames++;

                if (frames == 40) {
                    addObstacle();
                    frames = 0; 
                }
               
                update();
                detectCollision();
                repaint();
            }

            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }
    }

    void startGame() {
        inGame = true;
        riba = new Riba(this, PANEL_WIDTH / 4, PANEL_HEIGHT / 3);
        prepreke = new ArrayList<>();
        frames = 0;
        score=0;
        repaint();
    }

    public void stopGame() {
        inGame = false;
        paused=true;
    }

    private class GameKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            
            if (keyCode == KeyEvent.VK_SPACE) {
                riba.moveUp();
                paused = false;
            }
        }
    }
}
