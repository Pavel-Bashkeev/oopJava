package ru.bashkeev.weapons;

public class Shooter {
    private String name;
    private AbstractWeapon weapon;

    public Shooter(String name) {
        this.name = name;
    }

    public Shooter(String name, AbstractWeapon weapon) {
        this(name);
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbstractWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(AbstractWeapon weapon) {
        this.weapon = weapon;
    }

    public String shoot() {
        if (weapon == null) {
            return name + ": не могу участвовать в перестрелке";
        }
        return name + ": " + weapon.shoot();
    }

    @Override
    public String toString() {
        String weaponInfo = (weapon != null) ? ", вооружен: " + weapon : ", без оружия";
        return "Стрелок " + name + weaponInfo;
    }
}