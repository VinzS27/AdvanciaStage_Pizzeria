package org.advancia.entity;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Impasto extends PanacheEntity{
	public long id;
	public String nome;
	
	public static List<Impasto> findImpastoById(int Id) {
        return find("id", Id).list();
    }

    public static List<Impasto> findImpastoByName(String name) {
        // return find("from Impasto e WHERE (0 < LOCATE(?1,e.nome)) ", nome).list();
        return find("nome like CONCAT('%',?1, '%') ", name).list();
    }

}
