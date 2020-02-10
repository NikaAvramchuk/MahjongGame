package GameBoard;

public class Board {
    private final int xCoord = 15,
                      yCoord = 8,
                      zCoord = 5;

    //Mapa naszej planszy do gry w trójwymiarze
    private boolean [][][] board = {
            {
                    {false,true,true,true,true,true,true,true,true,true,true,true,true,false,false},
                    {false,false,false,true,true,true,true,true,true,true,true,false,false,false,false},
                    {false,false,true,true,true,true,true,true,true,true,true,true,false,false,false},
                    {false,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
                    {true,true,true,true,true,true,true,true,true,true,true,true,true,false,false},
                    {false,false,true,true,true,true,true,true,true,true,true,true,false,false,false},
                    {false,false,false,true,true,true,true,true,true,true,true,false,false,false,false},
                    {false,true,true,true,true,true,true,true,true,true,true,true,true,false,false}
            },
            {
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,true,true,true,true,true,true,false,false,false,false,false},
                    {false,false,false,false,true,true,true,true,true,true,false,false,false,false,false},
                    {false,false,false,false,true,true,true,true,true,true,false,false,false,false,false},
                    {false,false,false,false,true,true,true,true,true,true,false,false,false,false,false},
                    {false,false,false,false,true,true,true,true,true,true,false,false,false,false,false},
                    {false,false,false,false,true,true,true,true,true,true,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
            },
            {
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,true,true,true,true,false,false,false,false,false,false},
                    {false,false,false,false,false,true,true,true,true,false,false,false,false,false,false},
                    {false,false,false,false,false,true,true,true,true,false,false,false,false,false,false},
                    {false,false,false,false,false,true,true,true,true,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}

            },
            {
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,true,true,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,true,true,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
            },
            {
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,true,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false},
                    {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false}
            },
    };

    public void createBoard () {
        for (int z = 0; z<xCoord; z++) {
            for (int y = 0; y<yCoord; y++)
                for (int x = 0; x<zCoord; x++)
                    if (board[x][y][z] = true)






    }



}
