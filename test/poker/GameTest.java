package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Team B
 */
class GameTest {
    ByteArrayOutputStream outputStream;
    Game defaultGame, oneCardGame, twoCardGame, threeCardGame, fourCardGame, fiveCardGame,

    fourPlayerGame;

    /**
     * Initialize Game for testing
     **/
    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        Game.outputStream = new PrintStream(outputStream);
        defaultGame = new Game();
        oneCardGame = new Game(1);
        twoCardGame = new Game(2);
        threeCardGame = new Game(3);
        fourCardGame = new Game(4);
        fiveCardGame = new Game(5);
        fourPlayerGame = new Game(5, 4, 0);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2Ca;2Co;Égalité",
            "ACa;7Co;Main 1 (ACa) gagne avec la carte la plus haute : ACa"
    }, delimiter = ';')
    void oneCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(oneCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2Ca 3Co;2Co 3Ca;Égalité",
            "2Ca 2Co;2Pi ACa;Main 1 (2Ca 2Co) gagne avec une paire de : 2",
            "2Ca 2Co;4Ca 4Co;Main 2 (4Ca 4Co) gagne avec une paire de : 4",
            "2Ca ACo;9Co 7Ca;Main 1 (ACo 2Ca) gagne avec la carte la plus haute : ACo",
            "2Ca 2Co;2Pi 2Tr;Égalité"
    }, delimiter = ';')
    void twoCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(twoCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2Ca 3Co 4Ca;2Co 3Ca 4Co;Égalité",
            "2Ca 2Co 4Ca;2Tr 3Ca 4Tr;Main 1 (4Ca 2Co 2Ca) gagne avec une paire de : 2",
            "2Ca 2Co 4Ca;2Tr 4Tr 4Co;Main 2 (4Tr 4Co 2Tr) gagne avec une paire de : 4",
            "2Ca 2Co ACa;2Tr 2Pi 7Pi;Main 1 (ACa 2Co 2Ca) gagne avec la carte la plus haute : ACa",
            "2Ca 2Co 2Tr;4Tr 4Ca 3Co;Main 1 (2Ca 2Co 2Tr) gagne avec un brelan de : 2",
            "2Ca 2Co 2Tr;4Tr 4Ca 4Co;Main 2 (4Tr 4Ca 4Co) gagne avec un brelan de : 4",
    }, delimiter = ';')
    void threeCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(threeCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2Pi 3Pi 4Tr 5Pi;2Tr 3Tr 4Co 5Tr;Égalité",
            "2Pi 2Tr 4Pi 5Pi;2Ca 3Tr 4Tr 5Tr;Main 1 (5Pi 4Pi 2Tr 2Pi) gagne avec une paire de : 2",
            "2Ca 2Co 4Pi 6Tr;2Tr 4Tr 4Co 7Ca;Main 2 (7Ca 4Tr 4Co 2Tr) gagne avec une paire de : 4",
            "2Ca 2Co APi 3Tr;2Tr 2Pi 7Co KCa;Main 1 (APi 3Tr 2Ca 2Co) gagne avec la carte la plus haute : APi",
            "2Ca 2Co 3Pi 3Tr;2Tr APi 7Co KCa;Main 1 (3Pi 3Tr 2Ca 2Co) gagne avec une double paire dont la paire gagnante est 3",
            "2Ca 2Co 3Pi 3Tr;2Tr 2Pi 7Co KCa;Main 1 (3Pi 3Tr 2Ca 2Co) gagne avec une double paire dont la paire gagnante est 3",
            "ACa ACo 4Pi 4Tr;ATr APi 5Co 5Ca;Main 2 (ATr APi 5Co 5Ca) gagne avec une double paire dont la paire gagnante est 5",
            "ACa ACo 4Pi 4Tr;ATr APi 4Co 4Ca;Égalité",
            "ACa ACo APi ATr;2Tr 2Pi 2Co 5Ca;Main 1 (ACa ACo APi ATr) gagne avec un carré de : A",
            "ACa ACo APi ATr;KTr KPi KCo KCa;Main 1 (ACa ACo APi ATr) gagne avec un carré de : A",
            "ACa ACo 4Pi 4Tr;ATr APi 4Co 4Ca;Égalité"
    }, delimiter = ';')
    void fourCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(fourCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2Ca 3Co 4Tr 5Pi 6Ca;2Co 3Tr 4Pi 5Ca 6Co;Égalité",
            "2Ca 2Co 4Pi 5Tr 7Ca;2Tr 3Pi 4Tr 5Ca 7Co;Main 1 (7Ca 5Tr 4Pi 2Co 2Ca) gagne avec une paire de : 2",
            "2Ca 2Co 4Pi 6Tr 7Ca;2Pi 4Tr 4Co 7Co 8Tr;Main 2 (8Tr 7Co 4Co 4Tr 2Pi) gagne avec une paire de : 4",
            "2Ca 2Co ACa 3Ca 4Ca;2Tr 2Pi 7Ca KCa 8Ca;Main 1 (ACa 4Ca 3Ca 2Ca 2Co) gagne avec la carte la plus haute : ACa",
            "2Ca 2Co 3Ca 3Co 4Ca;2Tr 2Pi 7Tr KPi 4Pi;Main 1 (4Ca 3Ca 3Co 2Co 2Ca) gagne avec une double paire dont la paire gagnante est 3",
            "2Ca 2Co 3Ca 3Co 4Ca;2Tr 2Pi KTr KPi 4Co;Main 2 (KTr KPi 4Co 2Pi 2Tr) gagne avec une double paire dont la paire gagnante est K",
            "ACa ACo 4Pi 4Tr 7Ca;ATr APi 5Tr 5Pi 9Pi;Main 2 (ATr APi 9Pi 5Pi 5Tr) gagne avec une double paire dont la paire gagnante est 5",
            "ACa ACo APi ATr 5Ca;2Ca 2Co 4Ca 5Pi 3Pi;Main 1 (ACa ACo APi ATr 5Ca) gagne avec un carré de : A",
            "ACa ACo APi ATr 8Ca;KCa KCo KPi KTr 8Pi;Main 1 (ACa ACo APi ATr 8Ca) gagne avec un carré de : A",
            "ACa ACo 4Pi 4Tr 3Ca;APi ATr 4Ca 4Co 3Co;Égalité",
            "ACa KCo QCa JCo 10Ca;2Ca KCa 7Ca 10Co 6Ca;Main 1 (ACa KCo QCa JCo 10Ca) gagne avec une suite dont la carte la plus haute est ACa",
            "ACa KCo QCa JCo 10Ca;2Ca 3Co 4Ca 5Co 6Ca;Main 1 (ACa KCo QCa JCo 10Ca) gagne avec une suite dont la carte la plus haute est ACa",
            "7Ca 8Co 9Ca 10Co JCa;8Ca 9Co 10Ca JCo QCa;Main 2 (QCa JCo 10Ca 9Co 8Ca) gagne avec une suite dont la carte la plus haute est QCa",
            "7Ca 8Ca 9Ca KCa ACa;8Co 9Co 10Ca JCo QCa;Main 1 (ACa KCa 9Ca 8Ca 7Ca) gagne avec une couleur dont la carte décisive est ACa",
            "7Ca 8Ca 9Ca KCa ACa;5Ca 4Co 10Ca JCa QCa;Main 1 (ACa KCa 9Ca 8Ca 7Ca) gagne avec une couleur dont la carte décisive est ACa",
            "7Ca 8Ca 9Ca JCa ACa;8Co 9Co 10Co JCo ACo;Main 2 (ACo JCo 10Co 9Co 8Co) gagne avec une couleur dont la carte décisive est 10Co",
            "ACa KCa QCa JCa 10Ca;2Co 8Pi 10Co 10Tr 10Pi;Main 1 (ACa KCa QCa JCa 10Ca) gagne avec une quinte flush dont la carte la plus haute est ACa",
            "ACa KCa QCa JCa 10Ca;2Co 3Co 4Co 5Co 6Co;Main 1 (ACa KCa QCa JCa 10Ca) gagne avec une quinte flush dont la carte la plus haute est ACa",
            "7Pi 8Pi 9Pi 10Pi JPi;8Ca 9Ca 10Ca JCa QCa;Main 2 (QCa JCa 10Ca 9Ca 8Ca) gagne avec une quinte flush dont la carte la plus haute est QCa",
            "KCo KCa KPi 7Ca 7Co;ACa ACo APi 6Ca 6Co;Main 2 (ACa ACo APi 6Ca 6Co) gagne avec un full contenant un brelan de : A",
            "2Ca 2Co 2Pi 3Co 3Ca;7Ca 8Ca 9Ca KCa ACa;Main 1 (3Co 3Ca 2Pi 2Ca 2Co) gagne avec un full contenant un brelan de : 2",
            "2Ca 2Co 2Pi 3Co 3Ca;7Ca 7Co 7Pi KCa ACa;Main 1 (3Co 3Ca 2Pi 2Ca 2Co) gagne avec un full contenant un brelan de : 2",
            "2Ca 2Co 2Pi ACo ACa;7Ca 7Co 7Pi 3Ca 3Co;Main 2 (7Ca 7Co 7Pi 3Ca 3Co) gagne avec un full contenant un brelan de : 7"
    }, delimiter = ';')
    void fiveCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(fiveCardGame, firstHand, secondHand, output);
    }

    private void gameTest(Game game, String firstHand, String secondHand, String output) {
        System.setIn(new ByteArrayInputStream((firstHand + "\n" + secondHand).getBytes())); // Redirect stdin
        assertDoesNotThrow(game::poker); // The game should start without errors

        String methodOutput = outputStream.toString().trim(); // Reads the output stream
        assertEquals(output, methodOutput.substring("Main 1: Main 2: ".length()));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "APi KPi QPi JPi 10Pi;APi KCa QCa JCa 10Ca",
            "APi APi QPi JPi 10Pi;ACa KCa QCa JCa 10Ca",
            "APi KPi QPi JPi 10Pi;ACa ACa QCa JCa 10Ca",
            "APi APi APi APi APi;APi APi QCa JCa 10Ca"

    }, delimiter = ';')
    void duplicationTest(String firstHand, String secondHand) {
        System.setIn(new ByteArrayInputStream((firstHand + "\n" + secondHand).getBytes())); // Redirect stdin
        assertThrowsExactly(IllegalArgumentException.class, fiveCardGame::poker); // The game should start with errors
    }

    //Non-exhaustive test to know if it's possible to have an error in texasHold'em game
    @Test
    void texasTest() {
        for (int k = 1; k < 100; k++) {
            System.setIn(new ByteArrayInputStream(("a\na\na\na").getBytes())); // Redirect stdin
            assertDoesNotThrow(fourPlayerGame::texasHoldem);// The game should start without errors
            fourPlayerGame = new Game(5, 4, k);
        }
    }

    //Test for getRandomCard method
    @Test
    void getCRandomCardTest() {
        fourPlayerGame = new Game(5, 4, 40);
        List<Card> cardsGets = new ArrayList<>();
        List<Card> deckCopy = new ArrayList<>(fourPlayerGame.getDeck());
        for (int i = 0; i < deckCopy.size(); i++) cardsGets.add(fourPlayerGame.getRandomCard());
        assertTrue(fourPlayerGame.getDeck().isEmpty()); //Check if the deck is empty after removing all cards
        for (Card c : deckCopy) assertTrue(cardsGets.contains(c));//Check if the collection of remove cards is the deck
    }

    //Test for extraction method
    @Test
    void extractionTest() {
        fourPlayerGame = new Game(5, 4, 40);
        Hand hand1 = new Hand(new ArrayList<>(List.of(new Card(Value.QUEEN, Color.HEARTS), new Card(Value.TWO, Color.SPADES))));
        Hand hand2 = new Hand(new ArrayList<>(List.of(new Card(Value.JACK, Color.DIAMONDS), new Card(Value.TWO, Color.CLUBS))));
        Hand hand3 = new Hand(new ArrayList<>(List.of(new Card(Value.TEN, Color.HEARTS), new Card(Value.THREE, Color.HEARTS))));
        Hand hand4 = new Hand(new ArrayList<>(List.of(new Card(Value.TEN, Color.CLUBS), new Card(Value.NINE, Color.HEARTS))));
        Hand[] handVerify = {hand1, hand2, hand3, hand4};
        for (int i = 0; i < fourPlayerGame.getHand().length; i++) {
            fourPlayerGame.getHand()[i] = new Hand(new ArrayList<>(List.of(fourPlayerGame.getRandomCard())));
            fourPlayerGame.getHand()[i].addCards(List.of(fourPlayerGame.getRandomCard()));
            assertEquals(fourPlayerGame.getHand()[i], handVerify[i]);
        }
        List<Card> flopVerify = new ArrayList<>(List.of(new Card(Value.KING, Color.DIAMONDS), new Card(Value.FIVE, Color.CLUBS), new Card(Value.QUEEN, Color.DIAMONDS)));
        List<Card> turnVerify = new ArrayList<>(List.of(new Card(Value.ACE, Color.DIAMONDS)));
        List<Card> riverVerify = new ArrayList<>(List.of(new Card(Value.JACK, Color.CLUBS)));
        List<List<Card>> stepVerify = new ArrayList<>(List.of(flopVerify, turnVerify, riverVerify));
        List<AbstractMap.SimpleEntry<String, Integer>> steps = List.of(new AbstractMap.SimpleEntry<>("Flop", 3), new AbstractMap.SimpleEntry<>("Turn", 1), new AbstractMap.SimpleEntry<>("River", 1));
        for (int k = 0; k < steps.size(); k++) {
            List<Card> step = fourPlayerGame.extraction(steps.get(k).getValue());
            assertEquals(step, stepVerify.get(k));
            for (int i = 0; i < fourPlayerGame.getHand().length; i++) {
                handVerify[i].addCards(stepVerify.get(k));
                assertEquals(fourPlayerGame.getHand()[i], handVerify[i]);
            }
        }
    }

    //Four different tests for tournament and tournamentResult methods, each time we compare the result of the method on a precise game which we know all the steps during the "tournament" and the result
    @Test
    void tournamentTest() {
        fourPlayerGame = new Game(5, 4, 40);
        Hand hand1 = new Hand("Main 1", new ArrayList<>(List.of(new Card(Value.ACE, Color.DIAMONDS), new Card(Value.KING, Color.DIAMONDS), new Card(Value.QUEEN, Color.DIAMONDS), new Card(Value.QUEEN, Color.HEARTS), new Card(Value.JACK, Color.CLUBS))));
        Hand hand2 = new Hand("Main 2", new ArrayList<>(List.of(new Card(Value.ACE, Color.DIAMONDS), new Card(Value.KING, Color.DIAMONDS), new Card(Value.QUEEN, Color.DIAMONDS), new Card(Value.JACK, Color.CLUBS), new Card(Value.JACK, Color.DIAMONDS))));
        Hand hand3 = new Hand("Main 3", new ArrayList<>(List.of(new Card(Value.ACE, Color.DIAMONDS), new Card(Value.KING, Color.DIAMONDS), new Card(Value.QUEEN, Color.DIAMONDS), new Card(Value.JACK, Color.CLUBS), new Card(Value.TEN, Color.HEARTS))));
        Hand hand4 = new Hand("Main 4", new ArrayList<>(List.of(new Card(Value.ACE, Color.DIAMONDS), new Card(Value.KING, Color.DIAMONDS), new Card(Value.QUEEN, Color.DIAMONDS), new Card(Value.JACK, Color.CLUBS), new Card(Value.TEN, Color.CLUBS))));
        Hand[] hands = {hand1, hand2, hand3, hand4};
        for (int k = 0; k < fourPlayerGame.getHand().length; k++) {
            fourPlayerGame.getHand()[k] = hands[k];
        }
        SimpleEntry<ArrayList<Hand>, Map<Hand, ArrayList<Winner>>> result = fourPlayerGame.tournament();
        SimpleEntry<ArrayList<Hand>, Map<Hand, ArrayList<Winner>>> resultExpected = new SimpleEntry<>(new ArrayList<>(List.of(hand3)), new HashMap<>());
        Winner winner1 = new Winner(hand1, Patterns.PAIR, new Card(Value.QUEEN, Color.DIAMONDS));
        Winner winner2 = new Winner(hand3, Patterns.STRAIGHT, new Card(Value.ACE, Color.DIAMONDS));
        Winner winner3 = new Winner(null, Patterns.EQUALITY, null);
        resultExpected.getValue().put(hand1, new ArrayList<>(List.of(winner1)));
        resultExpected.getValue().put(hand3, new ArrayList<>(List.of(winner2)));
        resultExpected.getValue().get(hand3).add(winner3);
        assertEquals(resultExpected, result);
        assertEquals(fourPlayerGame.tournamentResult(resultExpected), winner3);

        fourPlayerGame = new Game(5, 4, 8);
        hand1 = new Hand("Main 1", new ArrayList<>(List.of(new Card(Value.ACE, Color.DIAMONDS), new Card(Value.KING, Color.DIAMONDS), new Card(Value.JACK, Color.HEARTS), new Card(Value.NINE, Color.CLUBS), new Card(Value.SEVEN, Color.HEARTS))));
        hand2 = new Hand("Main 2", new ArrayList<>(List.of(new Card(Value.ACE, Color.DIAMONDS), new Card(Value.KING, Color.DIAMONDS), new Card(Value.JACK, Color.HEARTS), new Card(Value.NINE, Color.CLUBS), new Card(Value.SEVEN, Color.HEARTS))));
        hand3 = new Hand("Main 3", new ArrayList<>(List.of(new Card(Value.ACE, Color.DIAMONDS), new Card(Value.ACE, Color.SPADES), new Card(Value.KING, Color.DIAMONDS), new Card(Value.JACK, Color.HEARTS), new Card(Value.TEN, Color.HEARTS))));
        hand4 = new Hand("Main 4", new ArrayList<>(List.of(new Card(Value.ACE, Color.DIAMONDS), new Card(Value.KING, Color.DIAMONDS), new Card(Value.JACK, Color.HEARTS), new Card(Value.SEVEN, Color.HEARTS), new Card(Value.SEVEN, Color.SPADES))));
        hands = new Hand[]{hand1, hand2, hand3, hand4};
        for (int k = 0; k < fourPlayerGame.getHand().length; k++) {
            fourPlayerGame.getHand()[k] = hands[k];
        }
        result = fourPlayerGame.tournament();
        resultExpected = new SimpleEntry<>(new ArrayList<>(List.of(hand3)), new HashMap<>());
        winner1 = new Winner(null, Patterns.EQUALITY, null);
        winner2 = new Winner(hand3, Patterns.PAIR, new Card(Value.ACE, Color.DIAMONDS));
        resultExpected.getValue().put(hand1, new ArrayList<>(List.of(winner1)));
        resultExpected.getValue().put(hand3, new ArrayList<>(List.of(winner2)));
        resultExpected.getValue().get(hand3).add(winner2);
        assertEquals(resultExpected, result);
        assertEquals(fourPlayerGame.tournamentResult(resultExpected), winner2);

        fourPlayerGame = new Game(5, 4, 8);
        hand1 = new Hand("Main 1", new ArrayList<>(List.of(new Card(Value.ACE, Color.HEARTS), new Card(Value.ACE, Color.SPADES), new Card(Value.QUEEN, Color.HEARTS), new Card(Value.FOUR, Color.HEARTS), new Card(Value.FOUR, Color.SPADES))));
        hand2 = new Hand("Main 2", new ArrayList<>(List.of(new Card(Value.ACE, Color.HEARTS), new Card(Value.ACE, Color.SPADES), new Card(Value.QUEEN, Color.HEARTS), new Card(Value.JACK, Color.DIAMONDS), new Card(Value.EIGHT, Color.DIAMONDS))));
        hand3 = new Hand("Main 3", new ArrayList<>(List.of(new Card(Value.ACE, Color.HEARTS), new Card(Value.ACE, Color.SPADES), new Card(Value.QUEEN, Color.HEARTS), new Card(Value.TEN, Color.HEARTS), new Card(Value.FIVE, Color.SPADES))));
        hand4 = new Hand("Main 4", new ArrayList<>(List.of(new Card(Value.ACE, Color.HEARTS), new Card(Value.ACE, Color.SPADES), new Card(Value.KING, Color.HEARTS), new Card(Value.KING, Color.CLUBS), new Card(Value.QUEEN, Color.HEARTS))));
        hands = new Hand[]{hand1, hand2, hand3, hand4};
        for (int k = 0; k < fourPlayerGame.getHand().length; k++) {
            fourPlayerGame.getHand()[k] = hands[k];
        }
        result = fourPlayerGame.tournament();

        resultExpected = new SimpleEntry<>(new ArrayList<>(List.of(hand4)), new HashMap<>());
        winner1 = new Winner(hand1, Patterns.DOUBLE_PAIR, new Card(Value.ACE, Color.HEARTS));
        winner2 = new Winner(hand4, Patterns.DOUBLE_PAIR, new Card(Value.KING, Color.HEARTS));
        resultExpected.getValue().put(hand1, new ArrayList<>(List.of(winner1)));
        resultExpected.getValue().get(hand1).add(winner1);
        resultExpected.getValue().put(hand4, new ArrayList<>(List.of(winner2)));

        assertEquals(resultExpected, result);
        assertEquals(fourPlayerGame.tournamentResult(resultExpected), winner2);

        fourPlayerGame = new Game(5, 4, 89);
        hand1 = new Hand("Main 1", new ArrayList<>(List.of(new Card(Value.KING, Color.DIAMONDS), new Card(Value.QUEEN, Color.SPADES), new Card(Value.TEN, Color.DIAMONDS), new Card(Value.FIVE, Color.DIAMONDS), new Card(Value.FIVE, Color.HEARTS))));
        hand2 = new Hand("Main 2", new ArrayList<>(List.of(new Card(Value.KING, Color.DIAMONDS), new Card(Value.QUEEN, Color.SPADES), new Card(Value.TEN, Color.DIAMONDS), new Card(Value.TEN, Color.HEARTS), new Card(Value.NINE, Color.DIAMONDS))));
        hand3 = new Hand("Main 3", new ArrayList<>(List.of(new Card(Value.KING, Color.DIAMONDS), new Card(Value.QUEEN, Color.SPADES), new Card(Value.QUEEN, Color.CLUBS), new Card(Value.JACK, Color.SPADES), new Card(Value.TEN, Color.DIAMONDS))));
        hand4 = new Hand("Main 4", new ArrayList<>(List.of(new Card(Value.KING, Color.DIAMONDS), new Card(Value.QUEEN, Color.SPADES), new Card(Value.QUEEN, Color.HEARTS), new Card(Value.TEN, Color.DIAMONDS), new Card(Value.FIVE, Color.HEARTS))));
        hands = new Hand[]{hand1, hand2, hand3, hand4};
        for (int k = 0; k < fourPlayerGame.getHand().length; k++) {
            fourPlayerGame.getHand()[k] = hands[k];
        }
        result = fourPlayerGame.tournament();

        resultExpected = new SimpleEntry<>(new ArrayList<>(List.of(hand3)), new HashMap<>());
        winner1 = new Winner(hand2, Patterns.PAIR, new Card(Value.TEN, Color.DIAMONDS));
        winner2 = new Winner(hand3, Patterns.PAIR, new Card(Value.QUEEN, Color.SPADES));
        winner3 = new Winner(hand3, Patterns.HIGHER, new Card(Value.JACK, Color.SPADES));
        resultExpected.getValue().put(hand2, new ArrayList<>(List.of(winner1)));
        resultExpected.getValue().put(hand3, new ArrayList<>(List.of(winner2)));
        resultExpected.getValue().get(hand3).add(winner3);
        assertEquals(resultExpected, result);
        assertEquals(fourPlayerGame.tournamentResult(resultExpected), winner3);
    }
}