package br.com.fiap;

import java.sql.SQLException;

import br.com.fiap.conexao.ConnectionFactory;
import br.com.fiap.dao.CredencialDao;
import br.com.fiap.model.Credencial;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;


public class PrimaryController {
    @FXML private TextField textFieldLocal;
    @FXML private TextField textFieldLogin;
    @FXML private TextField textFieldSenha;
    
    public void salvar(){ 
        try {
            new CredencialDao().salvar(carregarCredencialDoFormulario());
        } catch (SQLException e) {
            mostrarErro(e);
            e.printStackTrace();
        }
    }

    private Credencial carregarCredencialDoFormulario(){
        String local = textFieldLocal.getText();
        String login = textFieldLogin.getText();
        String senha = textFieldSenha.getText();
        var credencial = new Credencial(local, login, senha);
        return credencial;
    }

    private void mostrarErro(SQLException e) {
        var alerta = new Alert(AlertType.ERROR);
        alerta.setContentText(e.getMessage());
        alerta.show();
    }

}
