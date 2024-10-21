package dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IngredienteDTO")
public class IngredienteDTO {

	private int id;
	private String nome;
	
	public IngredienteDTO() {
		super();
	}

	public IngredienteDTO(int id, String nome) {
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
