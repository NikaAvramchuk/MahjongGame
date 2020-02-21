package LevelCreator;



import GUI.ResizeImages;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        try {
            ResizeImages.resizeImage("C:\\Users\\Marcin\\IdeaProjects\\Mahjong\\target\\classes\\GameMenuImage\\Mahjong.PNG", "C:\\Users\\Marcin\\IdeaProjects\\Mahjong\\target\\classes\\GameMenuImage\\MahjongMResize.PNG");
        }
        catch (Exception e){
            e.printStackTrace();
        }


//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                LevelMaker window = new LevelMaker();
//                window.setVisible(true);
//
//
//            }
//        });
    }
}
