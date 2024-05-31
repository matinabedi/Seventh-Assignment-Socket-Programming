package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Server Class
public class Server {

    private static int port = 2000;
    private static ServerSocket serverSocket;
    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    public static ArrayList<Socket> clients = new ArrayList<Socket>();


    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("\033[35mServer created :) waiting for connection...\033[0m");

        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String name = dataInputStream.readUTF();
            System.out.println(name + "  connected ...");
            ServerHandler serverHandler = new ServerHandler(name, socket);
            new Thread(new ServerHandler(name, socket)).start();




        }

    }
}