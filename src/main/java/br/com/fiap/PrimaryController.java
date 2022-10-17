package br.com.fiap;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.fiap.dao.CredencialDao;
import br.com.fiap.model.Credencial;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;


public class PrimaryController implements Initializable {
    @FXML private TextField textFieldLocal;
    @FXML private TextField textFieldLogin;
    @FXML private TextField textFieldSenha;

    @FXML private TableView<Credencial> tabela;
    @FXML private TableColumn<Credencial, String> colunaLocal;
    @FXML private TableColumn<Credencial, String> colunaLogin;
    @FXML private TableColumn<Credencial, String> colunaSenha;
    
    public void salvar(){ 
        try {
            new CredencialDao().salvar(carregarCredencialDoFormulario());
            carregarDados();
        } catch (SQLException e) {
            mostrarErro(e);
            e.printStackTrace();
        }
    }

    public void carregarDados(){
        tabela.getItems().clear();
        try {
            var lista = new CredencialDao().listarTodas();
            tabela.getItems().addAll(lista);
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        colunaLocal.setCellValueFactory(new PropertyValueFactory<>("local"));
        colunaLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colunaSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        carregarDados();
    }

}
