package UF3.actividades.Tasca2;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class DatagramSocketClient {
    InetAddress serverIP;
    int serverPort;
    DatagramSocket socket;
    int attemts = 0;
    Scanner scanner = new Scanner(System.in);

    public void init(String host, int port) throws SocketException,
            UnknownHostException {
        serverIP = InetAddress.getByName(host);
        serverPort = port;
        socket = new DatagramSocket();
    }

    public void runClient() throws IOException {
        byte [] receivedData = new byte[1024];
        byte [] sendingData;

//TcpSocketServer.java l'inici
        sendingData = getFirstRequest();
//el servidor atén el port indefinidament
        while(mustContinue(receivedData)){
            DatagramPacket packet = new DatagramPacket(sendingData,
                    sendingData.length,
                    serverIP,
                    serverPort);
//enviament de la resposta
            socket.send(packet);

//creació del paquet per rebre les dades
            packet = new DatagramPacket(receivedData, 1024);
//espera de les dades
            socket.receive(packet);
//processament de les dades rebudes i obtenció de la resposta
            sendingData = getDataToRequest(packet.getData(), packet.getLength());
        }
    }

    private byte[] getDataToRequest(byte[] data, int length) {
        System.out.println(convertResposeServer(data));
        System.out.println("Introduce datos: ");
        int num = scanner.nextInt();
        byte[] missatge = ByteBuffer.allocate(4).putInt(num).array();
        return missatge;
    }
    // No es estrictamente necesario tener este metodo.
    private byte[] getFirstRequest() {
        System.out.println("Introduce datos: ");
        int num = scanner.nextInt();
        byte[] missatge = ByteBuffer.allocate(4).putInt(num).array();
        return missatge;
    }

    private boolean mustContinue(byte[] data) {
        //procés diferent per cada aplicació
        if (ByteBuffer.wrap(data).getInt() == 0 && attemts !=0){
            System.out.println("Has acertado el numero en el intento: "+attemts);
            return false;
        }
        return true;
    }

    private String convertResposeServer(byte[]data){
        int n = ByteBuffer.wrap(data).getInt();
        String msg = null;
        if (n==0){msg = "Lo has logrado en el intento: "+attemts;}
        else if (n==1) {msg = "Es un numero mas pequeño"; attemts++;}
        else msg = "Es un numero mayor"; attemts++;

        return msg;
        }

    public static void main(String[] args) {
        DatagramSocketClient client = new DatagramSocketClient();
        try{
            client.init("192.168.250.84",5555);
            client.runClient();
        } catch (IOException e){
            e.getStackTrace();
        }
    }

}
