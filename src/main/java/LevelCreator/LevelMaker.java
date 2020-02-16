package LevelCreator;

import GUI.Window;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;

public class LevelMaker extends JFrame{
    public LevelMaker() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setVisible(true);
        add(Panel.panel);
        PanelLevel1.level1.setSize(200, 200);
        Panel.panel.add(PanelLevel1.level1);
        JButton jButton = new JButton("Test");
        jButton.setBounds(0,0, 200, 200);
        PanelLevel1.level1.add(jButton);






    }


}
