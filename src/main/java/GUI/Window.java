package GUI;

import javax.swing.*;

public class Window extends JFrame {
    public Window (){
        setSize(1000, 650);
        setTitle("Mahjong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Panel panel = new Panel();
        add(panel);


    }

}
