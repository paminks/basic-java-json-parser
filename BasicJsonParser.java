import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BasicJsonParser {

    public static void main(String[] args) {
        String targetDirectory = "/your/directory/here";
        String fileExtension = ".json";
        String searchString = "path";

        File directory = new File(targetDirectory);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(fileExtension)) {
                    String result = searchFileForStringAndExtract(file, searchString);
                    if (result != null) {
                        result = result.replace("\"", "");
                        System.out.println("String found in: " + file.getName() + ", Extracted: " + result);
                    
                    }
                }
            }
        }
    }

    public static String searchFileForStringAndExtract(File file, String searchString) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(searchString) && line.contains(":")) {
                    int colonIndex = line.indexOf(":");
                    int startIndex = line.indexOf(searchString);
                    if (startIndex < colonIndex) {
                        return line.substring(colonIndex + 1).trim();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

