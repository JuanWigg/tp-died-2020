/**
 * 
 */
package com.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author juanwigg
 *
 */
public class DetalleEnvioDAOImplSQL implements DetalleEnvioDAO{

	public void altaDetalleEnvio(DetalleEnvio de) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");
			Statement stmt = conn.createStatement();
			//stmt.execute("INSERT INTO tpdied.detalle_envio (id_ruta, costo, patente_camion) VALUES ( " + 
			//de.getRuta().getId() + ", " + de.getCosto() + ",\"" + de.getCamion().getPatente() + "\");");
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
			conn = DriverManager.getConnection("tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");
			Statement stmt = conn.createStatement();
			stmt.execute("DELETE FROM tpdied.detalle_envio WHERE id=" + de.getId() + ";");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
