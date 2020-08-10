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

public class InsumoGeneralDAOImplSQL implements InsumoGeneralDAO {
	
	Dotenv dotenv = Dotenv.load();

	public InsumoGeneralDAOImplSQL() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(InsumoGeneral obj) {
		
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
				 "INSERT INTO tpdied.insumo_general "
				 + "( id, peso ) "
				 + "VALUES ( ?, ? );"
			 );
			 
			 pstm2.setInt(1, obj.getId());
			 pstm2.setDouble(2, obj.getPeso());
			 
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
	public InsumoGeneral read(int id) {
		Connection conn = null;
		ResultSet res = null;
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			 PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.insumo I, tpdied.insumo_general IG WHERE I.id=? AND IG.id=I.id ;");
			 pstm.setInt(1, id);
			 res=pstm.executeQuery();
			 
			 
			 if(res.next()) {
				 return new InsumoGeneral(
				    id,
				 	res.getString("descripcion"),
				 	Unidad.valueOf(res.getString("unidad")),
				 	res.getDouble("costo_por_unidad"),
				 	res.getDouble("peso")
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
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			stmt1 = conn.prepareStatement(
				"UPDATE tpdied.insumo "
				+ "SET descripcion= ? , "
				+ "unidad= ?::tpdied.unidad , "
				+ "costo_por_unidad= ? "
				+ " WHERE id= ? ;"
			);
			
			stmt1.setString(1, newObj.getDescripcion());
			stmt1.setString(2, newObj.getUnidad().toString());
			stmt1.setDouble(3, newObj.getCostoPorUnidad());
			stmt1.setInt(4, newObj.getId());
			
			stmt1.execute();			
			

			stmt2 = conn.prepareStatement(
				"UPDATE tpdied.insumo_general "
				+ "SET peso= ? "
				+ " WHERE id= ?  ;"
			);
			
			stmt2.setDouble(1, newObj.getPeso());
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
	public boolean delete(InsumoGeneral obj) {
		Connection conn = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		int rowsDeletedParent = 0;
		int rowsDeletedChild = 0;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			
			stmt1 = conn.prepareStatement("DELETE FROM tpdied.insumo WHERE id= ? ;");
			stmt1.setInt(1, obj.getId());
			
			rowsDeletedParent = stmt1.executeUpdate();	
			
			stmt2  = conn.prepareStatement("DELETE FROM tpdied.insumo_general WHERE id= ? ;");
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
			pstm = conn.prepareStatement("SELECT I.id, I.descripcion, I.unidad, I.costo_por_unidad, IG.peso, SUM(SI.cantidad) " + 
					"FROM tpdied.insumo I " + 
					"JOIN tpdied.insumo_general IG ON I.id = IG.id " + 
					"LEFT JOIN tpdied.stock_insumo SI ON I.id = SI.id_insumo " + 
					"GROUP BY I.id, I.descripcion, I.unidad, I.costo_por_unidad, IG.peso ");
			
			res=pstm.executeQuery();		
			
			while(res.next()) {
				listaInsumosStock.add(
					new Pair<Insumo, Integer>(
					new InsumoGeneral(
				    res.getInt("id"),
				 	res.getString("descripcion"),
				 	Unidad.valueOf(res.getString("unidad")),
				 	res.getDouble("costo_por_unidad"),
				 	res.getDouble("peso")
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
	
	
	public List<Pair<Insumo, Integer>> readAllWithStockFiltered(String descripcion) {
		Connection conn = null;
		ResultSet res = null;
		PreparedStatement pstm = null;
		List<Pair<Insumo, Integer>> listaInsumosStock = new ArrayList<Pair<Insumo, Integer>>();
		try {
			String filtro1="LOWER(I.descripcion) LIKE LOWER(?) ";
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			pstm = conn.prepareStatement("SELECT I.id, I.descripcion, I.unidad, I.costo_por_unidad, IG.peso, SUM(SI.cantidad) " + 
					"FROM tpdied.insumo I " + 
					"JOIN tpdied.insumo_general IG ON I.id = IG.id " + 
					"LEFT JOIN tpdied.stock_insumo SI ON I.id = SI.id_insumo " + 
					"GROUP BY I.id, I.descripcion, I.unidad, I.costo_por_unidad, IG.peso "
					+ "HAVING " + filtro1);
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
					new InsumoGeneral(
				    res.getInt("id"),
				 	res.getString("descripcion"),
				 	Unidad.valueOf(res.getString("unidad")),
				 	res.getDouble("costo_por_unidad"),
				 	res.getDouble("peso")
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
