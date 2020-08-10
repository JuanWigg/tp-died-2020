package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.controladores.InsumoController;
import com.logica.Camion;
import com.logica.CamionDAOImplSQL;
import com.logica.Insumo;
import com.logica.InsumoDAO;
import com.logica.InsumoDAOImplSQL;

import kotlin.Pair;

public class PanelGestionInsumos extends JPanel {
	ArrayList<String> columnasTabla;
	JLabel labelDescripcion;

	JTextField fieldDescripcion;
	
	JTable tablaResultados;
	JButton botonAtras;
	JButton botonBuscar;
	JButton botonAgregarInsumo; 
	
	ModeloTablaInsumos modeloI;
	ArrayList<Pair<Insumo, Integer>> listaInsumosStock;
	
	public PanelGestionInsumos() {
		super();
		listaInsumosStock = (ArrayList<Pair<Insumo, Integer>>) (new InsumoDAOImplSQL()).readAllWithStock();
		inicializarComponentes();
		armarPanel();
	}
	
	 public void inicializarComponentes() {
		//LABELS
		labelDescripcion = new JLabel("Descripcion:");
		
		//CAMPOS
		fieldDescripcion = new JTextField();
		fieldDescripcion.setPreferredSize(new Dimension(100, 20));
		
		
		//TABLA

		tablaResultados = new JTable();
		tablaResultados.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int fila = tablaResultados.rowAtPoint(e.getPoint());
				int columna = tablaResultados.columnAtPoint(e.getPoint());
				
				if(columna==UtilTablaInsumos.MODIFICAR) {
					//Logica para modificar
					VentanaModificarInsumo dialogo = new VentanaModificarInsumo(listaInsumosStock.get(fila).getFirst(), new JFrame(), true);
					listaInsumosStock = (ArrayList<Pair<Insumo, Integer>>) (new InsumoDAOImplSQL()).readAllWithStock();
					construirTabla(setearColumnas(), obtenerMatrizDatos());
					
				} else if(columna==UtilTablaInsumos.ELIMINAR) {
					String[] botonesDialogo = {"Aceptar", "Cancelar"};
					int dialogo = JOptionPane.showOptionDialog(null,
							"¿Seguro que desea eliminar el insumo seleccionado?", 
							"Confirmacion",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.QUESTION_MESSAGE, null,
							botonesDialogo, botonesDialogo[0]
					);
					if(dialogo==0) {
						new InsumoController().delete(listaInsumosStock.get(fila).getFirst());
						modeloI.removeRow(fila);
						listaInsumosStock.remove(fila);
						modeloI.fireTableDataChanged();
					}
				}
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
		
		//BOTONES
		botonAtras = new JButton("Atrás");
		botonAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelPrincipal());
				ventana.revalidate();
				ventana.repaint();
			}
		});
		botonAgregarInsumo = new JButton("Agregar nuevo insumo");
		botonAgregarInsumo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				VentanaAgregarInsumo dialogo = new VentanaAgregarInsumo(new JFrame(), true);
				listaInsumosStock = (ArrayList<Pair<Insumo, Integer>>) (new InsumoDAOImplSQL()).readAllWithStock();
				construirTabla(setearColumnas(), obtenerMatrizDatos());
				
			}
			
		});
		botonBuscar = new JButton("Buscar");
		botonBuscar.setPreferredSize(new Dimension(100, 20));
		botonBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				construirTabla(setearColumnas(), obtenerMatrizDatosFiltrada());
			}
				
		});
	}
	 
	
	 public void construirTabla(String[] columnas, Object[][] data) {
				 modeloI = new ModeloTablaInsumos(data, columnas);
				tablaResultados.setModel(modeloI);
				
				//ASIGNO TIPO DE DATOS A CADA COLUMNA
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.DESCRIPCION).setCellRenderer(new GestionCeldas("texto"));
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.UNIDAD_DE_MEDIDA).setCellRenderer(new GestionCeldas("numero"));
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.COSTO).setCellRenderer(new GestionCeldasCamiones("numero"));
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.PESODENSIDAD).setCellRenderer(new GestionCeldas("numero"));
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.STOCK_TOTAL).setCellRenderer(new GestionCeldas("numero"));
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.MODIFICAR).setCellRenderer(new GestionCeldas("boton"));
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.ELIMINAR).setCellRenderer(new GestionCeldas("boton"));
				
				tablaResultados.getTableHeader().setReorderingAllowed(false);
				tablaResultados.setRowHeight(25);
				tablaResultados.setGridColor(Color.BLACK);
				
				// TAMAÑO DE CADA COLUMNA
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.DESCRIPCION).setPreferredWidth(10);
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.UNIDAD_DE_MEDIDA).setPreferredWidth(10);
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.COSTO).setPreferredWidth(20);
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.PESODENSIDAD).setPreferredWidth(20);
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.STOCK_TOTAL).setPreferredWidth(20);
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.MODIFICAR).setPreferredWidth(5);
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.ELIMINAR).setPreferredWidth(5);
				
			}
	 
	 private Object[][] obtenerMatrizDatos(){
		 	InsumoController IC = new InsumoController();
		 	List<Pair<Insumo, Integer>> listaInsumosStock =  IC.consultarInsumosStock();
			String informacion[][] = new String[listaInsumosStock.size()][columnasTabla.size()];
			for(int i=0; i<informacion.length ; i++) {
				informacion[i][UtilTablaInsumos.DESCRIPCION] = listaInsumosStock.get(i).getFirst().getDescripcion() + "";
				informacion[i][UtilTablaInsumos.UNIDAD_DE_MEDIDA] = listaInsumosStock.get(i).getFirst().getUnidad() + "";
				informacion[i][UtilTablaInsumos.COSTO] = listaInsumosStock.get(i).getFirst().getCostoPorUnidad() + "";
				informacion[i][UtilTablaInsumos.PESODENSIDAD] = listaInsumosStock.get(i).getFirst().getCantidadPorUnidad() + "";
				informacion[i][UtilTablaInsumos.STOCK_TOTAL] = listaInsumosStock.get(i).getSecond().intValue() + "";
				informacion[i][UtilTablaInsumos.MODIFICAR] = "MODIFICAR";
				informacion[i][UtilTablaInsumos.ELIMINAR] = "ELIMINAR";
			}
			return informacion;

		}
	 
	 private Object[][] obtenerMatrizDatosFiltrada(){
		 	InsumoController IC = new InsumoController();
		 	List<Pair<Insumo, Integer>> listaInsumosStock =  IC.consultarInsumosStockFiltrado(fieldDescripcion.getText());
			String informacion[][] = new String[listaInsumosStock.size()][columnasTabla.size()];
			for(int i=0; i<informacion.length ; i++) {
				informacion[i][UtilTablaInsumos.DESCRIPCION] = listaInsumosStock.get(i).getFirst().getDescripcion() + "";
				informacion[i][UtilTablaInsumos.UNIDAD_DE_MEDIDA] = listaInsumosStock.get(i).getFirst().getUnidad() + "";
				informacion[i][UtilTablaInsumos.COSTO] = listaInsumosStock.get(i).getFirst().getCostoPorUnidad() + "";
				informacion[i][UtilTablaInsumos.PESODENSIDAD] = listaInsumosStock.get(i).getFirst().getCantidadPorUnidad() + "";
				informacion[i][UtilTablaInsumos.STOCK_TOTAL] = listaInsumosStock.get(i).getSecond().intValue() + "";
				informacion[i][UtilTablaInsumos.MODIFICAR] = "MODIFICAR";
				informacion[i][UtilTablaInsumos.ELIMINAR] = "ELIMINAR";
			}
			return informacion;

		}
	 
	public void armarPanel() {
		this.setLayout(new BorderLayout());

		JPanel panelBusqueda = new JPanel();

		JPanel panelAbajo = new JPanel();
		
		panelBusqueda.setLayout(new GridBagLayout());
		panelAbajo.setLayout(new GridBagLayout());
		
		this.add(panelBusqueda, BorderLayout.NORTH);

		this.add(panelAbajo, BorderLayout.SOUTH);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelBusqueda.add(labelDescripcion, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelBusqueda.add(fieldDescripcion, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelBusqueda.add(botonBuscar, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(new JScrollPane(tablaResultados), BorderLayout.CENTER);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 20;
		gbc.ipady = 20;
		panelAbajo.add(botonAtras, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelAbajo.add(botonAgregarInsumo, gbc);
		
	}
	
	
private String[] setearColumnas() {
		
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Descripcion");
		columnasTabla.add("Costo");
		columnasTabla.add("Peso por Unidad (KG)");
		columnasTabla.add("Unidad De Medida");
		columnasTabla.add("Stock Total");
		columnasTabla.add("  ");
		columnasTabla.add("  ");
		String[] columnas = new String[columnasTabla.size()];
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		
		}
	
		return columnas;
		
	}
	
}
