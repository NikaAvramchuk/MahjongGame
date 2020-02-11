package GameBoard;

import javax.swing.*;
import java.util.ArrayList;

public class Card extends JButton {
    private int x;
    private int y;
    private int z;

    private int Id;

    public static ArrayList <Card> cardArrayList = new ArrayList<Card>();


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }



}
