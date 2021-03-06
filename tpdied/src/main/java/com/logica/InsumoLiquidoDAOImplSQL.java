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
import kotlin.Pair;

/**
 * @author josesei
 *
 */

public class InsumoLiquidoDAOImplSQL implements InsumoLiquidoDAO {
	
	Dotenv dotenv = Dotenv.load();

	public InsumoLiquidoDAOImplSQL() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(InsumoLiquido obj) {
		
		Connection conn = null;
		PreparedStatement pstm1, pstm2 = null;
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			
			
			pstm1  = conn.prepareStatement(
				 "INSERT INTO tpdied.insumo "
				 + "( descripcion, unidad, costo_por_unidad ) "
				 + "VALUES ( ?, ?::tpdied.unidad, ? );" ,
				 Statement.RETURN_GENERATED_KEYS
			);
			 
			 pstm1.setString(1, obj.getDescripcion());
			 pstm1.setString(2, obj.getUnidad().toString());
			 pstm1.setDouble(3, obj.getCostoPorUnidad());
			 
			 pstm1.execute();
			 
			 ResultSet generatedKeys = pstm1.getGeneratedKeys();
			 
			 
			 
			 if (generatedKeys.next()) {
	            obj.setId(generatedKeys.getInt(1));
	         }
			 
			 pstm2  = conn.prepareStatement(
				 "INSERT INTO tpdied.insumo_liquido "
				 + "( id, densidad ) "
				 + "VALUES ( ?, ? );"
			 );
			 
			 pstm2.setInt(1, obj.getId());
			 pstm2.setDouble(2, obj.getDensidad());
			 
			 pstm2.execute();
			 
			 pstm1.close();
			 pstm2.close();
			 
			 conn.close();
			 
			 
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}


	}

	@Override
	public InsumoLiquido read(int id) {
		Connection conn = null;
		ResultSet res = null;
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			 PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.insumo I, tpdied.insumo_liquido IL WHERE I.id= ? AND IL.id= I.id ;");
			 pstm.setInt(1, id);
			 res=pstm.executeQuery();
			 
			 if(res.next()) {
				 return new InsumoLiquido(
				    id,
				 	res.getString("descripcion"),
				 	Unidad.valueOf(res.getString("unidad")),
				 	res.getDouble("costo_por_unidad"),
				 	res.getDouble("densidad")
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
	public List<InsumoLiquido> readAll() {
		Connection conn = null;
		ResultSet res = null;
		List<InsumoLiquido> listaInsumos = new ArrayList<InsumoLiquido>();
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.insumo I, tpdied.insumo_liquido IL WHERE I.id=IL.id ;");
			res=pstm.executeQuery();

			 
			while(res.next()) {
				listaInsumos.add(
					new InsumoLiquido(
				    res.getInt("id"),
				 	res.getString("descripcion"),
				 	Unidad.valueOf(res.getString("unidad")),
				 	res.getDouble("costo_por_unidad"),
				 	res.getDouble("densidad")
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
	public void update(InsumoLiquido newObj, InsumoLiquido oldObj) {
		Connection conn = null;
		PreparedStatement stmt1, stmt2 = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			stmt1 = conn.prepareStatement(
				"UPDATE tpdied.insumo "
				+ "SET descripcion= ? , "
				+ "unidad= ?::tpdied.unidad , "
				+ "costo_por_unidad= ?  "
				+ " WHERE id= ? ;"
			);
			
			stmt1.setString(1, newObj.getDescripcion());
			stmt1.setString(2, newObj.getUnidad().toString());
			stmt1.setDouble(3, newObj.getCostoPorUnidad());
			stmt1.setInt(4, newObj.getId());
			
			stmt1.execute();

			stmt2 = conn.prepareStatement(
				"UPDATE tpdied.insumo_liquido "
				+ "SET densidad= ? "
				+ " WHERE id= ?  ;"
			);
			
			stmt2.setDouble(1, newObj.getDensidad());
			stmt2.setInt(2, oldObj.getId());
			
			stmt2.execute();
			
			stmt1.close();
			stmt2.close();
			conn.close();
			
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}

	}

	@Override
	public boolean delete(InsumoLiquido obj) {
		Connection conn = null;
		PreparedStatement stmt1, stmt2 = null;
		int rowsDeletedParent = 0;
		int rowsDeletedChild = 0;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			stmt1 = conn.prepareStatement("DELETE FROM tpdied.insumo WHERE id= ? ;");
			stmt1.setInt(1, obj.getId());
			rowsDeletedParent = stmt1.executeUpdate();
			
			stmt2  = conn.prepareStatement("DELETE FROM tpdied.insumo_liquido WHERE id= ? ;");
			stmt2.setInt(1, obj.getId());
			rowsDeletedChild = stmt2.executeUpdate();
			
			stmt1.close();
			stmt2.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsDeletedParent > 0 && rowsDeletedChild > 0;
	}

	@Override
	public List<Pair<Insumo, Integer>> readAllWithStock() {
		Connection conn = null;
		ResultSet res = null;
		PreparedStatement pstm = null;
		List<Pair<Insumo, Integer>> listaInsumosStock = new ArrayList<Pair<Insumo, Integer>>();
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			pstm = conn.prepareStatement("SELECT I.id, I.descripcion, I.unidad, I.costo_por_unidad, IL.densidad, SUM(SI.cantidad) " + 
					"FROM tpdied.insumo I " + 
					"JOIN tpdied.insumo_liquido IL ON I.id = IL.id " + 
					"LEFT JOIN tpdied.stock_insumo SI ON I.id = SI.id_insumo " + 
					"GROUP BY I.id, I.descripcion, I.unidad, I.costo_por_unidad, IL.densidad ");
			
			res=pstm.executeQuery();		
			
			while(res.next()) {
				listaInsumosStock.add(
					new Pair<Insumo, Integer>(
					new InsumoLiquido(
				    res.getInt("id"),
				 	res.getString("descripcion"),
				 	Unidad.valueOf(res.getString("unidad")),
				 	res.getDouble("costo_por_unidad"),
				 	res.getDouble("densidad")
				 ),
					Integer.valueOf(res.getInt("sum"))));
			 }
			
			pstm.close();
			conn.close();
			
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaInsumosStock;
	}
	
	@Override
	public List<Pair<Insumo, Integer>> readAllWithStockFiltered(String descripcion) {
		Connection conn = null;
		ResultSet res = null;
		PreparedStatement pstm = null;
		List<Pair<Insumo, Integer>> listaInsumosStock = new ArrayList<Pair<Insumo, Integer>>();
		try {
			String filtro1="LOWER(I.descripcion) LIKE LOWER(?) ";
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			pstm = conn.prepareStatement("SELECT I.id, I.descripcion, I.unidad, I.costo_por_unidad, IL.densidad, SUM(SI.cantidad) " + 
					"FROM tpdied.insumo I " + 
					"JOIN tpdied.insumo_liquido IL ON I.id = IL.id " + 
					"LEFT JOIN tpdied.stock_insumo SI ON I.id = SI.id_insumo " + 
					"GROUP BY I.id, I.descripcion, I.unidad, I.costo_por_unidad, IL.densidad " +
					"HAVING " + filtro1);
			if(descripcion != null && descripcion !="") {
				pstm.setString(1, "%" + descripcion + "%");
			}
			else {
				return this.readAllWithStock();
			}
			res=pstm.executeQuery();		
			
			while(res.next()) {
				listaInsumosStock.add(
					new Pair<Insumo, Integer>(
					new InsumoLiquido(
				    res.getInt("id"),
				 	res.getString("descripcion"),
				 	Unidad.valueOf(res.getString("unidad")),
				 	res.getDouble("costo_por_unidad"),
				 	res.getDouble("densidad")
				 ),
					Integer.valueOf(res.getInt("sum"))));
			 }
			
			pstm.close();
			conn.close();
			
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaInsumosStock;
	}
	
}
	
	

