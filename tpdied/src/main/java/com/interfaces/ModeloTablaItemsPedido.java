/**
 * 
 */
package com.interfaces;

import javax.swing.table.DefaultTableModel;

/**
 * @author Pichi
 *
 */
public class ModeloTablaItemsPedido extends DefaultTableModel{
	String[] columnas;
	Object[][] data;
	
	//Especifica el modelo para construir la tabla
	public ModeloTablaItemsPedido(Object[][] data, String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data, columnas);
	}
	
	public ModeloTablaItemsPedido() {
		
	}
	
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
