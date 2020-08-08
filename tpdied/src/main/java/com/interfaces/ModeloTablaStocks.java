/**
 * 
 */
package com.interfaces;

import javax.swing.table.DefaultTableModel;

/**
 * @author Pichi
 *
 */
public class ModeloTablaStocks extends DefaultTableModel{
	String[] columnas;
	Object[][] data;
	
	//Especifica el modelo para construir la tabla
	public ModeloTablaStocks(Object[][] data, String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data, columnas);
	}
	
	public ModeloTablaStocks() {
		
	}
	
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
