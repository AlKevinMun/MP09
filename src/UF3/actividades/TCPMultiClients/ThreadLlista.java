package UF3.actividades.TCPMultiClients;
import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ThreadLlista implements Runnable{

    private Socket clientSocket = null;
    private InputStream in = null;
    private OutputStream out = null;
    private Llista llista = null;
    private boolean acabat;
    public ThreadLlista(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;

        acabat = false;
        //Enllacem els canals de comunicació
        in = clientSocket.getInputStream();
        out = clientSocket.getOutputStream();
    }


    @Override
    public void run() {
        try {
            while(!acabat) {

                ObjectOutputStream oos = new ObjectOutputStream(out);
                //Llegim la jugada
                ObjectInputStream ois = new ObjectInputStream(in);
                try {
                    llista = (Llista) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("Nombre: "+llista.getNom()+"Numeros desordenados: "+llista.getNumberList());

                //Ordenar los numeros
                llista.getNumberList().sort((o1,o2) -> o1.compareTo(o2));
                //Remover los repetiros
                Set<Integer> set = new HashSet<Integer>(llista.getNumberList());
                llista.getNumberList().clear();
                llista.getNumberList().addAll(set);

                oos.writeObject(llista);
                oos.flush();
                clientSocket.close();

            }
        }catch(IOException e){
            System.out.println(e.getLocalizedMessage());
        }

    }
}
