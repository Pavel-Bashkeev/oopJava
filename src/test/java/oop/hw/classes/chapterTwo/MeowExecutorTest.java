package oop.hw.classes.chapterTwo;

import oop.hw.classes.meowableObject.Cat;
import oop.hw.classes.meowableObject.RobotCat;
import oop.hw.classes.meowableObject.MeowExecutor;
import oop.hw.interfaces.Meowable;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class MeowExecutorTest {
    @Test
    void testMakeAllMeow() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Meowable[] meowers = {
                new Cat("Барсик"),
                new RobotCat("XJ-9")
        };

        MeowExecutor.makeAllMeow(meowers);

        String expectedOutput = "Барсик: мяу!\nXJ-9: БИП-БИП-МЯУ!\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}