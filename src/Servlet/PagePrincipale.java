package Servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/PagePrincipale")
public class PagePrincipale extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		//Listes a afficher pour bloc 1 
		List<String> param1 = (ArrayList<String>) request.getSession().getAttribute("id");
		List<String> param2 = (ArrayList<String>) request.getSession().getAttribute("nom");
		List<String> param3 = (ArrayList<String>) request.getSession().getAttribute("etat");

		//Listes a afficher pour bloc 2 
		List<String> paramEC1 = (ArrayList<String>) request.getSession().getAttribute("idEC");
		List<String> paramEC2 = (ArrayList<String>) request.getSession().getAttribute("nomEC");
		List<String> paramEC3 = (ArrayList<String>) request.getSession().getAttribute("etatEC");

		//Listes a afficher pour bloc 3 
		List<String> paramF1 = (ArrayList<String>) request.getSession().getAttribute("idF");
		List<String> paramF2 = (ArrayList<String>) request.getSession().getAttribute("nomF");
		List<String> paramF3 = (ArrayList<String>) request.getSession().getAttribute("etatF");

		//Listes a afficher pour bloc 4
		List<String> paramH1 = (ArrayList<String>) request.getSession().getAttribute("idH");
		List<String> paramH2 = (ArrayList<String>) request.getSession().getAttribute("nomH");
		List<String> paramH3 = (ArrayList<String>) request.getSession().getAttribute("etatH");

		PrintWriter out = response.getWriter();


		// Bloc numero 1 : DEMANDE
		out.write("<html><body><div id='serlvetResponse' style='text-align: center;'>");
		out.print("<body>\r\n" + 
				"    <div id=\"bloc1\" class=\"ligne1\" style=\"border:1px solid black;width:49%;float:left;height:300px;\">\r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"    <form action=\"Change_Status\" method=\"post\">\r\n" + 
				"    <input type=\"submit\" value=\"Start\"/>\r\n" + 
				"	</form>\r\n" + 
				"\r\n" + 
				"<table>");
		out.print("<tr><td>Id_Article</td>");
		out.print("<td>Nom</td>");
		out.print("<td>Etat</td></tr>");
		for (int i = 1; i < 20 && i < param1.size(); i++ ) {
			out.print("<tr><td>"+ param1.get(i)+ "</td>");
			out.print("<td>"+ param2.get(i)+ "</td>");
			out.print("<td>"+ param3.get(i)+ "</td></tr>");
		}

		
		
		//Bloc numéro 2 : EN COURS
		out.print("</table></div>\r\n"); 
		out.print("<table></table></div>\r\n" + 
				"    <div id=\"bloc2\" class=\"ligne1\" style=\"border:1px solid red;width:49%;float:right;height:300px;\">\r\n" + 
				"    \r\n" + 
				"    	<form action=\"ChangeFini\" method=\"post\">\r\n" + 
				"    		<input type=\"submit\" value=\"Changer Etat\"/>\r\n" + 
				"		</form>\r\n" + 
				"    	<table>");


		out.print("<tr><td>Id_Article</td>");
		out.print("<td>Nom</td>");
		out.print("<td>Etat</td></tr>");
		for (int i = 0; i < 20 && i < paramEC1.size(); i++ ) {
			out.print("<tr><td>"+ paramEC1.get(i)+ "</td>");
			out.print("<td>"+ paramEC2.get(i)+ "</td>");
			out.print("<td>"+ paramEC3.get(i)+ "</td></tr>");
		}
		out.print("</table></div>\r\n");

		

		// Bloc numero 3 : FINI
		out.print("<div id=\"bloc3\" class=\"ligne2\" style=\"border:1px solid green;width:49%;float:left;height:300px;\">\r\n" + 
				"    	<form action=\"ChangeHistorise\" method=\"post\">\r\n" + 
				"    		<input type=\"submit\" value=\"Changer Etat\"/>\r\n" + 
				"		</form><table>");


		out.print("<tr><td>Id_Article</td>");
		out.print("<td>Nom</td>");
		out.print("<td>Etat</td></tr>");
		if (paramF1 != null) {
			for (int i = 0; i < 20 && i < paramF1.size(); i++ ) {
				out.print("<tr><td>"+ paramF1.get(i)+ "</td>");
				out.print("<td>"+ paramF2.get(i)+ "</td>");
				out.print("<td>"+ paramF3.get(i)+ "</td></tr>");
			}
		}
		out.print("</table></div>\r\n");

		
		
		// Bloc numero 4 : Historise
		out.print("<div id=\"bloc4\" class=\"ligne2\" style=\"border:1px solid yellow;width:49%;float:right;height:300px;\"> <table>\r\n");

		out.print("<tr><td>Id_Article</td>");
		out.print("<td>Nom</td>");
		out.print("<td>Etat</td></tr>");
		if (paramH1 != null) {
			for (int i = 0; i < 20 && i < paramH1.size(); i++ ) {
				out.print("<tr><td>"+ paramH1.get(i)+ "</td>");
				out.print("<td>"+ paramH2.get(i)+ "</td>");
				out.print("<td>"+ paramH3.get(i)+ "</td></tr>");
			}
			out.print("</table></div>\r\n");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
