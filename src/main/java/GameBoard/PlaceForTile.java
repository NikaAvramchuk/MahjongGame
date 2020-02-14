package GameBoard;

public class PlaceForTile {
    private boolean cardIsHere;

    private int x;
    private int y;
    private int z;

    public Tile tile;

    public PlaceForTile(int z, int y, int x, Tile tile) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.tile = tile;
        cardIsHere = true;
    }

    public PlaceForTile(boolean cardIsHere) {
        cardIsHere = cardIsHere;
    }

    public boolean isCardIsHere() {
        return cardIsHere;
    }

    public void setCardIsHere(boolean cardIsHere) {
        this.cardIsHere = cardIsHere;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
}