import java.net.*;
import java.io.*;

public class ChatClient {
    private String hostname;
    private int port;
    private String userName;

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);

            // Log in, connexion au serveur
            System.out.println("Connection to Server");

            // Lecture de message
            new ReadThread(socket, this).start();

            // Ecriture de message
            new WriteThread(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println("Not find Server: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Input/Output error: " + ex.getMessage());
        }

    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return this.userName;
    }

    public static void main(String[] args) {
        if (args.length < 2) return;

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);

        ChatClient client = new ChatClient(hostname, port);
        client.execute();
    }
}