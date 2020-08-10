/**
 * 
 */
package com.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author Pichi
 *
 */
public class RutaDAOImplSQL implements RutaDAO{
	Dotenv dotenv = Dotenv.load();
	@Override
	public int altaRuta(Ruta ruta) {
		// TODO Auto-generated method stub
		Connection conn = null;
		int id_ruta = 0;
		try {
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO tpdied.ruta (duracion_total, distancia_total, menor_peso_max, menor_peso_max_unidad) "
					+ "VALUES (?, ?, ?, ?::tpdied.unidad);" , Statement.RETURN_GENERATED_KEYS);
			
			stmt.setDouble(1,ruta.getDuracionTotal());
			stmt.setDouble(2, ruta.getDistanciaTotal());
			stmt.setDouble(3, ruta.getMenorPesoMax());
			stmt.setString(4, "kg");
			stmt.execute();
			
			ResultSet id_ruta_key = stmt.getGeneratedKeys();
			if (id_ruta_key.next()) {
		         id_ruta = id_ruta_key.getInt(1);
		     }

			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		TramoDAOImplSQL TramoSQL = new TramoDAOImplSQL();
		ArrayList<Tramo> tramos = (ArrayList<Tramo>) ruta.getListaTramos();
		for(int i=0; i<tramos.size(); i++) {
			TramoSQL.altaTramoDeRuta(id_ruta, tramos.get(i).getIdTramo(), i+1);
			
		}
		
		return id_ruta;
	}

}
