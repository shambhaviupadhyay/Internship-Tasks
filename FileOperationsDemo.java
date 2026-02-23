import java.io.*;

public class FileOperationsDemo {
    // File name to be used
    private static final String FILE_NAME = "sample.txt";

    public static void main(String[] args) {

        try {
            // 1️⃣ Write to file
            writeToFile("Hello, this is the original file content.\nThis line will be modified.");

            // 2️⃣ Read file
            System.out.println("----- File Content After Writing -----");
            readFromFile();

            // 3️⃣ Modify file
            modifyFile("modified", "UPDATED");

            // 4️⃣ Read again to show modification
            System.out.println("\n----- File Content After Modification -----");
            readFromFile();

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /*
     * Method to write content to file
     * If file does not exist, it will be created
     */
    public static void writeToFile(String content) throws IOException {
        FileWriter writer = new FileWriter(FILE_NAME);
        writer.write(content);
        writer.close();
        System.out.println("File written successfully.");
    }

    /*
     * Method to read content from file
     */
    public static void readFromFile() throws IOException {
        FileReader reader = new FileReader(FILE_NAME);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        bufferedReader.close();
    }

    /*
     * Method to modify file content
     * Replaces oldWord with newWord
     */
    public static void modifyFile(String oldWord, String newWord) throws IOException {

        File file = new File(FILE_NAME);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        StringBuilder content = new StringBuilder();
        String line;

        // Read existing content
        while ((line = reader.readLine()) != null) {
            content.append(line.replaceAll(oldWord, newWord)).append("\n");
        }

        reader.close();

        // Write modified content back to file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content.toString());
        writer.close();

        System.out.println("File modified successfully.");
    }
}
