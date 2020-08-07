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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.controladores.CamionController;
import com.controladores.PlantaController;
import com.controladores.TramoController;
import com.logica.Camion;
import com.logica.CamionDAOImplSQL;
import com.logica.PlantaDAOImplSQL;
import com.logica.Tramo;
import com.logica.TramoDAOImplSQL;

/**
 * @author Pichi
 *
 */
public class PanelGestionPlantas extends JPanel {
	ArrayList<String> columnasTabla;
	
	JButton botonAgregarPlanta;
	JButton botonAgregarTramo;
	JButton eliminarPlanta;
	JButton botonBuscar;
	JButton botonAtras;
	JComboBox<String> comboPlantaOrigen;
	JComboBox<String> comboPlantaDestino;
	JLabel labelOrigen;
	JLabel labelDestino;
	JLabel labelPesoMax;
	JLabel labelDuracion;
	JLabel labelDistancia;
	JTextField fieldPesoMax;
	JTextField fieldDuracion;
	JTextField fieldDistancia;
	ArrayList<Tramo> tramos;
	
	JTable tablaTramos;
	ModeloTablaTramos model;
	
	public PanelGestionPlantas() {
		super();
		tramos = (ArrayList<Tramo>) (new TramoDAOImplSQL()).obtenerTramos();
		inicializarComponentes();
		armarPanel();
	}
	
