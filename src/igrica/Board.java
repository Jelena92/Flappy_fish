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

    // Objekti u igri
    Riba riba;
    Prepreke pre1, pre2, pre3, pre4;

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

        riba = new Riba(this, PANEL_WIDTH / 4, PANEL_HEIGHT / 3);
        pre1 = new Prepreke(this, PANEL_WIDTH);
        pre2 = new Prepreke(this, PANEL_WIDTH + (PANEL_WIDTH / 2));
        pre3 = new Prepreke(this, PANEL_WIDTH + (PANEL_WIDTH / 2) + (PANEL_WIDTH / 2));
        pre4 = new Prepreke(this, PANEL_WIDTH + (PANEL_WIDTH / 2) + (PANEL_WIDTH / 2) + (PANEL_WIDTH / 2));

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
            pre1.draw(g2);
            pre2.draw(g2);
            pre3.draw(g2);
            pre4.draw(g2);

            //Font kojim se ispisuje text na ekranu           
            g2.setFont(new Font("comicsans", Font.BOLD, 20));
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
        pre1.move();
        pre2.move();
        pre3.move();
        pre4.move();

    }

    @Override
    public void run() {

        while (true) {
            update();
            repaint();

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
        pre1 = new Prepreke(this, PANEL_WIDTH);
        pre2 = new Prepreke(this, PANEL_WIDTH + (PANEL_WIDTH / 2));
        pre3 = new Prepreke(this, PANEL_WIDTH + (PANEL_WIDTH / 2) + (PANEL_WIDTH / 2));
        pre4 = new Prepreke(this, PANEL_WIDTH + (PANEL_WIDTH / 2) + (PANEL_WIDTH / 2) + (PANEL_WIDTH / 2));

    }

    public void stopGame() {
        inGame = false;
        riba = new Riba(this, PANEL_WIDTH / 4, PANEL_HEIGHT / 3);
        pre1 = new Prepreke(this, PANEL_WIDTH);
        pre2 = new Prepreke(this, PANEL_WIDTH + (PANEL_WIDTH / 2));
        pre3 = new Prepreke(this, PANEL_WIDTH + (PANEL_WIDTH / 2) + (PANEL_WIDTH / 2));
        pre4 = new Prepreke(this, PANEL_WIDTH + (PANEL_WIDTH / 2) + (PANEL_WIDTH / 2) + (PANEL_WIDTH /2));

    }

    private class GameKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_SPACE) {
                riba.moveUp();
            }
        }
    }
}
