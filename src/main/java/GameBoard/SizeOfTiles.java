package GameBoard;

public enum SizeOfTiles {

    WIDTH (52), HEIGHT (70);


    private final int value;


    SizeOfTiles(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}