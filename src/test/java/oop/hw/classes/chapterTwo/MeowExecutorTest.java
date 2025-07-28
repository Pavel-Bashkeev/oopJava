package oop.hw.classes.chapterTwo;

import ru.bashkeev.animals.cats.Cat;
import ru.bashkeev.animals.cats.RobotCat;
import ru.bashkeev.animals.cats.MeowExecutor;
import ru.bashkeev.animals.cats.Meowable;
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