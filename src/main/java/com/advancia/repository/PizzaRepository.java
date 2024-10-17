package com.advancia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.advancia.model.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
	@Query("SELECT p FROM Pizza p WHERE p.utente = (select u from Utente u where u.id = ?1)")
	List<Pizza> findAllByUserID(Integer id);
}
