package GameBoard;

public enum SizeOfTiles {

    WIDTH(52), HEIGHT(68), BOARD_lEFT(9), BOARD_DOWN(12);


    private final int value;


    SizeOfTiles(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


