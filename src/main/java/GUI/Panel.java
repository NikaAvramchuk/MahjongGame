package GUI;

import GameBoard.SizeOfTiles;

import javax.swing.*;
import java.util.ArrayList;

public class Panel extends JPanel {


    public Panel () {
        setLayout(null);
        JButton test = new JButton();
        test.setBounds(500, 320, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
        add(test);
    }
}
