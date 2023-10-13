/**
 * a representation of a playing card. This card has a suit, a face value string (e.g., " A" or " 5", or "10" or " Q")
 * and a value - an int representation of the face value (e.g., 1 or 5 or 10 or 10) - this allows you to play games
 * where cards have numerical values but show strings for the cards faces, themselves.
 */
public class Card
{
    private String face;
    private int value;
    private String suit;

    public Card()
    {
        face = " A";
        value = 1;
        suit = "Hearts";
    }

    // overloaded constructor
    public Card(String inFace, int inVal, String inSuit)
    {
        face = inFace;
        value = inVal;
        suit = inSuit;
    }

    public String getFace()
    {
        return face;
    }

    public int getValue()
    {
        return value;
    }

    public String getSuit()
    {
        return suit;
    }

    // NOTE: there are no modifiers (setters) because we
    //       don't want to ever change a card after we make it.

    public String toString()
    {
        return face + " of "+suit;
    }
}
