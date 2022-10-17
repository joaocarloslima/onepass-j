package br.com.fiap.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection conexao;

    public static Connection getConnection(String url, String usuario, String senha) throws SQLException{
        if (conexao == null)
            conexao = DriverManager.getConnection(url, usuario, senha);
        
        return conexao;
        
    }
    
}
