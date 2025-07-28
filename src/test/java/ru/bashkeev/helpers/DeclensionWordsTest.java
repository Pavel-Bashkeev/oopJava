package ru.bashkeev.helpers;

import org.junit.jupiter.api.Test;
import ru.bashkeev.helpers.DeclensionWords;

import static org.junit.jupiter.api.Assertions.*;

class DeclensionWordsTest {
    private final String[] FLOOR_FORMS = {"этажом", "этажами", "этажами"};

    @Test
    void testGetDeclensionWordBasicCases() {
        assertEquals("этажом", DeclensionWords.getDeclensionWord(1, FLOOR_FORMS));
        assertEquals("этажами", DeclensionWords.getDeclensionWord(2, FLOOR_FORMS));
        assertEquals("этажами", DeclensionWords.getDeclensionWord(4, FLOOR_FORMS));
        assertEquals("этажами", DeclensionWords.getDeclensionWord(5, FLOOR_FORMS));
        assertEquals("этажами", DeclensionWords.getDeclensionWord(11, FLOOR_FORMS));
    }

    @Test
    void testGetDeclensionWordEdgeCases() {
        assertEquals("этажами", DeclensionWords.getDeclensionWord(0, FLOOR_FORMS));
        assertEquals("этажом", DeclensionWords.getDeclensionWord(21, FLOOR_FORMS));
        assertEquals("этажами", DeclensionWords.getDeclensionWord(22, FLOOR_FORMS));
        assertEquals("этажами", DeclensionWords.getDeclensionWord(25, FLOOR_FORMS));
    }

    @Test
    void testGetDeclensionWordLargeNumbers() {
        assertEquals("этажами", DeclensionWords.getDeclensionWord(1012, FLOOR_FORMS));
        assertEquals("этажом", DeclensionWords.getDeclensionWord(15431, FLOOR_FORMS));
        assertEquals("этажами", DeclensionWords.getDeclensionWord(15432, FLOOR_FORMS));
    }

    @Test
    void testGetDeclensionWordNegativeNumbers() {
        assertEquals("этажом", DeclensionWords.getDeclensionWord(-1, FLOOR_FORMS));
        assertEquals("этажами", DeclensionWords.getDeclensionWord(-5, FLOOR_FORMS));
    }

    @Test
    void testGetDeclensionWordInvalidInput() {
        assertThrows(IllegalArgumentException.class,
                () -> DeclensionWords.getDeclensionWord(1, null));

        assertThrows(IllegalArgumentException.class,
                () -> DeclensionWords.getDeclensionWord(1, new String[]{"одна форма"}));
    }
}