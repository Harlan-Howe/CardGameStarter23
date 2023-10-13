public class Main
{
    public static void main(String[] args)
    {
        runTests();  // temporary code just to show that the CardPile works.
        // BlackJackReferee ref = new BlackJackReferee();
        // ref.play();
    }
    public static void runTests()
    {
        System.out.println("---------------------------------------------------------------------- 0");

        Card c0 = new Card();
        System.out.println(c0);  // This should print the Ace of Hearts.

        System.out.println("---------------------------------------------------------------------- 1");


        CardPile cp = new CardPile();
        cp.addCard(c0);  // Ace of Hearts
        cp.addCard(new Card(" J", 10, "Diamonds"));
        cp.addCard(new Card(" 5", 5, "Spades"));
        cp.addCard(new Card(" 7", 7, "Spades"));
        System.out.println("Pile 'cp' has "+cp.numCards()+" cards.");
        System.out.println(cp);

        System.out.println("---------------------------------------------------------------------- 2");
        System.out.println("Dealing "+cp.dealCard());
        System.out.println("Pile 'cp1' has "+cp.numCards()+" cards.");
        System.out.println("Now deck is:");
        System.out.println(cp);
        System.out.println("Total value: "+cp.totalValueOfCards());

        System.out.println("---------------------------------------------------------------------- 3");
        System.out.println("Creating a new 'ACE LOW' card pile.");
        CardPile cp1 = new CardPile(CardPile.ACE_LOW);
        System.out.println(cp1);

        System.out.println("---------------------------------------------------------------------- 4");
        CardPile cp2 = new CardPile(CardPile.EQUAL_FACES);

        for (int i=0; i< 40; i++)
            cp2.dealCard();  // Note: we normally would say something like dealtCard = cp2.dealCard() and do something
                             //       with dealtCard... but in this particular case, we're just throwing the dealt cards
                             //       away....
        System.out.println("pile 'cp2': ("+cp2.numCards()+" cards)\n"+cp2); // print the remaining 12 cards
        System.out.println("total = "+cp2.totalValueOfCards());
        System.out.println("---------------------------------------------------------------------- 5");

        System.out.println("Adding cp to cp2.");
        cp2.takeAllCardsFromPile(cp);
        System.out.println("Cp2: \n"+cp2);
        System.out.println("Cp: \n"+cp);
        System.out.println("---------------------------------------------------------------------- 6");

        System.out.println("Total in cp2: "+cp2.totalValueOfCards());
        System.out.println("Num aces in cp2: "+cp2.countCardsOfValue(1));

        System.out.println("---------------------------------------------------------------------- 7");
        System.out.println("Peeking at cp2: "+cp2.peek());
        cp2.removeAllCards();
        System.out.println("Removed all cards. Now cp2 has "+cp2.numCards()+" cards.");
    }
}