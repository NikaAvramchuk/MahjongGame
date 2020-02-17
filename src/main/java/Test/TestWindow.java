package Test;

import javax.swing.*;
import java.awt.*;

public class TestWindow extends JFrame {
    static JLayeredPane levels;

    public TestWindow() {
        super("Test");
        PanelTest panel = new PanelTest();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setVisible(true);
        add(panel);
        levels = getLayeredPane();
        System.out.println(levels.getSize());
    }



}





