/**
 * 
 */
package com.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author josesei
 *
 */
public class DetalleItemDAOImplSQL implements DetalleItemDAO {
	Dotenv dotenv = Dotenv.load();
	/**
	 * 
	 */
	public DetalleItemDAOImplSQL() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void create(DetalleItem obj) {
		//DEPRECATED
	}

	@Override
	public void create(DetalleItem obj, int nroOrden) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			
			
			pstm  = conn.prepareStatement(
				 "INSERT INTO tpdied.detalle_item "
				 + "( id_insumo, nro_orden_pedido, cantidad  ) "
				 + "VALUES ( ?, ?, ? );"
			);
			 
			 pstm.setInt(1, obj.getInsumo().getId());
			 pstm.setInt(2, nroOrden);
			 pstm.setDouble(3, obj.getCantidad());
			 System.out.println("EJECUTO EL SQL");
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
	public DetalleItem read(int id) {
		// DEPRECATED
		return null;
	}
	
	public DetalleItem read(int idInsumo, int nroOrden) {
		Connection conn = null;
		ResultSet res = null;
		InsumoDAO<Insumo> ID = new InsumoDAOImplSQL();
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			 PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.detalle_item WHERE id_insumo= ? AND nro_orden_pedido= ? ;");
			 pstm.setInt(1, idInsumo);
			 pstm.setInt(2, nroOrden);
			 res=pstm.executeQuery();
			 
			 if(res.next()) {
				 return new DetalleItem(
				    ID.read(idInsumo),
				 	res.getDouble("cantidad")
				 );
			 }
			 
			 pstm.close();
			 conn.close();
			 
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		
		return null;
	}

	@Override
	public List<DetalleItem> readAll() {
		// DEPRECATED
		return null;
	}
	
	@Override
	public List<DetalleItem> readAllFromOrden(int nroOrden){
		Connection conn = null;
		ResultSet res = null;
		InsumoDAO<Insumo> ID = new InsumoDAOImplSQL();
		List<DetalleItem> listaDetalleItems = new ArrayList<DetalleItem>();
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			 PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.detalle_item WHERE nro_orden_pedido= ? ;");
			 pstm.setInt(1, nroOrden);
			 res=pstm.executeQuery();
			 
			 while(res.next()) {
				 listaDetalleItems.add( new DetalleItem(
				    ID.read(res.getInt("id_insumo")),
				 	res.getDouble("cantidad")
				 ));
			 }
			 
			 pstm.close();
			 conn.close();
			 
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		
		return listaDetalleItems;
	}
	
	@Override
	public void update(DetalleItem newObj, DetalleItem oldObj) {
		//DEPRECATED
	}

	@Override
	public void update(int nroOrden, DetalleItem newObj, DetalleItem oldObj) {
		Connection conn = null;
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement pstm = conn.prepareStatement(
				"UPDATE tpdied.detalle_item "
				+ "SET cantidad= ? , "
				+ " WHERE id_insumo= ? AND nro_orden_pedido= ? ;"
			);
			pstm.setDouble(1, newObj.getCantidad());
			pstm.setInt(2, newObj.getInsumo().getId());
			pstm.setInt(3, nroOrden);			
			 
			pstm.close();
			conn.close();
			 
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public boolean delete(DetalleItem obj) {
		//DEPRECATED
		return false;
	}

	@Override
	public boolean delete(int nroOrden, DetalleItem obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		int rowsDeleted = 0;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			pstm = conn.prepareStatement("DELETE FROM tpdied.detalle_item WHERE nro_orden_pedido = ? AND id_insumo = ? ;");
			pstm.setInt(1, nroOrden);
			pstm.setInt(2, obj.getInsumo().getId());
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
