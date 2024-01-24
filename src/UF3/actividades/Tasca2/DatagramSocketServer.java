package UF3.actividades.Tasca2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;

public class DatagramSocketServer {
    DatagramSocket socket;
    SecretNum secretNum;
    boolean acabar;
    int fi = -1;

    public void init(int port) throws SocketException {
        socket = new DatagramSocket(port);
        secretNum = new SecretNum(100);
        acabar = true;
    }

    public void runServer() throws IOException {
        byte [] receivingData = new byte[1024];
        byte [] sendingData;
        InetAddress clientIP;
        int clientPort;

        //el servidor atén el port indefinidament
        while(acabar){
            //creació del paquet per rebre les dades
            DatagramPacket packet = new DatagramPacket(receivingData, 4);
            //espera de les dades
            socket.receive(packet);
            //processament de les dades rebudes i obtenció de la resposta
            sendingData = processData(packet.getData(), packet.getLength());
            //obtenció de l'adreça del client
            clientIP = packet.getAddress();
            //obtenció del port del client
            clientPort = packet.getPort();
            //creació del paquet per enviar la resposta
            packet = new DatagramPacket(sendingData, sendingData.length,
                    clientIP, clientPort);
            //enviament de la resposta
            socket.send(packet);
        }
    }

    private byte[] processData(byte[] data, int length) {
    //procés diferent per cada aplicació
        //String dataToTransform = new String(data,0,length);
        //dataToTransform = dataToTransform.toUpperCase();
        //System.out.println(dataToTransform);
        int n = ByteBuffer.wrap(data).getInt();
        int num=secretNum.comprova(n);
        fi = secretNum.comprova(num);
        if (fi==0) acabar = false;
        byte[] missatge = ByteBuffer.allocate(4).putInt(num).array();
        return missatge;
    }

    public static void main(String[] args) {
        DatagramSocketServer server = new DatagramSocketServer();
        try{
            server.init(5555);
            server.runServer();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
