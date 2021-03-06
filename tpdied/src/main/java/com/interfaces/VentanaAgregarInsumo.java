package com.interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.controladores.CamionController;
import com.controladores.InsumoController;
import com.logica.PlantaDAOImplSQL;
import com.logica.Unidad;

public class VentanaAgregarInsumo extends JDialog {

	JPanel panel;
	
	JLabel labelDescripcion;
	JLabel labelUnidad;
	JLabel labelCostoPorUnidad;
	JLabel labelTipoInsumo;
	JLabel labelPeso;
	JLabel labelDensidad;
	JTextField fieldDescripcion;
	JTextField fieldCostoPorUnidad;
	JTextField fieldMagnitudUnidad;
	JComboBox<String> comboUnidad;
	JComboBox<String> comboTipoInsumo;
	GridBagConstraints gbc = new GridBagConstraints();
	
	String [] unidades;
	
	JButton botonAceptar;
	JButton botonCancelar;
	public VentanaAgregarInsumo(JFrame padre, boolean modal) {
		
		super(padre, modal);
		inicializarComponentes();
		armarPanel();
		this.setContentPane(panel);
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
	}
	
	private void inicializarComponentes() {
		//LABELS
				
				labelDescripcion = new JLabel("Descripcion:");
				labelUnidad = new JLabel("Unidad:");
				labelCostoPorUnidad = new JLabel("Costo por unidad:");
				labelTipoInsumo = new JLabel("Tipo Insumo:");
				labelPeso = new JLabel("Peso:");
				labelDensidad = new JLabel("Densidad:");
				
				//CAMPOS
				fieldDescripcion = new JTextField();
				fieldDescripcion.setPreferredSize(new Dimension(100, 20));
				
				unidades = new String[1];
				unidades[0] = "<Ninguna>";
				comboUnidad = new JComboBox<String>(unidades);
				comboUnidad.setPreferredSize(new Dimension(100, 20));
				
				
				fieldCostoPorUnidad = new JTextField();
				fieldCostoPorUnidad.setPreferredSize(new Dimension(100, 20));
				
				fieldMagnitudUnidad = new JTextField();
				fieldMagnitudUnidad.setPreferredSize(new Dimension(100, 20));
				
				String[] tiposInsumo = new String[3];
				tiposInsumo[0] = "<Ninguno>";
				tiposInsumo[1] = "LIQUIDO";
				tiposInsumo[2] = "GENERAL";
				comboTipoInsumo = new JComboBox<String>(tiposInsumo);
				comboTipoInsumo.setPreferredSize(new Dimension(100, 20));
				

				
				botonAceptar = new JButton("Aceptar");
				botonAceptar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {						
						new InsumoController().altaInsumo(
							fieldDescripcion.getText().trim(),
							(String) comboTipoInsumo.getSelectedItem(),
							Unidad.valueOf((String) comboUnidad.getSelectedItem()),
							Double.parseDouble(fieldCostoPorUnidad.getText().trim()),
							Double.parseDouble(fieldMagnitudUnidad.getText().trim())				
						);
						JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(panel);
						frame.dispose();
						
					}
					
						
				});
				botonCancelar = new JButton("Cancelar");
				botonCancelar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(panel);
						frame.dispose();
					}
					
				});
				
				comboTipoInsumo.addItemListener(new ItemListener() {
		

					@Override
					public void itemStateChanged(ItemEvent e) {
							if (e.getStateChange() == ItemEvent.SELECTED) {
								comboUnidad.removeAllItems();
			                    
								switch(String.valueOf(comboTipoInsumo.getSelectedItem())) {
								case "LIQUIDO":
									comboUnidad.addItem("<Ninguna>");
									comboUnidad.addItem("m3");
									comboUnidad.addItem("l");
									labelPeso.setVisible(false);
									labelDensidad.setVisible(true);
									break;
								case "GENERAL":
									comboUnidad.addItem("<Ninguna>");
									comboUnidad.addItem("kg");
									comboUnidad.addItem("g");
									labelDensidad.setVisible(false);
									labelPeso.setVisible(true);
									break;
								default:
									comboUnidad.addItem("<Ninguna>");
									labelDensidad.setVisible(false);
									labelPeso.setVisible(false);
							}
													
		                }
							
					}


		        });
				
	}
	
	private void armarPanel() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(labelDescripcion, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(fieldDescripcion, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(labelTipoInsumo, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(comboTipoInsumo, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(labelUnidad, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(comboUnidad, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(labelCostoPorUnidad, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(fieldCostoPorUnidad, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(labelPeso, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(labelDensidad, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(fieldMagnitudUnidad, gbc);		
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(botonCancelar, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(botonAceptar, gbc);
		labelPeso.setVisible(false);
		labelDensidad.setVisible(false);
	}
	
	
	
}
