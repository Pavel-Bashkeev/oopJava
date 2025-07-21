package oop.hw.classes.weapon;

abstract public class AbstractWeapon {
    private int ammo;

    public AbstractWeapon(int ammo) {
        validInitAmmo(ammo);
        this.ammo = ammo;
    }

    abstract public String shoot();

    public int ammo() {
        return this.ammo;
    }

    public boolean getAmmo() {
        if (this.ammo == 0) return false;
        this.ammo--;
        return true;
    }

    public int load(int ammo) {
        validInitAmmo(ammo);
        this.ammo = ammo;
        return ammo;
    }

    private void validInitAmmo(int ammo) {
        if (ammo < 0) throw new RuntimeException();
    }
}
