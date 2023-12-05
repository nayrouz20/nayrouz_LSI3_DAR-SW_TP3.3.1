package server;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    public static void main(String[] args) {
        new Server().start();
    }

    private int numClient = 1;

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            while (true) {
                // Attendez qu'un client se connecte et acceptez la connexion.
                Socket socket = serverSocket.accept();

                // Créez un nouveau thread pour gérer le client connecté.
                new ClientProcess(socket, numClient++).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ClientProcess extends Thread {
        Socket socket;
        int ID;

        public ClientProcess(Socket socket, int ID) {
            super();
            this.socket = socket;
            this.ID = ID;
        }

        public void run() {
            try {
                System.out.println("Client ID " + ID + " (" + socket.getRemoteSocketAddress() + ") est connecté");

                // Créez un flux de sortie pour envoyer des données au client.
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

                // Envoyez un message au client.
                pw.println("Vous êtes le client " + ID);

                // Fermez la connexion avec le client.
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}