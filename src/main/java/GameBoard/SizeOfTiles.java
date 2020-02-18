package GameBoard;

public enum SizeOfTiles {

    WIDTH (62), HEIGHT (80);



    private final int value;


    SizeOfTiles(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}