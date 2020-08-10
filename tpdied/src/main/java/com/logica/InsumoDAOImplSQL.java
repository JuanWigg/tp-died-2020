/**
 * 
 */
package com.logica;

import java.util.ArrayList;
import java.util.List;

import kotlin.Pair;

/**
 * @author josesei
 *
 */
public class InsumoDAOImplSQL implements InsumoDAO<Insumo> {


	public InsumoDAOImplSQL() {
		// TODO Auto-generated constructor stub
	}
	
	public void create(Insumo obj) {
		InsumoGeneralDAO IGD = new InsumoGeneralDAOImplSQL(); 
		InsumoLiquidoDAO ILD = new InsumoLiquidoDAOImplSQL(); 
		
		if(obj.getClass() == InsumoGeneral.class) {
			IGD.create((InsumoGeneral) obj);
		}
		if(obj.getClass() == InsumoLiquido.class) {
			ILD.create((InsumoLiquido) obj);
		}
	}
	
	public Insumo read(int id) {
		InsumoGeneralDAO IGD = new InsumoGeneralDAOImplSQL(); 
		InsumoLiquidoDAO ILD = new InsumoLiquidoDAOImplSQL(); 
		
		InsumoGeneral insumoGeneral = null;
		InsumoLiquido insumoLiquido = null;
		
		if((insumoGeneral = IGD.read(id))!=null) {
			return insumoGeneral;
		}
	
		if((insumoLiquido = ILD.read(id))!=null) {
			return insumoLiquido;
		}
		
		return null;
		
	}
	
	public List<Insumo> readAll(){
		InsumoGeneralDAO IGD = new InsumoGeneralDAOImplSQL(); 
		InsumoLiquidoDAO ILD = new InsumoLiquidoDAOImplSQL(); 
		
		List<Insumo> listaInsumos = new ArrayList<Insumo>();
		
		listaInsumos.addAll(IGD.readAll());
		listaInsumos.addAll(ILD.readAll());
		
		return listaInsumos;
	}
	
	public List<Pair<Insumo, Integer>> readAllWithStock(){
		InsumoGeneralDAO IGD = new InsumoGeneralDAOImplSQL(); 
		InsumoLiquidoDAO ILD = new InsumoLiquidoDAOImplSQL(); 
		
		List<Pair<Insumo, Integer>> listaInsumos = new ArrayList<Pair<Insumo, Integer>>();
		
		listaInsumos.addAll(IGD.readAllWithStock());
		listaInsumos.addAll(ILD.readAllWithStock());
		
		return listaInsumos;
	}
	
	public void update(Insumo newObj, Insumo oldObj) {
		InsumoGeneralDAO IGD = new InsumoGeneralDAOImplSQL(); 
		InsumoLiquidoDAO ILD = new InsumoLiquidoDAOImplSQL(); 
		
		if(oldObj.getClass() == InsumoGeneral.class && newObj.getClass() == InsumoGeneral.class) {
			IGD.update((InsumoGeneral) newObj, (InsumoGeneral) oldObj);
		}
		if(oldObj.getClass() == InsumoLiquido.class && newObj.getClass() == InsumoLiquido.class) {
			ILD.update((InsumoLiquido) newObj, (InsumoLiquido) oldObj);
		}

	}
	
	public boolean delete(Insumo obj) {
		InsumoGeneralDAO IGD = new InsumoGeneralDAOImplSQL(); 
		InsumoLiquidoDAO ILD = new InsumoLiquidoDAOImplSQL(); 
		
		if(obj.getClass() == InsumoGeneral.class) {
			return IGD.delete((InsumoGeneral) obj);
		}
		if(obj.getClass() == InsumoLiquido.class) {
			return ILD.delete((InsumoLiquido) obj);
		}
		
		return false;
	}

	@Override
	public List<Pair<Insumo, Integer>> readAllWithStockFiltered(String descripcion) {
		InsumoGeneralDAO IGD = new InsumoGeneralDAOImplSQL(); 
		InsumoLiquidoDAO ILD = new InsumoLiquidoDAOImplSQL(); 
		
		List<Pair<Insumo, Integer>> listaInsumos = new ArrayList<Pair<Insumo, Integer>>();
		
		listaInsumos.addAll(IGD.readAllWithStockFiltered(descripcion));
		listaInsumos.addAll(ILD.readAllWithStockFiltered(descripcion));
		
		return listaInsumos;
	}

}
