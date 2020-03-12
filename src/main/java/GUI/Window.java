package GUI;

import GameBoard.Tile;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Window extends JFrame {
    Container contentPane = new Container();

    static Window window;
    public static int challangeNumber = 0;
    public static boolean isHardModeOn = true;
    public static AudioInputStream input;
    public static Clip clip;
    public static int musicNumber = 0;
    public static int numberOfLives = 3;
    public static String playerName = "";
    static int isListenerToBeAdded = 0;
    public static int theme = 1;
    public static int retry = 0;
    public static int oneTime = 0;




    public static void showGameBoard(){
        window.add(new Panel());
    }


    public Window (){
        setSize(1200, 680);
        setTitle("Mahjong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().add(new GameMenu());
        getContentPane().validate();


        Image im = null;
        try {
            im = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\yinYang.PNG"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Music.moveMusic();
            clip = AudioSystem.getClip();
            if (!clip.isRunning() && !clip.isOpen() && !clip.isActive()) {
                input = AudioSystem.getAudioInputStream(new File(Music.musicPaths.get((new Random()).nextInt(4))));

                clip.open(input);
                clip.loop(2);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        setIconImage(im);
    }
}