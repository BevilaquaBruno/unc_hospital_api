package grupo1.hospital.api.paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import grupo1.hospital.api.MySqlSingleton;
import grupo1.hospital.api.dao.PacienteDao;
import grupo1.hospital.api.dao.PessoaDao;
import grupo1.hospital.api.pessoa.Pessoa;

@Component
public class PacienteService implements PacienteInterface{
	
	private PacienteDao pacienteDao;
	private PessoaDao pessoaDao;

	@Override
	@Produces("application/json")
	public Paciente getPaciente(Integer id) {
		pacienteDao = new PacienteDao();
		Paciente p = pacienteDao.findById(id);
		return p;
	}

	@Override
	public Paciente salvarPaciente(Paciente paciente) {
		pessoaDao = new PessoaDao();
		pacienteDao = new PacienteDao();

		if(paciente.getIdPessoa() == 0) {
			Pessoa pessoaPaciente = new Pessoa(paciente.getNome(), paciente.getCpf(),
					paciente.getRg(), paciente.getTelefone(), paciente.getDtNascimento(), paciente.getSexo(), paciente.getIdPessoa());
			Integer idPessoa = pessoaDao.inserir(pessoaPaciente);
			paciente.setIdPessoa(idPessoa);
		}

		
		Integer idPaciente = pacienteDao.inserir(paciente);
		
		paciente.setId(idPaciente);
		
		return paciente;
	}
}
