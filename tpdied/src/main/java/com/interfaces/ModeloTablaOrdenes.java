package com.interfaces;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaOrdenes extends DefaultTableModel {
	
	String[] columnas;
	Object[][] data;
	
	public ModeloTablaOrdenes(Object[][] data, String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data,columnas);
	}
	
	public ModeloTablaOrdenes() {
		
	}
	
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	
}
