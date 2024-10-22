package org.advancia.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Pizza extends PanacheEntity {

	public long id;
	public String nome;

	@ManyToOne
	@JoinColumn(name = "id_impasto")
	public Impasto impasto;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "id_utente")
	public Utente utente;

	@ManyToMany(targetEntity = Ingrediente.class, fetch = FetchType.EAGER)
	@JoinTable(name = "pizza_ingrediente", joinColumns = { @JoinColumn(name = "pizza_id") }, inverseJoinColumns = {
			@JoinColumn(name = "id_ingrediente")})
	public List<Ingrediente> ingredienti = new ArrayList<>();
	
}