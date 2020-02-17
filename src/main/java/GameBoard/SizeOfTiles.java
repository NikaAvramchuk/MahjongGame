package GameBoard;

public enum SizeOfTiles {
    WIDTH (50), HEIGHT (50);

    private final int value;


    SizeOfTiles(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}