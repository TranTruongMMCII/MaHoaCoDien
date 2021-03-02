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

public class Caesar {

    @FXML
    private AnchorPane Caesar;

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
        else
            txtPlainText.setText(decrypt(txtCipherText.getText(), 26 - Integer.parseInt(txtKey.getText())));
    }

    @FXML
    void luuFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text file(s)", "*.txt");
        fileChooser.setTitle("Choose file...");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Stage stage = (Stage) Caesar.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null){
            String filename = file.getAbsolutePath();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filename)));
            bufferedWriter.write(txtCipherText.getText());
            bufferedWriter.close();
        }
    }

    private static String decrypt(String cipher, int shift) {
        StringBuffer result = new StringBuffer();

        if (shift < 0){
            shift = 26 - (-shift % 26);
        }

        for (int i = 0; i < cipher.length(); i++) {
            if (cipher.charAt(i) == 32){
                result.append(" ");
            }
            else if (Character.isUpperCase(cipher.charAt(i))) {
                char ch = (char) (((int) cipher.charAt(i) +
                        shift - 65) % 26 + 65);
                result.append(ch);
            } else {
                char ch = (char) (((int) cipher.charAt(i) +
                        shift - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result.toString();
    }

    private static String encrypt(String text, int s)
    {
        StringBuffer result= new StringBuffer();

        for (int i=0; i<text.length(); i++)
        {
            if (text.charAt(i) == 32){
                result.append(" ");
            }
            else if (Character.isUpperCase(text.charAt(i)))
            {
                char ch = (char)(((int)text.charAt(i) +
                        s - 65) % 26 + 65);
                result.append(ch);
            }
            else
            {
                char ch = (char)(((int)text.charAt(i) +
                        s - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result.toString();
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
        else
            txtCipherText.setText(encrypt(txtPlainText.getText(), Integer.parseInt(txtKey.getText())));
    }

    @FXML
    void moFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text file(s)", "*.txt");
        fileChooser.setTitle("Open file...");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Stage stage = (Stage) Caesar.getScene().getWindow();
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
