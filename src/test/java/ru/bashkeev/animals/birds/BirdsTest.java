package ru.bashkeev.animals.birds;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BirdsTest {
    @Test
    void testBirdsSinging() {
        AbstractBird[] birds = {
                new Sparrow(),
                new Cuckoo(),
                new Parrot("Привет, как дела?")
        };

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            for (AbstractBird bird : birds) {
                System.out.print(bird.getName() + " поет: ");
                bird.sing();
            }

            String output = outContent.toString();

            assertTrue(output.contains("Воробей поет: чирик"));
            assertTrue(output.contains("Кукушка поет: ку-ку"));
            assertTrue(output.contains("Попугай поет: Привет"));

            String cuckooPart = output.split("Кукушка поет: ")[1].split(System.lineSeparator())[0];
            int cuckooCount = cuckooPart.split(" ").length;
            assertTrue(cuckooCount >= 1 && cuckooCount <= 10);

            String parrotPart = output.split("Попугай поет: ")[1].split(System.lineSeparator())[0];
            assertFalse(parrotPart.isEmpty());
            assertTrue("Привет, как дела?".contains(parrotPart));
        } finally {
            // Cleanup
            System.setOut(originalOut);
        }
    }
}