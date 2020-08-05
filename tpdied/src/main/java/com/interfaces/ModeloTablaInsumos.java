package com.interfaces;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaInsumos extends DefaultTableModel {
	String[] columnas;
	Object[][] data;
	
	//Especifica el modelo para construir la tabla
	public ModeloTablaInsumos(Object[][] data, String[] columnas) {
		super();
		this.data = data;
		this.columnas = columnas;
		setDataVector(data, columnas);
	}
	
	public ModeloTablaInsumos() {
		
	}
	
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
