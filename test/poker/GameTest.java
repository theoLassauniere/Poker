package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Team B
 */
class GameTest {
    Game oneCardGame, twoCardGame, threeCardGame, fourCardGame, fiveCardGame;

    /**
     * Initialize Game for testing
     **/
    @BeforeEach
    void setUp() {
        oneCardGame = new Game(1);
        twoCardGame = new Game(2);
        threeCardGame = new Game(3);
        fourCardGame = new Game(4);
        fiveCardGame = new Game(5);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2;2;Égalité",
            "A;7;La main 1 gagne avec la carte la plus haute : A"
    }, delimiter = ';')
    void oneCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(oneCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2 3;2 3;Égalité",
            "2 2;2 A;La main 1 gagne avec une paire de : 2",
            "2 2;4 4;La main 2 gagne avec une paire de : 4",
            "2 A;9 7;La main 1 gagne avec la carte la plus haute : A",
            "2 2;2 2;Égalité"
    }, delimiter = ';')
    void twoCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(twoCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2 3 4;2 3 4;Égalité",
            "2 2 4;2 3 4;La main 1 gagne avec une paire de : 2",
            "2 2 4;2 4 4;La main 2 gagne avec une paire de : 4",
            "2 2 A;2 2 7;La main 1 gagne avec la carte la plus haute : A"
    }, delimiter = ';')
    void threeCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(threeCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2 3 4 5;2 3 4 5;Égalité",
            "2 2 4 5;2 3 4 5;La main 1 gagne avec une paire de : 2",
            "2 2 4 6;2 4 4 7;La main 2 gagne avec une paire de : 4",
            "2 2 A 3;2 2 7 K;La main 1 gagne avec la carte la plus haute : A",
            "2 2 3 3;2 2 7 K;La main 1 gagne avec une double paire dont la paire la paire gagnante est paire de : 3",
            "2 2 3 3;2 2 K K;La main 2 gagne avec une double paire dont la paire la paire gagnante est paire de : K",
            "A A 4 4;A A 5 5;La main 2 gagne avec une double paire dont la paire la paire gagnante est paire de : 5",
            "A A A A;2 2 4 5;La main 1 gagne avec un carré de : A",
            "A A A A;K K K K;La main 1 gagne avec un carré de : A",
            "A A 4 4;A A 4 4;Égalité"
    }, delimiter = ';')
    void fourCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(fourCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2 3 4 5 6;2 3 4 5 6;Égalité",
            "2 2 4 5 7;2 3 4 5 7;La main 1 gagne avec une paire de : 2",
            "2 2 4 6 7;2 4 4 7 8;La main 2 gagne avec une paire de : 4",
            "2 2 A 3 4;2 2 7 K 8;La main 1 gagne avec la carte la plus haute : A",
            "2 2 3 3 4;2 2 7 K 4;La main 1 gagne avec une double paire dont la paire la paire gagnante est paire de : 3",
            "2 2 3 3 4;2 2 K K 4;La main 2 gagne avec une double paire dont la paire la paire gagnante est paire de : K",
            "A A 4 4 7;A A 5 5 9;La main 2 gagne avec une double paire dont la paire la paire gagnante est paire de : 5",
            "A A A A 5;2 2 4 5 3;La main 1 gagne avec un carré de : A",
            "A A A A 8;K K K K 8;La main 1 gagne avec un carré de : A",
            "A A 4 4 3;A A 4 4 3;Égalité",
            "A K Q J 10;2 3 4 5 6;La main 1 gagne avec une suite dont la carte la plus haute est : A",
            "7 8 9 10 J;8 9 10 J Q;La main 2 gagne avec une suite dont la carte la plus haute est : Q"
    }, delimiter = ';')
    void fiveCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(fiveCardGame, firstHand, secondHand, output);
    }

    private void gameTest(Game game, String firstHand, String secondHand, String output) {
        // Redirect stdin and stdout
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream((firstHand + "\n" + secondHand).getBytes()));
        System.setOut(new PrintStream(outputStream));

        assertDoesNotThrow(game::start); // The game should start without errors

        String methodOutput = outputStream.toString().trim(); // Reads the output stream
        assertEquals(output, methodOutput.substring("Main 1: Main 2: ".length()));
    }
}