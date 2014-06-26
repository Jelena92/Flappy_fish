package igrica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Administrator
 */
public class Frame extends JFrame {

    Board board = new Board();

    public Frame() {
        add(board);
        setJMenuBar(initMenu());

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Flappy Fish");

        setVisible(true);
    }

    /**
     * Metoda koja pravi meni u igri sa stavkama New Game, Results i Instruction.
     */
    final JMenuBar initMenu() {
        // Napravimo liniju menija
        JMenuBar menuBar = new JMenuBar();

        // Napravimo meni
        JMenu gameMenu = new JMenu("Game");

        // Napravimo stavku za meni
        JMenuItem results = new JMenuItem("Results");
        results.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                board.readTextFileLineByLine();

            }
        });
        JMenuItem newGame = new JMenuItem("New game");
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                board.startGame();
            }
        });

        JMenuItem help = new JMenuItem("Instructions");
        help.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                board.Help();
            }
        });
        // Dodamo stavku u meni
        gameMenu.add(newGame);
        gameMenu.add(results);
        gameMenu.add(help);

        // Dodamo meni u liniju menija
        menuBar.add(gameMenu);

        return menuBar;
    }
}