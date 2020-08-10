package com.interfaces;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaPageRank extends DefaultTableModel{
	String[] columnas;
	Object[][] data;
	
	public ModeloTablaPageRank(Object[][] data, String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data, columnas);
	}
	
	public ModeloTablaPageRank() {
		
	}
	
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
