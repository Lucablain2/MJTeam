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

public class ChangeHistorise extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static int historise = 0;
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String url = "jdbc:postgresql://localhost/";

		String  user = "postgres";
		String password = "root";

		String selectHistorise = "SELECT * FROM public.commande WHERE etat='historise'";
		String selectFini = "SELECT * FROM public.commande WHERE etat='fini'";

		try {
			Class.forName("org.postgresql.Driver");		
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
		}
		url= "jdbc:postgresql://localhost:5432/Commande";
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}


		List<String> idH = new ArrayList<String>();
		List<String> nomH = new ArrayList<String>();
		List<String> etatH = new ArrayList<String>();
		
		
		List<String> idF = new ArrayList<String>();
		List<String> nomF = new ArrayList<String>();
		List<String> etatF = new ArrayList<String>();
		
		
		for (int i = historise; i < historise + 5; i++ )
		{
			try {
				String updateHistorise = "UPDATE public.commande SET etat='historise' WHERE etat='fini' AND id_article="+i;
				pst = conn.prepareStatement(updateHistorise);
				rs = pst.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
		historise = historise + 5;
		try {
			//String queryInsert ="UPDATE public.commande SET etat='{Demande}' WHERE etat='encours'";
			pst = conn.prepareStatement(selectHistorise);
			rs = pst.executeQuery();
			while (rs.next()) {
				idH.add(rs.getString(1));
				nomH.add(rs.getString(2));
				etatH.add(rs.getString(3));
			}
			request.getSession().setAttribute("idH", idH);
			request.getSession().setAttribute("nomH", nomH);
			request.getSession().setAttribute("etatH", etatH);
		} catch (SQLException e) {
			System.out.println("Probleme SelectDemande : " + e.getMessage());
		}
		
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
		response.sendRedirect("PagePrincipale");

	}
}
