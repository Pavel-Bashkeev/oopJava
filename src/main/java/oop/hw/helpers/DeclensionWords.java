package oop.hw.helpers;

public class DeclensionWords {

    /**
     * Возвращает правильную форму слова в зависимости от числа.
     *
     * @param number Число для склонения (может быть отрицательным)
     * @param words  Массив из 3 форм слова:
     *               words[0] — форма для 1 (например, "этаж")
     *               words[1] — форма для 2-4 (например, "этажа")
     *               words[2] — форма для 5-20 (например, "этажей")
     * @return Правильная форма слова
     * @throws IllegalArgumentException если words == null или words.length != 3
     */
    public static String getDeclensionWord(int number, String[] words) {

        if (words == null) {
            throw new IllegalArgumentException("Массив слов не может быть null");
        }
        if (words.length != 3) {
            throw new IllegalArgumentException("Массив слов должен содержать ровно 3 элемента");
        }

        number = Math.abs(number);
        int remainder100 = number % 100;
        int remainder10 = number % 10;

        if (remainder100 >= 11 && remainder100 <= 14) {
            return words[2];
        }

        if (remainder10 == 1) {
            return words[0];
        }
        if (remainder10 >= 2 && remainder10 <= 4) {
            return words[1];
        }

        return words[2];
    }
}
