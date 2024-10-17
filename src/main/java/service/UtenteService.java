package service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.PizzeriaRESTDAO;
import dto.UtenteDTO;
import model.Utente;

@Path("/utente")
public class UtenteService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UtenteDTO> getUtente_JSON() {
		List<UtenteDTO> listResult = PizzeriaRESTDAO.getAllUtente();
		return listResult;
	}

	@GET
	@Path("/{idUtente}")
	@Produces(MediaType.APPLICATION_JSON)
	public UtenteDTO getUtente(@PathParam("idUtente") int id) {
		return PizzeriaRESTDAO.getUtenteById(id);
	}

	@POST
	@Path("/{username}/{password}") //in generale è meglio usare l'oggetto e passare il parametro con JSON
	@Produces(MediaType.APPLICATION_JSON)
	public Utente addUtente(@PathParam("username")String username, @PathParam("password")String password) {
		return PizzeriaRESTDAO.createUtente(username,password);
	}

	@PUT
	@Path("/{id}/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Utente updateUtente(@PathParam("id")String id,
			@PathParam("username")String username, @PathParam("password")String password) {
		return PizzeriaRESTDAO.updateUtente(id,username,password);
	}

	@DELETE
	@Path("/{idUtente}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteUtente(@PathParam("idUtente") int id) {
		String result = PizzeriaRESTDAO.deleteUtente(id);
		return result;
	}
}
