package oop.hw.classes;

import oop.hw.helpers.DeclensionWords;

public class Gun extends AbstractWeapon {
    private final int maxCartridge;

    public Gun(int maxCartridge) {
        this(maxCartridge, 0);
    }

    public Gun(int maxCartridge, int initialAmmo) {
        super(initialAmmo);
        validInitMaxCartridge(maxCartridge);
        validateInitialAmmo(initialAmmo, maxCartridge);
        this.maxCartridge = maxCartridge;
    }

    private void validInitMaxCartridge(int maxCartridge) {
        if (maxCartridge <= 0) {
            throw new IllegalArgumentException("Максимальная вместимость должна быть положительной");
        }

    }

    private void validateInitialAmmo(int initialAmmo, int maxCartridge) {
        if (initialAmmo < 0) {
            throw new IllegalArgumentException("Начальное количество патронов не может быть отрицательным");
        }
        if (initialAmmo > maxCartridge) {
            throw new IllegalArgumentException("Начальное количество патронов не может превышать максимальную вместимость");
        }
    }

    public int getMaxCartridge() {
        return maxCartridge;
    }

    public boolean isReady() {
        return ammo() > 0;
    }

    @Override
    public String shoot() {
        if (getAmmo()) {
            return "Бах!";
        }
        return "Клац!";
    }

    public int reload(int countCartridge) {
        if (countCartridge < 0) {
            throw new IllegalArgumentException("Нельзя зарядить отрицательное количество патронов");
        }

        int availableSpace = maxCartridge - ammo();
        int loaded = Math.min(countCartridge, availableSpace);
        load(ammo() + loaded);

        return countCartridge - loaded;
    }

    public int unload() {
        int unloaded = ammo();
        load(0);
        return unloaded;
    }

    @Override
    public String toString() {
        return "Пистолет с " + ammo() + " " +
                DeclensionWords.getDeclensionWord(ammo(),
                        new String[]{"патроном", "патронами", "патронами"});
    }
}