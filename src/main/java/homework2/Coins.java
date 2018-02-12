package homework2;

import java.util.ArrayList;
import java.util.List;

public class Coins {
    private List<Coin> coins;

    Coins() {
        this.coins = new ArrayList<>();
    }

    public boolean add(Coin coin) { return coins.add(coin); }

    public boolean addAll(Coin[] coinsToAdd) {
        for(Coin coin : coinsToAdd) {
            if(!coins.add(coin)) return false;
        }

        return true;
    }

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

    public double getWeight() {
        double weight = 0;

        for(Coin coin : coins) {
            weight += coin.getWeight();
        }

        return weight;
    }

    public Coin fake() {
        Coins leftScale = new Coins();
        Coins rightScale = new Coins();
        // Add coins 1, 2, 3, 4 to left scalepan
        leftScale.addAll(new Coin[] {coins.get(0), coins.get(1), coins.get(2), coins.get(3)});
        // Add coins 5, 6, 7, 8 to right scalepan
        rightScale.addAll(new Coin[] {coins.get(4), coins.get(5), coins.get(6), coins.get(7)});

    // First weighing
        // If left scalepan is lower
        if(leftScale.getWeight() > rightScale.getWeight()) {
            leftScale = new Coins();
            rightScale = new Coins();
            // Add coins 1, 2, 5 to left scalepan
            leftScale.addAll(new Coin[] {coins.get(0), coins.get(1), coins.get(4)});
            // Add coins 3, 4, 6 to right scalepan
            rightScale.addAll(new Coin[] {coins.get(2), coins.get(3), coins.get(5)});
        // Second weighing
            // If left scalepan is lower
            if(leftScale.getWeight() > rightScale.getWeight()) {
            // Third weighing
                // Compare coins 1 and 2
                    // If left scalepan is lower then coin 1 is fake
                if(coins.get(0).getWeight() > coins.get(1).getWeight()) {
                    return coins.get(0);
                    // If right scalepan is lower then coin 2 is fake
                } else if(coins.get(0).getWeight() < coins.get(1).getWeight()) {
                    return coins.get(1);
                } else {
                    // If scalepans are equal then coin 6 is fake
                    return coins.get(5);
                }
        // Second weighing
            // If right scalepan is lower
            } else if(leftScale.getWeight() < rightScale.getWeight()) {
            // Third weighing
                // Compare coins 3 and 4
                    // If left scalepan is lower then coin 3 is fake
                if(coins.get(2).getWeight() > coins.get(3).getWeight()) {
                    return coins.get(2);
                    // If right scalepan is lower then coin 4 is fake
                } else if(coins.get(2).getWeight() < coins.get(3).getWeight()) {
                    return coins.get(3);
                } else {
                    // If scalepans are equal then coin 5 is fake
                    return coins.get(4);
                }
            } else {
            // Third weighing
                // Compare coins 7 and 8
                    // If left scalepan is lower then coin 8 is fake
                if(coins.get(6).getWeight() > coins.get(7).getWeight()) {
                    return coins.get(7);
                    // If right scalepan is lower the coin 7 is fake
                } else if(coins.get(6).getWeight() < coins.get(7).getWeight()) {
                    return coins.get(6);
                } else {
                    throw new RuntimeException("Something went wrong...");
                }
            }
    // First weighing
        // If right scalepan is lower
        } else if(leftScale.getWeight() < rightScale.getWeight()) {
            leftScale = new Coins();
            rightScale = new Coins();
            // Add coins 1, 2, 5 to left scalepan
            leftScale.addAll(new Coin[] {coins.get(0), coins.get(1), coins.get(4)});
            // Add coins 3, 4, 6 to right scalepan
            rightScale.addAll(new Coin[] {coins.get(2), coins.get(3), coins.get(5)});
        // Second weighing
            // If left scalepan is lower
            if(leftScale.getWeight() > rightScale.getWeight()) {
            // Third weighing
                // Compare coins 3 and 4
                    // If left scalepan is lower then coin 4 is fake
                if(coins.get(2).getWeight() > coins.get(3).getWeight()) {
                    return coins.get(3);
                    // If right scalepan is lower then coin 3 is fake
                } else if(coins.get(2).getWeight() < coins.get(3).getWeight()) {
                    return coins.get(2);
                } else {
                    // If scalepans are equal then coin 5 is fake
                    return coins.get(4);
                }
        // Second weighing
            // If right scalepan is lower
            } else if(leftScale.getWeight() < rightScale.getWeight()) {
            // Third weighing
                // Compare coins 1 and 2
                    // If left scalepan is lower then coin 2 is fake
                if(coins.get(0).getWeight() > coins.get(1).getWeight()) {
                    return coins.get(1);
                    // If right scalepan is lower then coin 1 is fake
                } else if(coins.get(0).getWeight() < coins.get(1).getWeight()) {
                    return coins.get(0);
                } else {
                    // If scalepans are equal then coin 6 is fake
                    return coins.get(5);
                }
            } else {
            // Third weighing
                // Compare coins 7 and 8
                    // If left scalepan is lower then coin 7 is fake
                if(coins.get(6).getWeight() > coins.get(7).getWeight()) {
                    return coins.get(6);
                    // If right scalepan is lower the coin 8 is fake
                } else if(coins.get(6).getWeight() < coins.get(7).getWeight()) {
                    return coins.get(7);
                } else {
                    throw new RuntimeException("Something went wrong...");
                }
            }
        // If scalepans are equal
        } else {
            leftScale = new Coins();
            rightScale = new Coins();
            // Add coins 1, 2, 9 to left scalepan
            leftScale.addAll(new Coin[] {coins.get(0), coins.get(1), coins.get(8)});
            // Add coins 3, 4, 10 to right scalepan
            rightScale.addAll(new Coin[] {coins.get(2), coins.get(3), coins.get(9)});
        // Second weighing
            // If left scalepan is lower
            if(leftScale.getWeight() > rightScale.getWeight()) {
            // Third weighing
                // Compare coins 1 and 9
                    // If left scalepan is lower then coin 9 is fake
                if(coins.get(0).getWeight() > coins.get(8).getWeight()) {
                    return coins.get(8);
                    // If right scalepan is lower then coin 9 is fake
                } else if(coins.get(0).getWeight() < coins.get(8).getWeight()) {
                    return coins.get(8);
                } else {
                    // If scalepans are equal then coin 10 is fake
                    return coins.get(9);
                }
        // Second weighing
            // If right scalepan is lower
            } else if(leftScale.getWeight() < rightScale.getWeight()) {
            // Third weighing
                // Compare coins 3 and 10
                    // If left scalepan is lower then coin 10 is fake
                if(coins.get(2).getWeight() > coins.get(9).getWeight()) {
                    return coins.get(9);
                    // If right scalepan is lower then coin 10 is fake
                } else if(coins.get(2).getWeight() < coins.get(9).getWeight()) {
                    return coins.get(9);
                } else {
                    // If scalepans are equal then coin 9 is fake
                    return coins.get(8);
                }
            } else {
            // Third weighing
                // Compare coins 5 and 11
                    // If left scalepan is lower then coin 11 is fake
                if(coins.get(4).getWeight() > coins.get(10).getWeight()) {
                    return coins.get(10);
                    // If right scalepan is lower the coin 11 is fake
                } else if(coins.get(4).getWeight() < coins.get(10).getWeight()) {
                    return coins.get(10);
                } else {
                    // If scalepans are equal then coin 12 is fake
                    return coins.get(11);
                }
            }

            // return new Coin(0);

        }
    }

}
