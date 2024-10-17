package dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import model.Ingrediente;

@XmlRootElement(name = "PizzaDTO")
public class PizzaDTO {
	
	private int id;
	private String nome;
	private ImpastoDTO impasto;
	private UtenteDTO utente;
	private List<Ingrediente> ingrediente;
	
	public PizzaDTO() {}
	
	public PizzaDTO(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public PizzaDTO(int id, String nome, ImpastoDTO impasto, UtenteDTO utente) {
		super();
		this.id = id;
		this.nome = nome;
		this.impasto = impasto;
		this.utente = utente;
	}
	
	public PizzaDTO(int id, String nome, ImpastoDTO impasto, List<Ingrediente> ingrediente, UtenteDTO utente) {
		super();
		this.id = id;
		this.nome = nome;
		this.impasto = impasto;
		this.utente = utente;
		this.ingrediente = ingrediente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ImpastoDTO getImpastoDTO() {
		return impasto;
	}

	public void setImpastoDTO(ImpastoDTO impasto) {
		this.impasto = impasto;
	}

	public UtenteDTO getUtenteDTO() {
		return utente;
	}

	public void setUtenteDTO(UtenteDTO utente) {
		this.utente = utente;
	}

	public List<Ingrediente> getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(List<Ingrediente> ingrediente) {
		this.ingrediente = ingrediente;
	}
}
