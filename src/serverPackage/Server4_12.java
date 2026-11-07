package serverPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class Server4_12 {

    public static void main(String[] args) {

        try {
            // 1. Création du socket UDP du serveur
            DatagramSocket socket = new DatagramSocket(null);

            // 2. Adresse sur laquelle le serveur va écouter
            InetSocketAddress adresseServeur =
                    new InetSocketAddress("0.0.0.0", 1234);

            // 3. Liaison du socket avec l'adresse
            socket.bind(adresseServeur);

            System.out.println("Serveur UDP démarré sur le port 1234...");
            System.out.println("En attente d'un message...");

            Scanner scanner = new Scanner(System.in);

            while (true) {

                // ============================
                // RECEPTION
                // ============================

                byte[] bufferReception = new byte[1024];

                DatagramPacket packetReception =
                        new DatagramPacket(
                                bufferReception,
                                bufferReception.length
                        );

                // Attendre un message du client
                socket.receive(packetReception);

                // Transformer les octets reçus en String
                String messageRecu = new String(
                        packetReception.getData(),
                        0,
                        packetReception.getLength()
                );

                System.out.println();
                System.out.println(
                        "Message reçu de "
                                + packetReception.getAddress()
                                + ":"
                                + packetReception.getPort()
                );

                System.out.println("Client : " + messageRecu);


                // ============================
                // ENVOI DE LA REPONSE
                // ============================

                System.out.print("Serveur : ");
                String reponse = scanner.nextLine();

                byte[] bufferEnvoi = reponse.getBytes();

                DatagramPacket packetEnvoi =
                        new DatagramPacket(
                                bufferEnvoi,
                                bufferEnvoi.length,
                                packetReception.getAddress(),
                                packetReception.getPort()
                        );

                socket.send(packetEnvoi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}