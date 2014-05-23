package igrica;
/**
 *
 * @author Administrator
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
import static javax.swing.SpringLayout.WIDTH;

public class Prepreke extends Rectangle.Double implements GameObjects {
    Random rnd= new Random();
    
    private int x;
    private int y;
    private int width;
    private int height;
    public static final int w = 55;
    public int brzina = 6;
    private Color fillColor = Color.BLUE;
    private Image image1;
    private Image image2;
    private Board board;
    private static int cost=150;
    
    
    public Prepreke(Board board, int x) {
        this.board = board;
        this.x = x;
        this.y = rnd.nextInt(board.PANEL_HEIGHT-400)+200;
        this.width = w;
        this.height =board.PANEL_HEIGHT - y;
        
        image1 = new ImageIcon(getClass().getResource("prepreke1.png")).getImage();
        image2 = new ImageIcon(getClass().getResource("prepreke2.png")).getImage();
    }
    

    public void move() {
        x -= brzina;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image1,x, y,width, height, null);
        g2.drawImage(image2, x, y +cost-600, null);
    }

}
