package View;

import View.BoardPanel;
import javax.swing.JFrame;

public class CheckersFrame extends JFrame {
    BoardPanel panel;
    public CheckersFrame(Logic.CheckerBoard cb) {
        panel=new BoardPanel(cb);
        add(panel);
        setTitle("Checkers");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(800, 600);//806,629
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    public void updateFrame(){
        panel.updateChipInfo();
    }
}