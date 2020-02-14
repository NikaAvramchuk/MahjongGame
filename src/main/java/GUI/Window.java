package GUI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window (){
        Dimension dimension = new Dimension(1000, 800);
        setPreferredSize(dimension);
        pack();
        setTitle("Mahjong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Panel panel = new Panel();
        add(panel);



    }

}