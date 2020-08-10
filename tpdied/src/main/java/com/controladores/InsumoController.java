/**
 * 
 */
package com.controladores;

import java.util.List;

import com.logica.Insumo;
import com.logica.InsumoDAOImplSQL;
import com.logica.InsumoGeneral;
import com.logica.InsumoGeneralDAOImplSQL;
import com.logica.InsumoLiquido;
import com.logica.InsumoLiquidoDAOImplSQL;
import com.logica.Unidad;

import kotlin.Pair;

/**
 * @author Pichi
 *
 */
public class InsumoController {
	public List<Insumo> consultarInsumos(){
		
		return ((new InsumoDAOImplSQL()).readAll());
		
		
	}
	
	public List<Pair<Insumo, Integer>> consultarInsumosStock(){
		return new InsumoDAOImplSQL().readAllWithStock();
	}
	
	public void altaInsumo(String descripcion, String tipoInsumo, Unidad unidad, double costoPorUnidad, double magnitudUnidad) {
		if(tipoInsumo == "GENERAL") {
			new InsumoGeneralDAOImplSQL().create(new InsumoGeneral(-1, descripcion, unidad, costoPorUnidad, magnitudUnidad));
		}
		else if(tipoInsumo == "LIQUIDO") {
			new InsumoLiquidoDAOImplSQL().create(new InsumoLiquido(-1, descripcion, unidad, costoPorUnidad, magnitudUnidad));
		}
		
	}
	
	public List<Pair<Insumo, Integer>> consultarInsumosStockFiltrado(String descripcion){
		return new InsumoDAOImplSQL().readAllWithStockFiltered(descripcion);
	}
	
	public String[][] consultarDescripcionesInsumos() {
		List<Insumo> insumos = ((new InsumoDAOImplSQL()).readAll());
		String[][] descripciones = new String[insumos.size()+1][2];
		descripciones[0][0] = "<Ninguno>";
		for(int i=1; i<insumos.size()+1; i++) {
			descripciones[i][0] = insumos.get(i-1).getDescripcion() + "";
			descripciones[i][1] = insumos.get(i-1).getId() + "";
		}
		
		
		return descripciones;
		
	}

	public void modificarInsumo(Insumo newInsumo, Insumo oldInsumo) {
		new InsumoDAOImplSQL().update(newInsumo, oldInsumo);
	}
	
	
	public void delete(Insumo insumo) {
		new InsumoDAOImplSQL().delete(insumo);	
	}
	
	
} 
