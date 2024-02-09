package UF3.actividades.TCPMultiClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TcpSocketServer {
    private Scanner sc = new Scanner(System.in);
    static final int PORT = 9090;
    private boolean end = false;

    public void listen(){
        ServerSocket serverSocket=null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while(!end){
                clientSocket = serverSocket.accept();
                System.out.println("Connexió amb: " + clientSocket.getInetAddress());
                ThreadLlista FilServidor = new ThreadLlista(clientSocket);
                Thread client = new Thread(FilServidor);
                client.start();
            }
            //tanquem el sòcol principal
            if(serverSocket!=null && !serverSocket.isClosed()){
                serverSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(TcpSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        TcpSocketServer tcpSocketServer = new TcpSocketServer();
        Thread thTcp = new Thread(tcpSocketServer::listen);
        thTcp.start();
    }
}