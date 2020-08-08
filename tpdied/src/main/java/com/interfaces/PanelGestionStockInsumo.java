/**
 * 
 */
package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.controladores.PlantaController;
import com.logica.Insumo;
import com.logica.Planta;
import com.logica.StockInsumo;

/**
 * @author Pichi
 *
 */
public class PanelGestionStockInsumo extends JPanel{
	ArrayList<String> columnasTabla;
	JLabel labelPlanta;
	JLabel labelInsumo;
	JComboBox<String> comboPlanta;
	JComboBox<String> comboInsumo;
	JTable tablaStocks;
	ModeloTablaStocks model;
	JButton botonAtras;
	JButton botonBuscar;
	String[] plantas;
	String[] insumos;
	ArrayList<StockInsumo> listaStocks;
	
	
	public PanelGestionStockInsumo() {
			super();
			inicializarComponentes();
			armarPanel();
		
		
			
		}
	
	
	private void inicializarComponentes() {
		//LABELS
		labelPlanta = new JLabel("Plantas:");
		labelInsumo = new JLabel("Insumos:");
		
		
		//COMBO
		/*
		plantas = (new PlantaController()).buscarNombresPlanta();
		if(plantas == null) {
			plantas = new String[1];
			plantas[0] = "<Ninguna>";
		}
		comboPlanta = new JComboBox<String>(plantas);
		comboPlanta.setPreferredSize(new Dimension(100,20));
		
		insumos = (new InsumoController()).buscarInsumos();
		if(insumos == null) {
			insumos = new String[1];
			insumos[0] = "<Ninguno>";
		}
		comboInsumo = new JComboBox<String>(insumos);
		comboInsumo.setPreferredSize(new Dimension(100,20));
		*/
		
		//TABLE 
		tablaStocks = new JTable();
		//construirTabla(setearColumnas(), obtenerMatrizDatos());
		
		//BUTTONS
		botonBuscar = new JButton("Buscar");
		botonAtras = new JButton("Atras");
		botonAtras.setPreferredSize(new Dimension(100, 30));
		botonAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelPrincipal());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		
		
	}
	
	
	private void armarPanel() {
		this.setLayout(new BorderLayout());
		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setLayout(new GridBagLayout());
		JPanel panelInf = new JPanel();
		panelInf.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		this.add(panelBusqueda, BorderLayout.NORTH);
		this.add(panelInf, BorderLayout.SOUTH);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelBusqueda.add(labelPlanta, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		//panelBusqueda.add(comboPlanta, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelBusqueda.add(labelInsumo, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		//panelBusqueda.add(comboInsumo, gbc);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		panelBusqueda.add(botonBuscar);
		
		panelInf.add(botonAtras);
		this.add(tablaStocks, BorderLayout.CENTER);
		
		
	}
	
	private void construirTabla(String[] columnas, Object[][] data) {
		model = new ModeloTablaStocks(data, columnas);
		tablaStocks.setModel(model);
		
		// ASIGNO TIPO DE DATO A CADA COLUMNA 
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PLANTA).setCellRenderer(new GestionCeldasStocks("texto"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.INSUMO).setCellRenderer(new GestionCeldasStocks("texto"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.STOCK_PLANTA).setCellRenderer(new GestionCeldasStocks("numero"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PUNTO_PEDIDO).setCellRenderer(new GestionCeldasStocks("numero"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.STOCK_TOTAL).setCellRenderer(new GestionCeldasStocks("numero"));
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.MODIFICAR).setCellRenderer(new GestionCeldasStocks("boton"));
		
		tablaStocks.getTableHeader().setReorderingAllowed(false);
		tablaStocks.setRowHeight(25);
		tablaStocks.setGridColor(Color.BLACK);
		
		// TAMAÑO DE CADA COLUMNA
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PLANTA).setPreferredWidth(10);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.INSUMO).setPreferredWidth(20);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.STOCK_PLANTA).setPreferredWidth(10);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.PUNTO_PEDIDO).setPreferredWidth(10);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.STOCK_TOTAL).setPreferredWidth(10);
		tablaStocks.getColumnModel().getColumn(UtilTablaStocks.MODIFICAR).setPreferredWidth(20);
	}
	
	private Object[][] obtenerMatrizDatos(){
		String informacion[][] = new String[listaStocks.size()][columnasTabla.size()];
		
		for(int i=0; i<informacion.length ; i++) {
			informacion[i][UtilTablaStocks.PLANTA] = listaStocks.get(i).getPlanta().getNombre() + "";
			informacion[i][UtilTablaStocks.INSUMO] = listaStocks.get(i).getInsumo().getDescripcion() + "";
			informacion[i][UtilTablaStocks.STOCK_PLANTA] = listaStocks.get(i).getCantidad() + ""; 
			informacion[i][UtilTablaStocks.PUNTO_PEDIDO] = listaStocks.get(i).getPuntoDePedido() + "";
			informacion[i][UtilTablaStocks.STOCK_TOTAL] = 
			informacion[i][UtilTablaStocks.MODIFICAR] = "MODIFICAR";
			
		}
		
		
		return informacion;
	}
	
	private String[] setearColumnas() {
		
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Planta");
		columnasTabla.add("Insumo");
		columnasTabla.add("Stock en la planta");
		columnasTabla.add("Punto de pedido");
		columnasTabla.add("Stock total");
		columnasTabla.add("  ");
		String[] columnas = new String[columnasTabla.size()];
		
		
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		
		}
	
		return columnas;
		
	}
	
}
