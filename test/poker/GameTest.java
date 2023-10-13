package poker;

import org.junit.jupiter.api.BeforeEach;

class GameTest {
    Game oneCardGame;

    /**
     * Initialize Game for testing
     **/
    @BeforeEach
    void setUp() {
        oneCardGame = new Game(1);
    }
}