package com.interfaces;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.logica.Planta;

public class PanelMatrizCaminos extends JPanel{
	JLabel labelCorta;
	JLabel labelRapida;
	JPanel panel;
	JPanel panelInf;
	JTable tablaCaminos;
	JButton botonAtras;
	ArrayList<String> columnasTabla;
	JSplitPane splitVert;
	JSplitPane split;
	JSplitPane split2;
	
	List<Planta> plantas;
	ModeloTablaCaminos model;
	
	public PanelMatrizCaminos() {
		super();
		inicializarComponentes();
		armarPanel();
	}


	private void inicializarComponentes() {
		botonAtras = new JButton("Atras");
		botonAtras.setPreferredSize(new Dimension(90, 30));
		botonAtras.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFrame ventana = ((JFrame) SwingUtilities.getWindowAncestor(((JButton) e.getSource()).getParent()));
				ventana.setContentPane(new PanelAlgoritmos());
				ventana.revalidate();
				ventana.repaint();
			}
			
		});
		 split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,labelRapida,labelCorta);
		 split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,labelCorta,labelRapida);
		 split.setResizeWeight(0.5d);
		 split2.setResizeWeight(0.5d);
		 splitVert = new JSplitPane(JSplitPane.VERTICAL_SPLIT,split2,split);
		
		
	}
	
	private void armarPanel() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panelInf = new JPanel();
		panelInf.setLayout(new FlowLayout());
		panel.add(splitVert, BorderLayout.CENTER);
		panel.add(panelInf, BorderLayout.SOUTH);
		panelInf.add(botonAtras);
		
	}
	
}
