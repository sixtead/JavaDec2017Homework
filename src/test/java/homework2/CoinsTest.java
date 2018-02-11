package homework2;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CoinsTest {
    private Coins coins;

    @BeforeEach
    void setUp() {
        coins = new Coins();
        coins.add(new Coin(1));
        coins.add(new Coin(1));
        coins.add(new Coin(1));
        coins.add(new Coin(1));
        coins.add(new Coin(1));
        coins.add(new Coin(1));
        coins.add(new Coin(1));
        coins.add(new Coin(1));
        coins.add(new Coin(1));
        coins.add(new Coin(1));
        coins.add(new Coin(1));
        coins.add(new Coin(1));
    }

    @Test
    @DisplayName("test when first coin is fake and it lighter than other coin")
    void testFakeFirstLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(0, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when first coin is fake and it heavier than other coin")
    void testFakeFirstHeavier() {
        Coin fakeCoin = new Coin(1.5);
        coins.set(0, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when second coin is fake and it lighter than other coin")
    void testFakeSecondLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(1, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when second coin is fake and it heavier than other coin")
    void testFakeSecondHeavier() {
        Coin fakeCoin = new Coin(1.5);
        coins.set(1, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when third coin is fake and it lighter than other coin")
    void testFakeThirdLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(2, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when third coin is fake and it heavier than other coin")
    void testFakeThirdHeavier() {
        Coin fakeCoin = new Coin(1.5);
        coins.set(2, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when fourth coin is fake and it lighter than other coin")
    void testFakeFourthLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(3, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when fourth coin is fake and it heavier than other coin")
    void testFakeFourthHeavier() {
        Coin fakeCoin = new Coin(1.5);
        coins.set(3, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when fifths coin is fake and it lighter than other coin")
    void testFakeFifthLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(4, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when fifths coin is fake and it heavier than other coin")
    void testFakeFifthHeavier() {
        Coin fakeCoin = new Coin(1.5);
        coins.set(4, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when sixth coin is fake and it lighter than other coin")
    void testFakeSixthLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(5, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when sixth coin is fake and it heavier than other coin")
    void testFakeSixthHeavier() {
        Coin fakeCoin = new Coin(1.5);
        coins.set(5, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when seventh coin is fake and it lighter than other coin")
    void testFakeSeventhLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(6, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when seventh coin is fake and it heavier than other coin")
    void testFakeSeventhHeavier() {
        Coin fakeCoin = new Coin(1.5);
        coins.set(6, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when eighth coin is fake and it lighter than other coin")
    void testFakeEighthLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(7, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when eighth coin is fake and it heavier than other coin")
    void testFakeEighthHeavier() {
        Coin fakeCoin = new Coin(1.5);
        coins.set(7, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when ninth coin is fake and it lighter than other coin")
    void testFakeNinthLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(8, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when ninth coin is fake and it heavier than other coin")
    void testFakeNinthHeavier() {
        Coin fakeCoin = new Coin(1.5);
        coins.set(8, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when tenth coin is fake and it lighter than other coin")
    void testFakeTenthLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(9, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when tenth coin is fake and it heavier than other coin")
    void testFakeTenthHeavier() {
        Coin fakeCoin = new Coin(1.5);
        coins.set(9, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when eleventh coin is fake and it lighter than other coin")
    void testFakeEleventhLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(10, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when eleventh coin is fake and it heavier than other coin")
    void testFakeEleventhHeavier() {
        Coin fakeCoin = new Coin(1.5);
        coins.set(10, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when twelfth coin is fake and it lighter than other coin")
    void testFakeTwelfthLighter() {
        Coin fakeCoin = new Coin(0.5);
        coins.set(11, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }

    @Test
    @DisplayName("test when twelfth coin is fake and it heavier than other coin")
    void testFakeTwelfthHeavier(Coins coins) {
        Coin fakeCoin = new Coin(1.5);
        coins.set(11, fakeCoin);
        assertEquals(fakeCoin, coins.fake());
    }
}