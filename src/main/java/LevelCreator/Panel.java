package LevelCreator;

import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.awt.font.TextAttribute.FONT;

public class Panel extends JPanel {

    ArrayList<JButton> collect = new ArrayList<JButton>();


    public Panel() {
        setLayout(null);
        final JButton custom = new JButton("New Game");
        custom.setContentAreaFilled(false);

        custom.setBorder(null);
        custom.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        custom.setBounds(300, 200, 400, 150);
        add(custom);
        custom.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse Clicked");

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse Pressed");

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Mouse released");



            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("Mouse entered");
                custom.setForeground(Color.MAGENTA);
                custom.setFont(new Font("Showcard Gothic", Font.BOLD, 50));
                repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                custom.setForeground(Color.BLACK);
                custom.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
                repaint();

            }
        });
        custom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Kliknąłeś w przycisk");

            }
        });

    }




            }

