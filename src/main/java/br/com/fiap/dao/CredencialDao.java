package br.com.fiap.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConnectionFactory;
import br.com.fiap.model.Credencial;
import br.com.fiap.service.CriptografiaService;

public class CredencialDao {

    private CriptografiaService service = new CriptografiaService();
    
    public void salvar(Credencial credencial) throws SQLException{
        var conexao = ConnectionFactory.getConnection();
        String sql = "INSERT INTO credenciais (local, login, senha) VALUES (?, ?, ?)";
        var ps = conexao.prepareStatement(sql);
        ps.setString(1, credencial.getLocal());
        ps.setString(2, credencial.getLogin());
        ps.setString(3, service.criptografar( credencial.getSenha() ));

        ps.execute();

        conexao.close();
    }

    public List<Credencial> listarTodas() throws SQLException{
        var lista = new ArrayList<Credencial>();

        var conexao = ConnectionFactory.getConnection();
        var ps = conexao.prepareStatement("SELECT * FROM credenciais ORDER BY local");
        var rs = ps.executeQuery();

        while(rs.next()){
            lista.add(new Credencial(
                rs.getString("local"), 
                rs.getString("login"), 
                service.descriptografar(rs.getString("senha"))
            ));
        }

        return lista;
    }

}
