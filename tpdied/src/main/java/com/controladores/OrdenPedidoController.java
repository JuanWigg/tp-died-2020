/**
 * 
 */
package com.controladores;

import java.util.ArrayList;

import com.logica.Camion;
import com.logica.EstadoOrden;
import com.logica.Modelo;
import com.logica.OrdenPedido;
import com.logica.Planta;

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
public class OrdenPedidoController {
	Dotenv dotenv = Dotenv.load();
	
	
	public ArrayList<OrdenPedido> getPedidosCreados() {
		Connection conn = null;
		ArrayList<OrdenPedido> pedidos = new ArrayList<OrdenPedido>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM tpdied.orden_pedido WHERE estado='CREADA'");
			
			if(!res.next()) {
				return pedidos;
			}
			else {
				
				do {
					OrdenPedido op = new OrdenPedido();
					op.setNroOrden(res.getInt(1));
					op.setEstado(EstadoOrden.valueOf(res.getString(4)));
					op.setPlantaDestino(new Planta(res.getString(5)));
					pedidos.add(op);
				} while (res.next());
			}
			
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pedidos;
	}
}
