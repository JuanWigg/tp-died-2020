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
public class MagnitudDAOImplSQL implements MagnitudDAO {

	public void altaMagnitud(Magnitud m) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");
			Statement stmt = conn.createStatement();
			stmt.execute("INSERT INTO tpdied.magnitud (valor, unidad) VALUES (" + m.getValue() + ", '" + m.getUnidad().getNombre() + "');");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void modificarMagnitud(Magnitud viejaMag, Magnitud nuevaMag) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("tpdied.cquiwsbyjbxy.sa-east-1.rds.amazonaws.com:5432/PichiDIED", "root", "trabajopracticodied");
			Statement stmt = conn.createStatement();
			stmt.execute("UPDATE tpdied.magnitud "
					+ "SET valor=" + nuevaMag.getValue() + 
					", unidad='" + nuevaMag.getUnidad().getNombre() + 
					"' WHERE id=" + viejaMag.getId() + ";");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
