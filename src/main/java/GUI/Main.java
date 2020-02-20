package GUI;

import GameBoard.Tile;

import java.awt.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Window window = new Window();
                window.setVisible(true);


            }
        });

    }

}