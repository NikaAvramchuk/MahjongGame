package GUI;

import GameBoard.Tile;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Music {
    public static ArrayList<String> musicPaths = new ArrayList<>();
    static Clip clip1;
    public static int musicManipulation = 0;

    public static void moveMusic(){
        for (File file : Objects.requireNonNull(new File(Tile.class.getClassLoader().getResource("Music").toString().substring(6)).listFiles())){
            musicPaths.add(file.getAbsolutePath());

        }
    }

    public static void playSound(String nameOfFile){
        try {
            clip1 = AudioSystem.getClip();
            AudioInputStream input = AudioSystem.getAudioInputStream(new File(Tile.class.getClassLoader().getResource("Sounds/" + nameOfFile + ".wav").toString().substring(6)));
            clip1.open(input);
            clip1.loop(0);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        moveMusic();


    }
}
