package oop.hw.classes;

import oop.hw.helpers.DeclensionWords;

public class Gun {
    private int countCartridge;
    private final int maxCartridge;

    public Gun(int maxCartridge) {
        if (maxCartridge <= 0) {
            throw new IllegalArgumentException("Максимальная вместимость должна быть положительной");
        }
        this.maxCartridge = maxCartridge;
        this.countCartridge = 0;
    }

    public int getMaxCartridge() {
        return maxCartridge;
    }

    public int getCountCartridge() {
        return countCartridge;
    }

    public boolean isReady() {
        return countCartridge > 0;
    }

    public String shoot() {
        if (countCartridge > 0) {
            countCartridge--;
            return "Бах!";
        }
        return "Клац!";
    }

    public int reload(int countCartridge) {
        if (countCartridge < 0) {
            throw new IllegalArgumentException("Нельзя зарядить отрицательное количество патронов");
        }

        int availableSpace = maxCartridge - this.countCartridge;
        int loaded = Math.min(countCartridge, availableSpace);
        this.countCartridge += loaded;

        return countCartridge - loaded;
    }

    public int unload() {
        int unloaded = countCartridge;
        countCartridge = 0;
        return unloaded;
    }

    @Override
    public String toString() {
        return "Пистолет с " + countCartridge + " " + DeclensionWords.getDeclensionWord(countCartridge, new String[]{"патроном", "патронами", "патронами"});
    }
}
