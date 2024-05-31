package Client;

import Server.Server;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
import java.util.SortedMap;

// Client Class
public class Client {
    private static int port = 2000;
    private static String name;
    private static DataOutputStream dataOutputStream;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", port);
            System.out.println("connected to server");
            System.out.println("Enter Your Name:");
            name = reader.readLine();
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(name);

            Server.threadPool.execute(new ClientHandler(socket));

            int input = 0;
            while (input!=3){

                System.out.println("1-Join Chat\n2-Download File\n3-Exit");
                input= scanner.nextInt();
                switch (input){
                    case 1:
                        joinChat(socket);
                        break;
                    case 2:
                        download();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }



            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }




    public static  void joinChat(Socket socket) throws IOException {
        dataOutputStream.writeUTF("Add124#!32");
        while (true) {
            System.out.println("Enter Message :  \nEnter Q For Exit");
            String message = reader.readLine();
            if (Objects.equals(message, "Q")) {
                break;
            }
            dataOutputStream.writeUTF(message);
        }
        System.out.println("You Left The chat...");
        dataOutputStream.writeUTF("Remove3&23@%");
    }


    public static void download() {
        System.out.println("\033[34mHere is the name of files you can download:\n\033[0m");
        File directory = new File("/Users/matinabedi/Desktop/java/Seventh-Assignment-Socket-Programming/data");
        File[] files = directory.listFiles();

        for (File file : files) {
            System.out.println("\033[37m" + file.getName() + "\033[0m");
        }

        System.out.println("\033[34mType the name of file you want to download.\033[0m");
        String fileName = scanner.nextLine();
        String fileNama = scanner.nextLine();
        fileNama=fileNama;


        System.out.println("\033[34mWhere do you want to save this file? give me a full path directory.\033[0m");
        String savePath = scanner.nextLine();

        File copyFile = new File("/Users/matinabedi/Desktop/java/Seventh-Assignment-Socket-Programming/data/" + fileName);
        File pasteFile = new File(savePath + "/" + fileName);

        try {
            FileInputStream fileInputStream = new FileInputStream(copyFile);
            FileOutputStream fileOutputStream = new FileOutputStream(pasteFile);

            byte[] bytes = new byte[1024];
            int data;
            while ((data = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, data);
            }
            System.out.println("\033[32mFile downloaded successfully.\033[0m");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}