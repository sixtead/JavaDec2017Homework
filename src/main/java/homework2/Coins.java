package homework2;

import java.util.ArrayList;
import java.util.List;

public class Coins {
    private List<Coin> coins;

    Coins() {
        this.coins = new ArrayList<>();
    }

    public boolean add(Coin coin) { return coins.add(coin); }

    public Coin set(int index, Coin newCoin) {
        if(index < 0 || index > coins.size()) throw new IndexOutOfBoundsException();

        Coin oldCoin = coins.get(index);
        coins.set(index, newCoin);

        return oldCoin;
    }

    public Coin get(int index) {
        if(index < 0 || index > coins.size()) throw new IndexOutOfBoundsException();

        return coins.get(index);
    }

    public Coin fake() {
        return new Coin(0);
    }

}
