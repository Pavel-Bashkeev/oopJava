package ru.bashkeev.karate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class KarateManTest {

    @Test
    void testCombination() {
        KarateMan karateka = new KarateMan("KarateMan");
        KickCombination combo = new KickCombination();

        combo.addKick(KickFactory.kickHand());
        combo.addKick(KickFactory.kickLeg());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        karateka.performCombination(combo);
        String expected = "KarateMan: кия!\nKarateMan: бац!\n";
        assertEquals(expected, outContent.toString());

        System.setOut(System.out);
    }

    @Test
    void testCombinationModification() {
        KarateMan karateka = new KarateMan("KarateMan");
        KickCombination combo = new KickCombination();

        combo.addKick(KickFactory.kickHand());
        combo.addKick(KickFactory.kickLeg());
        combo.addKick(KickFactory.kickHand());

        combo.removeKick(2);
        combo.addKick(KickFactory.kickJump());
        combo.addKick(0, KickFactory.kickLeg());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        karateka.performCombination(combo);

        String expected = """
            KarateMan: бац!
            KarateMan: кия!
            KarateMan: бац!
            KarateMan: вжух!""";

        assertEquals(expected, outContent.toString().trim());
    }
}