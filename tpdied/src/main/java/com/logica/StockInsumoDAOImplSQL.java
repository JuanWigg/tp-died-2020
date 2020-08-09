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
import java.util.HashMap;
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
			PreparedStatement pstm  = conn.prepareStatement("SELECT * " + 
					"FROM tpdied.insumo i " + 
					"RIGHT JOIN(SELECT * from tpdied.stock_insumo)as foo " + 
					"ON i.id=foo.id_insumo;");
			res=pstm.executeQuery();

			Planta plantaActual;	
			InsumoGeneral insumoActual;
			 
			while(res.next()) {		
				
				insumoActual = new InsumoGeneral(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4) );
				plantaActual = new Planta(res.getString("nombre_planta"));
				
				listaStockInsumos.add(new StockInsumo(plantaActual, insumoActual, res.getDouble("cantidad"), res.getDouble("punto_pedido")));
										
			 }
			
			pstm.close();
			conn.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaStockInsumos;
	}
	public List<StockInsumo> consultarStocksInsuficientes(){
		Connection conn = null;
		ResultSet res = null;
		List<StockInsumo> listaStockInsumos = new ArrayList<StockInsumo>();
		InsumoDAO<Insumo> ID = new InsumoDAOImplSQL();
		
		
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement pstm  = conn.prepareStatement("SELECT * " + 
					"FROM tpdied.insumo i " + 
					"RIGHT JOIN(SELECT * from tpdied.stock_insumo WHERE cantidad<punto_pedido)as foo " + 
					"ON i.id=foo.id_insumo;");
			res=pstm.executeQuery();
			
			
			Planta plantaActual;	

			 
			while(res.next()) {		
				
				Insumo insumoActual = new InsumoGeneral(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4));
				plantaActual = new Planta(res.getString("nombre_planta"));
				
				listaStockInsumos.add(new StockInsumo(plantaActual, insumoActual, res.getDouble("cantidad"), res.getDouble("punto_pedido")));
										
			 }
		 
			pstm.close();
			conn.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return listaStockInsumos;
	}
	public HashMap<Integer, Integer> buscarStockTotalInsumos(){
		Connection conn = null;
		HashMap<Integer, Integer> mapa = new HashMap<Integer,Integer>();
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT i.id, foo.sum FROM tpdied.insumo i " + 
					"LEFT JOIN( " + 
					"SELECT id_insumo, SUM(cantidad) FROM tpdied.stock_insumo " + 
					"GROUP BY id_insumo)as foo " + 
					"ON i.id=foo.id_insumo;");
			
			if(!res.next()) {
				return mapa;
			}
			else {
				
				do {
					mapa.put(res.getInt(1), res.getInt(2));
					
					
				} while (res.next());
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mapa;
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

	public void addStock(StockInsumo stock) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			Statement stmt = conn.createStatement();
			
			if(stock.getPuntoDePedido() == 0) {
				stmt.execute("DO $do$ BEGIN IF EXISTS (SELECT id_insumo, nombre_planta FROM tpdied.stock_insumo " + 
						"	   WHERE id_insumo=" + stock.getInsumo().getId() + " AND nombre_planta='" + stock.getPlanta().getNombre() + "') "+ 
						"	   THEN " + 
						"	   UPDATE tpdied.stock_insumo SET cantidad = cantidad + " + stock.getCantidad() + "WHERE id_insumo="
								+ stock.getInsumo().getId() + " AND nombre_planta='" + stock.getPlanta().getNombre() + "';" +
						"      ELSE " + 
						"	INSERT INTO tpdied.stock_insumo VALUES ('" + stock.getPlanta().getNombre() + 
						"', " + stock.getInsumo().getId() + ", " + stock.getCantidad() + ", " + stock.getPuntoDePedido() + "); END IF;" +
						" END $do$");
			}
			else {
				stmt.execute("DO $do$ BEGIN IF EXISTS (SELECT id_insumo, nombre_planta FROM tpdied.stock_insumo " + 
						"	   WHERE id_insumo=" + stock.getInsumo().getId() + " AND nombre_planta='" + stock.getPlanta().getNombre() + "') " + 
						"	   THEN " + 
						"	   UPDATE tpdied.stock_insumo SET cantidad = cantidad + " + stock.getCantidad() + ", punto_pedido = " +
						stock.getPuntoDePedido() + "WHERE id_insumo="
						+ stock.getInsumo().getId() + " AND nombre_planta='" + stock.getPlanta().getNombre() + "';" +
						"      ELSE " + 
						"	INSERT INTO tpdied.stock_insumo VALUES ('" + stock.getPlanta().getNombre() + 
						"', " + stock.getInsumo().getId() + ", " + stock.getCantidad() + ", " + stock.getPuntoDePedido() + "); END IF;" +
						" END $do$");
			}
			
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Planta> plantasConStockSuficiente(OrdenPedido orden){
		Connection conn = null;
		ArrayList<Planta> plantas = new ArrayList<Planta>();
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			PreparedStatement stmt = conn.prepareStatement("select nombre_planta from (select bar.nombre_planta "
					+ "from tpdied.detalle_item dt, " 
					+ "(select * from tpdied.stock_insumo st right join "
					+ "(SELECT * FROM tpdied.planta as p "
					+ "WHERE NOT EXISTS ((SELECT id_insumo FROM tpdied.detalle_item as DI ) "
					+ "EXCEPT (SELECT st.id_insumo FROM tpdied.stock_insumo as st WHERE st.nombre_planta = p.nombre) )"
					+ ") as foo on st.nombre_planta=foo.nombre) as bar "
					+ "where dt.id_insumo = bar.id_insumo and "
					+ "dt.cantidad <= bar.cantidad) as XD GROUP BY XD.nombre_planta having "
					+ "count(nombre_planta)=(select count(id_insumo) "
					+ "from tpdied.detalle_item group by nro_orden_pedido having nro_orden_pedido = " + orden.getNroOrden() + "); ");
			ResultSet res = stmt.executeQuery();
			if(!res.next()) {
				return plantas;
			}
			else {
				
				do {
					plantas.add(new Planta(res.getString(1)));
					
					
				} while (res.next());
			}
			stmt.close();
			conn.close();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return plantas;
	}

}
