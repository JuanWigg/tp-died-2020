package com.interfaces;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.logica.EstadoOrden;
import com.logica.OrdenPedido;
import com.logica.OrdenPedidoDAOImplSQL;

public class PanelOrdenesProcesadas extends JPanel{
	ArrayList<String> columnasTabla;
	JButton botonAtras;
	JButton verDetalles;
	JButton entregarOrden;
	JTable tablaOrdenes;
	OrdenPedido ordenPedido;
	ModeloTablaOrdenes model;
	ArrayList<OrdenPedido> ordenes;
	public PanelOrdenesProcesadas() {
		super();
		ordenes = (ArrayList<OrdenPedido>) (new OrdenPedidoDAOImplSQL()).readAllWithStatus(EstadoOrden.PROCESADA);
		inicializarComponentes();
		armarPanel();
	}
	
	
	public void inicializarComponentes() {
		botonAtras=new JButton("Atrás");
		verDetalles=new JButton("Ver detalles de orden");
		entregarOrden=new JButton("Marcar orden como Entregada");
		
		botonAtras.setPreferredSize(new Dimension(100,30));
		botonAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelGestionOrdenes());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		verDetalles.setPreferredSize(new Dimension(200, 50));
		verDetalles.setEnabled(false);
		verDetalles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaVerDetallesOrden dialogo = new VentanaVerDetallesOrden(ordenPedido, new JFrame(), true);
			}
			
		});
		entregarOrden.setPreferredSize(new Dimension(200, 50));
		entregarOrden.setEnabled(false);
		
		tablaOrdenes= new JTable();
		tablaOrdenes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(tablaOrdenes.getSelectedRow()!=-1) {
					verDetalles.setEnabled(true);
					entregarOrden.setEnabled(true);
					ordenPedido = ordenes.get(tablaOrdenes.getSelectedRow());
				}
				else {
					ordenPedido = null;
					verDetalles.setEnabled(false);
					entregarOrden.setEnabled(false);
				}
					
			}
			 
		 });
		entregarOrden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				OrdenPedido nuevaOrden = new OrdenPedido(ordenPedido.getNroOrden(), ordenPedido.getFechaSolicitud(),
						ordenPedido.getFechaEntrega(), EstadoOrden.ENTREGADA, ordenPedido.getDetalleEnvio(), ordenPedido.getDetalleItems()
						, ordenPedido.getPlantaDestino());
				(new OrdenPedidoDAOImplSQL()).update(nuevaOrden, ordenPedido);;
				JOptionPane.showMessageDialog(new JFrame(), "Orden marcada como entregada");
				ordenes.remove(tablaOrdenes.getSelectedRow());
				construirTabla(setearColumnas(), obtenerMatrizDatos());
			}
			
		});
		construirTabla(setearColumnas(), obtenerMatrizDatos());
		
		
	}
	public void armarPanel() {
		this.setLayout(new BorderLayout());
		JPanel panelBotones = new JPanel();
		JPanel panelInf = new JPanel();
		
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 0));
		panelBotones.setLayout(new GridBagLayout());
		
		this.add(panelBotones, BorderLayout.EAST);
		this.add(panelInf,BorderLayout.SOUTH);
		GridBagConstraints gbc = new GridBagConstraints();
		this.setPreferredSize(new Dimension(50,300));
		
		gbc.insets=new Insets(10,10,10,10);
		gbc.gridx=0;
		gbc.gridy=0;
		this.add(new JScrollPane(tablaOrdenes),BorderLayout.CENTER);
		gbc.gridx=2;
		gbc.gridy=0;
		gbc.weighty=0.0;
		gbc.anchor=GridBagConstraints.NORTH;
		gbc.fill=GridBagConstraints.NONE;
		panelBotones.add(verDetalles,gbc);
		gbc.gridx=2;
		gbc.gridy=1;
		gbc.weighty=1.0;
		panelBotones.add(entregarOrden,gbc);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.WEST;
		gbc.anchor=GridBagConstraints.WEST;
		panelInf.add(botonAtras,gbc);
			
	}

	public void construirTabla(String[] columnas, Object[][] data) {
		 model = new ModeloTablaOrdenes(data, columnas);
		 tablaOrdenes.setModel(model);
		
		//ASIGNO TIPO DE DATOS A CADA COLUMNA
		 tablaOrdenes.getColumnModel().getColumn(0).setCellRenderer(new GestionCeldasOrdenes("numero"));
		 tablaOrdenes.getColumnModel().getColumn(1).setCellRenderer(new GestionCeldasOrdenes("numero"));
		
		
		 tablaOrdenes.getTableHeader().setReorderingAllowed(false);
		 tablaOrdenes.setRowHeight(25);
		 tablaOrdenes.setGridColor(Color.BLACK);
		
		// TAMAÑO DE CADA COLUMNA
		 tablaOrdenes.getColumnModel().getColumn(0).setPreferredWidth(10);
		 tablaOrdenes.getColumnModel().getColumn(1).setPreferredWidth(15);
		
	}
	
	private Object[][] obtenerMatrizDatos(){
		String informacion[][] = new String[ordenes.size()][columnasTabla.size()];
		
		for(int i=0; i<informacion.length ; i++) {
			informacion[i][0] = ordenes.get(i).getNroOrden() + "";
			informacion[i][1] = ordenes.get(i).getPlantaDestino().getNombre() + "";

		}
		
		
		return informacion;
		
	}
	
	
	
	private String[] setearColumnas() {
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Id de Orden");
		columnasTabla.add("Planta de Destino");
		
		String[] columnas = new String[columnasTabla.size()];
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		}
		return columnas;
		
	}
	
}
