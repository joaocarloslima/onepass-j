package br.com.fiap;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class SecondaryController {

    @FXML private PasswordField senha;

    @FXML
    private void logar() throws IOException {
        if(senha.getText().equals("123")) App.setRoot("primary");
    }
}