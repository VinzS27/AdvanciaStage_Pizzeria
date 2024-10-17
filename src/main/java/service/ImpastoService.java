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
import dto.ImpastoDTO;
import model.Impasto;

@Path("/impasto")
public class ImpastoService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ImpastoDTO> getImpasto_JSON() {
		return PizzeriaRESTDAO.getAllImpasto();
	}

	@GET
	@Path("/{idImpasto}")
	@Produces(MediaType.APPLICATION_JSON)
	public ImpastoDTO getImpasto(@PathParam("idImpasto") int idImpasto) {
		return PizzeriaRESTDAO.getImpastoById(idImpasto);
	}
	
	@POST
	@Path("/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public Impasto addImpasto(@PathParam("nome")String nome) {
		return PizzeriaRESTDAO.createImpasto(nome);
	}

	@PUT
	@Path("/{id}/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public Impasto updateImpasto(@PathParam("id")String id, @PathParam("username")String nome) {
		return PizzeriaRESTDAO.updateImpasto(id,nome);
	}

	@DELETE
	@Path("/{idImpasto}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteImpasto(@PathParam("idImpasto") int id) {
		String result = PizzeriaRESTDAO.deleteImpasto(id);
		return result;
	}
}
