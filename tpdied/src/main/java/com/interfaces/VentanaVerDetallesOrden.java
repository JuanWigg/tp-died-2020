/**
 * 
 */
package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.logica.DetalleItem;
import com.logica.EstadoOrden;
import com.logica.Insumo;
import com.logica.OrdenPedido;

/**
 * @author Pichi
 *
 */
public class VentanaVerDetallesOrden extends JDialog {

	private OrdenPedido ordenPedido;
	JPanel panel;
	JLabel labelNroOrden;
	JLabel labelItems;
	JLabel labelDetallesEnvio;
	JTable tableItems;
	JSplitPane splitEnvio;
	JList<String> itemsEnvio;
	JTextArea areaEnvio;
	JButton botonAceptar;
	ArrayList<String> columnasTabla;
	private ModeloTablaItemsPedido model;
	

	public VentanaVerDetallesOrden(OrdenPedido ordenPedido, JFrame padre, boolean modal) {
		super();
		this.ordenPedido = ordenPedido;
		inicializarComponentes();
		armarPanel();
		
		this.setContentPane(panel);
		this.setSize(1024,720);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private void armarPanel() {
		// TODO Auto-generated method stub
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel panelSuperior = new JPanel();
		JPanel panelCentral = new JPanel();
		JPanel panelInferior = new JPanel();
		panelSuperior.setLayout(new GridBagLayout()); 
		panelCentral.setLayout(new GridBagLayout()); 
		panelInferior.setLayout(new GridBagLayout()); 
		panel.add(panelSuperior, BorderLayout.NORTH);
		panel.add(panelInferior, BorderLayout.SOUTH);
		panel.add(panelCentral, BorderLayout.CENTER);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 0;
		gbc.ipady = 10;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelSuperior.add(labelNroOrden, gbc);
		
		gbc.weighty = 0.05;
		panelCentral.add(labelItems, gbc);
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 1;
		JScrollPane pane = new JScrollPane(tableItems);
		pane.setMinimumSize(new Dimension(400, 200));
		pane.setMaximumSize(new Dimension(400,200));
		tableItems.setMinimumSize(new Dimension(400,200));
		tableItems.setMaximumSize(new Dimension(400,200));
		panelCentral.add(pane, gbc);
		
		if(ordenPedido.getEstado()!=EstadoOrden.CREADA) {
		gbc.ipadx = 0;
		gbc.ipady = 10;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty = 0.05;
		panelCentral.add(labelDetallesEnvio, gbc);
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weighty = 1;
		panelCentral.add(splitEnvio, gbc);
		}
		gbc.ipadx = 0;
		gbc.ipady = 100;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0;
		panelInferior.add(botonAceptar);		
		
	}

	private void inicializarComponentes() {
		// TODO Auto-generated method stub
		labelNroOrden = new JLabel("Numero de orden: " + ordenPedido.getNroOrden());
		labelItems = new JLabel("Items de la orden:");
		labelDetallesEnvio = new JLabel("Detalles del envio:");
		
		
		tableItems = new JTable();
		tableItems.setPreferredSize(new Dimension(200, 200));
		tableItems.setMinimumSize(new Dimension(200, 200));
		construirTabla(setearColumnas(), obtenerMatrizDatos());
		if(ordenPedido.getEstado()!=EstadoOrden.CREADA) {
		areaEnvio = new JTextArea();
		areaEnvio.setEditable(false);
		String[] lista = {"ID de Envio", "Ruta asignada", "Camion asignado", "Costo"};
		itemsEnvio = new JList<String>(lista);
		itemsEnvio.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				switch(itemsEnvio.getSelectedIndex()) {
				case 0:
					areaEnvio.setText(ordenPedido.getDetalleEnvio().getId() + "");
					break;
				case 1:
					areaEnvio.setText("ID de Ruta: " + ordenPedido.getDetalleEnvio().getRuta().getId() + "\n"
							+ "Planta de Origen: " + ordenPedido.getDetalleEnvio().getRuta().getListaPlantasRuta().get(0).getNombre() + "\n"
							+ "Planta de Destino: " + ordenPedido.getPlantaDestino() + "\n");
					break;
				case 2:
					areaEnvio.setText("Patente del camion: " + ordenPedido.getDetalleEnvio().getCamion().getPatente() + "\n"
							+ "Modelo: " + ordenPedido.getDetalleEnvio().getCamion().getModelo().getNombre() + "\n"
							+ "Marca: " + ordenPedido.getDetalleEnvio().getCamion().getModelo().getMarca().getNombre() + "\n");
					break;
				case 3:
					areaEnvio.setText("Costo del viaje: " + ordenPedido.getDetalleEnvio().getCosto());
					break;
				}
				
			}
			
		});
		
