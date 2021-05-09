package a5;

import java.io.IOException;
import java.net.*;

public class ClientVelocimetre {

    public static void main(String[] args) throws IOException {

        String serverIp = "224.0.1.5";
        int port = 5557;
        MulticastSocket socket = new MulticastSocket(port);
        InetAddress inetAddress = InetAddress.getByName(serverIp);
        InetSocketAddress groupMulticast = new InetSocketAddress(inetAddress, port);
        NetworkInterface netIf = NetworkInterface.getByInetAddress(inetAddress);

        socket.joinGroup(groupMulticast, netIf);


        byte[] receivedData = new byte[1024];
        DatagramPacket packet = new DatagramPacket(receivedData, receivedData.length);
        int[] velocitats = new int[5];
        int mitjana = 0;


        while (true) {
            for (int i = 0; i < 5; i++) {
                socket.receive(packet);
                velocitats[i] = packet.getData()[3];
                System.out.println(".");
            }
            mitjana = (velocitats[0] + velocitats[1] + velocitats[2] + velocitats[3] + velocitats[4]) / 5;
            System.out.println("Velocitat mitjana -> " + mitjana);
        }


    }

}