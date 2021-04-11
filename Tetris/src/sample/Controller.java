package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class Controller {
    public static final Game game = new Tetris();

    @FXML
    public void startTetris(){
        game.run();
    }
    @FXML
    public void exit(){
        System.exit(0);
    }
    @FXML
    public void initialize() throws IOException {

    }


}
