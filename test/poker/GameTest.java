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
            "2Ca;2Co;Égalité",
            "ACa;7Co;La main 1 gagne avec la carte la plus haute : A"
    }, delimiter = ';')
    void oneCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(oneCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2Ca 3Co;2Co 3Ca;Égalité",
            "2Ca 2Co;2Pi ACa;La main 1 gagne avec une paire de : 2",
            "2Ca 2Co;4Ca 4Co;La main 2 gagne avec une paire de : 4",
            "2Ca ACo;9Co 7Ca;La main 1 gagne avec la carte la plus haute : A",
            "2Ca 2Co;2Pi 2Tr;Égalité"
    }, delimiter = ';')
    void twoCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(twoCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2Ca 3Co 4Ca;2Co 3Ca 4Co;Égalité",
            "2Ca 2Co 4Ca;2Tr 3Ca 4Tr;La main 1 gagne avec une paire de : 2",
            "2Ca 2Co 4Ca;2Tr 4Tr 4Co;La main 2 gagne avec une paire de : 4",
            "2Ca 2Co ACa;2Tr 2Pi 7Pi;La main 1 gagne avec la carte la plus haute : A"
    }, delimiter = ';')
    void threeCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(threeCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2Pi 3Pi 4Tr 5Pi;2Tr 3Tr 4Co 5Tr;Égalité",
            "2Pi 2Tr 4Pi 5Pi;2Pi 3Tr 4Tr 5Tr;La main 1 gagne avec une paire de : 2",
            "2Ca 2Co 4Pi 6Tr;2Tr 4Pi 4Co 7Ca;La main 2 gagne avec une paire de : 4",
            "2Ca 2Co APi 3Tr;2Tr 2Pi 7Co KCa;La main 1 gagne avec la carte la plus haute : A",
            "2Ca 2Co 3Pi 3Tr;2Tr 2Pi 7Co KCa;La main 1 gagne avec une double paire dont la paire la paire gagnante est paire de : 3",
            "2Ca 2Co 3Pi 3Tr;2Tr 2Pi KCo KCa;La main 2 gagne avec une double paire dont la paire la paire gagnante est paire de : K",
            "ACa ACo 4Pi 4Tr;ATr APi 5Co 5Ca;La main 2 gagne avec une double paire dont la paire la paire gagnante est paire de : 5",
            "ACa ACo APi ATr;2Tr 2Pi 4Co 5Ca;La main 1 gagne avec un carré de : A",
            "ACa ACo APi ATr;KTr KPi KCo KCa;La main 1 gagne avec un carré de : A",
            "ACa ACo 4Pi 4Tr;ATr APi 4Co 4Ca;Égalité"
    }, delimiter = ';')
    void fourCardGameTest(String firstHand, String secondHand, String output) {
        gameTest(fourCardGame, firstHand, secondHand, output);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2Ca 3Co 4Tr 5Pi 6Ca;2Co 3Tr 4Pi 5Ca 6Co;Égalité",
            "2Ca 2Co 4Pi 5Tr 7Ca;2Co 3Pi 4Tr 5Ca 7Co;La main 1 gagne avec une paire de : 2",
            "2Ca 2Co 4Pi 6Tr 7Ca;2Pi 4Tr 4Co 7Co 8Tr;La main 2 gagne avec une paire de : 4",
            "2Ca 2Co ACa 3Ca 4Ca;2Tr 2Pi 7Ca KCa 8Ca;La main 1 gagne avec la carte la plus haute : A",
            "2Ca 2Co 3Ca 3Co 4Ca;2Tr 2Pi 7Tr KPi 4Pi;La main 1 gagne avec une double paire dont la paire la paire gagnante est paire de : 3",
            "2Ca 2Co 3Ca 3Co 4Ca;2Tr 2Pi KTr KPi 4Co;La main 2 gagne avec une double paire dont la paire la paire gagnante est paire de : K",
            "ACa ACo 4Pi 4Tr 7Ca;ACa ACo 5Tr 5Pi 9Pi;La main 2 gagne avec une double paire dont la paire la paire gagnante est paire de : 5",
            "ACa ACo APi ATr 5Ca;2Ca 2Co 4Ca 5Pi 3Pi;La main 1 gagne avec un carré de : A",
            "ACa ACo APi ATr 8Ca;KCa KCo KPi KTr 8Pi;La main 1 gagne avec un carré de : A",
            "ACa ACo 4Pi 4Tr 3Ca;APi ATr 4Ca 4Co 3Co;Égalité",
            "ACa KCo QCa JCo 10Ca;2Ca 3Co 4Ca 5Co 6Ca;La main 1 gagne avec une suite dont la carte la plus haute est : A",
            "7Ca 8Co 9Ca 10Co JCa;8Ca 9Co 10Ca JCo QCa;La main 2 gagne avec une suite dont la carte la plus haute est : Q",
            "7Ca 8Ca 9Ca KCa ACa;8Ca 9Co 10Ca JCo QCa;La main 1 gagne avec une couleur dont la carte la plus haute est : A",
            "7Ca 8Ca 9Ca KCa ACa;5Ca 4Co 10Ca JCa QCa;La main 1 gagne avec une couleur dont la carte la plus haute est : A",
            "7Ca 8Ca 9Ca JCa ACa;8Ca 9Ca 10Ca JCa ACa;La main 2 gagne avec la carte la plus haute : 10",
            "7Ca 8Ca 9Ca JCa ACa;8Co 9Co 10Co JCo ACo;La main 2 gagne avec la carte la plus haute : 10",
            "ACa KCa QCa JCa 10Ca;2Co 8Pi 10Co 10Tr 10Pi;La main 1 gagne avec une quinte flush dont la carte la plus haute est : A",
            "ACa KCa QCa JCa 10Ca;2Co 3Co 4Co 5Co 6Co;La main 1 gagne avec une quinte flush dont la carte la plus haute est : A",
            "7Pi 8Pi 9Pi 10Pi JPi;8Ca 9Ca 10Ca JCa QCa;La main 2 gagne avec une quinte flush dont la carte la plus haute est : Q"
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