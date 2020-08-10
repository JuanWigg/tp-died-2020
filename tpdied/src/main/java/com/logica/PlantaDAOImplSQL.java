package com.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import io.github.cdimascio.dotenv.Dotenv;
public class PlantaDAOImplSQL implements PlantaDAO{
	Dotenv dotenv = Dotenv.load();
	public void AltaPlanta(Planta p) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			Statement stmt = conn.createStatement();
			stmt.execute("INSERT INTO tpdied.planta VALUES ('" + p.getNombre() + "');");
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void BajaPlanta(Planta p) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			Statement stmt = conn.createStatement();
			stmt.execute("DELETE FROM tpdied.planta WHERE nombre='" + p.getNombre() + "';");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	public Optional<Planta> consultarPlanta(String nombrePlanta) {
		Connection conn = null;
		ResultSet res = null;
		Optional<Planta> p = Optional.empty();
		try{
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
		 PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.planta WHERE nombre='"+nombrePlanta+"';");
		 res=pstm.executeQuery();
		 
		 if (res.next() == false) {
			 Optional.ofNullable(null);
			}
		 else {
			 p=Optional.of(new Planta(res.getString(1)));
			 return p;
			 }
		pstm.close();
		conn.close();
		} catch(ClassNotFoundException e) {
			
		} catch(SQLException e) {
			
		}
		
		
		return p;	
		}
	
	public String[] buscarNombresPlanta() {
		ArrayList<String> plantasList = new ArrayList<String>();
		String[] plantas;
		Connection conn = null;
		ResultSet res = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement pstm = conn.prepareStatement("SELECT nombre FROM tpdied.planta;");
			res = pstm.executeQuery();
			if(!res.next()) {
				return null;
			}
			else {
				
				do {
					
					plantasList.add(res.getString(1));
					
				} while (res.next());
			}
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		plantas = new  String[plantasList.size()+1];
		plantas[0] = "<Ninguna>";
		for(int i=1; i<plantas.length; i++)
			plantas[i] = plantasList.get(i-1);
		
		return plantas;
	}
	
	
	
	public ArrayList<Planta> consultarPlantas(){
		ArrayList<Planta> plantasList = new ArrayList<Planta>();
		Connection conn = null;
		ResultSet res = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement pstm = conn.prepareStatement("SELECT nombre FROM tpdied.planta;");
			res = pstm.executeQuery();
			if(!res.next()) {
				return null;
			}
			else {
				
				do {
					
					plantasList.add(new Planta(res.getString(1)));
					
				} while (res.next());
			}
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return plantasList;
		
	}
	

}
