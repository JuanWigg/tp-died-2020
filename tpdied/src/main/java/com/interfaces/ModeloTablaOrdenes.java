package com.interfaces;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaOrdenes extends DefaultTableModel {
	
	String[] columnas;
	Object[][] data;
	
	public ModeloTablaOrdenes(String[] columnas) {
		super();
		Object[][] a={{1,2},{3,4}};
		data = a;
		this.columnas = columnas;
		setDataVector(data,columnas);
	}
	
	public ModeloTablaOrdenes() {
		
	}
	
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	
}
