import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryTreeWalker {
    public static void main(String[] args) throws IOException {
        Path startDir = Paths.get(".");
        System.out.println("=== Directory Tree File Audit Report ===");

        try (var stream = Files.walk(startDir, 2)) {
            stream.filter(Files::isRegularFile).forEach(p -> {
                try {
                    BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);
                    System.out.printf("%-30s | Size: %6d bytes | Last Modified: %s%n",
                            p.getFileName(), attrs.size(), attrs.lastModifiedTime());
                } catch (IOException e) {
                    System.err.println("Could not read attributes: " + p);
                }
            });
        }
    }
}