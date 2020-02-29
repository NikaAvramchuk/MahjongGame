package GUI;

import GameBoard.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        Image im = null;
        try {
            im = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\yinYang.PNG"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        setIconImage(im);

//        Panel panel = new Panel();


    }

}