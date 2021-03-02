package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Vigenere {

    @FXML
    private AnchorPane Vigenere;

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

    private static String encrypt(String text, final String key)
    {
        String res = "";
        text = text.toLowerCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'a' || c > 'z')
                continue;
            res += (char) ((c + key.charAt(j) - 2 * 'a') % 26 + 'a');
            j = ++j % key.length();
        }
        return res;
    }

    private static String decrypt(String text, final String key)
    {
        String res = "";
        text = text.toLowerCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'a' || c > 'z')
                continue;
            res += (char) ((c - key.charAt(j) + 26) % 26 + 'a');
            j = ++j % key.length();
        }
        return res;
    }

    public void maHoa(ActionEvent actionEvent) {
        if (txtPlainText.getText().equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Bạn vui lòng nhập điền vào chuỗi đầu vào!");
            alert.showAndWait();
            return;
        }
        else if (txtKey.getText().equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Bạn vui lòng nhập điền vào khóa!");
            alert.showAndWait();
            return;
        }
        else
            txtCipherText.setText(encrypt(txtPlainText.getText(), txtKey.getText()));
    }

    public void luuFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text file(s)", "*.txt");
        fileChooser.setTitle("Choose file...");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Stage stage = (Stage) Vigenere.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null){
            String filename = file.getAbsolutePath();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(filename)));
            bufferedWriter.write(txtCipherText.getText());
            bufferedWriter.close();
        }
    }

    public void giaiMa(ActionEvent actionEvent) {
        if (txtCipherText.getText().equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Bạn vui lòng nhập điền vào chuỗi giải mã!");
            alert.showAndWait();
            return;
        }
        else if (txtKey.getText().equals("")){
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Bạn vui lòng nhập điền vào khóa!");
            alert.showAndWait();
            return;
        }
        else
            txtPlainText.setText(decrypt(txtCipherText.getText(), txtKey.getText()));
    }

    public void moFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text file(s)", "*.txt");
        fileChooser.setTitle("Open file...");
        fileChooser.getExtensionFilters().add(extensionFilter);
        Stage stage = (Stage) Vigenere.getScene().getWindow();
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
