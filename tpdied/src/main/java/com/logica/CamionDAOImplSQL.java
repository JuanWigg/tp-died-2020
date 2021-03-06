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
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import io.github.cdimascio.dotenv.Dotenv;
/**
 * @author juanwigg
 *
 */
public class CamionDAOImplSQL implements CamionDAO{
	Dotenv dotenv = Dotenv.load();

	public List<Camion> buscarCamiones() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Connection conn = null;		
		ArrayList<Camion> camiones = new ArrayList<Camion>();
		try{
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
		PreparedStatement pstm = conn.prepareStatement("SELECT * from tpdied.camion");
		ResultSet res = pstm.executeQuery();
		if(!res.next()) {
			return camiones;
		}
		else {
			
			do {
				Camion c = new Camion();
				c.setPatente(res.getString(1));
				c.setKilometrosRecorridos(res.getInt(2));
				c.setCostoPorKilometro(res.getDouble(3));
				c.setCostoPorHora(res.getDouble(4));
				c.setFechaCompra(LocalDate.parse(res.getString(5), formatter));
				Modelo m = new Modelo(res.getString(6), res.getString(7));
				c.setModelo(m);
				camiones.add(c);
				
			} while (res.next());
		}
		
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return camiones;
	}
	
	
	public void modificarCamion(Camion camionNuevo, Camion camionViejo) {
		Connection conn = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha = LocalDate.parse(camionNuevo.getFechaCompra().toString(), formatter);
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			Statement stmt = conn.createStatement();
			stmt.execute("UPDATE tpdied.camion "
					+ "SET patente='" + camionNuevo.getPatente() + "',"
					+ "km_recorridos=" + camionNuevo.getKilometrosRecorridos() + ", "
					+ "costo_por_km=" + camionNuevo.getCostoPorKilometro() + ", "
					+ "costo_por_hora=" + camionNuevo.getCostoPorHora() + ", "
					+ "fecha_compra='" + fecha + "', "
					+ "modelo='" + camionNuevo.getModelo().getNombre() + "', "
					+ "marca='" + camionNuevo.getModelo().getMarca().getNombre() 
					+ "' WHERE patente='" + camionViejo.getPatente() + "';"
					);
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void altaCamion(Camion camion) {
		Connection conn = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha = LocalDate.parse(camion.getFechaCompra().toString(), formatter);
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
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
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
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
	
	public ArrayList<Camion> consultarCamionesDisponibles(){
		Connection conn = null;
		ArrayList<Camion> camiones = new ArrayList<Camion>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement stmt = conn.prepareStatement("SELECT * from tpdied.camion CM LEFT JOIN " + 
					"(SELECT patente_camion FROM tpdied.detalle_envio DI " + 
					"LEFT JOIN (SELECT nro_orden FROM tpdied.orden_pedido WHERE estado='PROCESADA') as foo ON DI.nro_orden=foo.nro_orden) as bar ON bar.patente_camion = CM.patente;");
			ResultSet res = stmt.executeQuery();
			
			if(!res.next()) {
				return camiones;
			}
			else {
				
				do {
					Camion c = new Camion();
					c.setPatente(res.getString(1));
					c.setKilometrosRecorridos(res.getInt(2));
					c.setCostoPorKilometro(res.getDouble(3));
					c.setCostoPorHora(res.getDouble(4));
					c.setFechaCompra(LocalDate.parse(res.getString(5), formatter));
					Modelo m = new Modelo(res.getString(6), res.getString(7));
					c.setModelo(m);
					camiones.add(c);
					
				} while (res.next());
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return camiones;
	}


	public Camion buscarCamion(String patente) {
		Connection conn = null;
		Camion c = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement stmt = conn.prepareStatement("SELECT * from tpdied.camion WHERE patente=?");
			stmt.setString(1, patente);
			ResultSet res = stmt.executeQuery();
			
			if(res.next()) {
				c = new Camion(res.getString("patente"), 
						res.getInt("km_recorridos"),
						res.getDouble("costo_por_km"),
						res.getDouble("costo_por_hora"),
						LocalDate.parse(res.getString("fecha_compra"), formatter),
						new Modelo(res.getString("modelo"), res.getString("marca"))
						);
			}

			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
		
	}

}
