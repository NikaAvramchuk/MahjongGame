package GUI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window (){
        setSize(1000, 700);
        setTitle("Mahjong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Panel panel = new Panel();
        add(panel, BorderLayout.CENTER);


    }

}