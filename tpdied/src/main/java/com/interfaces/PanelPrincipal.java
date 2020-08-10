/**
 * 
 */
package com.interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author Pichi
 *
 */
public class PanelPrincipal extends JPanel{
	JButton opcionesCamiones;
	JButton opcionesPlantas;
	JButton opcionesInsumos;
	JButton opcionesStockInsumo;
	JButton opcionesPedidos;
	JButton opcionesAlgoritmos;
	
	public PanelPrincipal() {
		super();
		inicializarComponentes();
		armarPanel();
		
		
	}
	
	public void inicializarComponentes() {
		opcionesCamiones = new JButton("Gestionar Camiones");
		opcionesPlantas = new JButton("Gestionar Plantas");
		opcionesInsumos = new JButton("Gestionar Insumos");
		opcionesStockInsumo = new JButton("Gestionar Stocks de Plantas");
		opcionesPedidos = new JButton("Gestionar Pedidos");
		opcionesAlgoritmos = new JButton("Algoritmos Sobre El Grafo");
		
		opcionesCamiones.setPreferredSize(new Dimension(200,75));
		opcionesCamiones.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelGestionCamiones());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		
		opcionesPlantas.setPreferredSize(new Dimension(200,75));
		opcionesPlantas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelGestionPlantas());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		opcionesInsumos.setPreferredSize(new Dimension(200,75));
		opcionesInsumos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelGestionInsumos());
				ventana.revalidate();
				ventana.repaint();
			}
		});
		opcionesStockInsumo.setPreferredSize(new Dimension(200,75));
		opcionesStockInsumo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelGestionStockInsumo());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		opcionesPedidos.setPreferredSize(new Dimension(200,75));
		opcionesPedidos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelGestionOrdenes());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		
		opcionesAlgoritmos.setPreferredSize(new Dimension(200,75));
		opcionesAlgoritmos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelAlgoritmos());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		
		
	}
	
	public void armarPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2, 10, 10, 2);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(opcionesCamiones, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(opcionesPlantas, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(opcionesInsumos, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(opcionesStockInsumo, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(opcionesPedidos, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(opcionesAlgoritmos, gbc);
	}
}
