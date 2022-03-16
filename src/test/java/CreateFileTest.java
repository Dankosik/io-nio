import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;


//примеры создания файла
public class CreateFileTest {

    private final String FILE_PATH = "src/test/resources/fileToCreate.txt";

    @AfterEach
    @BeforeEach
    public void cleanUpFiles() {
        File targetFile = new File(FILE_PATH);
        targetFile.delete();
    }

    //ипользуя java nio
    @Test
    public void givenUsingNio_whenCreatingFile_thenCorrect() throws IOException {
        Path newFilePath = Paths.get(FILE_PATH);
        Files.createFile(newFilePath);
    }

    //используя java io
    @Test
    public void givenUsingFile_whenCreatingFile_thenCorrect() throws IOException {
        File newFile = new File(FILE_PATH);
        boolean success = newFile.createNewFile();
        assertTrue(success);
    }
}
