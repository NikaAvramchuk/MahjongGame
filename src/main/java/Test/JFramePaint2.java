package Test;

/* JFramePaint2.java
 * Copyright (c) HerongYang.com. All Rights Reserved.
 */
import java.awt.*;
import javax.swing.*;
public class JFramePaint2 {
    public static void main(String[] a) {
        JFrame f = new JFrame();
        f.setTitle("Drawing Graphics in a Frame"
                +" by Adding a Component");
        f.setBounds(100,50,500,300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getLayeredPane().add(new MyComponent());
//        f.getContentPane().add(new MyComponent());
        System.out.println(f.getLayeredPane().getSize());
        System.out.println(f.getContentPane().getSize());
        f.setVisible(true);
    }
    static class MyComponent extends JComponent {
          public void paintComponent(Graphics g) {
            g.fillRect(20,10,100,60);
        }
    }
}