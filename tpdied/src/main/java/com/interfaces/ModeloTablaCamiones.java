/**
 * 
 */
package com.interfaces;

import javax.swing.table.DefaultTableModel;

/**
 * @author Pichi
 *
 */
public class ModeloTablaCamiones extends DefaultTableModel{
	String[] columnas;
	Object[][] data;
	
	//Especifica el modelo para construir la tabla
	public ModeloTablaCamiones(Object[][] data, String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data, columnas);
	}
	
	public ModeloTablaCamiones() {
		
	}
	
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
