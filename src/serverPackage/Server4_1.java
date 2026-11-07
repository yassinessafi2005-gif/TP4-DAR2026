package serverPackage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Server4_1 {
    private static final int PORT=1234;
    private static final int TAILLE_BUFFER=1024;
    static void main(String[] args) {
        DatagramSocket socket=null;
        try{
            socket = new DatagramSocket(null);
            socket.bind(new InetSocketAddress(PORT));
            System.out.println("[server]En ecoute sur le port "+PORT);
            while (true){
                byte[] buffer = new byte[TAILLE_BUFFER];
                DatagramPacket paquet = new DatagramPacket(buffer,buffer.length);
                socket.receive(paquet);
                String message = new String(paquet.getData(),0,paquet.getLength());
                System.out.println("aho el message :"+message);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

