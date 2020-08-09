/**
 * 
 */
package com.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author josesei
 *
 */
public class OrdenPedidoDAOImplSQL implements OrdenPedidoDAO {

	Dotenv dotenv = Dotenv.load();
	/**
	 * 
	 */
	public OrdenPedidoDAOImplSQL() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(OrdenPedido obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public OrdenPedido read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdenPedido> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(OrdenPedido newObj, OrdenPedido oldObj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaSolicitud = LocalDate.parse(newObj.getFechaSolicitud().toString(), formatter);
		LocalDate fechaEntrega = LocalDate.parse(newObj.getFechaEntrega().toString(), formatter);
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			pstm = conn.prepareStatement(
				"UPDATE tpdied.orden_pedido "
				+ "SET fecha_solicitud = ? , "
				+ "SET fecha_entrega = ? , "
				+ "SET estado_pedido = ? , "
				+ " WHERE nro_orden_pedido ;"
			);		
			
			pstm.setString(1, fechaSolicitud.toString());
			pstm.setString(2, fechaEntrega.toString());
			pstm.setString(3, newObj.getEstado().toString());
			pstm.setInt(4, oldObj.getNroOrden());
			
			pstm.execute();
			
			pstm.close();

			conn.close();
			
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public boolean delete(OrdenPedido obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int rowsDeleted = 0;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			pstm = conn.prepareStatement("DELETE FROM tpdied.orden_pedido WHERE nro_orden_pedido = ? ;");
			pstm.setInt(1, obj.getNroOrden());
			rowsDeleted = pstm.executeUpdate();
			pstm.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsDeleted > 0;
	}

}
