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
    //Примеры записи в файл используя абстрактный класс OutputStream

    //пример записи в файл по одному байту, намного медленнее чем буферная запись
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


    //пример буферной записи в файл
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

    //буферная запись используя BufferedOutputStream декоратор
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


    //Примеры записи в файл используя абстрактный класс Writer

    //пример записи в файл по одному байту, используя FileWriter
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

    //пример буферной записи в файл, используя BufferedWriter декоратор
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
