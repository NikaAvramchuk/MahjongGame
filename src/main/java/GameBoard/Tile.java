package GameBoard;

import javax.swing.*;
import java.util.ArrayList;

public class Tile extends JButton {
    private int x;
    private int y;
    private int z;

    private int level;
    public static ArrayList<Tile> allTiles = new ArrayList<Tile>(Tile.createAllTilesInBoard((new ImageIcon("C:\\Users\\Nika\\Downloads\\button.png")),new ImageIcon("C:\\Users\\Nika\\Downloads\\button1.png")));

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int IDTile;

    private boolean tileIsEnable;

    public boolean tileIsEnable() {
        return tileIsEnable;
    }

    public void tileSetEnable(boolean enable) {
        tileIsEnable = enable;
    }

    public int getTileX() {
        return x;
    }

    public void setTileX(int x) {
        this.x = x;
    }

    public int getTileY() {
        return y;
    }

    public void setTileY(int y) {
        this.y = y;
    }

    public int getTileZ() {
        return z;
    }

    public void setTileZ(int z) {
        this.z = z;
    }

    public int getTileID() {
        return IDTile;
    }

    public void setTileId(int id) {
        IDTile = id;
    }



    public static ArrayList<Tile> createAllTilesInBoard (ImageIcon imageIcon, ImageIcon imageIcon1){
        ArrayList<Tile> allTiles = new ArrayList<Tile>();
        for (int i = 0; i < 144; i++){
            Tile t = new Tile();
            allTiles.add(t);
            if(i%2==0) {
                t.setTileId(0);
                t.setIcon(imageIcon);
            }

            else {
                t.setTileId(1);
                t.setIcon(imageIcon1);
            }
        }
        return allTiles;
    }



    /*statyczne pole do parowania kafelków (nie użyłem metody, bo jeżeli metoda parowania kafelków
    zostałaby użyta ponownie gdzieś w kodzie, to zepsułoby to całą aplikację, ponieważ lista z kafelkami
    będzie kilkukrotnie tasowana na początku i w trakcie gry*/
//    static {
//        int counter = 0;
//        int counterForPairs = 1;
//        createAllTiles();
//        for (Tile tile : allTiles){
//            if (counter < 2){
//                tile.setName("Pair " + Integer.toString(counterForPairs));
//            }
//            if (counter == 1) {
//                counterForPairs++;
//                counter = 0;
//            }
//            else if (counter == 0){
//                counter++;
//            }
////        }
//
//    }





}