package GUI;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class Window extends JFrame {
    Container contentPane = new Container();

    public Window (){
        setSize(1000, 700);
        setTitle("Mahjong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Panel panel = new Panel();
        getContentPane().add(panel);
        getContentPane().validate();


    }

}