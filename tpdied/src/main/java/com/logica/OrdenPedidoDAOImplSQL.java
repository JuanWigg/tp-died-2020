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
		//DEPRECATED
	}

	@Override
	public int createAndGetKey(OrdenPedido obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet nroOrdenKey = null;
		int nroOrden = -1;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			
			
			pstm  = conn.prepareStatement(
				 "INSERT INTO tpdied.orden_pedido "
				 + "( fecha_solicitud, fecha_entrega, estado, planta_destino ) "
				 + "VALUES ( ?, ?, ?::tpdied.estado_pedido, ? );",
				 Statement.RETURN_GENERATED_KEYS
			);
			 
			 pstm.setObject(1, obj.getFechaSolicitud());
			 pstm.setObject(2, obj.getFechaEntrega());
			 pstm.setString(3, obj.getEstado().toString());
			 pstm.setString(4, obj.getPlantaDestino().getNombre());
			 
			 pstm.execute();
			 
			 nroOrdenKey = pstm.getGeneratedKeys();		
			 
			 if (nroOrdenKey.next()) {
		          nroOrden = nroOrdenKey.getInt(1);
		     }
			 
			 pstm.close();
			 
			 conn.close();
			 
			 
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		return nroOrden;
	}

	@Override
	public OrdenPedido read(int nroOrden) {
		Connection conn = null;
		ResultSet res = null;
		OrdenPedido ordenPedido = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DetalleEnvioDAO DED = new DetalleEnvioDAOImplSQL();
		DetalleItemDAO DID = new DetalleItemDAOImplSQL();
		PlantaDAO PD = new PlantaDAOImplSQL();
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement pstm  = conn.prepareStatement("SELECT * "
					+ "FROM tpdied.orden_pedido "
					+ "WHERE nro_orden = ? "
					+ ";" );
			pstm.setInt(1, nroOrden);
			res=pstm.executeQuery();
			 
			if(res.next()) {
				
				DetalleEnvio detalleEnvio = DED.consultarDetalleEnvio(res.getInt("nro_orden_pedido"));
				
				
				ordenPedido = new OrdenPedido(res.getInt("nro_orden_pedido"), (LocalDate) formatter.parse(res.getString("fecha_solicitud")), 
						(LocalDate) formatter.parse(res.getString("fecha_entrega")), EstadoOrden.valueOf(res.getString("estado")) , 
						detalleEnvio , DID.readAllFromOrden(res.getInt("nro_orden_pedido")), PD.consultarPlanta(res.getString("planta_destino")).get());
										
			 }
			
			pstm.close();
			conn.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ordenPedido;
	}

	@Override
	public List<OrdenPedido> readAll() {
		Connection conn = null;
		ResultSet res = null;
		List<OrdenPedido> listaOrdenesPedido = new ArrayList<OrdenPedido>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DetalleEnvioDAO DED = new DetalleEnvioDAOImplSQL();
		DetalleItemDAO DID = new DetalleItemDAOImplSQL();
		PlantaDAO PD = new PlantaDAOImplSQL();
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement pstm  = conn.prepareStatement("SELECT * "
					+ "FROM tpdied.orden_pedido ;" );
			res=pstm.executeQuery();
			 
			while(res.next()) {
				
				DetalleEnvio detalleEnvio = DED.consultarDetalleEnvio(res.getInt("nro_orden_pedido"));
				
				
				listaOrdenesPedido.add(new OrdenPedido(res.getInt("nro_orden_pedido"), (LocalDate) formatter.parse(res.getString("fecha_solicitud")), 
						(LocalDate) formatter.parse(res.getString("fecha_entrega")), EstadoOrden.valueOf(res.getString("estado")) , 
						detalleEnvio , DID.readAllFromOrden(res.getInt("nro_orden_pedido")), PD.consultarPlanta(res.getString("planta_destino")).get()));
										
			 }
			
			pstm.close();
			conn.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaOrdenesPedido;
	}
	
	@Override
	public List<OrdenPedido> readAllWithStatus(EstadoOrden estado) {
		Connection conn = null;
		ResultSet res = null;
		List<OrdenPedido> listaOrdenesPedido = new ArrayList<OrdenPedido>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DetalleEnvioDAO DED = new DetalleEnvioDAOImplSQL();
		DetalleItemDAO DID = new DetalleItemDAOImplSQL();
		PlantaDAO PD = new PlantaDAOImplSQL();
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement pstm  = conn.prepareStatement("SELECT * "
					+ "FROM tpdied.orden_pedido " 
					+ "WHERE estado = ?::tpdied.estado_pedido ;" );
			pstm.setString(1, estado.toString());
			res=pstm.executeQuery();
			 
			while(res.next()) {
				
				
				
				if(estado == EstadoOrden.CREADA) {
					listaOrdenesPedido.add(new OrdenPedido(res.getInt("nro_orden"), LocalDate.parse(res.getString("fecha_solicitud"), formatter), 
							LocalDate.parse(res.getString("fecha_entrega"), formatter), estado , DID.readAllFromOrden(res.getInt("nro_orden")),
							PD.consultarPlanta(res.getString("planta_destino")).get()));
				}
				else {
				DetalleEnvio detalleEnvio = DED.consultarDetalleEnvio(res.getInt("nro_orden"));
				listaOrdenesPedido.add(new OrdenPedido(res.getInt("nro_orden"), LocalDate.parse(res.getString("fecha_solicitud"), formatter), 
						LocalDate.parse(res.getString("fecha_entrega"), formatter), estado , 
						detalleEnvio , DID.readAllFromOrden(res.getInt("nro_orden")), PD.consultarPlanta(res.getString("planta_destino")).get()));
				}
			 }
			
			pstm.close();
			conn.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaOrdenesPedido;
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
				+ " fecha_entrega = ? , "
				+ " estado = ?::tpdied.estado_pedido , "
				+ " planta_destino = ?  "
				+ " WHERE nro_orden = ? ;"
			);		
			
			pstm.setObject(1, newObj.getFechaSolicitud());
			pstm.setObject(2, newObj.getFechaEntrega());
			pstm.setString(3, newObj.getEstado().toString());
			pstm.setString(4, newObj.getPlantaDestino().getNombre());
			
			pstm.setInt(5, oldObj.getNroOrden());
			
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
