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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.*;

import com.controladores.OrdenPedidoController;
import com.controladores.StockInsumoController;
import com.logica.Camion;
import com.logica.CamionDAOImplSQL;
import com.logica.OrdenPedido;
import com.logica.Planta;

public class PanelOrdenesCreadas extends JPanel{
	ArrayList<String> columnasTabla;
	JButton botonAtras;
	JButton verDetalles;
	JButton procesarOrden;
	JTable tablaOrdenes;
	
	ModeloTablaOrdenes model;
	//ArrayList<OrdenPedido> listaOrdenes;
	ArrayList<OrdenPedido> listaOrdenes;
	public PanelOrdenesCreadas() {
		super();
		listaOrdenes = (new OrdenPedidoController()).getPedidosCreados();
		inicializarComponentes();
		armarPanel();
	}
	
	
	public void inicializarComponentes() {
		botonAtras=new JButton("Atrás");
		verDetalles=new JButton("Ver detalles de orden");
		procesarOrden=new JButton("Procesar Orden");
		
		botonAtras.setPreferredSize(new Dimension(100,30));
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelGestionOrdenes());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		verDetalles.setPreferredSize(new Dimension(200, 50));
		verDetalles.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					//Ventana ver detalles orden
					// TODO INICIALIZAR LISTA ORDENES
					construirTabla(setearColumnas(), obtenerMatrizDatos());
				}

			
			
		});
		
		procesarOrden.setPreferredSize(new Dimension(200, 50));
		procesarOrden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = tablaOrdenes.getSelectedRow();
				VentanaProcesarOrden dialogo = new VentanaProcesarOrden(listaOrdenes.get(fila), new JFrame(), true);
				//listaOrdenes = (new OrdenPedidoController()).getPedidosCreados();
				//construirTabla(setearColumnas(),obtenerMatrizDatos());
				}
			
		});
		
		tablaOrdenes= new JTable();
		tablaOrdenes.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int fila = tablaOrdenes.rowAtPoint(e.getPoint());
				int columna = tablaOrdenes.columnAtPoint(e.getPoint());
				}
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
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
		panelInf.setLayout(new FlowLayout(FlowLayout.LEFT));
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
		panelBotones.add(procesarOrden,gbc);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.WEST;
		gbc.anchor=GridBagConstraints.WEST;
		panelInf.add(botonAtras);
			
	}
	private Object[][] obtenerMatrizDatos() {
		String informacion[][] = new String[listaOrdenes.size()][columnasTabla.size()];
		
		for(int i=0; i<informacion.length ; i++) {
			informacion[i][0] = listaOrdenes.get(i).getNroOrden() + "";
			informacion[i][1] = listaOrdenes.get(i).getPlantaDestino().getNombre() + "";

		}
		
		
		return informacion;
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
