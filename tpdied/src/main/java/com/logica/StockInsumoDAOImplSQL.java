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
public class StockInsumoDAOImplSQL implements StockInsumoDAO {
	
	Dotenv dotenv = Dotenv.load();


	public StockInsumoDAOImplSQL() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(StockInsumo obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public StockInsumo read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StockInsumo> readAll() {
		Connection conn = null;
		ResultSet res = null;
		List<StockInsumo> listaStockInsumos = new ArrayList<StockInsumo>();
		
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.stock_insumo SI, tpdied.insumo I WHERE SI.id_insumo=I.id ;");
			res=pstm.executeQuery();
			int idInsumo;
			pstm.close();
			conn.close();
			
			InsumoDAO ID = new InsumoDAOImplSQL();
			 
			while(res.next()) {
				
				idInsumo = res.getInt("id_insumo");
				
				
					
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
