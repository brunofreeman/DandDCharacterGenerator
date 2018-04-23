package charactergenerator;

import java.util.Random;


public class Dice {

    private int[] dice;
    private int diceMod;

    public Dice() {
        //d4, d6, d8, d10, d12, d20, d100
        dice = new int[]{0, 0, 0, 0, 0, 0, 0};
        diceMod = 0;
    }

    public Dice(int[] dice, int diceMod) {
        if (dice.length != 7) {
            throw new IllegalArgumentException();
        }
        this.dice = dice;
        this.diceMod = diceMod;
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < dice.length; i++) {
            if (dice[i] > 0) {
                string += dice[i] + "d" + indexToD(i) + ", ";
            }
        }
        if (string.substring(string.length() - 2).equals(", ")) {
            string = string.substring(0, string.length() - 2);
        }
        if (diceMod > 0) {
            string += "+" + diceMod;
        }
        return string;
    }

    public int getDice(int d) {
        return dice[dToIndex(d)];
    }

    public void setDice(int d, int num) {
        dice[dToIndex(d)] = num;
    }

    public int getDiceMod() {
        return diceMod;
    }

    public void setDiceMod(int diceMod) {
        this.diceMod = diceMod;
    }

    private static int dToIndex(int d) {
        switch (d) {
            case 4:
                return 0;
            case 6:
                return 1;
            case 8:
                return 2;
            case 10:
                return 3;
            case 12:
                return 4;
            case 20:
                return 5;
            case 100:
                return 6;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static int indexToD(int i) {
        switch (i) {
            case 0:
                return 4;
            case 1:
                return 6;
            case 2:
                return 8;
            case 3:
                return 10;
            case 4:
                return 12;
            case 5:
                return 20;
            case 6:
                return 100;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static int roll(int[] ds, int mod) {
        if (ds.length != 6) {
            throw new IllegalArgumentException();
        }
        int total = mod;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < ds[i]; j++) {
                Random rand = new Random();
                total += rand.nextInt(indexToD(i)) + 1;
            }
        }
        return total;
    }

    public int roll() {
        return roll(dice, diceMod);
    }
}
