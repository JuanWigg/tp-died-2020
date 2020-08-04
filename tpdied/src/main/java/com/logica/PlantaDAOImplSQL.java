package com.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class PlantaDAOImplSQL implements PlantaDAO{

	public void AltaPlanta(Planta p) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");
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
			conn = DriverManager.getConnection("jdbc:postgresql://tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");
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
		 conn = DriverManager.getConnection("jdbc:postgresql://tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");
		 PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.planta WHERE nombre='"+nombrePlanta+"';");
		 res=pstm.executeQuery();
		 pstm.close();
		 conn.close();
		 if (res.next() == false) {
			 return null;
			}
		 else {
			 p=Optional.of(new Planta(res.getString(1)));
			 return p;
			 }
		 
		} catch(ClassNotFoundException e) {
			
		} catch(SQLException e) {
			
		}
		return p;	
		}

	

}
