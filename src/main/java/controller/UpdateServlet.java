package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzeriaDAO;
import model.Impasto;
import model.Ingrediente;
import model.Pizza;
import model.Utente;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		String nomePizza = request.getParameter("nomePizza");
		int impasto_id =  Integer.parseInt(request.getParameter("id_impasto"));
		String[] ingredienti_id = request.getParameterValues("id_ingrediente");
		int idPizza = Integer.parseInt(request.getParameter("id_pizza"));
		
		PizzeriaDAO dao = new PizzeriaDAO();		
		Impasto impasto = dao.findImpastoById(impasto_id);
		
		int[] IdIngredienti = null;
		if (ingredienti_id != null) {
			IdIngredienti = new int[ingredienti_id.length];
			for (int i=0; i < ingredienti_id.length; i++) {
				IdIngredienti[i] = Integer.parseInt(ingredienti_id[i]);
			}
		}
		List<Ingrediente> ingredientiList = dao.findIngredienteById(IdIngredienti);

		Pizza pizza = new Pizza();
		pizza.setId(idPizza);
		pizza.setNome(nomePizza);
		pizza.setImpasto(impasto);
		pizza.setIngredienti(ingredientiList);
		pizza.setUtente(utente);

		dao.updatePizza(pizza);

		utente.setPizze(PizzeriaDAO.findPizzaByUser(utente.getId()));

		request.setAttribute("impasti", dao.findImpasto());
		request.setAttribute("ingredienti", PizzeriaDAO.findIngrediente());

		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}
}
