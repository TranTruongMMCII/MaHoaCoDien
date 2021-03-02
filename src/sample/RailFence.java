package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class RailFence {

    @FXML
    private AnchorPane RailFence;

    @FXML
    private JFXTextField txtPlainText;

    @FXML
    private JFXTextField txtKey;

    @FXML
    private Button btnMaHoa;

    @FXML
    private Button btnLuuFile;

    @FXML
    private JFXTextField txtCipherText;

    @FXML
    private Button btnGiaiMa;

    @FXML
    private Button btnMoFile;

    private Alert alert;

    @FXML
    void giaiMa(ActionEvent event) {
        if (txtCipherText.getText().equals("")){
            alert = new Alert(AlertType.WARNING);
            alert.setContentText("Bạn vui lòng nhập điền vào chuỗi giải mã!");
            alert.showAndWait();
            return;
        }
        else if (txtKey.getText().equals("Key")){
            alert = new Alert(AlertType.WARNING);
            alert.setContentText("Bạn vui lòng nhập điền vào khóa!");
            alert.showAndWait();
            return;
        }
        else if (Integer.parseInt(txtKey.getText()) < 0){
            alert = new Alert(AlertType.WARNING);
            alert.setContentText("Bạn vui lòng nhập điền khóa lớn hơn 0!");
            alert.showAndWait();
            return;
        }
        else
            txtPlainText.setText(decrypt(txtCipherText.getText(), Integer.parseInt(txtKey.getText())));
    }

    @FXML
    void luuFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text file(s)", "*.txt");
        fileChooser.setTitle("Choose file...");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Stage stage = (Stage) RailFence.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null){
            String filename = file.getAbsolutePath();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filename)));
            bufferedWriter.write(txtCipherText.getText());
            bufferedWriter.close();
        }
    }

    private static int getTerm(int iteration, int row, int size) {
        if ((size == 0) || (size == 1)) {
            return 1;
        }
        if((row == 0) || (row == size-1)) { // Max. distance is achieved at the ends and equally (size-1)*2
            return (size-1)*2;
        }

        if (iteration % 2 == 0) { // In the description of the method above this identity is demonstrated
            return (size-1-row)*2;
        }
        return 2*row;
    }

    private static String decrypt(String message, int key) {
        if (key < 0) {
            throw new ArithmeticException("Negative key value");
        }
        StringBuilder decodedMessage = new StringBuilder(message);
        int currPosition = 0; // Position in source string
        for(int row = 0; row < key; row++) { // Look rows
            int iter = 0; // The number of the character in the row
            for(int i = row; i < message.length(); i += getTerm(iter++, row, key)) {
                decodedMessage.setCharAt(i, message.charAt(currPosition++));
            }


        }
        return decodedMessage.toString();
    }

    private static String encrypt(String message, int key)
    {
        if (key < 0) {
            throw new ArithmeticException("Negative key value");
        } else if (key == 0) {
            key = 1;
        }
        String encodedMessage = "";

        for(int row = 0; row < key; row++) { // Look rows
            int iter = 0; // The number of the character in the row
            for(int i = row; i < message.length(); i += getTerm(iter++, row, key)) {
                // i - the number of row character in source line

                encodedMessage += message.charAt(i); // "Add characters line by row"
            }


        }
        return encodedMessage;
    }

    @FXML
    void maHoa(ActionEvent event) {
        if (txtPlainText.getText().equals("")){
            alert = new Alert(AlertType.WARNING);
            alert.setContentText("Bạn vui lòng nhập điền vào chuỗi đầu vào!");
            alert.showAndWait();
            return;
        }
        else if (txtKey.getText().equals("Key")){
            alert = new Alert(AlertType.WARNING);
            alert.setContentText("Bạn vui lòng nhập điền vào khóa!");
            alert.showAndWait();
            return;
        }
        else if (Integer.parseInt(txtKey.getText()) < 0){
            alert = new Alert(AlertType.WARNING);
            alert.setContentText("Bạn vui lòng nhập điền khóa lớn hơn 0!");
            alert.showAndWait();
            return;
        }
        else
            txtCipherText.setText(encrypt(txtPlainText.getText(), Integer.parseInt(txtKey.getText())));
    }

    @FXML
    void moFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text file(s)", "*.txt");
        fileChooser.setTitle("Open file...");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Stage stage = (Stage) RailFence.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null){
            String filename = file.getAbsolutePath();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filename)));
            StringBuffer stringBuffer = new StringBuffer();
            int i;
            while ((i = bufferedReader.read()) != -1){
                stringBuffer.append((char)i);
            }

            txtPlainText.setText(stringBuffer.toString());

            bufferedReader.close();
        }
    }

}
