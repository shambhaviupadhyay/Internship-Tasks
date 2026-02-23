import java.io.*;
import java.net.*;

public class ChatClient {

    private static final String SERVER = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {

        try {
            Socket socket = new Socket(SERVER, PORT);

            BufferedReader console =
                    new BufferedReader(new InputStreamReader(System.in));

            PrintWriter writer =
                    new PrintWriter(socket.getOutputStream(), true);

            BufferedReader serverReader =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));

            // Thread to read messages from server
            new Thread(() -> {
                String msg;
                try {
                    while ((msg = serverReader.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected");
                }
            }).start();

            // Send messages
            String userInput;
            while ((userInput = console.readLine()) != null) {
                writer.println(userInput);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
