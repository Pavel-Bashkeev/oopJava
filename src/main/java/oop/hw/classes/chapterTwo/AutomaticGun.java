package oop.hw.classes.chapterTwo;

import oop.hw.classes.Gun;
import oop.hw.helpers.DeclensionWords;

public class AutomaticGun extends Gun {
    private final int fireSpeed;
    
    public AutomaticGun() {
        this(30, 30);
    }

    public AutomaticGun(int maxCartridge) {
        this(maxCartridge, maxCartridge / 2);
    }

    public AutomaticGun(int maxCartridge, int fireSpeed) {
        super(maxCartridge);
        if (fireSpeed <= 0) {
            throw new IllegalArgumentException("Скорострельность должна быть положительной");
        }
        this.fireSpeed = fireSpeed;
    }

    public int getFireSpeed() {
        return fireSpeed;
    }

    @Override
    public String shoot() {
        if (!isReady()) {
            return "Клац!";
        }

        StringBuilder result = new StringBuilder();
        int shotsFired = Math.min(getCountCartridge(), fireSpeed);

        for (int i = 0; i < shotsFired; i++) {
            super.shoot();
            if (i > 0) result.append("\n");
            result.append("Бах!");
        }

        return result.toString();
    }

    public String shootForSeconds(int seconds) {
        if (seconds <= 0) {
            throw new IllegalArgumentException("Количество секунд должно быть положительным");
        }

        StringBuilder result = new StringBuilder();
        int totalShots = Math.min(getCountCartridge(), seconds * fireSpeed);

        for (int i = 0; i < totalShots; i++) {
            super.shoot();
            if (i > 0) result.append("\n");
            result.append("Бах!");
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return "Автомат с " + getCountCartridge() + " " +
                DeclensionWords.getDeclensionWord(getCountCartridge(),
                        new String[]{"патроном", "патронами", "патронами"}) +
                ", скорострельность " + fireSpeed + "/сек";
    }
}