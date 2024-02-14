package UF3.actividades.TCPMultiClients;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TcpSocketClient extends Thread{
    private Scanner sc = new Scanner(System.in);
    private Llista lista;
    private InputStream in;
    private OutputStream out;

    public void connect(String address, int port) {
        String serverData;
        Llista request;
        Socket socket;


        try {
            socket = new Socket(InetAddress.getByName(address), port);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            //Llegim la jugada
            ObjectInputStream ois = new ObjectInputStream(in);
            //el client atén el port fins que decideix finalitzar
            //processament de les dades rebudes i obtenció d'una nova petició
            request = getRequest();
            //enviament de la petició
            oos.writeObject(request);//assegurem que acaba amb un final de línia
            out.flush(); //assegurem que s'envia
            //comprovem si la petició és un petició de finalització i en cas
            //que ho sigui ens preparem per sortir del bucle

            //Espera a recibir la lista del servidor
            lista = (Llista) ois.readObject();
            System.out.println("Nombre de la lista: "+ lista.getNom()+" Numeros de la lista: "+lista.getNumberList());

            close(socket);
        } catch (UnknownHostException ex) {
            System.out.printf("Error de connexió. No existeix el host, %s", ex);
        } catch (IOException ex) {
            System.out.printf("Error de connexió indefinit, %s", ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private Llista getRequest() {
        //System.out.println("servidor$ " + serverData);
        //System.out.print("$ ");
        System.out.println("Escribe un nombre para la lista: ");
        String nombreLista = sc.nextLine();
        System.out.println("Escribe un conjunto de numeros: ");
        List<Integer> listaNumeris = new ArrayList<>();
        while (sc.hasNextInt()){
            listaNumeris.add(sc.nextInt());
        }
        return new Llista(nombreLista, listaNumeris);
    }

    private void close(Socket socket){
        //si falla el tancament no podem fer gaire cosa, només enregistrar
        //el problema
        try {
            //tancament de tots els recursos
            if(socket!=null && !socket.isClosed()){
                if(!socket.isInputShutdown()){
                    socket.shutdownInput();
                }
                if(!socket.isOutputShutdown()){
                    socket.shutdownOutput();
                }
                socket.close();
            }
        } catch (IOException ex) {
            //enregistrem l'error amb un objecte Logger
            Logger.getLogger(TcpSocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        TcpSocketClient tcpSocketClient = new TcpSocketClient();
        tcpSocketClient.connect("localhost", 9090);
    }
}