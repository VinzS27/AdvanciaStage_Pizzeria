package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pizza")
public class Pizza {

	@Id
	@Column(name = "id_pizza")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "id_impasto")
	private Impasto impasto;

	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pizza_ingrediente", joinColumns = { @JoinColumn(name = "id_pizza") }, inverseJoinColumns = {
			@JoinColumn(name = "id_ingrediente") })
	private List<Ingrediente> ingredienti;

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

	public Impasto getImpasto() {
		return impasto;
	}

	public void setImpasto(Impasto impasto) {
		this.impasto = impasto;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

}