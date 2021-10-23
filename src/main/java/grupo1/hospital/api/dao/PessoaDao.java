package grupo1.hospital.api.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import grupo1.hospital.api.MySqlSingleton;
import grupo1.hospital.api.pessoa.Pessoa;

public class PessoaDao {
	private Connection connection;
	
	public PessoaDao() {
		this.connection = MySqlSingleton.getConnection();
	}
	
    public Integer inserir(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa(nome, cpf, rg, dtNascimento, telefone, sexo) VALUES(?, ?, ?, ?, ?, ?)";
        Integer idPessoa = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getCpf());
            stmt.setInt(3, pessoa.getRg());
            stmt.setDate(4, Date.valueOf(pessoa.getDtNascimento()));
            stmt.setString(5, pessoa.getTelefone());
            stmt.setString(6, pessoa.getSexo());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0)
                idPessoa = 0;
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idPessoa = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException ex) {
        	idPessoa = 0;
        }
        return idPessoa;
    }
}
