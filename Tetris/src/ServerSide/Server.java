package ServerSide;
import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Server {
    /**
     *
     * @return string of files seperated by \n
     */
    public static ArrayList<Integer> getHighScores(){
        ArrayList<Integer> highScores = new ArrayList<>();
        try {
            File myObj = new File("highScores.txt");
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                highScores.add(Integer.valueOf(reader.nextLine()));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return highScores;
    }

    /**
     * adds file to list of files
     * @param newHighScore value of highScore
     */
    public static void addToHighScores(String newHighScore){
        ArrayList<Integer> highScores = getHighScores();
        highScores.add(Integer.valueOf(newHighScore));
        Collections.sort(highScores);
        Collections.reverse(highScores);
        try(FileWriter fw = new FileWriter("highScores.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            for (Integer score: highScores) {
                out.println(score);
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
    public static void main(String[] args) throws Exception {

        try (var listener = new ServerSocket(25505)) {
            System.out.println("The server is running...");
            var pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new instance(listener.accept()));
            }
        }
    }


}

//one instance per connection
class instance implements Runnable {
    private Socket socket;

    instance(Socket socket) {
        System.out.println("new thread created");
        this.socket = socket;
    }
    @Override
    public void run() {
        System.out.println("Connected: " + socket);
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
             in = new DataInputStream(socket.getInputStream());
             out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
                /**
                 * format of a command is one argument per line
                 * first line is always the command eg ADDHIGHSCORE, GETHIGHSCORES
                 * the following is what the client sends:
                 *      "ADDHIGHSCORE" + "\n" + "highScore"
                 *      "GETHIGHSCORES"
                 */
                String args[] = in.readUTF().split("\\r?\\n");
                System.out.println(args[0]);

                if(args[0].equals("GETHIGHSCORES")){
                    String listString = "";
                    ArrayList<Integer> highScores = Server.getHighScores();
                    for (Integer score : highScores) {
                        listString += score + "\n";
                    }
                    out.writeUTF(listString);
                }
                else if(args[0].equals("ADDHIGHSCORE")){
                    String highScore = args[1];
                    out.writeUTF("Got Filename");
                    out.flush();
                    //update list
                    Server.addToHighScores(highScore);

                }
                else {
                    out.writeUTF("not a recognized command");
                }
// messy error catches do not touch
        } catch (Exception e) {
            System.out.println("Error:" + e);
            try {
                out.writeUTF(e.toString());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
            System.out.println("Closed: " + socket);
        }
    }

}

