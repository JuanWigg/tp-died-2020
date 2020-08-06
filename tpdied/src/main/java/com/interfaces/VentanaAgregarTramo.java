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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.controladores.CamionController;
import com.controladores.TramoController;
import com.logica.PlantaDAOImplSQL;

/**
 * @author Pichi
 *
 */
public class VentanaAgregarTramo extends JDialog{
	JPanel panel;
	JPanel panelAbajo;
	JPanel panelCampos;
	JComboBox<String> comboOrigen;
	JComboBox<String> comboDestino;
	JLabel labelOrigen;
	JLabel labelDestino;
	JLabel labelPesoMax;
	JLabel labelDuracion;
	JLabel labelDistancia;
	JTextField fieldPesoMax;
	JTextField fieldDuracion;
	JTextField fieldDistancia;
	JButton botonAceptar;
	JButton botonCancelar;
	
	
	public VentanaAgregarTramo(JFrame padre, boolean modal) {
		super(padre, modal);
		inicializarComponentes();
		armarPanel();
		
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.setContentPane(panel);
		this.setVisible(true);
		
		
	}


	private void armarPanel() {
		// TODO Auto-generated method stub
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panelCampos = new JPanel(new GridBagLayout());
		panelAbajo = new JPanel(new FlowLayout());
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10,10,10,10);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelCampos.add(labelOrigen, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panelCampos.add(comboOrigen, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelCampos.add(labelDestino, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panelCampos.add(comboDestino, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panelCampos.add(labelPesoMax, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelCampos.add(fieldPesoMax, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panelCampos.add(labelDuracion, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panelCampos.add(fieldDuracion, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		panelCampos.add(labelDistancia, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panelCampos.add(fieldDistancia, gbc);

		panelAbajo.add(botonCancelar);
		panelAbajo.add(botonAceptar);
		
		
		panel.add(panelCampos, BorderLayout.CENTER);
		panel.add(panelAbajo, BorderLayout.SOUTH);
		
		
	}


	private void inicializarComponentes() {
		// TODO Auto-generated method stub
		//LABELS
		labelOrigen = new JLabel("Planta de origen:");
		labelDestino = new JLabel("Planta de destino:");
		labelPesoMax = new JLabel("Peso maximo (en kg):");
		labelDuracion = new JLabel("Duracion estimada (en horas):");
		labelDistancia = new JLabel("Distancia (en km):");

		//COMBOS
		String[] plantas;
		plantas = (new PlantaDAOImplSQL()).buscarNombresPlanta();
		if(plantas == null) {
			plantas = new String[1];
			plantas[0] = "<Ninguna>";
		}
		comboOrigen = new JComboBox<String>(plantas);
		comboDestino = new JComboBox<String>(plantas);
		comboOrigen.setPreferredSize(new Dimension(100, 20));
		comboDestino.setPreferredSize(new Dimension(100, 20));
		
		//FIELDS
		fieldPesoMax = new JTextField();
		fieldDuracion = new JTextField();
		fieldDistancia = new JTextField();
		fieldPesoMax.setPreferredSize(new Dimension(100, 20));
		fieldDuracion.setPreferredSize(new Dimension(100, 20));
		fieldDistancia.setPreferredSize(new Dimension(100, 20));
		
		//BUTTONS
		botonAceptar = new JButton("Aceptar");

		botonCancelar= new JButton("Cancelar");
		botonAceptar.setPreferredSize(new Dimension(100,20));
		botonCancelar.setPreferredSize(new Dimension(100,20));
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<String> datosLista = new ArrayList<String>();
				if(comboOrigen.getSelectedItem()!="<Ninguna>" && comboDestino.getSelectedItem()!="<Ninguna>"
						&& fieldPesoMax.getText().trim().length()!=0 && fieldDuracion.getText().trim().length()!=0
						&& fieldDistancia.getText().trim().length()!=0) {
					datosLista.add(comboOrigen.getSelectedItem().toString());
					datosLista.add(comboDestino.getSelectedItem().toString());
					datosLista.add(fieldPesoMax.getText().trim());
					datosLista.add(fieldDuracion.getText().trim());
					datosLista.add(fieldDistancia.getText().trim());
					(new TramoController()).altaTramo(datosLista);
					JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(panel);
					frame.dispose();
					
				}
				else {
				JOptionPane.showMessageDialog((JDialog) SwingUtilities.getWindowAncestor(panel), "No puede dejar campos vacios");
				}
				
				
			}
			
		});
		botonCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog frame = (JDialog) SwingUtilities.getWindowAncestor(panel);
				frame.dispose();
			}
			
		});
		
	}
}
