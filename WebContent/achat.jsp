<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Commande</title>
</head>
<body>
<h1>Votre commande :</h1>
<% String magasin = (String) session.getAttribute("magasin"); %> 
<% String produit = (String) session.getAttribute("produit"); %> 
<% String acheteur = (String) session.getAttribute("acheteur"); %> 
<h2>Bonjour Monsieur <%=acheteur %>, vous avez acheter sur le magasin <%=magasin %>, le produit : <%=produit %></h2>
</body>
</html>