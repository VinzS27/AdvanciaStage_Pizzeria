package service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dao.*;
import dto.PizzaDTO;

@WebService
public class PizzaServiceSoap {
	
	@WebMethod
	public List<PizzaDTO> getPizze_JSON() {
		return PizzeriaRESTDAO.getAllPizza();
	}

	@WebMethod
	public PizzaDTO getPizzaById(@WebParam(name = "id")int id) {
		return PizzeriaRESTDAO.getPizzaById(id);	
	}

	@WebMethod
	public String deletePizza(@WebParam(name = "id")int id) {
		return PizzeriaRESTDAO.deletePizza(id);	
	}
}
