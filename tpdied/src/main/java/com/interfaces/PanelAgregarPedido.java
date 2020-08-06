/**
 * 
 */
package com.interfaces;

import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.logica.DetalleItem;
import com.logica.Insumo;
import com.logica.PlantaDAOImplSQL;

/**
 * @author Pichi
 *
 */
public class PanelAgregarPedido extends JPanel{
	JButton botonAceptar;
	JButton botonCancelar;
	JButton botonAgregarItem;
	JLabel labelDestino;
	JLabel labelInsumos;
	ArrayList<DetalleItem> insumosAgregados;
	JComboBox<String> comboPlantas;
	JFormattedTextField fieldFecha;
	JLabel labelFecha;
	JTable tablaItems;
	
	ArrayList<String> columnasTabla;
	ModeloTablaItemsPedido model;
	
	public PanelAgregarPedido() {
		insumosAgregados = new ArrayList<DetalleItem>();
		inicializarComponentes();
		armarPanel();
	}

	private void armarPanel() {
		// TODO Auto-generated method stub
		
	}

	private void inicializarComponentes() {
		// TODO Auto-generated method stub
		//LABELS
		labelDestino = new JLabel("Planta de destino:");
		labelInsumos = new JLabel("Items del pedido:");
		labelFecha = new JLabel("Fecha de entrega máx.:");
		
		
		//COMBO
		String[] plantas;
		plantas = (new PlantaDAOImplSQL()).buscarNombresPlanta();
		if(plantas == null) {
			plantas = new String[1];
			plantas[0] = "<Ninguna>";
		}
		comboPlantas = new JComboBox<String>(plantas);
		
		
		//FIELDS
		fieldFecha = new JFormattedTextField();
		
		//BOTONES
		botonAceptar = new JButton("Aceptar");
		botonCancelar = new JButton("Cancelar");
		botonAgregarItem = new JButton("Agregar item");
		
		//TABLE
		tablaItems =  new JTable();
		construirTabla(setearColumnas(), obtenerMatrizDatos());
		
	}

	private void construirTabla(String[] columnas, Object[][] data) {
		// TODO Auto-generated method stub
		model = new ModeloTablaItemsPedido(data, columnas);
		tablaItems.setModel(model);
		
		//ASIGNO TIPO DE DATOS A CADA COLUMNA
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.NOMBRE_INSUMO).setCellRenderer(new GestionCeldasItemsPedido("texto"));
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.CANTIDAD).setCellRenderer(new GestionCeldasItemsPedido("numero"));
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_UNIDAD).setCellRenderer(new GestionCeldasItemsPedido("numero"));
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_TOTAL).setCellRenderer(new GestionCeldasItemsPedido("numero"));
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.ELIMINAR).setCellRenderer(new GestionCeldasItemsPedido("boton"));
		
				
		tablaItems.getTableHeader().setReorderingAllowed(false);
		tablaItems.setRowHeight(25);
		tablaItems.setGridColor(Color.BLACK);
		
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.NOMBRE_INSUMO).setPreferredWidth(32);
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.CANTIDAD).setPreferredWidth(10);
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_UNIDAD).setPreferredWidth(20);
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_TOTAL).setPreferredWidth(20);
		tablaItems.getColumnModel().getColumn(UtilTablaItemsPedido.ELIMINAR).setPreferredWidth(10);
	}

	private String[] setearColumnas() {
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Insumo");
		columnasTabla.add("Cantidad");
		columnasTabla.add("Precio por unidad");
		columnasTabla.add("Costo total");
		columnasTabla.add("  ");
		String[] columnas = new String[columnasTabla.size()];
		
		
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		
		}
	
		return columnas;

	}

	private Object[][] obtenerMatrizDatos() {
		String informacion[][] = new String[insumosAgregados.size()][columnasTabla.size()];
		
		for(int i=0; i<informacion.length ; i++) {
			informacion[i][UtilTablaItemsPedido.NOMBRE_INSUMO] = insumosAgregados.get(i).getInsumo().getDescripcion() + "";
			informacion[i][UtilTablaItemsPedido.CANTIDAD] = insumosAgregados.get(i).getCantidad() + "";
			informacion[i][UtilTablaItemsPedido.PRECIO_UNIDAD] = insumosAgregados.get(i).getInsumo().getCostoPorUnidad() + "";
			informacion[i][UtilTablaItemsPedido.PRECIO_TOTAL] = (insumosAgregados.get(i).getCantidad() * insumosAgregados.get(i).getInsumo().getCostoPorUnidad())+ "";
			informacion[i][UtilTablaItemsPedido.ELIMINAR] = "ELIMINAR";
		}
		
		
		return informacion;
	}
	
	
	
	
}
