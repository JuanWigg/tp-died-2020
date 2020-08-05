/**
 * 
 */
package com.interfaces;

import javax.swing.table.DefaultTableModel;

/**
 * @author Pichi
 *
 */
public class ModeloTablaTramos  extends DefaultTableModel{
	String[] columnas;
	Object[][] data;
	
	public ModeloTablaTramos(Object[][] data, String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data, columnas);
	}
	
	public ModeloTablaTramos() {
		
	}
	
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
