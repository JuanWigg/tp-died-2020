package com.interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import com.logica.Insumo;
import com.logica.InsumoGeneral;
import com.logica.InsumoLiquido;
import com.logica.Unidad;

public class VentanaModificarInsumo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel panel;
	
	JLabel labelDescripcion;
	JLabel labelUnidad;
	JLabel labelCostoPorUnidad;
	JLabel labelPeso;
	JLabel labelDensidad;
	JTextField fieldDescripcion;
	JTextField fieldCostoPorUnidad;
	JTextField fieldMagnitudUnidad;
	JComboBox<String> comboUnidad;
	GridBagConstraints gbc = new GridBagConstraints();
	String[] unidades;
	
	JButton botonAceptar;
	JButton botonCancelar;
	
	Insumo oldInsumo;
	
	public VentanaModificarInsumo(Insumo insumo, JFrame padre, boolean modal) {		
		super(padre, modal);
		this.oldInsumo = insumo;
		inicializarComponentes(insumo);
		armarPanel();
		this.setContentPane(panel);
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		
		
		
	}
	
	private void inicializarComponentes(final Insumo oldInsumo) {
		//LABELS
				
				labelDescripcion = new JLabel("Descripcion:");
				labelUnidad = new JLabel("Unidad:");
				labelCostoPorUnidad = new JLabel("Costo por unidad:");
				labelPeso = new JLabel("Peso:");
				labelDensidad = new JLabel("Densidad:");
				
				//CAMPOS
				fieldDescripcion = new JTextField();
				fieldDescripcion.setPreferredSize(new Dimension(100, 20));
				fieldDescripcion.setText(oldInsumo.getDescripcion());
				
				if(oldInsumo instanceof InsumoLiquido) {
					unidades = new String[2];
					unidades[0] = "m3";
					unidades[1] = "l";
					labelPeso.setVisible(false);
					labelDensidad.setVisible(true);
				} else if(oldInsumo instanceof InsumoGeneral) {
					unidades = new String[2];
					unidades[0] = "kg";
					unidades[1] = "g";
					labelDensidad.setVisible(false);
					labelPeso.setVisible(true);
				}
				else {
					unidades = new String[1];
					unidades[0] = "<Ninguna>";
					labelDensidad.setVisible(false);
					labelPeso.setVisible(false);
				}					
	
				
				comboUnidad = new JComboBox<String>(unidades);
				comboUnidad.setPreferredSize(new Dimension(100, 20));
				comboUnidad.setSelectedItem(this.oldInsumo.getUnidad().toString());
				
				
				fieldCostoPorUnidad = new JTextField();
				fieldCostoPorUnidad.setPreferredSize(new Dimension(100, 20));
				fieldCostoPorUnidad.setText(String.valueOf(oldInsumo.getCostoPorUnidad()));
				
				fieldMagnitudUnidad = new JTextField();
				fieldMagnitudUnidad.setPreferredSize(new Dimension(100, 20));		
				if(oldInsumo instanceof InsumoGeneral) {
					fieldMagnitudUnidad.setText(String.valueOf(((InsumoGeneral) oldInsumo).getPeso()));
				} else if(oldInsumo instanceof InsumoLiquido) {
					fieldMagnitudUnidad.setText(String.valueOf(((InsumoLiquido) oldInsumo).getDensidad()));
				}
				
				botonAceptar = new JButton("Aceptar");
				botonAceptar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {	
						Insumo newInsumo = null;
						String descripcion = fieldDescripcion.getText().trim();
						String unidadString = ((String) comboUnidad.getSelectedItem()).trim();
						Unidad unidad = Unidad.valueOf(unidadString);
						String costoPorUnidadString = fieldCostoPorUnidad.getText();
						double costoPorUnidad;
						costoPorUnidad = Double.parseDouble(costoPorUnidadString);
						String magnitudUnidadString = fieldMagnitudUnidad.getText();
						double magnitudUnidad = Double.parseDouble(magnitudUnidadString);
						
						
						if(oldInsumo instanceof InsumoLiquido) {
							newInsumo = new InsumoLiquido(oldInsumo.getId(), descripcion , unidad , costoPorUnidad, magnitudUnidad);
						}
						else if (oldInsumo instanceof InsumoGeneral) {
							newInsumo = new InsumoGeneral(oldInsumo.getId(), descripcion , unidad , costoPorUnidad, magnitudUnidad);
						}
						
						new InsumoController().modificarInsumo(
								newInsumo
								, oldInsumo);
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
		panel.add(labelUnidad, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(comboUnidad, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(labelCostoPorUnidad, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(fieldCostoPorUnidad, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(labelPeso, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(labelDensidad, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(fieldMagnitudUnidad, gbc);		
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(botonCancelar, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(botonAceptar, gbc);
	}
	
	
	
}
