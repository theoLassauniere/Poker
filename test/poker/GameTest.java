package poker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    Game oneCardGame,twoCardGame,threeCardGame;

    /**
     * Initialize Game for testing
     **/
    @BeforeEach
    void setUp() {
        oneCardGame = new Game(1);
        twoCardGame = new Game(2);
        threeCardGame = new Game(3);
    }

    @Test
    void equalityTest(){
            InputStream inputStream = new ByteArrayInputStream("2 3 4\n2 3 4".getBytes());
            System.setIn(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            try{
                threeCardGame.start();
            }
            catch (IllegalArgumentException e){
                System.out.println("Problème avec les entrées");
            }
            catch (ParseException e){
                System.out.println("Problème durant le parsing");
            }

            String methodOutput = outputStream.toString().trim();
            assertEquals("Egalité", methodOutput.substring(16));
    }

    @Test
    void oneHandPairTest(){
        InputStream inputStream = new ByteArrayInputStream("2 2 4\n2 3 4".getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try{
            threeCardGame.start();
        }
        catch (IllegalArgumentException e){
            System.out.println("Problème avec les entrées");
        }
        catch (ParseException e){
            System.out.println("Problème durant le parsing");
        }

        String methodOutput = outputStream.toString().trim();
        assertEquals("La main 1 gagne avec une paire de : 2", methodOutput.substring(16));

    }

    @Test
    void twoHandDifferentPairTest(){
        InputStream inputStream = new ByteArrayInputStream("2 2 4\n2 4 4".getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try{
            threeCardGame.start();
        }
        catch (IllegalArgumentException e){
            System.out.println("Problème avec les entrées");
        }
        catch (ParseException e){
            System.out.println("Problème durant le parsing");
        }

        String methodOutput = outputStream.toString().trim();
        assertEquals("La main 2 gagne avec une paire de : 4", methodOutput.substring(16));

    }

    @Test
    void twoHandSamePairTest(){
        InputStream inputStream = new ByteArrayInputStream("2 2 A\n2 2 7".getBytes());
        System.setIn(inputStream);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        try{
            threeCardGame.start();
        }
        catch (IllegalArgumentException e){
            System.out.println("Problème avec les entrées");
        }
        catch (ParseException e){
            System.out.println("Problème durant le parsing");
        }

        String methodOutput = outputStream.toString().trim();
        assertEquals("La main 1 gagne avec la carte la plus haute : 14", methodOutput.substring(16));
    }

}