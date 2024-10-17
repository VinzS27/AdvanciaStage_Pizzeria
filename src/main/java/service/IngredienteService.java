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
import dto.IngredienteDTO;
import model.Ingrediente;

@Path("/ingrediente")
public class IngredienteService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<IngredienteDTO> getIngrediente_JSON() {
		return PizzeriaRESTDAO.getAllIngrediente();	
	}

	@GET
	@Path("/{idIngrediente}")
	@Produces(MediaType.APPLICATION_JSON)
	public IngredienteDTO getIngrediente(@PathParam("idIngrediente") int id) {
		return PizzeriaRESTDAO.getIngredienteById(id);
	}
	
	@POST
	@Path("/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ingrediente addIngrediente(@PathParam("nome")String nome) {
		return PizzeriaRESTDAO.createIngrediente(nome);
	}

	@PUT
	@Path("/{id}/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ingrediente updateIngrediente(@PathParam("id")String id, @PathParam("username")String nome) {
		return PizzeriaRESTDAO.updateIngrediente(id,nome);
	}

	@DELETE
	@Path("/{idIngrediente}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteIngrediente(@PathParam("idIngrediente") int id) {
		String result = PizzeriaRESTDAO.deleteIngrediente(id);
		return result;
	}
}
