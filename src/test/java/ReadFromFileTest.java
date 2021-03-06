import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Примеры чтения из файла
 */
public class ReadFromFileTest {

    private final String filePath = "src/test/resources/hello-world.txt";

    /**
     * Примеры чтения файла используя абстрактный класс {@link java.io.InputStream}
     * <p>
     * <p>
     * <p>
     * Пример чтения файла по одному байту, намного медленнее чем буферное чтение
     */
    @Test
    public void readByOneByte() throws IOException {
        long time1 = System.currentTimeMillis();
        InputStream inputStream = new FileInputStream(filePath);
        int symbolAsByte;
        while ((symbolAsByte = inputStream.read()) != -1) {
            // System.out.print((char)symbolAsByte);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        inputStream.close();
    }

    /**
     * Пример буферного чтения файла
     */
    @Test
    public void readByBytes() throws IOException {
        long time1 = System.currentTimeMillis();
        InputStream inputStream = new FileInputStream(filePath);
        int symbolsRead;
        byte[] buffer = new byte[16];
        while ((symbolsRead = inputStream.read(buffer)) != -1) {
//            System.out.println("buffer: " + Arrays.toString(buffer));
//            System.out.println(new String(buffer, 0, symbolsRead));
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        inputStream.close();
    }

    /**
     * Буферное чтение используя {@link java.io.BufferedInputStream} декоратор
     */
    @Test
    public void readByBufferedInputStream() throws IOException {
        long time1 = System.currentTimeMillis();
        InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
        int symbolsRead;
        while ((symbolsRead = inputStream.read()) != -1) {
            //System.out.print((char)symbolsRead);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        inputStream.close();
    }


    /**
     * Примеры чтения файла используя абстрактный класс Reader
     * <p>
     * <p>
     * <p>
     * Пример чтения файла по одному байту, используя {@link java.io.FileReader}, быстрее чтения по одному байту чем {@link java.io.InputStream}
     */
    @Test
    public void readByReader() throws IOException {
        long time1 = System.currentTimeMillis();
        Reader reader = new FileReader(filePath);
        int symbolsRead;
        while ((symbolsRead = reader.read()) != -1) {
//            System.out.print((char)symbolsRead);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        reader.close();
    }

    /**
     * Буферное чтение используя {@link java.io.BufferedReader} декоратор
     */
    @Test
    public void readByBufferedReader() throws IOException {
        long time1 = System.currentTimeMillis();
        Reader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8));
        int symbolsRead;
        while ((symbolsRead = reader.read()) != -1) {
            //    System.out.print((char)symbolsRead);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        reader.close();
    }
}