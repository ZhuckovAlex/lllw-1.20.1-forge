package net.sanberdir.llw.common.utils;


import net.sanberdir.llw.LLW;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {
    public static List<String> getFileManager(String path)
    {
        List<String> files = null;
        try
        {
            files = listFilesInJar("/data/" + LLW.MOD_ID + "/" + path);
        }
        catch (IOException | URISyntaxException exception)
        {
            exception.printStackTrace();
        }

        return files;
    }

    private static List<String> listFilesInJar(String resourcePath) throws URISyntaxException, IOException {
        // Получаем URI к ресурсу в JAR-файле.
        URI uri = FileManager.class.getResource(resourcePath).toURI();

        // Если ресурс находится в JAR-файле, создаем файловую систему для JAR.
        try (FileSystem fileSystem = (uri.getScheme().equals("jar")) ? FileSystems.newFileSystem(uri, Collections.emptyMap()) : null) {
            Path path = fileSystem == null ? Path.of(uri) : fileSystem.getPath(resourcePath);

            // Используем Files.walk для перебора всех файлов в каталоге.
            try (Stream<Path> walk = Files.walk(path)) {
                return walk.filter(Files::isRegularFile)
                        .map(Path::toString)
                        .collect(Collectors.toList());
            }
        }
    }

    public static String extractFileNameWithoutExtension(String fullPath) {
        String fileName = Paths.get(fullPath).getFileName().toString(); // Извлекаем имя файла: "alphabet_elements.json"
        int dotIndex = fileName.lastIndexOf('.'); // Находим позицию последней точки
        if (dotIndex > 0) { // Убеждаемся, что точка присутствует, чтобы избежать StringIndexOutOfBoundsException
            fileName = fileName.substring(0, dotIndex); // Обрезаем строку до точки, получаем "alphabet_elements"
        }
        return fileName;
    }
}