		splitEnvio = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(itemsEnvio), areaEnvio);
		splitEnvio.setPreferredSize(new Dimension(600,300));
		splitEnvio.setMinimumSize(new Dimension(600,200));
		
		}
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setMinimumSize(new Dimension(100, 30));
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(panel);
				frame.dispose();
			}
			
		});
	}

	private void construirTabla(String[] columnas, Object[][] data) {
		model = new ModeloTablaItemsPedido(data, columnas);
		tableItems.setModel(model);
		
		//ASIGNO TIPO DE DATOS A CADA COLUMNA
		tableItems.getColumnModel().getColumn(UtilTablaItemsPedido.NOMBRE_INSUMO).setCellRenderer(new GestionCeldasItemsPedido("texto"));
		tableItems.getColumnModel().getColumn(UtilTablaItemsPedido.CANTIDAD).setCellRenderer(new GestionCeldasItemsPedido("numero"));
		tableItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_UNIDAD).setCellRenderer(new GestionCeldasItemsPedido("numero"));
		tableItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_TOTAL).setCellRenderer(new GestionCeldasItemsPedido("numero"));
		
				
		tableItems.getTableHeader().setReorderingAllowed(false);
		tableItems.setRowHeight(25);
		tableItems.setGridColor(Color.BLACK);
		
		tableItems.getColumnModel().getColumn(UtilTablaItemsPedido.NOMBRE_INSUMO).setPreferredWidth(32);
		tableItems.getColumnModel().getColumn(UtilTablaItemsPedido.CANTIDAD).setPreferredWidth(10);
		tableItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_UNIDAD).setPreferredWidth(20);
		tableItems.getColumnModel().getColumn(UtilTablaItemsPedido.PRECIO_TOTAL).setPreferredWidth(20);
		
	}

	private Object[][] obtenerMatrizDatos() {
		ArrayList<DetalleItem> insumosAgregados = (ArrayList<DetalleItem>) (ordenPedido.getDetalleItems());
		String informacion[][] = new String[insumosAgregados.size()][columnasTabla.size()];
		
		for(int i=0; i<informacion.length ; i++) {
			informacion[i][UtilTablaItemsPedido.NOMBRE_INSUMO] = insumosAgregados.get(i).getInsumo().getDescripcion() + "";
			informacion[i][UtilTablaItemsPedido.CANTIDAD] = insumosAgregados.get(i).getCantidad() + "";
			informacion[i][UtilTablaItemsPedido.PRECIO_UNIDAD] = insumosAgregados.get(i).getInsumo().getCostoPorUnidad() + "";
			informacion[i][UtilTablaItemsPedido.PRECIO_TOTAL] = (insumosAgregados.get(i).getCantidad() * insumosAgregados.get(i).getInsumo().getCostoPorUnidad())+ "";
		}
		
		
		return informacion;
	}

	private String[] setearColumnas() {
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Insumo");
		columnasTabla.add("Cantidad");
		columnasTabla.add("Precio por unidad");
		columnasTabla.add("Costo total");
		String[] columnas = new String[columnasTabla.size()];
		
		
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		
		}
	
		return columnas;
	}
}
