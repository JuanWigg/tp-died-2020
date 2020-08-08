package com.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author josesei
 *
 */

public class InsumoGeneralDAOImplSQL implements InsumoGeneralDAO {
	
	Dotenv dotenv = Dotenv.load();

	public InsumoGeneralDAOImplSQL() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(InsumoGeneral obj) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			
			
			pstm  = conn.prepareStatement(
				 "INSERT INTO tpdied.insumo "
				 + "( descripcion, unidad, costo_por_unidad ) "
				 + "VALUES ( ?, ?, ? );" ,
				 Statement.RETURN_GENERATED_KEYS
			);
			 
			 pstm.setString(1, obj.getDescripcion());
			 pstm.setString(2, obj.getUnidad().toString());
			 pstm.setDouble(3, obj.getCostoPorUnidad());
			 
			 pstm.execute();
			 
			 ResultSet generatedKeys = pstm.getGeneratedKeys();
			 
			 pstm.close();
			 
			 if (generatedKeys.next()) {
	            obj.setId(generatedKeys.getInt(1));
	         }
			 
			 pstm  = conn.prepareStatement(
				 "INSERT INTO tpdied.insumo_general "
				 + "( id, peso ) "
				 + "VALUES ( ?, ? );"
			 );
			 
			 pstm.setInt(1, obj.getId());
			 pstm.setDouble(2, obj.getPeso());
			 
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
	public InsumoGeneral read(int id) {
		Connection conn = null;
		ResultSet res = null;
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			 PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.insumo I, tpdied.insumo_general IG WHERE I.id=? AND IG.id=I.id ;");
			 pstm.setInt(1, id);
			 res=pstm.executeQuery();
			 pstm.close();
			 conn.close();
			 
			 if(res.next()) {
				 return new InsumoGeneral(
				    id,
				 	res.getString("descripcion"),
				 	Unidad.valueOf(res.getString("unidad")),
				 	res.getDouble("costo_por_unidad"),
				 	res.getDouble("peso")
				 );
			 }
			 
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		
		return null;
		
	}

	@Override
	public List<InsumoGeneral> readAll() {
		Connection conn = null;
		ResultSet res = null;
		List<InsumoGeneral> listaInsumos = new ArrayList<InsumoGeneral>();
		
		try{
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
		PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.insumo I, tpdied.insumo_general IG WHERE I.id=IG.id ;");
		res=pstm.executeQuery();
		
		 
		while(res.next()) {
			listaInsumos.add(
				new InsumoGeneral(
			    res.getInt("id"),
			 	res.getString("descripcion"),
			 	Unidad.valueOf(res.getString("unidad")),
			 	res.getDouble("costo_por_unidad"),
			 	res.getDouble("peso")
			 ));
		 }
		
		pstm.close();
		conn.close();
		 
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaInsumos;
	}

	@Override
	public void update(InsumoGeneral newObj, InsumoGeneral oldObj) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			stmt = conn.prepareStatement(
				"UPDATE tpdied.insumo "
				+ "SET descripcion= ? , "
				+ "unidad= ? , "
				+ "costo_por_unidad= ? , "
				+ " WHERE id= ? ;"
			);
			
			stmt.setString(1, newObj.getDescripcion());
			stmt.setString(2, newObj.getUnidad().toString());
			stmt.setDouble(3, newObj.getCostoPorUnidad());
			stmt.setInt(4, newObj.getId());
			
			stmt.execute();
			
			stmt.close();

			stmt = conn.prepareStatement(
				"UPDATE tpdied.insumo_general "
				+ "SET densidad= ? ,"
				+ " WHERE id= ?  ;"
			);
			
			stmt.setDouble(1, newObj.getPeso());
			stmt.setInt(2, oldObj.getId());
			
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
	public boolean delete(InsumoGeneral obj) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int rowsDeletedParent = 0;
		int rowsDeletedChild = 0;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			stmt = conn.prepareStatement("DELETE FROM tpdied.insumo WHERE id= ? ;");
			stmt.setInt(1, obj.getId());
			rowsDeletedParent = stmt.executeUpdate();
			stmt.close();
			stmt  = conn.prepareStatement("DELETE FROM tpdied.insumo_general WHERE id= ? ;");
			stmt.setInt(1, obj.getId());
			rowsDeletedChild = stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsDeletedParent > 0 && rowsDeletedChild > 0;
	}

}
