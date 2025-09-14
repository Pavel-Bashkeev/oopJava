package ru.bashkeev.karate;

import java.util.ArrayList;
import java.util.List;

public class KickCombination {
    private final List<Kick> kicks = new ArrayList<>();

    public void addKick(Kick kick) {
        kicks.add(kick);
    }

    public void addKick(int index, Kick kick) {
        if (index < 0 || index >= kicks.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        kicks.add(index, kick);
    }

    public void removeKick(int index) {
        kicks.remove(index);
    }

    public void clearKicks() {
        kicks.clear();
    }

    public void execute(String name) {
        for (Kick kick : kicks) {
            kick.blow(name);
        }
    }


}
