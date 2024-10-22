package org.advancia.entity;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Utente extends PanacheEntity {

	public long id;
	public String username;
	public String password;

	@OneToMany(mappedBy = "utente", fetch = FetchType.EAGER)
	public List<Pizza> pizza = new ArrayList<>();

	public static List<Utente> findUtenteById(int Id) {
		return find("id", Id).list();
	}

	public static List<Utente> findUtenteByUsername(String username) {
		// return find("from Utente e WHERE (0 < LOCATE(?1,e.username)) ",
		// username).list();
		return find("username like CONCAT('%',?1, '%') ", username).list();
	}

}
