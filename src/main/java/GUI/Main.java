package GUI;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Window window = new Window();
                window.setVisible(true);
                try {
                    ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\Pulpit\\tilesfinally.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tilesnew.png" );
                    ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\Pulpit\\tile1.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tile2.png");
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

}