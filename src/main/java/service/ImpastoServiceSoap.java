package service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import dao.PizzeriaRESTDAO;
import dto.ImpastoDTO;
import model.Impasto;

@WebService
public class ImpastoServiceSoap {

	@WebMethod
	public List<ImpastoDTO> getImpasto_JSON() {
		return PizzeriaRESTDAO.getAllImpasto();
	}

	@WebMethod
	public ImpastoDTO getImpasto(@WebParam(name = "id") int idImpasto) {
		return PizzeriaRESTDAO.getImpastoById(idImpasto);
	}
	
	@WebMethod
	public Impasto addImpasto(@WebParam(name = "nome")String nome) {
		return PizzeriaRESTDAO.createImpasto(nome);
	}

	@WebMethod
	public Impasto updateImpasto(String id, String nome) {
		return PizzeriaRESTDAO.updateImpasto(id,nome);
	}

	@WebMethod
	public String deleteImpasto( int id) {
		String result = PizzeriaRESTDAO.deleteImpasto(id);
		return result;
	}
}

