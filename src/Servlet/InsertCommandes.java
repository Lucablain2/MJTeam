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



public class InsertCommandes {
	public static void main(String[] args) {


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
		for (int i = 1; i <= 1000; i++) {
			try {
				String insert = "INSERT INTO public.commande values("+i+",'Article_"+i+"','Demande')";
				pst = conn.prepareStatement(insert);
				rs = pst.executeQuery();
			} catch (SQLException e) {
			}
		}
	}
}
