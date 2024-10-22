package org.advancia.dto;

import java.util.ArrayList;
import java.util.List;

import org.advancia.entity.Impasto;
import org.advancia.entity.Ingrediente;
import org.advancia.entity.Utente;

public class PizzaDto {

    private long id;
    private String nome;
    private Utente utente;
    private Impasto impasto;
    private List<Ingrediente> ingredienti = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public Impasto getImpasto() {
		return impasto;
	}

	public void setImpasto(Impasto impasto) {
		this.impasto = impasto;
	}

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
}