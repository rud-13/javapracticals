import java.io.*;
import java.nio.file.*;
import java.util.List;

public class PersistenceSuite {
    public static void main(String[] args) throws IOException {
        Path tempFile = Files.createTempFile("test_data_", ".txt");
        List<String> original = List.of("ID:101,NAME:Riya", "ID:102,NAME:Arjun");

        Files.write(tempFile, original);
        List<String> reloaded = Files.readAllLines(tempFile);

        boolean identical = original.equals(reloaded);
        System.out.println("Persistence Match Test: " + (identical ? "PASSED" : "FAILED"));

        Files.deleteIfExists(tempFile);
    }
}