package Test;

import javax.swing.*;
import java.awt.*;

public class TestWindow extends JFrame {
    public TestWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setVisible(true);
        add(Panel.panel);
//        JLayeredPane level1 = getLayeredPane();
//        JButton test1 = new JButton();
//        test1.setBounds(100, 100, 46, 60);
//        JButton test2 = new JButton();
//        test2.setBounds(120, 100, 46, 60);
//        level1.add(test1, 1);
//        level1.add(test2, 2);
    }
}
