package br.com.fiap;

import java.sql.SQLException;

import br.com.fiap.conexao.ConnectionFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PrimaryController {

    public void salvar(){
        try {
            var conexao = ConnectionFactory.getConnection();
            System.out.println("conectado");
            conexao.close();
        } catch (SQLException e) {
            mostrarErro(e);
            e.printStackTrace();
        }
    }

    private void mostrarErro(SQLException e) {
        var alerta = new Alert(AlertType.ERROR);
        alerta.setContentText(e.getMessage());
        alerta.show();
    }

}
