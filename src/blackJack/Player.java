package blackJack;

public class Player {
    private String name;
    private int cash;
    private int bet;

    public Player(String n, int c) //Create a new player
    {
        name = n;
        cash = c;
    }

    public void placeBet(int myBet) {
        bet = myBet; //sets current bet
        cash = cash - myBet; //deducts bet from cash
    }

    public int blackJack(Boolean bJack) {
        if(bJack == true) {
            return (int) (bet * 2.5);
        }
        else {
            return bet * 2;
        }
    }

    public void addCash(int moreMoney)
    {
        cash = cash + moreMoney;
    }

    public String getName() {
        return name;
    }

    public int getCash() {
        return cash;
    }

    public int getBet() {
        return bet;
    }

}
