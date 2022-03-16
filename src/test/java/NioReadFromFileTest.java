import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * Примеры чтения файла используя nio
 */
public class NioReadFromFileTest {

    /**
     * Пример чтения файла используя Files.readAllLines, если файл большой лучше использовать
     * BufferedReader reader = Files.newBufferedReader(path);
     */
    @Test
    public void whenReadSmallFileJava7_thenCorrect() throws IOException {
        long time1 = System.currentTimeMillis();

        Path path = Paths.get("src/test/resources/hello-world.txt");
        String data = String.join("", Files.readAllLines(path));

        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        System.out.println(data);
    }

    /**
     * Аналог вышестоящего примера
     */
    @Test
    public void givenFilePath_whenUsingFilesLines_thenFileData() throws IOException {
        long time1 = System.currentTimeMillis();

        Path path = Paths.get("src/test/resources/hello-world.txt");
        String data = Files.lines(path)
                .collect(Collectors.joining("\n"));

        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        System.out.println(data);

    }

    /**
     * Пример построчного буферного чтения файла, больше подходит для чтения больших файлов
     */
    @Test
    public void whenReadLargeFileJava7_thenCorrect() throws IOException {
        long time1 = System.currentTimeMillis();

        Path path = Paths.get("src/test/resources/hello-world.txt");
        BufferedReader reader = Files.newBufferedReader(path);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        System.out.println(stringBuilder);
    }
}
