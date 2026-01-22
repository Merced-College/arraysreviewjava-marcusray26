import java.util.Random;
import java.util.Scanner;

public class BlackJack {

    //string and int initializing
    private static final String[] SUITS = { "Hearts", "Diamonds", "Clubs", "Spades" }; // suits
    private static final String[] RANKS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King",
            "Ace" }; // ranks
    private static final int[] DECK = new int[52];
    private static int currentCardIndex = 0;

    public static void main(String[] args) {

        //get player input
        Scanner scanner = new Scanner(System.in);

        // card methods
        initializeDeck();
        shuffleDeck();

        // player + dealer card initialized to method
        int playerTotal = dealInitialPlayerCards();
        int dealerTotal = dealInitialDealerCards();

        // Player input
        playerTotal = playerTurn(scanner, playerTotal);
        if (playerTotal > 21) {
            System.out.println("You busted! Dealer wins.");
            return;
        }
        
        // dealer method
        dealerTotal = dealerTurn(dealerTotal);
        determineWinner(playerTotal, dealerTotal);

        //close player input
        scanner.close();
    }
    
    // initialize method
    private static void initializeDeck() {
        for (int i = 0; i < DECK.length; i++) {
            DECK[i] = i;
        }
    }
    
    // shuffle method
    private static void shuffleDeck() {
        Random random = new Random();
        for (int i = 0; i < DECK.length; i++) { // increments
            int index = random.nextInt(DECK.length);
            int temp = DECK[i];
            DECK[i] = DECK[index];
            DECK[index] = temp;
        }

        //prints deck
        System.out.println("printed deck");
        for (int i = 0; i < DECK.length; i++) {
            System.out.println(DECK[i] + " ");
        }
    }
    
    // deals and prints player cards to console
    private static int dealInitialPlayerCards() { 
        int card1 = dealCard(); 
        int card2 = dealCard(); 
        System.out.println("Your cards: " + RANKS[card1] + " of " + SUITS[DECK[currentCardIndex] % 4] + " and "
                + RANKS[card2] + " of " + SUITS[card2 / 13]);
        return cardValue(card1) + cardValue(card2);
    }

    
    private static int dealInitialDealerCards() {
        int card1 = dealCard(); // deals
        System.out.println("Dealer's card: " + RANKS[card1] + " of " + SUITS[DECK[currentCardIndex] % 4]); // prints to console
        return cardValue(card1);
    }

    // hit or stand function
    private static int playerTurn(Scanner scanner, int playerTotal) {
        while (true) {
            System.out.println("Your total is " + playerTotal + ". Do you want to hit or stand?"); // prints total and ask if player wants to hit/stand
            String action = scanner.nextLine().toLowerCase();
            if (action.equals("hit")) { // hit
                int newCard = dealCard();
                playerTotal += cardValue(newCard);
                System.out.println("new card index is " + newCard); // prints new card index
                System.out.println("You drew a " + RANKS[newCard] + " of " + SUITS[DECK[currentCardIndex] % 4]); // prints new drawn 
                if (playerTotal > 21) {
                    break;
                }
            } else if (action.equals("stand")) { // stand, does nothing
                break;
            } else {
                System.out.println("Invalid action. Please type 'hit' or 'stand'."); // prints if input is anything other than "hit" or "stand"
            }
        }
        return playerTotal;
    }

    // dealer stand function
    private static int dealerTurn(int dealerTotal) {
        while (dealerTotal < 17) { 
            int newCard = dealCard();
            dealerTotal += cardValue(newCard);
        }
        System.out.println("Dealer's total is " + dealerTotal); // prints dealer total
        return dealerTotal; 
    }

    // determined winnner
    private static void determineWinner(int playerTotal, int dealerTotal) {
        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("You win!");
        } else if (dealerTotal == playerTotal) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer wins!");
        }
    }

    // determines what card is dealt
    private static int dealCard() {
        return DECK[currentCardIndex++] % 13;
    }

    // unsure
    private static int cardValue(int card) {
        return card < 9 ? card + 2 : 10;
    }
    
    // card index
    int linearSearch(int[] numbers, int key) {
        int i = 0;
        for (i = 0; i < numbers.length; i++) {
            if (numbers[i] == key) {
                return i;
            }
        }
        return -1; // not found
    }
}
