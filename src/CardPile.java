/**
 * The CardPile class represents an ordered collection of Card objects, though the Cards themselves may be in a shuffled
 * order within the pile. It has a maximum capacity of 52 cards, but there may not be that many cards in the pile. An
 * internal variable, "lastCard," holds the index of the last filled space in the pile; all slots after that will be
 * null.
 * The CardPile acts as a Queue. Cards can be added to the end of the list (the bottom of the pile) and you can "deal" a
 * card from the front/top of the pile (index 0). As cards are dealt from the pile, the subsequent cards are shifted
 * towards the start of the list to fill in. There should be no "null" spaces in the pile before the "lastCard" index.
 *
 * The CardPile is written to prevent the user from accessing cards in the middle of the pile; just the top card.
 *
 * The vanilla constructor creates an empty CardPile in which lastCard is -1.
 * A second constructor will make a shuffled deck of 52 standard playing cards; it takes an integer parameter that can
 * be CardPile.ACE_LOW, CardPile.ACE_HIGH, or CardPile.EQUAL_FACES that will determine the values for the Aces and Face
 * cards.
 */
public class CardPile
{
    private Card[] myCards;
    private int lastCard;
    //-----------------
    // The following "public static final" variables are publicly available constants.
    /**
     * ACE_LOW means that the Ace = 1, Jack = 11, Queen = 12, and K = 13.
     * Used when calling constructor. (e.g. CardPile deck = new CardPile(CardPile.ACE_LOW); )
     */
    public static final int ACE_LOW = 0;
    /**
     * ACE_HIGH means that the Ace = 14, Jack = 11, Queen = 12, and K = 13.
     * Used when calling constructor. (e.g. CardPile deck = new CardPile(CardPile.ACE_HIGH); )
     */
    public static final int ACE_HIGH = 1;
    /**
     * EQUAL_FACES means that Ace = 1, Jack = 10, Queen = 10, and K = 10.
     * Used when calling constructor. (e.g. CardPile deck = new CardPile(CardPile.EQUAL_FACES); )
     */
    public static final int EQUAL_FACES = 2;
    //---------------

    /**
     * create a new, empty CardPile
     */
    public CardPile()
    {
        myCards = new Card[52];
        lastCard = -1;
    }

    /**
     * create a new CardPile filled with a shuffled standard deck of cards.
     * @param type - determines the values of the aces and face cards that we'll use for these cards.
     */
    public CardPile(int type)
    {
        this();  // call the default constructor first.

        // Note: the faces are all two-character strings, and most of them have a leading space. This is to accomodate
        //        the 10, which is two characters. This way, a list of cards will line up well.
        String[] faces = {" A"," 2"," 3"," 4"," 5"," 6"," 7"," 8"," 9","10"," J"," Q"," K"};
        int[] ace_low_values = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        int[] ace_high_values = {14,2,3,4,5,6,7,8,9,10,11,12,13};
        int[] equal_facecard_values = {1,2,3,4,5,6,7,8,9,10,10,10,10};
        String[] suits = {"Hearts","Clubs","Diamonds","Spades"};
        int [] values;
        switch (type)
        {
            case ACE_LOW: values = ace_low_values; break;
            case ACE_HIGH: values = ace_high_values; break;
            case EQUAL_FACES: values = equal_facecard_values; break;
            default: values = ace_low_values;
        }
        int i=0;
        for (int suitNum = 0; suitNum < suits.length; suitNum++)
        {
            for (int faceNum = 0; faceNum < values.length; faceNum++)
            {
                myCards[i] = new Card(faces[faceNum],values[faceNum],suits[suitNum]);
                i++;
            }
        }
        lastCard = myCards.length - 1;
        this.shuffle();
    }

    /**
     * Adds a single Card to the end (bottom) of the CardPile. If this puts the list over 52 cards, throws an
     * ArrayIndexOutOfBoundsException.
     * @param c - the card to add.
     */
    public void addCard(Card c)
    {
        if (lastCard < myCards.length - 1)
        {
            lastCard++;
            myCards[lastCard] = c;
        }
        else
            throw new ArrayIndexOutOfBoundsException("Attempted to add a card to a full deck.");
    }

