import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FolderWalker {
    public static void main(String[] args) {
       
        Path startDir = Paths.get("."); 
        Path reportFile = Paths.get("folder_summary_report.txt");
        
        List<String> reportLines = new ArrayList<>();
        reportLines.add("==================================================");
        reportLines.add("FOLDER TREE SUMMARY REPORT");
        reportLines.add("Target Directory: " + startDir.toAbsolutePath().normalize());
        reportLines.add("==================================================\n");
        reportLines.add(String.format("%-40s | %-12s | %-25s", "File Name", "Size (Bytes)", "Last Modified Time"));
        reportLines.add("-------------------------------------------------------------------------------------");

        System.out.println("Scanning directory structure...");

        try {

            Files.walkFileTree(startDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (!file.getFileName().equals(reportFile.getFileName())) {
                        String name = file.getFileName().toString();
                        long size = attrs.size();
                        String lastModified = attrs.lastModifiedTime().toString();

                        // Truncate long names to prevent layout breaking
                        if (name.length() > 40) {
                            name = name.substring(0, 37) + "...";
                        }

                        reportLines.add(String.format("%-40s | %-12d | %-25s", name, size, lastModified));
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    reportLines.add(String.format("%-40s | %-12s | %-25s", file.getFileName() + " [ACCESS DENIED]", "N/A", "N/A"));
                    return FileVisitResult.CONTINUE;
                }
            });

            Files.write(reportFile, reportLines);
            System.out.println("Scan complete! The report has been saved to: " + reportFile.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("An error occurred walking the folder tree: " + e.getMessage());
        }
    }
}