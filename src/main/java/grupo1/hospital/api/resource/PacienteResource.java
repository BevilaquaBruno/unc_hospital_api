package grupo1.hospital.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import grupo1.hospital.api.paciente.Paciente;
import grupo1.hospital.api.paciente.PacienteInterface;
import grupo1.hospital.api.pessoa.Pessoa;

@Path("paciente")
public class PacienteResource {

	private PacienteInterface pacienteInterface;

	@Autowired
	public PacienteResource(PacienteInterface paciente) {
		this.pacienteInterface = paciente;
	}

	@GET
	@Produces("application/json")
	public Paciente getPaciente(@QueryParam("id") Integer id) {
		return this.pacienteInterface.getPaciente(id);
	}
	
	@POST
	@Produces("application/json")
	public Paciente createPaciente(@RequestBody() Paciente paciente) {
		return this.pacienteInterface.salvarPaciente(paciente);
	}
}
