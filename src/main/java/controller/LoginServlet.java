package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PizzeriaDAO;
import model.Impasto;
import model.Ingrediente;
import model.Utente;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		PizzeriaDAO pizzeriaDAO = new PizzeriaDAO();
		List<Impasto> impasti = new ArrayList<Impasto>();
		List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();

		Utente utente = pizzeriaDAO.authenticate(username, password);
		if (utente != null) {
			impasti = pizzeriaDAO.findImpasto();
			ingredienti = pizzeriaDAO.findIngrediente();
			
			request.getSession().setAttribute("utente", utente);
			request.setAttribute("impasti", impasti);
			request.setAttribute("ingredienti", ingredienti);
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "CREDENZIALI ERRATE");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
}
