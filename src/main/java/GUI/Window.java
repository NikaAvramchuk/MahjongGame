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
    FileReader fileReader;
    PrintWriter pw;
    BufferedReader bufferedReader;
    public static final String bestScoresPath = Tile.class.getClassLoader().getResource("BestScores").getFile() + "\\TopScores.txt";
    File file;
    public static ArrayList<String> top5ScoresSortedWithNames = new ArrayList<>();
    public static ArrayList<String> top5ScoresWithNames = new ArrayList<>();
    public static ArrayList<Integer> top5ScoresSorted = new ArrayList<>();
    static String playerScore;
    static String playersToMove;
    public static int numberOfLives = 3;
    public static String playerName = "";
    static int isListenerToBeAdded = 0;


    public static void showGameBoard(){
        window.add(new Panel());
    }

    private static void movePlayersFromFile (ArrayList arrayList){
        try {
            Scanner scanner = new Scanner(new File(bestScoresPath));
            while (scanner.hasNextLine()){
                playersToMove = scanner.nextLine();
                arrayList.add(playersToMove);
            }
            scanner.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void listSortedTimes (ArrayList arrayList){
        try {
            Scanner scanner = new Scanner(new File(bestScoresPath));
            while (scanner.hasNextLine()){
                playerScore = scanner.nextLine();
                arrayList.add(Integer.parseInt(playerScore.substring(7, playerScore.lastIndexOf("Name")).trim()));
                Collections.sort(arrayList);
            }
            scanner.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listSortedScoresWithNames(ArrayList arrayList){
        for (Integer integer : top5ScoresSorted){
            for (String scoreName : top5ScoresWithNames){
                if (scoreName.contains(String.valueOf(integer))){
                    String name = scoreName.substring(scoreName.lastIndexOf("Name") + 5).trim();
                    if (!arrayList.contains("Score: " + integer + " Name: " + name)) {
                        arrayList.add("Score: " + integer + " Name: " + name);
                    }


                }
            }

        }


    }

    public Window (){
        setSize(1200, 680);
        setTitle("Mahjong");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().add(new GameMenu());
        getContentPane().validate();
        listSortedTimes(top5ScoresSorted);
        movePlayersFromFile(top5ScoresWithNames);
        listSortedScoresWithNames(top5ScoresSortedWithNames);
        int counter = 0;
        try {
            pw = new PrintWriter(new File(bestScoresPath));
            for (String top5 : top5ScoresSortedWithNames) {
                if (counter < 5) {
                    pw.println(top5);
                    counter++;
                    pw.flush();
                }
            }
            pw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }



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

//        Panel panel = new Panel();


    }

}