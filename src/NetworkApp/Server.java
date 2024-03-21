package NetworkApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 5000;
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (Scanner scanner = new Scanner(clientSocket.getInputStream());
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                System.out.println("New connection from " + clientSocket.getInetAddress());

                // Read message from client
                String message = scanner.nextLine();
                double sendTime = Double.parseDouble(scanner.nextLine());

                // Simulate sending notification after specified time
                Thread.sleep((long) (sendTime * 1000));

                // Send notification back to client
                out.println(message);

                System.out.println("Notification sent to " + clientSocket.getInetAddress());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
