package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ImpastoDTO")
public class ImpastoDTO {
	
	private int id;
	private String nome;
	
	public ImpastoDTO() {
		super();
	}

	public ImpastoDTO(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
}
