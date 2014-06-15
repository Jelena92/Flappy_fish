package igrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
/**
 *
 * @author Administrator
 */
public class Frame extends JFrame{
   
     Board board = new Board();
    
    public Frame() {
        add(board);
        setJMenuBar(initMenu());
               
        pack();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Flappy Bird");
        
        setVisible(true);
    }

    final JMenuBar initMenu() {
       // Napravimo liniju menija
        JMenuBar menuBar = new JMenuBar();
        
        // Mapravimo meni
        JMenu gameMenu = new JMenu("Game");
        
        // Napravimo stavku za meni
        JMenuItem results = new JMenuItem("Results");
        results.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    board.read();
                } catch (IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        JMenuItem newGame = new JMenuItem("New game");
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                board.startGame();
            }
        });
        // Dodamo stavku u meni
        gameMenu.add(newGame);
        gameMenu.add(results);
        
        // Dodamo meni u liniju menija
        menuBar.add(gameMenu);
        
        return menuBar;
    } 
}
