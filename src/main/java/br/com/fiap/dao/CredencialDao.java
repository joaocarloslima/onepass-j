package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexao.ConnectionFactory;
import br.com.fiap.model.Credencial;
import br.com.fiap.service.CriptografiaService;

public class CredencialDao {

    // TODO carregar do arquivo de propriedades
    private static final String URL = "jdbc:mysql://localhost:3306/senhas";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    private CriptografiaService service = new CriptografiaService();
    private Connection conexao;
    
    public CredencialDao() throws SQLException {
        conexao = ConnectionFactory.getConnection(URL, USUARIO, SENHA);
    }

    public void salvar(Credencial credencial) throws SQLException{
        String sql = "INSERT INTO credenciais (local, login, senha) VALUES (?, ?, ?)";
        var ps = conexao.prepareStatement(sql);
        ps.setString(1, credencial.getLocal());
        ps.setString(2, credencial.getLogin());
        ps.setString(3, service.criptografar( credencial.getSenha() ));

        ps.execute();

    }

    public List<Credencial> listarTodas() throws SQLException{
        var lista = new ArrayList<Credencial>();

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
