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
import io.github.cdimascio.dotenv.Dotenv;
/**
 * @author juanwigg
 *
 */
public class DetalleEnvioDAOImplSQL implements DetalleEnvioDAO{
	Dotenv dotenv = Dotenv.load();
	public void altaDetalleEnvio(DetalleEnvio de) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO tpdied.detalle_envio (id_ruta, costo, patente_camion, nro_orden)"
					+ " VALUES (?,?,?,?)");
			stmt.setInt(1, de.getRuta().getId());
			stmt.setDouble(2 , de.getCosto());
			stmt.setString(3, de.getCamion().getPatente());
			stmt.setInt(4, de.getNroOrden());
			
			
			stmt.execute();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void bajaDetalleEnvio(DetalleEnvio de) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			Statement stmt = conn.createStatement();
			stmt.execute("DELETE FROM tpdied.detalle_envio WHERE id=" + de.getId() + ";");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public DetalleEnvio consultarDetalleEnvio(int nroOrden) {
		Connection conn = null;
		DetalleEnvio de = null;
		String patente = null;
		int id_ruta = 0;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement stmt = conn.prepareStatement("SELECT * from tpdied.detalle_envio WHERE nro_orden=?");
			stmt.setInt(1, nroOrden);
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				de = new DetalleEnvio();
				de.setId_envio(res.getInt("id"));
				de.setCosto(res.getDouble("costo"));
				de.setNro_orden(nroOrden);
				patente = res.getString("patente_camion");
				id_ruta = res.getInt("id_ruta");
			}
			else {
				return null;
			}
			
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		CamionDAOImplSQL camionSQL = new CamionDAOImplSQL();
		de.setCamion(camionSQL.buscarCamion(patente));
		de.setRuta((new RutaDAOImplSQL()).armarRutar(id_ruta));
		
		return de;
	}

	
	
}
