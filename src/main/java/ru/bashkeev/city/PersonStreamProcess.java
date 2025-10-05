package ru.bashkeev.city;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonStreamProcess {

    public static Map<Integer, List<String>> processPersonFile(String fileName) {
        Path path = Paths.get(fileName);

        try {
            return Files.lines(path)
                    .map(line -> line.split(":"))
                    .filter(parts -> parts.length == 2 && !parts[1].trim().isEmpty())
                    .map(parts -> {
                        String name = formatName(parts[0].trim());
                        int    idx  = Math.abs(Integer.parseInt(parts[1].trim()));
                        return new AbstractMap.SimpleEntry<>(name, idx);
                    })
                    .collect(Collectors.groupingBy(
                            Map.Entry::getValue,
                            Collectors.mapping(
                                    Map.Entry::getKey,
                                    Collectors.toList()
                            )
                    ));
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            return Collections.emptyMap();
        } catch (NumberFormatException e) {
            System.err.println("Ошибка формата номера: " + e.getMessage());
            return Collections.emptyMap();
        }
    }

    private static String formatName(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
