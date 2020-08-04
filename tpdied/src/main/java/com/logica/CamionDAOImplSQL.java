/**
 * 
 */
package com.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author juanwigg
 *
 */
public class CamionDAOImplSQL implements CamionDAO{


	public List<Camion> consultarCamion(Camion c) {
		int cont = 0;
		Connection conn = null;
		if(c.getFechaCompra()!=null) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
		LocalDate fecha = LocalDate.parse(c.getFechaCompra().toString(), formatter);
		}
		
		try{
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");

		
		
		
		
		} catch(ClassNotFoundException e) {
			
		} catch(SQLException e) {
			
		}
		return null;
	}

	public void altaCamion(Camion camion) {
		Connection conn = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
		LocalDate fecha = LocalDate.parse(camion.getFechaCompra().toString(), formatter);
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");
			Statement stmt = conn.createStatement();
			stmt.execute("INSERT INTO tpdied.camion VALUES ('" + camion.getPatente() +
					"', " + camion.getKilometrosRecorridos() + ", " + camion.getCostoPorKilometro() + ", " + 
					camion.getCostoPorHora() + ", '" + fecha + "', '" + camion.getModelo().getNombre() + "', '" + 
					camion.getModelo().getMarca().getNombre() + "');"
					);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	public void bajaCamion(Camion camion) {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");
			Statement stmt = conn.createStatement();
			stmt.execute("DELETE FROM tpdied.camion WHERE patente='" + camion.getPatente() + "';");
			stmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
