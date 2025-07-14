package oop.hw.classes;

import oop.hw.helpers.DeclensionWords;

public class Gun {
    static final int DEFAULT_COUNT_CARTRIDGE = 5;
    private int countCartridge;

    public Gun (int countCartridge) {
        this.countCartridge = Math.max(countCartridge, 0);
    }

    public Gun () {
        this(DEFAULT_COUNT_CARTRIDGE);
    }

    public int getCountCartridge() {
        return countCartridge;
    }

    public void setCountCartridge(int countCartridge) {
        this.countCartridge = countCartridge;
    }

    public String shoot() {
        if (countCartridge > 0) {
            countCartridge--;
            return "Бах!";
        }
        return "Клац!";
    }

    @Override
    public String toString() {
        return "Пистолет с " + countCartridge + " " + DeclensionWords.getDeclensionWord(countCartridge, new String[]{"патроном", "патронами", "патронами"});
    }
}
