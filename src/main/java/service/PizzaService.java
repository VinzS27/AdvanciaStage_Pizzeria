package service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.*;
import dto.PizzaDTO;

@Path("/pizza")
public class PizzaService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PizzaDTO> getPizze_JSON() {
		return PizzeriaRESTDAO.getAllPizza();
	}

	@GET
	@Path("/{idPizza}")
	@Produces(MediaType.APPLICATION_JSON)
	public PizzaDTO getPizzaById(@PathParam("idPizza") int id) {
		return PizzeriaRESTDAO.getPizzaById(id);	
	}

	@DELETE
	@Path("/{idPizza}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deletePizza(@PathParam("idPizza") int id) {
		return PizzeriaRESTDAO.deletePizza(id);	
	}
}
