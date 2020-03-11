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

public class ChangeFini extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static int fini = 0;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String url = "jdbc:postgresql://localhost/";

		String  user = "postgres";
		String password = "root";

		String selectFini = "SELECT * FROM public.commande WHERE etat='fini'";
		String selectEnCours = "SELECT * FROM public.commande WHERE etat='encours'";

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


		List<String> idF = new ArrayList<String>();
		List<String> nomF = new ArrayList<String>();
		List<String> etatF = new ArrayList<String>();
		
		List<String> idEC = new ArrayList();
		List<String> nomEC = new ArrayList();
		List<String> etatEC = new ArrayList();
		
		for (int i = fini; i < fini + 5; i++ )
		{
			try {
				String updateEnCours = "UPDATE public.commande SET etat='fini' WHERE etat='encours' AND id_article="+i;
				pst = conn.prepareStatement(updateEnCours);
				rs = pst.executeQuery();
			} catch (SQLException e) {
			}			
		}
		
		fini = fini + 5;
		
		try {
			pst = conn.prepareStatement(selectFini);
			rs = pst.executeQuery();
			while (rs.next()) {
				idF.add(rs.getString(1));
				nomF.add(rs.getString(2));
				etatF.add(rs.getString(3));
			}
			request.getSession().setAttribute("idF", idF);
			request.getSession().setAttribute("nomF", nomF);
			request.getSession().setAttribute("etatF", etatF);
		} catch (SQLException e) {
			System.out.println("Probleme SelectDemande : " + e.getMessage());
		}
		
		try {
			//String queryInsert ="UPDATE public.commande SET etat='{Demande}' WHERE etat='encours'";
			pst = conn.prepareStatement(selectEnCours);
			rs = pst.executeQuery();
			while (rs.next()) {
				idEC.add(rs.getString(1));
				nomEC.add(rs.getString(2));
				etatEC.add(rs.getString(3));
			}
			request.getSession().setAttribute("idEC", idEC);
			request.getSession().setAttribute("nomEC", nomEC);
			request.getSession().setAttribute("etatEC", etatEC);
		} catch (SQLException e) {
			System.out.println("Probleme SelectDemande : " + e.getMessage());
		}
		response.sendRedirect("PagePrincipale");

	}
}
