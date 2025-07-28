package ru.bashkeev.weapons;

import ru.bashkeev.helpers.DeclensionWords;

public class AutomaticGun extends Gun {
    private final int fireSpeed;

    public AutomaticGun() {
        this(30, 30, 0);
    }

    public AutomaticGun(int maxCartridge) {
        this(maxCartridge, maxCartridge / 2, maxCartridge);
    }

    public AutomaticGun(int maxCartridge, int fireSpeed) {
        this(maxCartridge, fireSpeed, 0);
    }

    public AutomaticGun(int maxCartridge, int fireSpeed, int initialAmmo) {
        super(maxCartridge, initialAmmo);
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
        return shootMultiple(Math.min(ammo, fireSpeed));
    }

    public String shootForSeconds(int seconds) {
        if (seconds <= 0) {
            throw new IllegalArgumentException("Количество секунд должно быть положительным");
        }
        return shootMultiple(Math.min(ammo, seconds * fireSpeed));
    }

    private String shootMultiple(int shots) {
        if (ammo == 0) {
            return "Клац!";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shots; i++) {
            ammo--;
            if (i > 0) result.append("\n");
            result.append("Бах!");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return "Автомат с " + ammo() + " " +
                DeclensionWords.getDeclensionWord(ammo(),
                        new String[]{"патроном", "патронами", "патронами"}) +
                ", скорострельность " + fireSpeed + "/сек";
    }
}