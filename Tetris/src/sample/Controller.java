package sample;

import ServerSide.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;


public class Controller {
    Game game;
    @FXML
    private TextArea textArea;

    @FXML
    public void startTetris(){
        Game game = new Tetris();
        game.run();
    }
    @FXML
    public void exit(){
        System.exit(0);
    }
    @FXML
    public void showHighScores() throws IOException {
        Socket socket = new Socket("localhost", 25505);
        Client client = new Client(socket);
        String HS = client.GETHIGHSCORES();
        textArea.setText(HS);
    }
    @FXML
    public void initialize() throws IOException {

    }



}
