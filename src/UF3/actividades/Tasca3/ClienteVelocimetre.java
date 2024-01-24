package UF3.actividades.Tasca3;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class ClienteVelocimetre {
    /* Client afegit al grup multicast SrvVelocitats.java que representa un velocímetre */

    private boolean continueRunning = true;
    private MulticastSocket socket;
    private InetAddress multicastIP;
    private int port;
    NetworkInterface netIf;
    InetSocketAddress group;
    Map<String, Integer> repeticiones = new HashMap<>();


    public ClienteVelocimetre(int portValue, String strIp) throws IOException {
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        socket = new MulticastSocket(port);
        //netIf = NetworkInterface.getByName("enp1s0");
        netIf = socket.getNetworkInterface();
        group = new InetSocketAddress(strIp,portValue);
    }

    public void runClient() throws IOException{
        DatagramPacket packet;
        byte [] receivedData = new byte[1024];

        socket.joinGroup(group,netIf);
        System.out.printf("Connectat a %s:%d%n",group.getAddress(),group.getPort());

        while(continueRunning){
            packet = new DatagramPacket(receivedData, 1024);
            socket.setSoTimeout(5000);
            try{
                socket.receive(packet);
                continueRunning = getData(packet.getData(),packet.getLength());
            }catch(SocketTimeoutException e){
                System.out.println("S'ha perdut la connexió amb el servidor.");
                continueRunning = false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        socket.leaveGroup(group,netIf);
        socket.close();
    }

    protected  boolean getData(byte[] data,int lenght) {
        boolean ret=true;

        String msg = new String(data,0,lenght);

        repeticiones.putIfAbsent(msg,0);

        repeticiones.replace(msg,repeticiones.get(msg)+1);

        System.out.println(msg+" --> "+repeticiones.get(msg));


        return ret;
    }

    public static void main(String[] args) throws IOException {
        ClienteVelocimetre cvel = new ClienteVelocimetre(5557, "224.0.11.115");
        cvel.runClient();
        System.out.println("Parat!");

    }

}