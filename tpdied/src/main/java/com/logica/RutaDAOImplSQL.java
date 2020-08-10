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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public Ruta armarRutar(int id_ruta) {
		Connection conn = null;
		Ruta ruta = null;
		try {
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement stmt = conn.prepareStatement("SELECT * from tpdied.ruta WHERE id=?");
			stmt.setInt(1, id_ruta);
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				ruta = new Ruta();
				ruta.setId(id_ruta);
				ruta.setDistanciaTotal(res.getInt("distancia_total"));
				ruta.setDuracionTotal(res.getDouble("duracion_total"));
				ruta.setMenorPesoMax(res.getDouble("menor_peso_max"));
				
			}
			
			
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Integer> idsTramos = (new TramoDAOImplSQL()).consultarIdTramosConRuta(id_ruta);
		ArrayList<Tramo> tramos = new ArrayList<Tramo>();
		ArrayList<Planta> plantasLista = new ArrayList<Planta>();
		for(Integer i: idsTramos) {
			tramos.add((new TramoDAOImplSQL()).ConsultarTramo(i).get());
		}
		for(Tramo t : tramos) {
			plantasLista.add(t.getPlantaOrigen());
			plantasLista.add(t.getPlantaDestino());
		}
		Set<Planta> plantasSet = new HashSet<Planta>(plantasLista);
		
		ruta.setListaTramos(tramos);
		ruta.setListaPlantasRuta(new ArrayList<Planta>(plantasSet));
		return ruta;
	}
}
