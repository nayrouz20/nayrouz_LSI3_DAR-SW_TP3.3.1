package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // Établir une connexion avec le serveur en utilisant un Socket.
            Socket socket = new Socket("localhost", 1234);

            // Créer un flux d'entrée pour lire les données envoyées par le serveur.
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Lire une ligne de texte du serveur.
            String output = inputReader.readLine();

            // Afficher la ligne de texte reçue du serveur.
            System.out.println(output);

            // Fermer la connexion avec le serveur.
            socket.close();

        } catch (IOException e) {
            // En cas d'erreur lors de la connexion ou de la communication avec le serveur,
            // afficher les détails de l'exception.
            e.printStackTrace();
        }
    }
}