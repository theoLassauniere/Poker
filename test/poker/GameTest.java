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
    Game oneCardGame, twoCardGame, threeCardGame;

    /**
     * Initialize Game for testing
     **/
    @BeforeEach
    void setUp() {
        oneCardGame = new Game(1);
        twoCardGame = new Game(2);
        threeCardGame = new Game(3);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2 3 4;2 3 4;Egalité",
            "2 2 4;2 3 4;La main 1 gagne avec une paire de : 2",
            "2 2 4;2 4 4;La main 2 gagne avec une paire de : 4",
            "2 2 A;2 2 7;La main 1 gagne avec la carte la plus haute : A"
    }, delimiter = ';')
    void threeCardGameTest(String firstHand, String secondHand, String output) {
        // Redirect stdin and stdout
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream((firstHand + "\n" + secondHand).getBytes()));
        System.setOut(new PrintStream(outputStream));

        assertDoesNotThrow(() -> threeCardGame.start()); // The game should start without errors

        String methodOutput = outputStream.toString().trim(); // Reads the output stream
        assertEquals(output, methodOutput.substring("Main 1: Main 2: ".length()));
    }

}