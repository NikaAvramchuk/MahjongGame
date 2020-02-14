package GameBoard;

import GUI.Panel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Tiles {

    static ArrayList<JButton> allTiles = new ArrayList<JButton>();

    /*statyczne pole do parowania kafelków (nie użyłem metody, bo jeżeli metoda parowania kafelków
    zostałaby użyta ponownie gdzieś w kodzie, to zepsułoby to całą aplikację, ponieważ lista z kafelkami
    będzie kilkukrotnie tasowana na początku i w trakcie gry*/
    static {
        int counter = 0;
        int counterForPairs = 1;
        createAllTiles();
        for (JButton tile : allTiles){
            if (counter < 2){
                tile.setName("Pair " + Integer.toString(counterForPairs));
            }
            if (counter == 1) {
                counterForPairs++;
                counter = 0;
            }
            else if (counter == 0){
                counter++;
            }
        }

    }

    public static void setCoordinates(int [][][] table){

    }

    //Tworzy 144 kafelki do gry
    public static void createAllTiles(){
        for (int i = 0; i < 144; i++){
            JButton tile = new JButton();
            allTiles.add(tile);
        }
    }

    //Metoda na tasowanie kafelków w Arrayliście zawierającej wszystkie 144 kafelki
    public static void shuffleTiles(){
        Collections.shuffle(allTiles);

    }

    /*Jeszcze nie skończona metoda, która pewnie będzie przyznawać koordynaty x i y kafelkom
    i uwidaczniać je w panelu*/
    static void createBoard(){
        for (JButton tile : allTiles){

        }
    }
}
