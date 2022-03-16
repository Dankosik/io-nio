import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

public class WriteToFileTest {


    private final String FILE_PATH = "src/test/resources/fileToCreate.txt";

    @AfterEach
    @BeforeEach
    public void cleanUpFiles() {
        File targetFile = new File(FILE_PATH);
        targetFile.delete();
    }

    /**
     * Примеры записи в файл используя абстрактный класс {@link java.io.OutputStream}
     * <p>
     * <p>
     * <p>
     * Пример записи в файл по одному байту, намного медленнее чем буферная запись
     */
    @Test
    public void writeByOneByte() throws IOException {
        long time1 = System.currentTimeMillis();
        OutputStream outputStream = new FileOutputStream(FILE_PATH);
        for (int i = 0; i < 100000; i++) {
            outputStream.write(1);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        outputStream.close();
    }


    /**
     * Пример буферной записи в файл
     */
    @Test
    public void writeByBytes() throws IOException {
        long time1 = System.currentTimeMillis();
        OutputStream outputStream = new FileOutputStream(FILE_PATH);
        byte[] buffer = new byte[]{1, 1, 1, 1, 1};
        for (int i = 0; i < 100000 / buffer.length; i++) {
            outputStream.write(buffer);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        outputStream.close();
    }

    /**
     * Буферная запись используя {@link java.io.BufferedOutputStream} декоратор
     */
    @Test
    public void writeByBufferedOutputStream() throws IOException {
        long time1 = System.currentTimeMillis();
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(FILE_PATH), 1024);
        for (int i = 0; i < 100000; i++) {
            outputStream.write(1);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        outputStream.close();
    }


    /**
     * Примеры записи в файл используя абстрактный класс {@link java.io.Writer}
     * <p>
     * <p>
     * <p>
     * Пример записи в файл по одному байту, используя  {@link java.io.FileWriter}
     */
    @Test
    public void writeByOneByteByFileWriter() throws IOException {
        long time1 = System.currentTimeMillis();
        Writer fileWriter = new FileWriter(FILE_PATH);
        for (int i = 0; i < 100000; i++) {
            fileWriter.write(1);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);

        fileWriter.close();
    }

    /**
     * Пример буферной записи в файл, используя {@link java.io.BufferedWriter} декоратор
     */
    @Test
    public void writeByOneByteByBufferedWriter() throws IOException {
        long time1 = System.currentTimeMillis();
        Writer fileWriter = new BufferedWriter(new FileWriter(FILE_PATH));
        for (int i = 0; i < 100000; i++) {
            fileWriter.write(1);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);

        fileWriter.close();
    }
}
