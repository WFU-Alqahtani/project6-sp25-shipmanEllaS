/**********************************************************************************************
 * @file : lab6.java
 * @description : A one-player version of Blind Man's Bluff, played between the user and the
 *                computer. The game lasts five rounds, and reshuffles and re-deals the user
 *                and the computer's hands after the player has lost three times. The game
 *                lasts for five rounds.
 * @author : Ella Shipman
 * @date : 23 March 2025
 *********************************************************************************************/

import java.util.*;

public class lab6 {

    /*  --------------------------------------------------------------------------------------
     *   initialize_deck - fill a French-style deck of cards (52 cards, 4 suits, 13 values)
     */
    public static LinkedList initialize_deck() {

        LinkedList deck = new LinkedList();

        // populate linked list with a single deck of cards
        for (Card.suits s : Card.suits.values()) {
            for(Card.ranks r : Card.ranks.values()) {
                if (r != Card.ranks.NULL && s != Card.suits.NULL) {
                    Card newCard = new Card(s, r);
                    deck.add_at_tail(newCard);
                }
            }
        }
        return deck;
    }

    /*  --------------------------------------------------------------------------------------
     *   play_blind_mans_bluff - playing the game
     *   LinkedList player1 : the user's hand
     *   LinkedList computer : the computer's hand
     *   LinkedList deck : the main deck of cards
     */
    private static void play_blind_mans_bluff(LinkedList player1, LinkedList computer, LinkedList deck) {
        System.out.println("\nStarting Blind mans Bluff \n");

        int rounds = 1;
        int losses = 0;
        int wins = 0;

        while (rounds <= 5) {
            //Remove cards from player1 and computer
            System.out.println("ROUND " + rounds);

            Card playerCard = player1.remove_from_head();
            Card computerCard = computer.remove_from_head();

            //Compare the cards -- find where card is stored in deck index and go from there?
            int rankCompare = playerCard.getRank().compareTo(computerCard.getRank());
            //Show user their card
            System.out.print("The computer's card is: ");
            computerCard.print_card();
            System.out.println();

            System.out.println("Is your card higher or lower than your opponent's card?       (0: lower / 1: higher)");
            Scanner scnr = new Scanner(System.in);
            int playerGuess = scnr.nextInt();
            while (!(playerGuess == 0 || playerGuess == 1)) {
                System.out.println("Please enter 0 or 1");
                playerGuess = scnr.nextInt();
            }

            if (rankCompare > 0) {             //Prompt for higher/lower
                if (playerGuess == 1) {
                    System.out.print("Correct!");
                    wins++;
                } else {
                    System.out.print("Incorrect!");
                    losses++;
                }
            } else if (rankCompare < 0) {
                if (playerGuess == 0) {
                    System.out.print("Correct!");
                    wins++;
                } else {
                    System.out.print("Incorrect!");
                    losses++;
                }
            } else {            //If same number, prompt for suit
                System.out.print("Card rank is the same! Instead, please guess the suit of your card.      ");
                System.out.println("(SPADES: 1 / CLUBS: 2 / DIAMONDS: 3 / HEARTS: 4)");
                playerGuess = scnr.nextInt();

                while (!(playerGuess == 1 || playerGuess == 2 || playerGuess == 3 || playerGuess == 4)) {
                    System.out.print("Please enter a valid number.            ");
                    System.out.println("(SPADES: 1 / CLUBS: 2 / DIAMONDS: 3 / HEARTS: 4)");
                    playerGuess = scnr.nextInt();
                }

                if (playerGuess == playerCard.getSuit()) {
                    System.out.print("Correct!");
                    wins++;
                } else {
                    System.out.print("Incorrect!");
                    losses++;
                }
            }
            System.out.print("    Your card was ");
            playerCard.print_card();
            System.out.println();

            //Add cards back in after playing the round
            deck.add_at_tail(playerCard);
            deck.add_at_tail(computerCard);
            deck.shuffle(2);

            //If player1 loses 3 times, rage_quit()
            if (losses == 3) {
                rage_quit(player1, computer, deck);
            }
            rounds++;
            System.out.println();
        }

        //At the end, display win/loss stats
        System.out.println("===========GAME OVER===========");
        System.out.println("Rounds : " + (rounds - 1));
        System.out.println("Wins : " + wins);
        System.out.println("Losses : " + losses);
        System.out.println("======THANKS FOR PLAYING!======");
    }

    /*  --------------------------------------------------------------------------------------
     *   rage_quit - puts each hand back in the deck, reshuffles, and re-deals the hands
     */
    public static void rage_quit(LinkedList player1, LinkedList computer, LinkedList deck) {
        //Empty hand into main deck
        while (!player1.isEmpty()) { deck.add_at_tail(player1.remove_from_head()); }
        while (!computer.isEmpty()) { deck.add_at_tail(computer.remove_from_head()); }
        deck.shuffle(5);

        //Re-deal cards back to the players
        for (int i = 0; i <= 4; i++) {
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }

        deck.sanity_check();
    }

    /*  --------------------------------------------------------------------------------------
     *   Main
     */
    public static void main(String[] args) {
        // create a deck (in order)
        LinkedList deck = initialize_deck();
        deck.print();
        deck.sanity_check(); // because we can all use one

        // shuffle the deck (random order)
        deck.shuffle(512);
        deck.print();
        deck.sanity_check(); // because we can all use one

        // cards for player 1 (hand)
        LinkedList player1 = new LinkedList();
        // cards for player 2 (hand)
        LinkedList computer = new LinkedList();

        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }

        // let the games begin!
        play_blind_mans_bluff(player1, computer, deck);
    }
}
