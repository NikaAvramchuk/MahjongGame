package GameBoard;

import GUI.BlackTheme;
import GUI.QualityIcon;
import GUI.ResizeImages;
import GUI.TilesChooseMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Tile extends JButton {
    private int x;
    private int y;
    private int z;

    private String iconPath;

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public static ArrayList<String> imagesPaths = new ArrayList<String>();

    private int level;

    public static ArrayList<Tile> allTiles = new ArrayList<Tile>(Tile.createAllTilesInBoard());

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





    public static void putImagesOnTiles(String inputFolder, String outputFolder){
        try {
            File isExisting = new File(Tile.class.getClassLoader().getResource(outputFolder).getFile());
            File dir = new File(Tile.class.getClassLoader().getResource(inputFolder).getFile());
            File[] listOfFiles = dir.listFiles();
            for (File file : listOfFiles) {
                imagesPaths.add(dir.getParent() + outputFolder + file.getName());
                ResizeImages.resizeImage(file.getAbsolutePath(), dir.getParent() + outputFolder + file.getName());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public static ArrayList<Tile> createAllTilesInBoard() {
        if (TilesChooseMenu.theme == 1) {
            putImagesOnTiles("TilesBlack", "\\TilesFixBlack\\");
        }
        else if (TilesChooseMenu.theme == 2){
            putImagesOnTiles("TilesStandard", "\\TilesFixStandard\\");
        }

        final ArrayList<Tile> allTiles = new ArrayList<Tile>();
            int lastID = 35;
            for (int i = 0; i < 144; i++) {
                Tile t = new Tile();
                t.setContentAreaFilled(false);
                allTiles.add(t);
                t.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (allTiles.size() == 0){
                            System.out.println("You win!!!");
                        }
                    }
                });
                if (i % 34 == 0 && lastID == 35 && i < 136) {
                    t.setTileId(0);
                    t.setIcon(new QualityIcon(0));
                    t.setIconPath(imagesPaths.get(0));
                }
                else if (i % 34 == 1 && lastID == 35 && i <= 136){
                    t.setTileId(1);
                    t.setIcon(new QualityIcon(1));
                    t.setIconPath(imagesPaths.get(1));
                }
                else if (i % 34 == 2 && lastID == 35 && i <= 136){
                    t.setTileId(2);
                    t.setIcon(new QualityIcon(2));
                    t.setIconPath(imagesPaths.get(2));
                }
                else if (i % 34 == 3 && lastID == 35 && i <= 136){
                    t.setTileId(3);
                    t.setIcon(new QualityIcon(3));
                    t.setIconPath(imagesPaths.get(3));
                }
                else if (i % 34 == 4 && lastID == 35 && i <= 136){
                    t.setTileId(4);
                    t.setIcon(new QualityIcon(4));
                    t.setIconPath(imagesPaths.get(4));
                }
                else if (i % 34 == 5 && lastID == 35 && i <= 136){
                    t.setTileId(5);
                    t.setIcon(new QualityIcon(5));
                    t.setIconPath(imagesPaths.get(5));
                }
                else if (i % 34 == 6 && lastID == 35 && i <= 136){
                    t.setTileId(6);
                    t.setIcon(new QualityIcon(6));
                    t.setIconPath(imagesPaths.get(6));
                }
                else if (i % 34 == 7 && lastID == 35 && i <= 136){
                    t.setTileId(7);
                    t.setIcon(new QualityIcon(7));
                    t.setIconPath(imagesPaths.get(7));
                }
                else if (i % 34 == 8 && lastID == 35 && i <= 136){
                    t.setTileId(8);
                    t.setIcon(new QualityIcon(8));
                    t.setIconPath(imagesPaths.get(8));
                }
                else if (i % 34 == 9 && lastID == 35 && i <= 136){
                    t.setTileId(9);
                    t.setIcon(new QualityIcon(9));
                    t.setIconPath(imagesPaths.get(9));
                }
                else if (i % 34 == 10 && lastID == 35 && i <= 136){
                    t.setTileId(10);
                    t.setIcon(new QualityIcon(10));
                    t.setIconPath(imagesPaths.get(10));
                }
                else if (i % 34 == 11 && lastID == 35 && i <= 136){
                    t.setTileId(11);
                    t.setIcon(new QualityIcon(11));
                    t.setIconPath(imagesPaths.get(11));
                }
                else if (i % 34 == 12 && lastID == 35 && i <= 136){
                    t.setTileId(12);
                    t.setIcon(new QualityIcon(12));
                    t.setIconPath(imagesPaths.get(12));
                }
                else if (i % 34 == 13 && lastID == 35 && i <= 136){
                    t.setTileId(13);
                    t.setIcon(new QualityIcon(13));
                    t.setIconPath(imagesPaths.get(13));
                }
                else if (i % 34 == 14 && lastID == 35 && i <= 136){
                    t.setTileId(14);
                    t.setIcon(new QualityIcon(14));
                    t.setIconPath(imagesPaths.get(14));
                }
                else if (i % 34 == 15 && lastID == 35 && i <= 136){
                    t.setTileId(15);
                    t.setIcon(new QualityIcon(15));
                    t.setIconPath(imagesPaths.get(15));
                }
                else if (i % 34 == 16 && lastID == 35 && i <= 136){
                    t.setTileId(16);
                    t.setIcon(new QualityIcon(16));
                    t.setIconPath(imagesPaths.get(16));
                }
                else if (i % 34 == 17 && lastID == 35 && i <= 136){
                    t.setTileId(17);
                    t.setIcon(new QualityIcon(17));
                    t.setIconPath(imagesPaths.get(17));
                }
                else if (i % 34 == 18 && lastID == 35 && i <= 136){
                    t.setTileId(18);
                    t.setIcon(new QualityIcon(18));
                    t.setIconPath(imagesPaths.get(18));
                }
                else if (i % 34 == 19 && lastID == 35 && i <= 136){
                    t.setTileId(19);
                    t.setIcon(new QualityIcon(19));
                    t.setIconPath(imagesPaths.get(19));
                }
                else if (i % 34 == 20 && lastID == 35 && i <= 136){
                    t.setTileId(20);
                    t.setIcon(new QualityIcon(20));
                    t.setIconPath(imagesPaths.get(20));
                }
                else if (i % 34 == 21 && lastID == 35 && i <= 136){
                    t.setTileId(21);
                    t.setIcon(new QualityIcon(21));
                    t.setIconPath(imagesPaths.get(21));
                }
                else if (i % 34 == 22 && lastID == 35 && i <= 136){
                    t.setTileId(22);
                    t.setIcon(new QualityIcon(22));
                    t.setIconPath(imagesPaths.get(22));
                }
                else if (i % 34 == 23 && lastID == 35 && i <= 136){
                    t.setTileId(23);
                    t.setIcon(new QualityIcon(23));
                    t.setIconPath(imagesPaths.get(23));
                }
                else if (i % 34 == 24 && lastID == 35 && i <= 136){
                    t.setTileId(24);
                    t.setIcon(new QualityIcon(24));
                    t.setIconPath(imagesPaths.get(24));
                }
                else if (i % 34 == 25 && lastID == 35 && i <= 136){
                    t.setTileId(25);
                    t.setIcon(new QualityIcon(25));
                    t.setIconPath(imagesPaths.get(25));
                }
                else if (i % 34 == 26 && lastID == 35 && i <= 136){
                    t.setTileId(26);
                    t.setIcon(new QualityIcon(26));
                    t.setIconPath(imagesPaths.get(26));
                }
                else if (i % 34 == 27 && lastID == 35 && i <= 136){
                    t.setTileId(27);
                    t.setIcon(new QualityIcon(27));
                    t.setIconPath(imagesPaths.get(27));
                }
                else if (i % 34 == 28 && lastID == 35 && i <= 136) {
                    t.setTileId(28);
                    t.setIcon(new QualityIcon(28));
                    t.setIconPath(imagesPaths.get(28));
                }
                else if (i % 34 == 29 && lastID == 35 && i <= 136){
                    t.setTileId(29);
                    t.setIcon(new QualityIcon(29));
                    t.setIconPath(imagesPaths.get(29));
                }
                else if (i % 34 == 30 && lastID == 35 && i <= 136){
                    t.setTileId(30);
                    t.setIcon(new QualityIcon(30));
                    t.setIconPath(imagesPaths.get(30));
                }
                else if (i % 34 == 31 && lastID == 35 && i <= 136){
                    t.setTileId(31);
                    t.setIcon(new QualityIcon(31));
                    t.setIconPath(imagesPaths.get(31));
                }
                else if (i % 34 == 32 && lastID == 35 && i <= 136){
                    t.setTileId(32);
                    t.setIcon(new QualityIcon(32));
                    t.setIconPath(imagesPaths.get(32));
                }
                else if (i % 34 == 33 && lastID == 35 && i <= 136){
                    t.setTileId(33);
                    t.setIcon(new QualityIcon(33));
                    t.setIconPath(imagesPaths.get(33));
                }
                else if (i >= 135 && lastID < 43){
                    t.setTileId(34);
                    t.setIcon(new QualityIcon(lastID - 1));
                    t.setIconPath(imagesPaths.get(lastID - 1));
                    lastID++;

                }
            }

            return allTiles;

    }
}



