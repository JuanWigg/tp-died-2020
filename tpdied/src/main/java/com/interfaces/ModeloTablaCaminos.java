package com.interfaces;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaCaminos extends DefaultTableModel {
	String[] columnas;
	Object[][] data;
	
	public ModeloTablaCaminos(Object[][] data, String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data, columnas);
	}
	
	public ModeloTablaCaminos() {
		
	}
	
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
