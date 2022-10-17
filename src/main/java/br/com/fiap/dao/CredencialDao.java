package br.com.fiap.dao;

import java.sql.SQLException;

import br.com.fiap.conexao.ConnectionFactory;
import br.com.fiap.model.Credencial;

public class CredencialDao {
    
    public void salvar(Credencial credencial) throws SQLException{
        var conexao = ConnectionFactory.getConnection();
        String sql = "INSERT INTO credenciais (local, login, senha) VALUES (?, ?, ?)";
        var ps = conexao.prepareStatement(sql);
        ps.setString(1, credencial.getLocal());
        ps.setString(2, credencial.getLogin());
        ps.setString(3, credencial.getSenha());

        ps.execute();

        conexao.close();
    }

}
