package GUI;

import GameBoard.SizeOfTiles;
import GameBoard.Tiles;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Panel extends JPanel {


    public Panel () {
        setLayout(null);
        JButton test = new JButton();
        test.setBounds(500, 320, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
        add(test);
    }
}
