package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import dto.*;
import model.*;
import util.JpaUtil;

public class PizzeriaRESTDAO {
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	/// ********* GET ***********
	public static UtenteDTO getUtenteById(int id) {
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<UtenteDTO> query = em.createQuery(
				"select new dto.UtenteDTO(u.id, u.username, u.password) from Utente u where u.id = :id",
				UtenteDTO.class);
		query.setParameter("id", id);
		List<UtenteDTO> list = query.getResultList();
		em.close();
		
		return list.isEmpty() ? null : list.get(0);
	}

	public static List<UtenteDTO> getAllUtente() {
		EntityManager em = emf.createEntityManager();

		TypedQuery<UtenteDTO> query = em.createQuery(
				"select new dto.UtenteDTO(u.id, u.username, u.password) from Utente u",
				UtenteDTO.class);
		List<UtenteDTO> list = query.getResultList();
		em.close();

		return list.isEmpty() ? null : list;
	}

	public static PizzaDTO getPizzaById(int id) {
		EntityManager em = emf.createEntityManager();

	    TypedQuery<Pizza> query = em.createQuery( "select p from Pizza p where p.id = :id", Pizza.class);
	    query.setParameter("id", id);
	    List<Pizza> list = query.getResultList();
	    em.close();

	    Pizza pizza = list.get(0);

	    PizzaDTO pizzaDTO = new PizzaDTO(pizza.getId(), pizza.getNome(),
	    	new ImpastoDTO(pizza.getImpasto().getId(), pizza.getImpasto().getNome()),
	    	pizza.getIngredienti(),
	        new UtenteDTO(pizza.getUtente().getId(), pizza.getUtente().getUsername(), pizza.getUtente().getPassword()));

		return pizzaDTO!=null ? pizzaDTO:null;
	}

	public static List<PizzaDTO> getAllPizza() {
		EntityManager em = emf.createEntityManager();

	    TypedQuery<Pizza> query = em.createQuery( "select p from Pizza p", Pizza.class);
	    List<Pizza> list = query.getResultList();
	    em.close();
	    
	    List<PizzaDTO> listaPizza = new ArrayList<PizzaDTO>();

	    for(Pizza p : list) {
			listaPizza.add(new PizzaDTO(p.getId(), p.getNome(), 
					new ImpastoDTO(p.getImpasto().getId(), p.getImpasto().getNome()),
					p.getIngredienti(),
					new UtenteDTO(p.getUtente().getId(), p.getUtente().getUsername(), p.getUtente().getPassword())));
		}

		return listaPizza.isEmpty() ? null : listaPizza;
	}

	public static IngredienteDTO getIngredienteById(int id) {
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<IngredienteDTO> query = em.createQuery(
				"select new dto.IngredienteDTO(i.id, i.nome) from Ingrediente i where i.id = :id", IngredienteDTO.class);
		query.setParameter("id", id);
		List<IngredienteDTO> list = query.getResultList();
		em.close();

		return list.isEmpty() ? null : list.get(0);
	}

	public static List<IngredienteDTO> getAllIngrediente() {
		EntityManager em = emf.createEntityManager();

		TypedQuery<IngredienteDTO> query = em.createQuery(
				"select new dto.IngredienteDTO(i.id, i.nome) from Ingrediente i", IngredienteDTO.class);
		List<IngredienteDTO> list = query.getResultList();
		em.close();

		return list.isEmpty() ? null : list;
	}

	public static ImpastoDTO getImpastoById(int id) {
		EntityManager em = emf.createEntityManager();
		
		TypedQuery<ImpastoDTO> query = em.createQuery(
				"select new dto.ImpastoDTO(i.id, i.nome) from Impasto i where i.id = :id", ImpastoDTO.class);
		query.setParameter("id", id);
		List<ImpastoDTO> list = query.getResultList();
		em.close();

		return list.isEmpty() ? null : list.get(0);
	}

	public static List<ImpastoDTO> getAllImpasto() {
		EntityManager em = emf.createEntityManager();

		TypedQuery<ImpastoDTO> query = em.createQuery(
				"select new dto.ImpastoDTO(i.id, i.nome) from Impasto i", ImpastoDTO.class);
		List<ImpastoDTO> list = query.getResultList();
		em.close();

		return list.isEmpty() ? null : list;
	}
	
	// ********** POST **************
	public static Utente createUtente(String username, String password) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Utente utente = new Utente(username,password);
		em.persist(utente);
		em.getTransaction().commit();
		em.close();

		return (utente != null) ? utente : null;
	}
	
	public static Ingrediente createIngrediente(String nome) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Ingrediente ingrediente = new Ingrediente(nome);
		em.persist(ingrediente);
		em.getTransaction().commit();
		em.close();

		return (ingrediente != null) ? ingrediente : null;
	}
	
	public static Impasto createImpasto(String nome) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Impasto impasto = new Impasto(nome);
		em.persist(impasto);
		em.getTransaction().commit();
		em.close();

		return (impasto != null) ? impasto : null;
	}

	// ********** PUT ***************
	public static Utente updateUtente(String id,String username, String password) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Utente utente = em.find(Utente.class, Integer.parseInt(id));
		utente.setUsername(username);
		utente.setPassword(password);
		em.getTransaction().commit();
		em.close();

		return (utente != null) ? utente : null;
	}
	
	public static Ingrediente updateIngrediente(String id,String nome) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Ingrediente ingrediente = em.find(Ingrediente.class, Integer.parseInt(id));
		ingrediente.setNome(nome);
		em.getTransaction().commit();
		em.close();

		return (ingrediente != null) ? ingrediente : null;
	}
	
	public static Impasto updateImpasto(String id,String nome) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Impasto impasto = em.find(Impasto.class, Integer.parseInt(id));
		impasto.setNome(nome);
		em.getTransaction().commit();
		em.close();

		return (impasto != null) ? impasto : null;
	}
	
	// ********** DELETE ***********
	public static String deletePizza(int id) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Pizza pizza = em.find(Pizza.class, id);

		if (pizza != null) {
			em.remove(pizza);
			em.getTransaction().commit();
			em.close();
			return "pizza " + id + " " + pizza.getNome() + " eliminata";
		} else {
			em.close();
			return "pizza " + id + " non eliminata";
		}
	}

	public static String deleteUtente(int id) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Utente utente = em.find(Utente.class, id);

		if (utente != null) {
			em.remove(utente);
			em.getTransaction().commit();
			em.close();
			return "utente " + id + " eliminato";
		} else {
			em.close();
			return "utente " + id + " non eliminato";
		}
	}
	
	public static String deleteIngrediente(int id) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Ingrediente ingrediente = em.find(Ingrediente.class, id);

		if (ingrediente != null) {
			em.remove(ingrediente);
			em.getTransaction().commit();
			em.close();
			return "ingrediente " + id + " eliminato";
		} else {
			em.close();
			return "ingrediente " + id + " non eliminato";
		}
	}
	
	public static String deleteImpasto(int id) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		Impasto impasto = em.find(Impasto.class, id);

		if (impasto != null) {
			em.remove(impasto);
			em.getTransaction().commit();
			em.close();
			return "impasto " + id + " eliminato";
		} else {
			em.close();
			return "impasto " + id + " non eliminato";
		}
	}
}
