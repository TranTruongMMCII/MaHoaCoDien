package sample;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    private JFXButton btnVigenere;

    @FXML
    private JFXButton btnCaesar;

    @FXML
    private JFXButton btnRailFence;

    private Parent fxmlLoader;
    private Stage stage;

    public void setBtnCaesar(ActionEvent e) throws IOException {
        stage = new Stage();
        fxmlLoader = FXMLLoader.load(getClass().getResource("Caesar.fxml"));
        stage.setTitle("Mã hóa Caesar");
        stage.setScene(new Scene(fxmlLoader, 851, 461));
        stage.show();
    }

    public void setBtnVigenere(ActionEvent e) throws IOException {
        stage = new Stage();
        fxmlLoader = FXMLLoader.load(getClass().getResource("Vigenere.fxml"));
        stage.setTitle("Mã hóa Vigenere");
        stage.setScene(new Scene(fxmlLoader, 851, 461));
        stage.show();
    }

    public void setBtnRailFence(ActionEvent actionEvent) throws IOException {
        stage = new Stage();
        fxmlLoader = FXMLLoader.load(getClass().getResource("RailFence.fxml"));
        stage.setTitle("Mã hóa Rail Fence");
        stage.setScene(new Scene(fxmlLoader, 851, 461));
        stage.show();
    }
}
