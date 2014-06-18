package igrica;

import java.awt.Color;
import java.awt.Component;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
    static String message = "";
    private Image image;

    Boolean inGame;
    Boolean paused;
    static int score = 0;
    private String playerName = "";

    long frames;
    int framesBetweenObstacles = 50;
    final int minFBO = 30;

    // Objekti u igri
    Fish fish;
    ArrayList<Obstacle> obstacles;
    private Component frame;
    

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

        fish = new Fish(this, PANEL_WIDTH / 4, PANEL_HEIGHT / 3);

        obstacles = new ArrayList<>();

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
            fish.draw(g2);

            int d = obstacles.size();
            for (int i = 0; i < d; i++) {
                obstacles.get(i).draw(g2);
            }

            //Font kojim se ispisuje text na ekranu           
            g2.setFont(new Font("comicsans", Font.PLAIN, 30));
            g2.setColor(Color.WHITE);
            g2.drawString(" Score: " + score, 30, 40);
            g2.drawString(" Name : " + playerName, 30, 65);
            g2.drawString(message, PANEL_WIDTH / 4, 100);

            // Sinhronizovanje sa grafičkom kartom
            Toolkit.getDefaultToolkit().sync();

            // Optimizacija upotrebe RAM-a, 
            g.dispose();

        } else {
            image = new ImageIcon(getClass().getResource("Pocetak.PNG")).getImage();
            g2.drawImage(image, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
            g2.setFont(new Font("comicsans", Font.BOLD, 30));
            g2.setColor(Color.WHITE);
            g2.drawString(message, 10, 150);

        }
    }

    private void update() {
        fish.move();

        int d = obstacles.size();
        for (int i = 0; i < d; i++) {
            obstacles.get(i).move();
        }
        for (int i = 0; i < d; i++) {
            if (obstacles.get(i).valueX() == fish.x) {
                score += 1;
                
                if(score>3 && score%2==0 && minFBO < framesBetweenObstacles)
                    framesBetweenObstacles-= 5;
                        
            }
        }

    }

    private void addObstacle() {
        obstacles.add(new Obstacle(this));
    }

    private void detectCollision() {
        int d = obstacles.size();
        for (int i = 0; i < d; i++) {
            if (obstacles.get(i).intersectsWithFish(fish)) {
                stopGame();
            }
        }

    }

    
    public void readTextFileLineByLine() {
        FileReader in = null;
        //BufferedReader dozvoljava čitanje većeg "komada" datoteke odjednom.
        BufferedReader bin = null;

        try {

            File file = new File("src/igrica/saveFile.txt");

            in = new FileReader(file);
            // Za inicijalizaciju, BufferedReader zahtjeva otvoren FileReader tok
            bin = new BufferedReader(in);

            String data;
            ArrayList<String> rijeci = new ArrayList<>();

            /*
             * Metoda readLine klase BufferedReader učitava jedan red teksta iz
             * datoteke. Vraća null ukoliko dođe do kraja datoteke.
             */
            while ((data = bin.readLine()) != null) {
                rijeci.add(data);
            }
            int d = rijeci.size();

            String stringing = "";
            for (int i = 0; i < d; i++) {
                stringing += (i + 1) + ": " + rijeci.get(i) + "\n";

            }

            JOptionPane.showMessageDialog(null, stringing, "Results", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        } finally {
            if (bin != null) {
                try {
                    bin.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
            }
        }
    }


    @Override
    public void run() {

        while (true) {
            if (inGame && !paused) {
                frames++;
                
                if (frames == framesBetweenObstacles) {
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
        fish = new Fish(this, PANEL_WIDTH / 4, PANEL_HEIGHT / 3);
        obstacles = new ArrayList<>();
        frames = 0;
        score = 0;
        framesBetweenObstacles=50;
        playerName = JOptionPane.showInputDialog(null, "Please, enter your name:", "Flappy fish", JOptionPane.INFORMATION_MESSAGE);

        inGame = true;
        
        message = "Use SPACE for jump";
        Timer deathTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                message = "";
            }
        ;
        });
 	deathTimer.start();

        if (playerName == null || "".equals(playerName)) {
            playerName="Nepoznat";
        }
        repaint();
    }

    //Čuvanje razultata u datoteci
    private void save_file(String name_fale, List<String> scores) throws IOException {
        File file = new File(name_fale);
        if (!file.exists()) { //Ako ne postoji datoteka, kreirati je
            file.createNewFile();
        }
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            for (String score : scores) {
                writer.println(score);
            }
        }
    }

    // postaviti u listu rezultate(punjenje liste)
    private List<String> load(String file_name) throws FileNotFoundException {
        File file = new File(file_name);
        if (!file.exists()) {
            //ako ne postoji datotetka, izbaci izuzetak
            throw new FileNotFoundException();
        }

        List<String> scores = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) { //kreiranje citaoca koji unosi podatke
            while (scanner.hasNextLine()) {
                scores.add(scanner.nextLine()); //postavljanje rezultata u listu
            }
        }

        return scores;
    }

   

    public void stopGame() {
        inGame = false;
        JOptionPane.showMessageDialog(frame, "" + playerName + " won " + score + " points.", "Flappy fish", JOptionPane.INFORMATION_MESSAGE);
        try {
            List<String> scores = load("src/igrica/saveFile.txt");
            scores.add(playerName + " - " + score);
            save_file("src/igrica/saveFile.txt", scores); //igra je sacuvana
        } catch (IOException ex) { //ako ne moze da bude prikazano, objavi gresku
            System.out.println("Error : " + ex);
        }

        paused = true;
    }
    
    
    public void Help() {
        JOptionPane.showMessageDialog(null,
                "The point of the game is to keep the bird in flight while crossing a series of obstacles.\n \n"
                + "You have to use SPACE to keep the bird in flight\n\n"
                + "If you want a break, you will press P on the keyboard.",
                "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    private class GameKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();

            if (keyCode == KeyEvent.VK_SPACE) {
                fish.moveUp();
                paused = false;
            }
        }
    }
}
