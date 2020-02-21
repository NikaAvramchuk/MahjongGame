package LevelCreator;

import GUI.ResizeImages;
import GameBoard.Tile;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {

        try {
            File dir = new File(Tile.class.getClassLoader().getResource("TilesOrigin").getFile());
            File[] listOfFiles = dir.listFiles();
            for (File file : listOfFiles) {
                System.out.println(file.getAbsolutePath());

                ResizeImages.resizeImage(file.getAbsolutePath(), dir.getParent() + "\\TilesFixed\\" + file.getName());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
//        try {
//            File[] listOfFiles = dir.listFiles();
//            for (File file : listOfFiles) {
//                ResizeImages.resizeImage(file.getAbsolutePath(), dir.getAbsolutePath() + "\\TilesFixed\\" + file.getName());
//                Tile.imagesPaths.add(dir.getParent() + "\\TilesFixed\\" + file.getName());
//            }
//        } catch (Exception e) {
//            System.out.println(Tile.class.getClassLoader().getResource("TilesOrigin").getFile());
//            System.out.println(dir.getParent() + "\\TilesFixed\\");

            LevelMaker editor = new LevelMaker();



    }
}
