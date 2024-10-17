<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
	
	<head>
    <title>Rubrica delle Pizze Preferite</title>
	</head>
	<body>
		<%  Utente utente = (Utente) request.getSession().getAttribute("utente"); 
			List<Impasto> listaImpasto = (List<Impasto>)request.getAttribute("impasti");
		    List<Ingrediente> listaIngrediente = (List<Ingrediente>)request.getAttribute("ingredienti");
		%>
    	
    	<h1>Rubrica pizze preferite di <%=utente.getUsername() %></h1>
    	
    	<form action="DashboardServlet" method="post">
	        <table border="1" cellspacing="10" cellpadding="10" style="border-color: yellow;">
		        <tr>
		            <td>
		                <h3>Seleziona un impasto</h3>
		                <% for (Impasto i : listaImpasto) { %>
		                    <p>
		                        <label>
		                            <input type="radio" name="id_impasto" value="<%= i.getId() %>"> <%= i.getNome() %>
		                        </label>
		                    </p>
		                <% } %>
		            </td>
		            <td>
		                <h3>Seleziona gli ingredienti</h3>
		                <% for (Ingrediente i : listaIngrediente) { %>
		                    <p>
		                        <label>
		                            <input type="checkbox" name="id_ingrediente" value="<%= i.getId() %>"> <%= i.getNome() %>
		                        </label>
		                    </p>
		                <% } %>
		            </td>
		        </tr>
		    </table>
	         <p>
	         	<label for="nomePizza">Nome pizza </label>
	         	<input type="text" id="nomePizza" name="nomePizza" required>
				<input type="hidden" name="azioni" value="salvaPizza"> 
				<input type="submit" value="Salva Pizza">
	        </p>
    	</form>

    	<h2>Le tue Pizze Preferite</h2>
	    <table border="1">
	        <tr>
	            <th>Nome</th>
	            <th>Impasto</th>
	            <th>Ingredienti</th>
	            <th></th>
	            <th></th>
	        </tr>	
        	<tbody>
			<%	List<Pizza> listaPizza = utente.getPizze();
		    	Set<Integer> set = new HashSet<>();  // Set per evitare duplicati
		    	for (Pizza p : listaPizza) { 
		        	if (set.add(p.getId())) {  %>
					<tr>
						<td><%=p.getNome()%></td>
						<td> <% Impasto ip = p.getImpasto();
								if (ip != null) { %>
								<%=ip.getNome()%>
 							<% } %>
						</td>
						<td>
							<ul>
								<% for (Ingrediente i : p.getIngredienti()) { %>
								<li><%=i.getNome()%></li>
								<% } %>
							</ul>
						</td>
						<td>
							<form action="DashboardServlet" method="post">
								<input type="hidden" name="azioni" value="updatePizza">
								<input type="hidden" name="id_pizza" value="<%=p.getId()%>">
								<input type="submit" value="Update">
							</form>
						</td>
						<td>
							<form action="DashboardServlet" method="post">
								<input type="hidden" name="azioni" value="deletePizza">
								<input type="hidden" name="id_pizza" value="<%=p.getId()%>">
								<input type="submit" value="Delete">
							</form>
						</td>
					</tr>
					<% }} %>
				</tbody>
    	</table>
	</body>
</html>