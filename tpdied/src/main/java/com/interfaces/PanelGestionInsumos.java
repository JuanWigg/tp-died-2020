package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PanelGestionInsumos extends JPanel {
	ArrayList<String> columnasTabla;
	JLabel labelDescripcion;
	JLabel labelUnidadDeMedida;
	JLabel labelCosto;
	JLabel labelMedidaUnitaria;

	JTextField fieldDescripcion;
	JTextField fieldUnidadDeMedida;
	JTextField fieldCosto;
	JTextField fieldMedidaUnitaria;
	
	JTable tablaResultados;
	JButton botonAtras;
	JButton botonBuscar;
	JButton botonAgregarInsumo; 
	
	ModeloTablaInsumos modeloI;
	//ArrayList<Insumo> listaInsumos;
	
	public PanelGestionInsumos() {
		super();
		//listaInsumos = (ArrayList<Insumo>) (new InsumosDAOImplSQL()).buscarInsumos();
		inicializarComponentes();
		armarPanel();
	}
	
	 public void inicializarComponentes() {
		//LABELS
		labelDescripcion = new JLabel("Descripcion:");
		labelCosto = new JLabel("Costo:");
		labelUnidadDeMedida = new JLabel("Unidad de medida:");
		labelCosto = new JLabel("Costo:");
		labelMedidaUnitaria = new JLabel("MedidaUnitaria");
		
		//CAMPOS
		fieldDescripcion = new JTextField();
		fieldDescripcion.setPreferredSize(new Dimension(100, 20));
		
		fieldUnidadDeMedida = new JTextField();
		fieldUnidadDeMedida.setPreferredSize(new Dimension(100, 20));
		
		fieldCosto = new JTextField();
		fieldCosto.setPreferredSize(new Dimension(100, 20));
		
		fieldMedidaUnitaria = new JTextField();
		fieldMedidaUnitaria.setPreferredSize(new Dimension(100, 20));
		
		//TABLA

		tablaResultados = new JTable();
		tablaResultados.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int fila = tablaResultados.rowAtPoint(e.getPoint());
				int columna = tablaResultados.columnAtPoint(e.getPoint());
				
			/*	if(columna==UtilTablaInsumos.MODIFICAR) {
					//Logica para modificar
					VentanaModificarCamion dialogo = new VentanaModificarInsumo(modeloI.getDataVector().elementAt(fila), 
							((JTable) e.getSource()), (new JFrame()), true);
					listaInsumos = (ArrayList<Insumos>) (new InsumosDAOImplSQL()).buscarInsumos();
					construirTabla(setearColumnas(), obtenerMatrizDatos());
					
				} else if(columna==UtilTablaCamiones.ELIMINAR) {
					String[] botonesDialogo = {"Aceptar", "Cancelar"};
					int dialogo = JOptionPane.showOptionDialog(null,
							"¿Seguro que desea eliminar el insumo seleccionado?", 
							"Confirmacion",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.QUESTION_MESSAGE, null,
							botonesDialogo, botonesDialogo[0]
							);
					if(dialogo==0) {
						(new InsumoController()).eliminar(tablaResultados.getModel().getValueAt(fila, 0).toString());
						model.removeRow(fila);
						listaInsumos.remove(fila);
						model.fireTableDataChanged();
					}
				}
			}
			*/}
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
		botonAgregarInsumo = new JButton("Agregar nuevo insumo");
		botonAgregarInsumo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*VentanaAgregarInsumo dialogo = new VentanaAgregarInsumo(new JFrame(), true);
				listaInsumos = (ArrayList<Insumo>) (new InsumosDAOImplSQL()).buscarInsumos();
				construirTabla(setearColumnas(), obtenerMatrizDatos());*/
			}
			
		});
		botonBuscar = new JButton("Buscar");
		botonBuscar.setPreferredSize(new Dimension(100, 20));
		/*botonBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ArrayList<Predicate<Insumo>> predicados = new ArrayList<Predicate<Insumo>>();
				if(fieldDescripcion.getText().trim().length()!=0)
					predicados.add(i-> i.getDescripcion().equals(fieldDescripcion.getText().trim()));
				if(fieldUnidadDeMedida.getText().trim().length()!=0)
					predicados.add(i -> i.getUnidadDeMedida().equals(fieldUnidadDeMedida.getText().trim()));
				if(fieldCosto.getText().trim().length()!=0)
					predicados.add(i -> i.getCosto().equals(Integer.parseInt(fieldCosto.getText().trim())));
				if(fieldMedidaUnitaria.getText().trim().length()!=0)
					predicados.add(i -> i.getMedidaU().equals(fieldMedidaUnitaria.getText().trim()));
				
				if(predicados.size()==0) {
					listaInsumos = (ArrayList<Insumo>) (new InsumosDAOImplSQL()).buscarInsumos();
					construirTabla(setearColumnas(), obtenerMatrizDatos());
				}
				else {
					listaInsumos = (ArrayList<Insumo>) (new InsumosDAOImplSQL()).buscarInsumos();
					ArrayList<Insumo> result = (ArrayList<Insumo>) listaInsumos.stream()
				          .filter(predicados.stream().reduce(x->true, Predicate::and))
				          .collect(Collectors.toList());
					listaInsumos = result;
					construirTabla(setearColumnas(), obtenerMatrizDatos());
				}
			}
			
		});*/
	} 
	
	 public void construirTabla(String[] columnas, Object[][] data) {
				 modeloI = new ModeloTablaInsumos(data, columnas);
				tablaResultados.setModel(modeloI);
				
				//ASIGNO TIPO DE DATOS A CADA COLUMNA
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.DESCRIPCION).setCellRenderer(new GestionCeldasCamiones("texto"));
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.UNIDAD_DE_MEDIDA).setCellRenderer(new GestionCeldasCamiones("numero"));
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.COSTO).setCellRenderer(new GestionCeldasCamiones("numero"));
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.PESODENSIDAD).setCellRenderer(new GestionCeldasCamiones("numero"));
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.MODIFICAR).setCellRenderer(new GestionCeldasCamiones("boton"));
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.ELIMINAR).setCellRenderer(new GestionCeldasCamiones("boton"));
				
				tablaResultados.getTableHeader().setReorderingAllowed(false);
				tablaResultados.setRowHeight(25);
				tablaResultados.setGridColor(Color.BLACK);
				
				// TAMAÑO DE CADA COLUMNA
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.DESCRIPCION).setPreferredWidth(10);
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.UNIDAD_DE_MEDIDA).setPreferredWidth(15);
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.COSTO).setPreferredWidth(20);
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.PESODENSIDAD).setPreferredWidth(20);
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.MODIFICAR).setPreferredWidth(5);
				tablaResultados.getColumnModel().getColumn(UtilTablaInsumos.ELIMINAR).setPreferredWidth(5);
				
			}
	 
	 private Object[][] obtenerMatrizDatos(){
		/*	String informacion[][] = new String[listaInsumos.size()][columnasTabla.size()];
			for(int i=0; i<informacion.length ; i++) {
				informacion[i][UtilTablaInsumos.DESCRIPCION] = listaInsumos.get(i).getDescripcion() + "";
				informacion[i][UtilTablaInsumos.UNIDAD_DE_MEDIDA] = listaInsumos.get(i).getUMedida() + "";
				informacion[i][UtilTablaCamiones.COSTO] = listaInsumos.get(i).getCosto() + "";
				informacion[i][UtilTablaCamiones.PESODENSIDAD] = listaInsumos.get(i).getPesoDensidad() + "";
				informacion[i][UtilTablaCamiones.MODIFICAR] = "MODIFICAR";
				informacion[i][UtilTablaCamiones.ELIMINAR] = "ELIMINAR";
			}
			return informacion;*/
		 return null;
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
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelBusqueda.add(labelCosto, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		panelBusqueda.add(fieldCosto, gbc);
		gbc.gridx = 4;
		gbc.gridy = 0;
		panelBusqueda.add(labelUnidadDeMedida, gbc);
		gbc.gridx = 5;
		gbc.gridy = 0;
		panelBusqueda.add(fieldUnidadDeMedida, gbc);
		gbc.gridx = 6;
		gbc.gridy = 0;
		panelBusqueda.add(labelMedidaUnitaria, gbc);
		gbc.gridx = 7;
		gbc.gridy = 0;
		panelBusqueda.add(fieldMedidaUnitaria, gbc);
		
		gbc.gridx = 6;
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
		columnasTabla.add("Medida Unitaria");
		columnasTabla.add("Unidad De Medida");
		columnasTabla.add("  ");
		columnasTabla.add("  ");
		String[] columnas = new String[columnasTabla.size()];
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		
		}
	
		return columnas;
		
	}
	
}
