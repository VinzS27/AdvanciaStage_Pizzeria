package service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dao.PizzeriaRESTDAO;
import dto.IngredienteDTO;
import model.Ingrediente;

@WebService
public class IngredienteServiceSoap {

	@WebMethod
	public List<IngredienteDTO> getIngrediente_JSON() {
		return PizzeriaRESTDAO.getAllIngrediente();	
	}

	@WebMethod
	public IngredienteDTO getIngrediente(@WebParam(name = "id") int id) {
		return PizzeriaRESTDAO.getIngredienteById(id);
	}
	
	@WebMethod
	public Ingrediente addIngrediente(@WebParam(name = "nome")String nome) {
		return PizzeriaRESTDAO.createIngrediente(nome);
	}

	@WebMethod
	public Ingrediente updateIngrediente(String id,String nome) {
		return PizzeriaRESTDAO.updateIngrediente(id,nome);
	}

	@WebMethod
	public String deleteIngrediente(int id) {
		String result = PizzeriaRESTDAO.deleteIngrediente(id);
		return result;
	}
}
