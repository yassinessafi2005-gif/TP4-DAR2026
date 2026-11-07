package clientPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client4_1 {
    public static void main(String[] args) {
        final int portServeur = 1234;

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress adresseServeur = InetAddress.getByName("localhost");

            System.out.print("Nom d'utilisateur : ");
            String nom = scanner.nextLine().trim();

            System.out.println("Écris un message (ou /quit pour quitter).");

            while (true) {
                System.out.print("> ");
                String texte = scanner.nextLine();

                if (texte.equalsIgnoreCase("/quit")) {
                    break;
                }

                String message = "[" + nom + "] : " + texte;
                byte[] donnees = message.getBytes(StandardCharsets.UTF_8);

                DatagramPacket paquet = new DatagramPacket(
                        donnees, donnees.length, adresseServeur, portServeur
                );
                socket.send(paquet);
            }
        } catch (Exception e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}
