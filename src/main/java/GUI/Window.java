package GUI;

import GameBoard.Tile;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
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
    static int randomMusic;
    static Random randomM = new Random();
    static int delay;
    static Timer timeForMusic;
    static int isWindowMinimized = 0;
    public static ActionListener musicListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("im in action listener");
            clip.stop();
            clip.close();
            timeForMusic.stop();
            playRandomMusic();
        }
    };


    public static void playRandomMusic(){
        try {
            input = AudioSystem.getAudioInputStream(new File(Music.musicPaths.get(randomM.nextInt(4))));
            randomMusic = randomM.nextInt(4);
            clip.open(input);
            delay = (int) clip.getMicrosecondLength()/1000;
            timeForMusic = new Timer(delay, musicListener);
            System.out.println(timeForMusic.getDelay());
            timeForMusic.start();
            clip.start();
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }


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
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
                if (!clip.isRunning()) {
                    clip.start();
                    timeForMusic.start();

                }
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
                if (clip.isRunning()){
                    clip.stop();
                    timeForMusic.stop();
                }

            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                    System.out.println("Test");
                    try {
                        Thread.sleep(30000);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }



            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        Image im = null;
        try {
            im = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\yinYang.PNG"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Music.moveMusic();
            clip = AudioSystem.getClip();
            playRandomMusic();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        setIconImage(im);
    }
}