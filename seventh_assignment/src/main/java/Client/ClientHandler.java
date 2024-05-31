package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private String clientName;
    private Socket socket;
    private DataInputStream dataInputStream;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        dataInputStream = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = dataInputStream.readUTF();
                System.out.println(message);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
