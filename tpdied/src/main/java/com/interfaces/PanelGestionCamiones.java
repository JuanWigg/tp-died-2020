/**
 * 
 */
package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.controladores.CamionController;
import com.logica.Camion;
import com.logica.CamionDAOImplSQL;

/**
 * @author Pichi
 *
 */
public class PanelGestionCamiones extends JPanel{
	ArrayList<String> columnasTabla;
	JLabel labelPatente;
	JLabel labelKmReco;
	JLabel labelCostoKm;
	JLabel labelCostoHora;
	JLabel labelFechaCompra;
	JLabel labelModelo;
	JLabel labelMarca;
	JTextField fieldPatente;
	JTextField fieldKmReco;
	JTextField fieldCostoKm;
	JTextField fieldCostoHora;
	JFormattedTextField fieldFechaCompra;
	JTextField fieldModelo;
	JTextField fieldMarca;
	JTable tablaResultados;
	JButton botonAtras;
	JButton botonBuscar;
	JButton botonAgregarCamion; 
	
	ModeloTablaCamiones model;
	ArrayList<Camion> listaCamiones;
	 
	public PanelGestionCamiones() {
		super();
		listaCamiones = (ArrayList<Camion>) (new CamionDAOImplSQL()).buscarCamiones();
		inicializarComponentes();
		armarPanel();
	}
	
	
	public void inicializarComponentes() {
		//LABELS
		labelPatente = new JLabel("Patente:");
		labelKmReco = new JLabel("Kilometros recorridos:");
		labelCostoKm = new JLabel("Costo por kilometro:");
		labelCostoHora = new JLabel("Costo por hora:");
		labelFechaCompra = new JLabel("Fecha de compra:");
		labelModelo = new JLabel("Modelo:");
		labelMarca = new JLabel("Marca:");
		
		//CAMPOS
		fieldPatente = new JTextField();
		fieldPatente.setPreferredSize(new Dimension(100, 20));
		
		fieldKmReco = new JTextField();
		fieldKmReco.setPreferredSize(new Dimension(100, 20));
		
		fieldCostoKm = new JTextField();
		fieldCostoKm.setPreferredSize(new Dimension(100, 20));
		
		fieldCostoHora = new JTextField();
		fieldCostoHora.setPreferredSize(new Dimension(100, 20));
		
		fieldFechaCompra = new JFormattedTextField();
		fieldFechaCompra.setPreferredSize(new Dimension(100, 20));
		
		fieldModelo = new JTextField();
		fieldModelo.setPreferredSize(new Dimension(100, 20));
		
		fieldMarca = new JTextField();
		fieldMarca.setPreferredSize(new Dimension(100, 20));
		
		//TABLA

		tablaResultados = new JTable();
		tablaResultados.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int fila = tablaResultados.rowAtPoint(e.getPoint());
				int columna = tablaResultados.columnAtPoint(e.getPoint());
				
				if(columna==UtilTablaCamiones.MODIFICAR) {
					//Logica para modificar
					VentanaModificarCamion dialogo = new VentanaModificarCamion(model.getDataVector().elementAt(fila), 
							((JTable) e.getSource()), (new JFrame()), true);
					listaCamiones = (ArrayList<Camion>) (new CamionDAOImplSQL()).buscarCamiones();
					construirTabla(setearColumnas(), obtenerMatrizDatos());
					
					
					
					
					
				} else if(columna==UtilTablaCamiones.ELIMINAR) {
					String[] botonesDialogo = {"Aceptar", "Cancelar"};
					int dialogo = JOptionPane.showOptionDialog(null,
							"¿Seguro que desea eliminar el camion seleccionado?", 
							"Confirmacion",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.QUESTION_MESSAGE, null,
							botonesDialogo, botonesDialogo[0]
							);
					if(dialogo==0) {
						(new CamionController()).eliminar(tablaResultados.getModel().getValueAt(fila, 0).toString());
						model.removeRow(fila);
						listaCamiones.remove(fila);
						model.fireTableDataChanged();
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
				// TODO Auto-generated method stub
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelPrincipal());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		botonAgregarCamion = new JButton("Agregar nuevo camión");
		botonAgregarCamion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaAgregarCamion dialogo = new VentanaAgregarCamion(new JFrame(), true);
				listaCamiones = (ArrayList<Camion>) (new CamionDAOImplSQL()).buscarCamiones();
				construirTabla(setearColumnas(), obtenerMatrizDatos());
			}
			
		});
		botonBuscar = new JButton("Buscar");
		botonBuscar.setPreferredSize(new Dimension(100, 20));
		botonBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ArrayList<Predicate<Camion>> predicados = new ArrayList<Predicate<Camion>>();
				if(fieldPatente.getText().trim().length()!=0)
					predicados.add(c -> c.getPatente().equals(fieldPatente.getText().trim()));
				if(fieldKmReco.getText().trim().length()!=0)
					predicados.add(c -> c.getKilometrosRecorridos().equals(Integer.parseInt(fieldKmReco.getText().trim())));
				if(fieldCostoKm.getText().trim().length()!=0)
					predicados.add(c -> c.getCostoPorKilometro().equals(Double.parseDouble(fieldCostoKm.getText().trim())));
				if(fieldCostoHora.getText().trim().length()!=0)
					predicados.add(c -> c.getCostoPorHora().equals(Double.parseDouble(fieldCostoHora.getText().trim())));
				if(fieldFechaCompra.getText().trim().length()!=0)
					predicados.add(c -> c.getFechaCompra().equals(LocalDate.parse(fieldFechaCompra.getText().trim())));
				if(fieldModelo.getText().trim().length()!=0)
					predicados.add(c -> c.getModelo().getNombre().equals(fieldModelo.getText().trim()));
				if(fieldMarca.getText().trim().length()!=0)
					predicados.add(c -> c.getModelo().getMarca().getNombre().equals(fieldMarca.getText().trim()));
				
				if(predicados.size()==0) {
					listaCamiones = (ArrayList<Camion>) (new CamionDAOImplSQL()).buscarCamiones();
					construirTabla(setearColumnas(), obtenerMatrizDatos());
				}
				else {
					listaCamiones = (ArrayList<Camion>) (new CamionDAOImplSQL()).buscarCamiones();
					ArrayList<Camion> result = (ArrayList<Camion>) listaCamiones.stream()
				          .filter(predicados.stream().reduce(x->true, Predicate::and))
				          .collect(Collectors.toList());
					listaCamiones = result;
					construirTabla(setearColumnas(), obtenerMatrizDatos());
				}
			}
			
		});
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
		panelBusqueda.add(labelPatente, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelBusqueda.add(fieldPatente, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelBusqueda.add(labelKmReco, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		panelBusqueda.add(fieldKmReco, gbc);
		gbc.gridx = 4;
		gbc.gridy = 0;
		panelBusqueda.add(labelCostoHora, gbc);
		gbc.gridx = 5;
		gbc.gridy = 0;
		panelBusqueda.add(fieldCostoHora, gbc);
		gbc.gridx = 6;
		gbc.gridy = 0;
		panelBusqueda.add(labelCostoKm, gbc);
		gbc.gridx = 7;
		gbc.gridy = 0;
		panelBusqueda.add(fieldCostoKm, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelBusqueda.add(labelFechaCompra, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelBusqueda.add(fieldFechaCompra, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		panelBusqueda.add(labelModelo, gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		panelBusqueda.add(fieldModelo, gbc);
		gbc.gridx = 4;
		gbc.gridy = 1;
		panelBusqueda.add(labelMarca, gbc);
		gbc.gridx = 5;
		gbc.gridy = 1;
		panelBusqueda.add(fieldMarca, gbc);
		
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
		panelAbajo.add(botonAgregarCamion, gbc);
		
		
	}
	
	
	public void construirTabla(String[] columnas, Object[][] data) {
		 model = new ModeloTablaCamiones(data, columnas);
		tablaResultados.setModel(model);
		
		//ASIGNO TIPO DE DATOS A CADA COLUMNA
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.PATENTE).setCellRenderer(new GestionCeldasCamiones("texto"));
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.KM_RECO).setCellRenderer(new GestionCeldasCamiones("numero"));
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.COSTO_KM).setCellRenderer(new GestionCeldasCamiones("numero"));
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.COSTO_HORA).setCellRenderer(new GestionCeldasCamiones("numero"));
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.FECHA_COMPRA).setCellRenderer(new GestionCeldasCamiones("texto"));
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.MODELO).setCellRenderer(new GestionCeldasCamiones("texto"));
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.MARCA).setCellRenderer(new GestionCeldasCamiones("texto"));
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.MODIFICAR).setCellRenderer(new GestionCeldasCamiones("boton"));
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.ELIMINAR).setCellRenderer(new GestionCeldasCamiones("boton"));
		
		tablaResultados.getTableHeader().setReorderingAllowed(false);
		tablaResultados.setRowHeight(25);
		tablaResultados.setGridColor(Color.BLACK);
		
		// TAMAÑO DE CADA COLUMNA
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.PATENTE).setPreferredWidth(10);
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.KM_RECO).setPreferredWidth(15);
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.COSTO_KM).setPreferredWidth(20);
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.COSTO_HORA).setPreferredWidth(20);
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.FECHA_COMPRA).setPreferredWidth(15);
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.MODELO).setPreferredWidth(20);
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.MARCA).setPreferredWidth(20);
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.MODIFICAR).setPreferredWidth(5);
		tablaResultados.getColumnModel().getColumn(UtilTablaCamiones.ELIMINAR).setPreferredWidth(5);
	}
	
	private Object[][] obtenerMatrizDatos(){
		String informacion[][] = new String[listaCamiones.size()][columnasTabla.size()];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		for(int i=0; i<informacion.length ; i++) {
			LocalDate fecha = LocalDate.parse(listaCamiones.get(i).getFechaCompra().toString(), formatter);
			informacion[i][UtilTablaCamiones.PATENTE] = listaCamiones.get(i).getPatente() + "";
			informacion[i][UtilTablaCamiones.KM_RECO] = listaCamiones.get(i).getKilometrosRecorridos() + "";
			informacion[i][UtilTablaCamiones.COSTO_KM] = listaCamiones.get(i).getCostoPorKilometro() + "";
			informacion[i][UtilTablaCamiones.COSTO_HORA] = listaCamiones.get(i).getCostoPorHora() + "";
			informacion[i][UtilTablaCamiones.FECHA_COMPRA] = fecha + "";
			informacion[i][UtilTablaCamiones.MODELO] = listaCamiones.get(i).getModelo().getNombre() + "";
			informacion[i][UtilTablaCamiones.MARCA] = listaCamiones.get(i).getModelo().getMarca().getNombre() + "";
			informacion[i][UtilTablaCamiones.MODIFICAR] = "MODIFICAR";
			informacion[i][UtilTablaCamiones.ELIMINAR] = "ELIMINAR";
		}
		
		
		return informacion;
	}
	
	private String[] setearColumnas() {
		
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Patente");
		columnasTabla.add("Km. recorridos");
		columnasTabla.add("Costo por km");
		columnasTabla.add("Costo por hora");
		columnasTabla.add("Fecha de compra");
		columnasTabla.add("Modelo");
		columnasTabla.add("Marca");
		columnasTabla.add("  ");
		columnasTabla.add("  ");
		String[] columnas = new String[columnasTabla.size()];
		
		
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		
		}
	
		return columnas;
		
	}



}
