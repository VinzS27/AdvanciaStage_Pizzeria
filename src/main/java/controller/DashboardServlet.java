package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzeriaDAO;
import model.Pizza;
import model.Utente;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DashboardServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String azione = request.getParameter("azioni");

		if (azione != null) {
			switch (azione) {
			case "salvaPizza":
				saveNewPizza(request, response);
				break;
			case "updatePizza":
				updatePizza(request, response);
				break;
			case "deletePizza":
				deletePizza(request, response);
			default:
				break;
			}
		}
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	// Aggiungi una nuova pizza alla rubrica
	private void saveNewPizza(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utente utente = (Utente) request.getSession().getAttribute("utente");
		String nomePizza = request.getParameter("nomePizza");
		int impastoId = Integer.parseInt(request.getParameter("id_impasto"));
		String[] ingredientiId = request.getParameterValues("id_ingrediente");

		PizzeriaDAO pizzeriaDao = new PizzeriaDAO();

		pizzeriaDao.savePizza(nomePizza, utente, impastoId, ingredientiId);

		request.setAttribute("impasti", pizzeriaDao.findImpasto());
		request.setAttribute("ingredienti", PizzeriaDAO.findIngrediente());
	}

	// Cancella pizza esistente
	private void deletePizza(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PizzeriaDAO pizzeriaDao = new PizzeriaDAO();
		Utente utente = (Utente) request.getSession().getAttribute("utente");

		int id = Integer.parseInt(request.getParameter("id_pizza"));

		pizzeriaDao.deletePizza(id, utente);

		utente.setPizze(PizzeriaDAO.findPizzaByUser(utente.getId()));

		request.setAttribute("impasti", pizzeriaDao.findImpasto());
		request.setAttribute("ingredienti", PizzeriaDAO.findIngrediente());
	}

	// Aggiorna pizza esistente
	public void updatePizza(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PizzeriaDAO pizzeriaDao = new PizzeriaDAO();

		int id_pizza = Integer.parseInt(request.getParameter("id_pizza"));
		Pizza pizza = PizzeriaDAO.findPizzaById(id_pizza);

		request.setAttribute("updatePizza", pizza);
		request.setAttribute("impasti", pizzeriaDao.findImpasto());
		request.setAttribute("ingredienti", PizzeriaDAO.findIngrediente());

		request.getRequestDispatcher("update.jsp").forward(request, response);
	}
}