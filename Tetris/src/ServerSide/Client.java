package ServerSide;

import java.io.*;
import java.net.Socket;

/**
 * Client class:
 * I think you can run this from anywhere
 * I mostly ran it from jack@Laptop:~/Documents/Programs/Java/Assignment_2/src/sample
 * look at main method for example on how to use
 */
public class Client {
    DataInputStream in;
    DataOutputStream out;

    public Client(Socket socket) throws IOException {
        this.out = new DataOutputStream(socket.getOutputStream());
        this.in = new DataInputStream(socket.getInputStream());
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//        //create socket and client
//        var socket = new Socket("localhost", 25505);
//        Client client = new Client(socket);
//        /**
//         * command examples that worked for me are commented above their if statement
//         */
//        //command: java Client.java DIR
//        if (args[0].equals("DIR")) {
//            System.out.println("Available Files: ");
//            System.out.print(client.DIR());
//
//            //command: java Client.java UPLOAD ~jack/Downloads/WordCounter2.zip
//        }
//        if (args[0].equals("UPLOAD")) {
//            File file = new File(args[1]);
//            System.out.print(client.DIR());
//            socket = new Socket("localhost", 25505);
//            client = new Client(socket);
//            client.UPLOAD(file);
//
//            //command: java Client.java DOWNLOAD WordCounter2.zip ~jack/Downloads/
//        }
//        if (args[0].equals("DOWNLOAD")) {
//            client.DOWNLOAD(args[1], args[2]);
//        }
//
//    }

    /**
     * get the list of highScores
     * @return string of highScores
     * @throws IOException
     */
    public String GETHIGHSCORES() throws IOException {
        out.writeUTF("GETHIGHSCORES");
        String highScores = in.readUTF();
        return highScores;
    }

    /**
     * add a highScore to list
     * @param score: the string value of a score
     * @throws IOException
     */
    public void ADDHIGHSCORE(String score) throws IOException {
        out.writeUTF("ADDHIGHSCORE\n" + score);
    }
}

