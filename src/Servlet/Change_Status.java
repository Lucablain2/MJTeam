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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Change_Status extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static int demande = 0;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String url = "jdbc:postgresql://localhost/";

		String  user = "postgres";
		String password = "root";


		try {
			Class.forName("org.postgresql.Driver");		
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		url= "jdbc:postgresql://localhost:5432/Commande";
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String magasin = request.getParameter("magasin");
		String produit = request.getParameter("produit");
		String acheteur = request.getParameter("acheteur");
		String insertData = "INSERT INTO public.commande VALUES ('"+magasin+"','"+produit+"','"+acheteur+"')";
		try {
			pst = conn.prepareStatement(insertData);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("magasin",magasin);
		session.setAttribute("produit", produit);
		session.setAttribute("acheteur", acheteur);
		
		this.getServletContext().getRequestDispatcher("/achat.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/achat.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
