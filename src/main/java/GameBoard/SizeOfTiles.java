package GameBoard;

public enum SizeOfTiles {
    WIDTH (46), HEIGHT (60);

    private final int value;


    SizeOfTiles(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}