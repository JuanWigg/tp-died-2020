/**
 * 
 */
package com.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.logica.Camion;
import com.logica.DetalleItem;
import com.logica.Insumo;
import com.logica.InsumoGeneral;
import com.logica.OrdenPedido;
import com.logica.Planta;
import com.logica.StockInsumo;
import com.logica.StockInsumoDAOImplSQL;

/**
 * @author Pichi
 *
 */
public class StockInsumoController {
	public ArrayList<StockInsumo> consultarStocksInsuficientes(){
		return ((ArrayList<StockInsumo>) (new StockInsumoDAOImplSQL()).consultarStocksInsuficientes());
	}
	
	
	
	public HashMap<Integer, Integer> buscarStockTotalInsumos(){
		return (new StockInsumoDAOImplSQL()).buscarStockTotalInsumos();
	}
	
	
	public void agregarStock(ArrayList<String> datosStock) {
		StockInsumo stock = new StockInsumo(new Planta(datosStock.get(0)),
				(Insumo) (new InsumoGeneral(Integer.parseInt(datosStock.get(1)))),
				Double.parseDouble(datosStock.get(2)),
				Double.parseDouble(datosStock.get(3)));
		(new StockInsumoDAOImplSQL()).addStock(stock);
	}
	
	
	public ArrayList<Planta> plantasConStockSuficiente(OrdenPedido orden){
		ArrayList<StockInsumo> stocks = (ArrayList<StockInsumo>) (new StockInsumoDAOImplSQL()).readAll();
		ArrayList<Predicate<StockInsumo>> predicados = new ArrayList<Predicate<StockInsumo>>();
		for(DetalleItem di : orden.getDetalleItems()) {
			predicados.add(st -> ((st.getInsumo().getId()==(di.getInsumo().getId()) && (st.getCantidad()>=di.getCantidad()))));
		}
		
		ArrayList<Planta> plantas = (ArrayList<Planta>) stocks.stream()
		          .filter(predicados.stream().reduce(x->true, Predicate::or))
		          .map(st -> st.getPlanta()).collect(Collectors.toList());
		
		Set<Planta> setPlanta = new HashSet<Planta>(plantas);
		ArrayList<Planta> result = new ArrayList<Planta>();
		for(Planta p : setPlanta) {
			if(Collections.frequency(plantas,p)==predicados.size())
				result.add(p);
		}
		return result;
		
		
		
	}
}
