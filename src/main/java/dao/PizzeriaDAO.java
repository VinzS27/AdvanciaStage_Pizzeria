package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.Utente;
import util.JpaUtil;

public class PizzeriaDAO {

	private static final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public Utente authenticate(String username, String password) {
		List<Utente> listaResult = new ArrayList<Utente>();
		EntityManager em = emf.createEntityManager();

		TypedQuery<Utente> query = em.createQuery(
				"SELECT u FROM Utente u WHERE u.username = :username AND u.password=:password", Utente.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		listaResult = query.getResultList();
		em.close();

		return listaResult.isEmpty() ? null : listaResult.get(0);
	}

	public List<Impasto> findImpasto() {
		List<Impasto> listaResult = new ArrayList<Impasto>();
		EntityManager em = emf.createEntityManager();

		TypedQuery<Impasto> query = em.createQuery("SELECT i from Impasto i", Impasto.class);
		listaResult = query.getResultList();
		em.close();

		return listaResult.isEmpty() ? null : listaResult;
	}

	public Impasto findImpastoById(int idImpasto) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Impasto impasto = em.find(Impasto.class, idImpasto);
		em.getTransaction().commit();
		em.close();

		return (impasto != null) ? impasto : null;
	}

	public List<Ingrediente> findIngrediente() {
		List<Ingrediente> listaResult = new ArrayList<Ingrediente>();
		EntityManager em = emf.createEntityManager();

		TypedQuery<Ingrediente> query = em.createQuery("SELECT i from Ingrediente i", Ingrediente.class);
		listaResult = query.getResultList();
		em.close();

		return listaResult.isEmpty() ? null : listaResult;
	}

	public List<Ingrediente> findIngredienteById(int[] ingredienti) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		List<Ingrediente> listaResult = new ArrayList<>();
		for (int id : ingredienti) {
			Ingrediente ingrediente = em.find(Ingrediente.class, id);
			if (ingrediente != null) {
				listaResult.add(ingrediente);
			}
		}
		em.getTransaction().commit();
		em.close();

		return listaResult.isEmpty() ? null : listaResult;

	}

	// Salva la pizza creata dall'utente
	public void savePizza(Pizza pizza, Utente utente) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(pizza);

		utente.getPizze().add(pizza);
		em.flush(); // force the data to be persisted in the database immediately
		em.getTransaction().commit();
		em.close();
	}

	// Cancella una pizza in rubrica
	public void deletePizza(int id, Utente utente) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Pizza pizza = em.find(Pizza.class, id);
		em.remove(pizza);
		utente.getPizze().remove(pizza);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}

	// Aggiorna una pizza esistente
	public void updatePizza(Pizza pizza) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.merge(pizza);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}

	public List<Pizza> findPizzaByUser(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Utente utente = em.find(Utente.class, id);

		if (utente.equals(null))
			return null;

		List<Pizza> listaResult = utente.getPizze();
		em.getTransaction().commit();
		em.close();

		return listaResult.isEmpty() ? null : listaResult;
	}

	public Pizza findPizzaById(int id_pizza) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Pizza pizza = em.find(Pizza.class, id_pizza);
		em.getTransaction().commit();
		em.close();
		return (pizza != null) ? pizza : null;
	}

	public void savePizza(String nomePizza, Utente utente, int impastoId, String[] ingredientiId) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Impasto impasto = em.find(Impasto.class, impastoId);
		List<Ingrediente> ingredientiList = new ArrayList();

		for (String idString : ingredientiId) {
			Ingrediente ingrediente = em.find(Ingrediente.class, Integer.valueOf(idString));
			ingredientiList.add(ingrediente);
		}

		Pizza pizza = new Pizza();
		pizza.setNome(nomePizza);
		pizza.setImpasto(impasto);
		pizza.setIngredienti(ingredientiList);
		pizza.setUtente(utente);

		em.persist(pizza);

		utente.getPizze().add(pizza);
		em.flush(); // force the data to be persisted in the database immediately
		em.getTransaction().commit();
		em.close();
	}
}