	public void inicializarComponentes() {
		//LABELS
		labelOrigen = new JLabel("Planta de origen:");
		labelDestino = new JLabel("Planta de destino:");
		labelPesoMax = new JLabel("Peso maximo permitido (en kg):");
		labelDuracion = new JLabel("Duracion estimada (en horas):");
		labelDistancia = new JLabel("Distancia (en km):");
		
		//FIELDS
		fieldPesoMax = new JTextField();
		fieldPesoMax.setPreferredSize(new Dimension(100, 20));
		
		fieldDuracion = new JTextField();
		fieldDuracion.setPreferredSize(new Dimension(100, 20));
		
		fieldDistancia = new JTextField();
		fieldDistancia.setPreferredSize(new Dimension(100, 20));
		//COMBOS
		String[] plantas;
		plantas = (new PlantaDAOImplSQL()).buscarNombresPlanta();
		if(plantas == null) {
			plantas = new String[1];
			plantas[0] = "<Ninguna>";
		}
		comboPlantaOrigen = new JComboBox<String>(plantas);
		comboPlantaDestino = new JComboBox<String>(plantas);
		
		comboPlantaOrigen.setPreferredSize(new Dimension(100, 20));
		comboPlantaDestino.setPreferredSize(new Dimension(100, 20));
		
		//BUTTONS
		botonAgregarPlanta = new JButton("Agregar nueva planta");
		botonAgregarPlanta.setPreferredSize(new Dimension(200, 50));
		
		botonAgregarPlanta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nombre_planta = JOptionPane.showInputDialog("Ingrese nombre de la nueva planta");
				if(nombre_planta!=null && nombre_planta.length()!=0) {
					(new PlantaController()).altaPlanta(nombre_planta);
					comboPlantaOrigen.addItem(nombre_planta);
					comboPlantaDestino.addItem(nombre_planta);
					
				}
					
			}
			
		});
		
		
		botonAgregarTramo = new JButton("Agregar nuevo tramo");
		botonAgregarTramo.setPreferredSize(new Dimension(200, 50));
		botonAgregarTramo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VentanaAgregarTramo dialogo = new VentanaAgregarTramo(new JFrame(), true);
				tramos = (ArrayList<Tramo>) (new TramoDAOImplSQL()).obtenerTramos();
				construirTabla(setearColumnas(), obtenerMatrizDatos());
			}
			
			
			
		});
		
		
		
		botonBuscar = new JButton("Buscar");
		botonBuscar.setPreferredSize(new Dimension(100, 20));
		botonBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ArrayList<Predicate<Tramo>> predicados = new ArrayList<Predicate<Tramo>>();
				if(!comboPlantaOrigen.getSelectedItem().toString().equals("<Ninguna>"))
					predicados.add(t -> t.getPlantaOrigen().getNombre().equals(comboPlantaOrigen.getSelectedItem().toString()));
				if(!comboPlantaDestino.getSelectedItem().toString().equals("<Ninguna>"))
					predicados.add(t -> t.getPlantaDestino().getNombre().equals(comboPlantaDestino.getSelectedItem().toString()));
				if(fieldPesoMax.getText().trim().length()!=0)
					predicados.add(t -> t.getPesoMaximoPermitido().equals(Integer.parseInt(fieldPesoMax.getText().trim())));
				if(fieldDuracion.getText().trim().length()!=0)
					predicados.add(t -> t.getDuracionEstimada().equals(Double.parseDouble(fieldDuracion.getText().trim())));
				if(fieldDistancia.getText().trim().length()!=0)
					predicados.add(t -> t.getDistancia().equals(Integer.parseInt(fieldDistancia.getText().trim())));
				
				
				if(predicados.size()==0) {
					tramos = (ArrayList<Tramo>) (new TramoDAOImplSQL()).obtenerTramos();
					construirTabla(setearColumnas(), obtenerMatrizDatos());
				}
				else {
					tramos = (ArrayList<Tramo>) (new TramoDAOImplSQL()).obtenerTramos();
					ArrayList<Tramo> result = (ArrayList<Tramo>) tramos.stream()
				          .filter(predicados.stream().reduce(x->true, Predicate::and))
				          .collect(Collectors.toList());
					tramos = result;
					construirTabla(setearColumnas(), obtenerMatrizDatos());
				}
			}
			
		});
		
		
		
		
		
		
		
		botonAtras = new JButton("Atrás");
		botonAtras.setPreferredSize(new Dimension(200,50));
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
		
		//TABLE
		tablaTramos = new JTable();
		tablaTramos.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int fila = tablaTramos.rowAtPoint(e.getPoint());
				int columna = tablaTramos.columnAtPoint(e.getPoint());
				if(columna==UtilTablaTramos.ELIMINAR) {
					String[] botonesDialogo = {"Aceptar", "Cancelar"};
					int dialogo = JOptionPane.showOptionDialog(null,
							"¿Seguro que desea eliminar el tramo seleccionado?", 
							"Confirmacion",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.QUESTION_MESSAGE, null,
							botonesDialogo, botonesDialogo[0]
							);
					if(dialogo==0) {
						(new TramoController()).eliminar( tramos.get(fila) );
						model.removeRow(fila);
						tramos.remove(fila);
						model.fireTableDataChanged();
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		construirTabla(setearColumnas(), obtenerMatrizDatos());
	}
	
	public void armarPanel() {
		this.setLayout(new BorderLayout());
		
		
		JPanel panelBusqueda = new JPanel();

		JPanel panelAbajo = new JPanel();
		
		panelBusqueda.setLayout(new GridBagLayout());
		panelAbajo.setLayout(new FlowLayout());
		
		this.add(panelBusqueda, BorderLayout.NORTH);

		this.add(panelAbajo, BorderLayout.SOUTH);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10,10,10,10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelBusqueda.add(labelOrigen, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelBusqueda.add(comboPlantaOrigen, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		panelBusqueda.add(labelDestino, gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		panelBusqueda.add(comboPlantaDestino, gbc);
		gbc.gridx = 4;
		gbc.gridy = 0;
		panelBusqueda.add(labelPesoMax, gbc);
		gbc.gridx = 5;
		gbc.gridy = 0;
		panelBusqueda.add(fieldPesoMax, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelBusqueda.add(labelDuracion, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelBusqueda.add(fieldDuracion, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		panelBusqueda.add(labelDistancia, gbc);
		gbc.gridx = 3;
		gbc.gridy = 1;
		panelBusqueda.add(fieldDistancia, gbc);
		gbc.gridx = 4;
		gbc.gridy = 1;
		panelBusqueda.add(botonBuscar, gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(new JScrollPane(tablaTramos), BorderLayout.CENTER);
		
		panelAbajo.add(botonAtras);

		panelAbajo.add(botonAgregarPlanta);

		panelAbajo.add(botonAgregarTramo);
		
	}
	
	private String[] setearColumnas() {
		
		columnasTabla = new ArrayList<String>();
		columnasTabla.add("Planta origen");
		columnasTabla.add("Planta destino");
		columnasTabla.add("Peso maximo en kg");
		columnasTabla.add("Duracion estimada en horas");
		columnasTabla.add("Distancia en km");
		columnasTabla.add("  ");
		String[] columnas = new String[columnasTabla.size()];
		
		
		for(int i=0; i<columnas.length; i++) {
			columnas[i] = columnasTabla.get(i);
		
		}
		return columnas;
	}
	
	
	private Object[][] obtenerMatrizDatos(){
		String informacion[][] = new String[tramos.size()][columnasTabla.size()];
		
		for(int i=0; i<informacion.length ; i++) {
			informacion[i][UtilTablaTramos.PLANTA_ORIGEN] = tramos.get(i).getPlantaOrigen().getNombre() + "";
			informacion[i][UtilTablaTramos.PLANTA_DESTINO] = tramos.get(i).getPlantaDestino().getNombre() + "";
			informacion[i][UtilTablaTramos.PESO_MAX] = tramos.get(i).getPesoMaximoPermitido() + "";
			informacion[i][UtilTablaTramos.DURACION] = tramos.get(i).getDuracionEstimada() + "";
			informacion[i][UtilTablaTramos.DISTANCIA] = tramos.get(i).getDistancia() + "";
			informacion[i][UtilTablaTramos.ELIMINAR] = "ELIMINAR";
		}
		
		
		return informacion;
	}
	
	public void construirTabla(String[] columnas, Object[][] data) {
		model = new ModeloTablaTramos(data, columnas);
		tablaTramos.setModel(model);
		
		//ASIGNO TIPO DE DATOS A CADA COLUMNA
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.PLANTA_ORIGEN).setCellRenderer(new GestionCeldasCamiones("texto"));
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.PLANTA_DESTINO).setCellRenderer(new GestionCeldasCamiones("texto"));
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.PESO_MAX).setCellRenderer(new GestionCeldasCamiones("numero"));
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.DURACION).setCellRenderer(new GestionCeldasCamiones("numero"));
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.DISTANCIA).setCellRenderer(new GestionCeldasCamiones("numero"));
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.ELIMINAR).setCellRenderer(new GestionCeldasCamiones("boton"));
		
		tablaTramos.getTableHeader().setReorderingAllowed(false);
		tablaTramos.setRowHeight(25);
		tablaTramos.setGridColor(Color.BLACK);
		
		// TAMAÑO DE CADA COLUMNA
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.PLANTA_ORIGEN).setPreferredWidth(20);
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.PLANTA_DESTINO).setPreferredWidth(20);
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.PESO_MAX).setPreferredWidth(20);
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.DURACION).setPreferredWidth(15);
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.DISTANCIA).setPreferredWidth(15);
		tablaTramos.getColumnModel().getColumn(UtilTablaTramos.ELIMINAR).setPreferredWidth(5);
		
	}
}
