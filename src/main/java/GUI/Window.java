package GUI;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class Window extends JFrame {
    Container contentPane = new Container();

    static Window window;

    public static void showGameBoard(){
        window.add(new Panel());
    }

    public Window (){
        setSize(1200, 680);
        setTitle("Mahjong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().add(new GameMenu());
        getContentPane().validate();

//        Panel panel = new Panel();


    }

}