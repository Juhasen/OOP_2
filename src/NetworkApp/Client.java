package NetworkApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             Scanner scanner = new Scanner(System.in);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner in = new Scanner(socket.getInputStream())) {

            // Read notification message and send time from user
            System.out.print("Enter notification message: ");
            String message = scanner.nextLine();
            System.out.print("Enter send time (in seconds): ");
            double sendTime = scanner.nextDouble();

            // Send message and send time to server
            out.println(message);
            out.println(sendTime);

            // Receive and print notification from server
            String notification = in.nextLine();
            System.out.println("Received notification: " + notification);

        } catch (ConnectException e) {
            System.out.println("Server is not running");
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
