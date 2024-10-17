package service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dao.PizzeriaRESTDAO;
import dto.UtenteDTO;
import model.Utente;

@WebService
public class UtenteServiceSoap {

	@WebMethod
	public List<UtenteDTO> getUtente_JSON() {
		List<UtenteDTO> listResult = PizzeriaRESTDAO.getAllUtente();
		return listResult;
	}

	@WebMethod
	public UtenteDTO getUtente(@WebParam(name = "id") int id) {
		return PizzeriaRESTDAO.getUtenteById(id);
	}

	@WebMethod
	public Utente addUtente(@WebParam(name = "username") String username,
			@WebParam(name = "password") String password) {
		return PizzeriaRESTDAO.createUtente(username, password);
	}

	@WebMethod
	public Utente updateUtente(String id, String username, String password) {
		return PizzeriaRESTDAO.updateUtente(id, username, password);
	}

	@WebMethod
	public String deleteUtente(int id) {
		String result = PizzeriaRESTDAO.deleteUtente(id);
		return result;
	}
}
