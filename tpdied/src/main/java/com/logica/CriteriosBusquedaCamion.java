/**
 * 
 */
package com.logica;

import java.time.LocalDate;
import java.util.function.BiPredicate;

/**
 * @author Pichi
 *
 */
public class CriteriosBusquedaCamion {
	public static final BiPredicate<Camion, String> criterioPatente = new BiPredicate<Camion, String>(){

		public boolean test(Camion t, String u) {
			return u.equals(t.getPatente());
		}
		
	};
	public static final BiPredicate<Camion, Integer> criterioKmreco = new BiPredicate<Camion, Integer>(){

		public boolean test(Camion t, Integer u) {
			return u.equals(t.getKilometrosRecorridos());
		}
		
	};
	public static final BiPredicate<Camion, Double> criterioCostoKm = new BiPredicate<Camion, Double>(){

		public boolean test(Camion t, Double u) {
			return u.equals(t.getCostoPorKilometro());
		}
		
	};
	public static final BiPredicate<Camion, Double> criterioCostoHora = new BiPredicate<Camion, Double>(){

		public boolean test(Camion t, Double u) {
			return u.equals(t.getCostoPorHora());
		}
		
	};
	public static final BiPredicate<Camion, LocalDate> criterioFecha = new BiPredicate<Camion, LocalDate>(){

		public boolean test(Camion t, LocalDate u) {
			return u.equals(t.getFechaCompra());
		}
		
	};
	public static final BiPredicate<Camion, String> criterioModelo = new BiPredicate<Camion, String>(){

		public boolean test(Camion t, String u) {
			return u.equals(t.getModelo().getNombre());
		}
		
	};
	public static final BiPredicate<Camion, String> criterioMarca= new BiPredicate<Camion, String>(){

		public boolean test(Camion t, String u) {
			return u.equals(t.getModelo().getMarca().getNombre());
		}
		
	};
	
}
