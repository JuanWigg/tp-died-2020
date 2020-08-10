package com.logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import io.github.cdimascio.dotenv.Dotenv;

public class TramoDAOImplSQL implements TramoDAO {
	

	Dotenv dotenv = Dotenv.load();
	public void AltaTramo(Tramo T) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			Statement stmt = conn.createStatement();
			stmt.execute("INSERT INTO tpdied.tramo (distancia, duracion, peso_max_permitido, peso_max_permitido_unidad,"
					+ "planta_origen, planta_destino) VALUES ("+T.getDistancia()+","
					+T.getDuracionEstimada()+","+T.getPesoMaximoPermitido()+","+" '"+T.getUnidad()+"'"+",'"+T.getPlantaOrigen()
					+"','"+T.getPlantaDestino()+"');");
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void BajaTramo(Tramo T) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			Statement stmt = conn.createStatement();
			stmt.execute("DELETE FROM tpdied.tramo WHERE id_tramo=" + T.getIdTramo()+";");
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<Tramo> ConsultarTramo(Integer id) {
		Connection conn = null;
		ResultSet res = null;
		Optional<Tramo> t = Optional.empty();
		Planta PlantaO;
		Planta PlantaD;
	
		try{
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
		 PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.tramo WHERE id_tramo=?;");
		 pstm.setInt(1, id);
		 res=pstm.executeQuery();
		 
		 if (!res.next()) {
			 return Optional.ofNullable(null);
			}
		 else {
			 PlantaO = new Planta(res.getString(6));
			 PlantaD = new Planta(res.getString(7));
			 t=Optional.of(new Tramo(Integer.valueOf(res.getInt(1)),
					 PlantaO,PlantaD,Integer.valueOf(res.getInt(2)),Double.valueOf(res.getDouble(3)),
			 Double.valueOf(res.getDouble(4)),res.getString(5)));
			 
			 }
		pstm.close();
		conn.close();
		} catch(ClassNotFoundException e) {
			
		} catch(SQLException e) {
			
		}
		
		return t;	
		}

	public List<Tramo> obtenerTramos() {
		Connection conn = null;
		ResultSet res = null;
		List<Tramo> listaTramos = new ArrayList<Tramo>();
		Planta PlantaO;
		Planta PlantaD;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			 PreparedStatement pstm  = conn.prepareStatement("SELECT * FROM tpdied.tramo;");
			 res=pstm.executeQuery();
			
			 while(res.next()) {
				 Tramo t;
				 PlantaO = new Planta(res.getString(6));
				 PlantaD = new Planta(res.getString(7));
				 t= new Tramo(Integer.valueOf(res.getInt(1)),
						 PlantaO,PlantaD,Integer.valueOf(res.getInt(2)),Double.valueOf(res.getDouble(3)),
				 Double.valueOf(res.getDouble(4)),res.getString(5));
				 listaTramos.add(t);
			 }
			 pstm.close();
			 conn.close();
			} catch(ClassNotFoundException e) {
				
			} catch(SQLException e) {
				
			}
		
		return listaTramos;
	}
	
	public void altaTramoDeRuta(int id_ruta, int id_tramo, int orden) {
		Connection conn = null;
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			 PreparedStatement pstm  = conn.prepareStatement("INSERT INTO tpdied.tramos_de_ruta VALUES (?,?,?);");
			 pstm.setInt(1, id_ruta);
			 pstm.setInt(2, id_tramo);
			 pstm.setInt(3, orden);
			 
			 pstm.execute();
			 
			 pstm.close();
			 conn.close();
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public ArrayList<Integer> consultarIdTramosConRuta(int id_ruta) {
		Connection conn = null;
		ArrayList<Integer> ids_tramos = new ArrayList<Integer>();
		try{
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PSW"));
			 PreparedStatement pstm  = conn.prepareStatement("SELECT id_tramo FROM tpdied.tramos_de_ruta WHERE id_ruta=? ORDER BY orden");
			 pstm.setInt(1, id_ruta);
			 ResultSet res = pstm.executeQuery();
			 
			 if(!res.next()) {
					return ids_tramos;
				}
				else {
					
					do {
						ids_tramos.add(res.getInt(1));
						
					} while (res.next());
				}
			 
			 pstm.close();
			 conn.close();
			} catch(ClassNotFoundException e) {
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		
		return ids_tramos;
	}
}
