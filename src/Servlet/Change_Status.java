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

public class Change_Status extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static int demande = 0;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String url = "jdbc:postgresql://localhost/";

		String  user = "postgres";
		String password = "root";

		String updateFinie = "UPDATE public.commande SET etat='finie' WHERE id_article =1000";
		String updateHistorise = "UPDATE public.commande SET etat='historise' WHERE id_article =1000";

		String selectDemande = "SELECT * FROM public.commande WHERE etat='Demande'";
		String selectEnCours = "SELECT * FROM public.commande WHERE etat='encours'";
		String selectFinie = "SELECT * FROM public.commande WHERE etat='finie'";
		String selectHistorise = "SELECT * FROM public.commande WHERE etat='historise'";

		try {
			Class.forName("org.postgresql.Driver");		
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		url= "jdbc:postgresql://localhost:5432/Commande";
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		List<String> id = new ArrayList();
		List<String> nom = new ArrayList();
		List<String> etat = new ArrayList();
		
		List<String> idEC = new ArrayList();
		List<String> nomEC = new ArrayList();
		List<String> etatEC = new ArrayList();
		
		
		for (int i = demande; i < demande + 5; i++ )
		{
			try {
				String updateEnCours = "UPDATE public.commande SET etat='encours' WHERE etat='Demande' AND id_article="+i;
				pst = conn.prepareStatement(updateEnCours);
				rs = pst.executeQuery();
			} catch (SQLException e) {
				
			}			
		}
		
		demande = demande + 5;
		try {
			//String queryInsert ="UPDATE public.commande SET etat='{Demande}' WHERE etat='encours'";
			pst = conn.prepareStatement(selectDemande);
			rs = pst.executeQuery();
			while (rs.next()) {
				id.add(rs.getString(1));
				nom.add(rs.getString(2));
				etat.add(rs.getString(3));
			}
			request.getSession().setAttribute("id", id);
			request.getSession().setAttribute("nom", nom);
			request.getSession().setAttribute("etat", etat);
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
