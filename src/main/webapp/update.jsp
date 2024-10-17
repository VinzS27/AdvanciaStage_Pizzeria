<?xml version = "1.0"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Modifica Pizza</title>
</head>
	<body>
	<% Pizza pizza = (Pizza) request.getAttribute("updatePizza"); %>
		<h1>
			Modifica <%= pizza.getNome() %> 
		</h1>
		
		<form action="UpdateServlet" method="post">
		<table border="1" cellspacing="10" cellpadding="10" style="border-color: yellow;">
			<tr>
				<td>
					<h2>Impasti</h2>
						<table>
							<tbody>
								<% List<Impasto> listaImpasto = (List<Impasto>) request.getAttribute("impasti");
									for (Impasto i : listaImpasto) { %>
								<tr>
									<td><input type="radio" name="id_impasto" value="<%=i.getId()%>" ></td>
									<td><%=i.getNome()%></td>
								</tr>
								<% } %>
							</tbody>
						</table>
				</td>
					<td>
						<h2>Ingredienti</h2>
						<table>
							<tbody>
								<% List<Ingrediente> listaIngrediente = (List<Ingrediente>) request.getAttribute("ingredienti");
									for (Ingrediente in : listaIngrediente) {
										for (Ingrediente ip : pizza.getIngredienti()) {
											if (in.getId() == ip.getId()) {
												break;
											}
										}%>
								<tr>
									<td><input type="checkbox" name="id_ingrediente" value="<%=in.getId()%>" ></td>
									<td><%=in.getNome()%></td>
								</tr>
								<% } %>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			<br> <label for="nomePizza">Nome pizza:</label> 
			<input type="text" id="nomePizza" name="nomePizza" value="<%=pizza.getNome()%>">
			<input type="hidden" name="id_pizza" value="<%=pizza.getId()%>">
			<input type="hidden" name="azioni" value="salvaPizza">
			<input type="submit" value="Update">
				<h2>Pizza da modificare:</h2>
				<table border="1">
					<tr>
						<th>Nome Pizza</th>
						<th>Impasto</th>
						<th>Ingredienti</th>
					</tr>
					<tbody>
						<tr>
							<td><%=pizza.getNome()%></td>
							<td><%=pizza.getImpasto().getNome()%></td>
							<td>
								<ul>
									<%for (Ingrediente in : pizza.getIngredienti()) {%>
									<li><%=in.getNome()%></li>
									<%}%>
								</ul>
							</td>
						</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>