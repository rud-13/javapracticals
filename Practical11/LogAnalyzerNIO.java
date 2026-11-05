import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzerNIO {
    public static void main(String[] args) throws IOException {
        Path tempFile = Files.createTempFile("server_log_", ".txt");
        List<String> mockLogs = List.of(
            "INFO Server initialized on port 8080",
            "WARN High memory usage detected: 78%",
            "ERROR Database timeout connecting to cluster",
            "INFO Retry successful",
            "ERROR Connection dropped by peer"
        );
        Files.write(tempFile, mockLogs);

        // Analysis
        List<String> lines = Files.readAllLines(tempFile);
        int errorCount = 0;
        for (String line : lines) {
            if (line.contains("ERROR")) errorCount++;
        }

        BasicFileAttributes attrs = Files.readAttributes(tempFile, BasicFileAttributes.class);

        System.out.println("Log File: " + tempFile.getFileName());
        System.out.println("Total Lines: " + lines.size());
        System.out.println("Keyword 'ERROR' Matches: " + errorCount);
        System.out.println("File Size: " + attrs.size() + " bytes");
        System.out.println("Created At: " + attrs.creationTime());

        Files.deleteIfExists(tempFile);
    }
}