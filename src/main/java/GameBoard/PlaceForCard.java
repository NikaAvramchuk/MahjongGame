package GameBoard;

public class PlaceForCard {
    private boolean cardIsHere;

    private int x;
    private int y;
    private int z;

    public Card card;

    public PlaceForCard(int x, int y, int z, Card card) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.card = card;
        cardIsHere = true;
    }

    public PlaceForCard() {
        cardIsHere = false;
    }
}
