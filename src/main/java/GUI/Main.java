package GUI;

import GameBoard.SizeOfTiles;
import GameBoard.Tiles;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Tiles.createAllTiles();
        Tiles.shuffleTiles();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Window window = new Window();
                window.setVisible(true);
            }
        });

    }

}