    /**
     * scrambles the order of the cards in this CardPile
     */
    public void shuffle()
    {
        for (int i = 0; i < numCards() * 3; i++)
        {
            int a = (int)(Math.random()*(numCards()));
            int b = (int)(Math.random()*(numCards()));

            if (a != b)
            {
                Card temp = myCards[a];
                myCards[a] = myCards[b];
                myCards[b] = temp;
            }

        }
    }

    /**
     * gets how many Card objects are actually in this CardPile.
     * @return - the count of the cards
     */
    public int numCards()
    {
        return lastCard + 1;
    }

    /**
     * indicates whether there are 1 or more Cards stored in this CardPile
     * @return - whether this CardPile has any cards
     */
    public boolean hasAnyCards()
    {
        return lastCard > -1;
    }

    /**
     * returns the first (top) Card in the Pile, shifting the remaining Cards forward and adjusting the position of
     * "lastCard." Throws an ArrayIndexOutOfBounds error if there was no card to return.
     * @return - the card that was at position 0 in the list before removing it.
     * postcondition: this CardPile now has one fewer Cards than before.
     */
    public Card dealCard()
    {
        if (this.hasAnyCards())
        {
            Card toReturn = myCards[0];
            for (int i = 0; i < lastCard; i++)
            {
                myCards[i] = myCards[i + 1];
            }
            myCards[lastCard] = null;
            lastCard--;
            return toReturn;
        }
        else
            throw new ArrayIndexOutOfBoundsException("Attempted to return a card from an empty CardPile.");

    }

    /**
     * returns the Card that is at the start (top) of the CardPile _without_ removing it from this CardPile.
     * Throws an ArrayIndexOutOfBounds exception if there is no Card in the Pile.
     * @return - the card at top of the CardPile
     * postcondition: the CardPile is unchanged.
     */
    public Card peek()
    {
        if (this.hasAnyCards())
            return myCards[0];
        else
            throw new ArrayIndexOutOfBoundsException("Asked to peek at top card, but pile has no cards!");
    }

    /**
     * Clears out all the cards from this CardPile, filling all the spaces with null and resetting lastCard to -1.
     */
    public void removeAllCards()
    {
        for (int i=0; i<myCards.length; i++)
        {
            myCards[i] = null;
        }
        lastCard = -1;
    }

    /**
     * removes all the cards that were in "otherPile" and adds them to the bottom of this pile. If this makes this
     * CardPile have more than 52 cards, throws an ArrayIndexOutOfBoundsException.
     * @param otherPile - the CardPile from which to take all the cards.
     * postcondition: The other CardPile is now empty. This CardPile now contains all the cards that were originally in
     *                  both CardPiles.
     */
    public void takeAllCardsFromPile(CardPile otherPile)
    {
        if (this.numCards()+otherPile.numCards() > 52)
            throw new ArrayIndexOutOfBoundsException("Attempting to add a pile with "+otherPile.numCards()+" cards to a pile with "+this.numCards()+", but that is more than 52 cards.");
        else
            while (otherPile.hasAnyCards())
            {
                this.addCard(otherPile.dealCard());
            }
    }

    /**
     * sums up the total of the values of all the Cards in this CardPile.
     * @return - the sum of the values.
     */
    public int totalValueOfCards()
    {
        int sum = 0;
        for (int i=0; i<=lastCard; i++)
        {
            sum += myCards[i].getValue();
        }
        return sum;
    }

    /**
     * counts how many cards with the given value are in this CardPile. (e.g., "How many 3's does this CardPile have?")
     * @param valueToFind - the value we are attempting to count
     * @return - the number of cards whose value is valueToFind.
     */
    public int countCardsOfValue(int valueToFind)
    {
        int count = 0;
        for (int i=0; i<=lastCard; i++)
        {
            if (myCards[i].getValue() == valueToFind)
                count++;
        }
        return count;
    }

    public String toString()
    {
        if (this.hasAnyCards())
        {
            String result = "";
            for (int i = 0; i <= lastCard; i++)
            {
                result += myCards[i];  // Note: automatically uses Card's toString() method.
                if (i < lastCard)
                    result += "\n";
            }
            return result;
        }
        else
            return "Empty Card Pile";
    }
}
