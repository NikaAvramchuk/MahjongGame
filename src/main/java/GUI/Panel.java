package GUI;

import GameBoard.Card;
import GameBoard.SizeOfTiles;
import GameBoard.Tiles;
import GameBoard.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Panel extends JPanel {
    JButton jButton;


    public Panel () {
        setLayout(null);
        final JButton test = new JButton();
        test.setBounds(500, 320, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
        add(test);
        test.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                test.setEnabled(false);
            }
        });



    }
    public void createBoard() {
        Collections.shuffle(Card.cardArrayList);
        int i =0;
        while (i<Card.cardArrayList.size()) {
            for (int z = 0; z < Board.zCoord; z++) {
                for (int y = 0; y < Board.yCoord; y++)
                    for (int x = 0; x < Board.zCoord; x++)
                        if (Board.boardNew[z][y][x] = true) {
                            Card.cardArrayList.get(i).setZ(z);
                            Card.cardArrayList.get(i).setY(y);
                            Card.cardArrayList.get(i).setX(x);
//                          Card.cardArrayList.get(i).setBounds(); // tutaj musimy wymyslic jak auomatycznie wyznaczac gdzie bedzie narysowany przycisk
                            add(Card.cardArrayList.get(i));
                        }
            }
            i++;
        }

    }

}
