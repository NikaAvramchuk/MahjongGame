package GameBoard;

import GUI.Panel;

import java.util.Collection;
import java.util.Collections;

public class Board {
    public static final int xCoord = 15,
            yCoord = 8,
            zCoord = 5;

    //Mapa naszej planszy do gry w trójwymiarze

    public static boolean[][][] boardNew = {
            {
                    {false, true, true, true, true, true, true, true, true, true, true, true, true, false, false},
                    {false, false, false, true, true, true, true, true, true, true, true, false, false, false, false},
                    {false, false, true, true, true, true, true, true, true, true, true, true, false, false, false},
                    {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
                    {true, true, true, true, true, true, true, true, true, true, true, true, true, false, false},
                    {false, false, true, true, true, true, true, true, true, true, true, true, false, false, false},
                    {false, false, false, true, true, true, true, true, true, true, true, false, false, false, false},
                    {false, true, true, true, true, true, true, true, true, true, true, true, true, false, false}
            },
            {
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, true, true, true, true, true, true, false, false, false, false, false},
                    {false, false, false, false, true, true, true, true, true, true, false, false, false, false, false},
                    {false, false, false, false, true, true, true, true, true, true, false, false, false, false, false},
                    {false, false, false, false, true, true, true, true, true, true, false, false, false, false, false},
                    {false, false, false, false, true, true, true, true, true, true, false, false, false, false, false},
                    {false, false, false, false, true, true, true, true, true, true, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
            },
            {
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, true, true, true, true, false, false, false, false, false, false},
                    {false, false, false, false, false, true, true, true, true, false, false, false, false, false, false},
                    {false, false, false, false, false, true, true, true, true, false, false, false, false, false, false},
                    {false, false, false, false, false, true, true, true, true, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}

            },
            {
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, true, true, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, true, true, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
            },
            {
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, true, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                    {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
            },
    };

}
