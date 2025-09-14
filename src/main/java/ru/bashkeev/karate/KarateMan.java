package ru.bashkeev.karate;

public record KarateMan(String name) {
    public void performCombination(KickCombination combination) {
        combination.execute(name);
    }
}
