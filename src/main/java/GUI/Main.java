package GUI;

import GameBoard.SizeOfTiles;
import GameBoard.Tiles;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> hold = new ArrayList<String>();
        Tiles.createAllTiles();
        Tiles.shuffleTiles();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Window window = new Window();
                window.setVisible(true);
            }
        });
        char tab[] = new char[]{'a', 'j', 'u', 'r', 'u', 'j', 'h', 'u', 'h', 'z', 'z'};

        for (int i = 0; i < tab.length; i++){
            for (int y = 0; y < tab.length; y++){
                if (y == i){
                    continue;
                }
                if (tab[y] == tab[i] && !hold.contains(String.valueOf(tab[i]))){
                    System.out.println(tab[i]);
                    if (!hold.contains(String.valueOf(tab[i]))) {
                        hold.add(String.valueOf(tab[i]));
                    }
                }
            }
        }


    }

}
