/**
 * 
 */
package com.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author juanwigg
 *
 */
public class ModeloDAOImplSQL implements ModeloDAO {
	public Optional<Modelo> consultarModelo(List<Predicate<Modelo>> criterios){
		// @TODO
		return null;
	}
	public void altaModelo(Modelo m) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");
			Statement stmt = conn.createStatement();
			stmt.execute("INSERT INTO tpdied.modelo(nombre, marca) VALUES ('" + m.getNombre() + "', '" + m.getMarca().getNombre() + "');");
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void bajaModelo(Modelo m) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");
			Statement stmt = conn.createStatement();
			stmt.execute("DELETE FROM tpdied.modelo WHERE nombre=" + m.getNombre() + " AND marca=" + m.getMarca().getNombre() + ";");
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
