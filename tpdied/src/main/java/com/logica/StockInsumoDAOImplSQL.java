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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author josesei
 *
 */
public class StockInsumoDAOImplSQL implements StockInsumoDAO {
	
	Dotenv dotenv = Dotenv.load();


	public StockInsumoDAOImplSQL() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(StockInsumo obj) {
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			
			
			pstm  = conn.prepareStatement(
				 "INSERT INTO tpdied.stock_insumo "
				 + "( nombre_planta, id_insumo, cantidad, punto_pedido ) "
				 + "VALUES ( ?, ?, ?, ? );"
			);
			 
			 pstm.setString(1, obj.getPlanta().getNombre());
			 pstm.setInt(2, obj.getInsumo().getId());
			 pstm.setDouble(3, obj.getCantidad());
			 pstm.setDouble(4, obj.getPuntoDePedido());
			 
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
	public StockInsumo read(int id) {
		// DO NOT USE - USE read(id, nombrePlanta) instead
		return null;
	}

	@Override
	public StockInsumo read(int id, String nombrePlanta) {
		Connection conn = null;
		ResultSet res = null;
		InsumoDAO<Insumo> ID = new InsumoDAOImplSQL();
		PlantaDAO PD = new PlantaDAOImplSQL();
		
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.stock_insumo WHERE nombre_planta  = ? AND id_insumo = ? ;");
			pstm.setString(1, nombrePlanta);
			pstm.setInt(2, id);
			res=pstm.executeQuery();
			pstm.close();
			conn.close();
			
			Planta planta;	
			Insumo insumo;
			 
			if(res.next()) {		
				
				insumo = ID.read(res.getInt("id_insumo"));
				planta = PD.consultarPlanta(res.getString("nombre_planta")).get();
				
				return new StockInsumo(planta, insumo, res.getDouble("cantidad"), res.getDouble("punto_pedido"));
										
			 }
		 
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<StockInsumo> readAll() {
		Connection conn = null;
		ResultSet res = null;
		List<StockInsumo> listaStockInsumos = new ArrayList<StockInsumo>();
		InsumoDAO<Insumo> ID = new InsumoDAOImplSQL();
		PlantaDAO PD = new PlantaDAOImplSQL();
		
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.stock_insumo ;");
			res=pstm.executeQuery();
			pstm.close();
			conn.close();
			
			Planta plantaActual;	
			Insumo insumoActual;
			 
			while(res.next()) {		
				
				insumoActual = ID.read(res.getInt("id_insumo"));
				plantaActual = PD.consultarPlanta(res.getString("nombre_planta")).get();
				
				listaStockInsumos.add(new StockInsumo(plantaActual, insumoActual, res.getDouble("cantidad"), res.getDouble("punto_pedido")));
										
			 }
		 
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaStockInsumos;
	}

	@Override
	public void update(StockInsumo newObj, StockInsumo oldObj) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			stmt = conn.prepareStatement(
				"UPDATE tpdied.stock_insumo "
				+ "SET cantidad = ? , "
				+ " WHERE nombre_planta = ? AND id_insumo = ? ;"
			);		
			
			stmt.setDouble(1, newObj.getCantidad());
			stmt.setString(2, newObj.getPlanta().getNombre());
			stmt.setInt(3, newObj.getInsumo().getId());			
			
			stmt.execute();
			
			stmt.close();

			conn.close();
			
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public boolean delete(StockInsumo obj) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsDeleted = 0;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			stmt = conn.prepareStatement("DELETE FROM tpdied.stock_insumo WHERE nombre_planta = ? AND id_insumo = ? ;");
			stmt.setString(1, obj.getPlanta().getNombre());
			stmt.setInt(2, obj.getInsumo().getId());
			rowsDeleted = stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsDeleted > 0;
	}



}
