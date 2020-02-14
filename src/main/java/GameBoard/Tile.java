package GameBoard;

import javax.swing.*;
import java.util.ArrayList;

public class Tile extends JButton {
    private int x;
    private int y;
    private int z;


    private int Id;

    private boolean isEnable;

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setCoords (int z, int y, int x) {
        this.z = z;
        this.y = y;
        this.x = x;
    }


    public static ArrayList<Tile> allTiles = new ArrayList<Tile>();

    public static void createAllTiles(){
        for (int i = 0; i < 144; i++){
            Tile t = new Tile();
            allTiles.add(t);
            if(i%2==0)
                t.setId(0);
            else
                t.setId(1);
        }
    }

    public static ArrayList<Tile> createAllTilesinBoard(){
        ArrayList<Tile> tiles= new ArrayList<Tile>();
        for (int i = 0; i < 144; i++){
            Tile t = new Tile();
            tiles.add(t);
        }

        return tiles;
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
