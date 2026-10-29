import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class LogAnalyzer {
    public static void main(String[] args) {
       
        String[] fileNames = {"log1.txt", "log2.txt", "log3.txt"};
        String keyword = "ERROR";
        

        createDummyLogs(fileNames);

        long totalLinesAcrossAllFiles = 0;
        long totalKeywordMatches = 0;

        System.out.println("--- Processing Files ---");
        
        for (String name : fileNames) {
            Path filePath = Paths.get(name);
            
            try {
           
                long fileSize = Files.size(filePath);
                System.out.printf("File: %s | Size: %d bytes\n", filePath.getFileName(), fileSize);

               
                try (Stream<String> lines = Files.lines(filePath)) {
                    long matchCount = Files.lines(filePath)
                            .filter(line -> line.contains(keyword))
                            .count();
                    
                    long lineCount = Files.lines(filePath).count();
                    
                    totalLinesAcrossAllFiles += lineCount;
                    totalKeywordMatches += matchCount;
                }

            } catch (IOException e) {
                System.err.println("Error processing file " + name + ": " + e.getMessage());
            }
        }

     
        System.out.println("\n--- Summary Report ---");
        System.out.println("Total lines processed: " + totalLinesAcrossAllFiles);
        System.out.println("Total lines containing '" + keyword + "': " + totalKeywordMatches);
    }

  
    private static void createDummyLogs(String[] files) {
        try {
            Files.write(Paths.get(files[0]), List.of("INFO: System started", "ERROR: Connection timeout", "INFO: Retry 1"));
            Files.write(Paths.get(files[1]), List.of("INFO: User login success", "INFO: Dashboard loaded"));
            Files.write(Paths.get(files[2]), List.of("WARNING: High memory usage", "ERROR: Database crashed!", "ERROR: Out of memory"));
            System.out.println("Sample dummy log files generated successfully.\n");
        } catch (IOException e) {
            System.err.println("Could not create dummy files: " + e.getMessage());
        }
    }
}