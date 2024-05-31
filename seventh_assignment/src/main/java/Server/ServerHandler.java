package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ServerHandler implements Runnable {

    private String name;
    private Socket socket;
    private DataInputStream dataInputStream;


    public ServerHandler(String name, Socket socket) throws IOException {
        this.name = name;
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = dataInputStream.readUTF();
                if (message.equals("Add124#!32")){
                    Server.clients.add(socket);
                }
                else if (message.equals("Remove3&23@%")){
                    Server.clients.remove(socket);

                }else {
                    sentAll(socket, message);
                    System.out.println(name + " : " + message);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void sentAll(Socket socket, String message) throws IOException {
        for (Socket target : Server.clients) {
            if (target != socket) {
                DataOutputStream dataOutputStream = new DataOutputStream(target.getOutputStream());
                dataOutputStream.writeUTF(name + " : " + message);
            }
        }
    }


}
