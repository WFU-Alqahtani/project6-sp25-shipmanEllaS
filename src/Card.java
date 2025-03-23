/**********************************************************************************************
 * @file : Card.java
 * @description : Stores the suit and rank (value) of a playing card.
 * @author : Ella Shipman
 * @date : 23 March 2025
 *********************************************************************************************/

// Standard French-style cards
public class Card {

    // Suites
    public enum suits {
        NULL, SPADES, CLUBS, DIAMONDS, HEARTS
    }

    // Ranks
    public enum ranks {
        NULL, two, three, four, five, six, seven, eight, nine, ten, jack, queen, king, ace
    }

    private suits suit;
    private ranks rank;

    Card(){
        suit = suits.NULL;
        rank = ranks.NULL;
    }

    Card(suits s, ranks r){
        suit = s;
        rank = r;
    }

    public void print_card(){
        System.out.print(suit + ": " + rank);
    }

    public ranks getRank() { return rank; }

    /*  --------------------------------------------------------------------------------------
     *   getSuit - returns a numerical representation of the card's suit
     *             (1 - SPADES, 2 - CLUBS, 3 - DIAMONDS, 4 -  HEARTS)
     */
    public int getSuit() {
        switch (suit) {
            case suits.SPADES:
                return 1;
            case suits.CLUBS:
                return 2;
            case suits.DIAMONDS:
                return 3;
            case suits.HEARTS:
                return 4;
            default:
                return -1;
        }
    }

}
