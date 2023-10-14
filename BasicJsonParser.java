import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BasicJsonParser {

    public static void main(String[] args) {
        //change targetDirectory with your desired directory, I believe this can be static too depending on your case of use 
        String targetDirectory = "/your/directory/here";
        String fileExtension = ".json";
        //change this to your desired keyword
        String searchString = "path";

        File directory = new File(targetDirectory);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(fileExtension)) {
                    String result = searchFileForStringAndExtract(file, searchString);
                    if (result != null) {
                        //this replaces the "" with a null string so you read the actual data without ""
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
                    //this removes : from the json file so you can read the actual data
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

