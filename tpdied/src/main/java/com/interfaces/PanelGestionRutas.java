package com.interfaces;

import java.util.List;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.controladores.RutaController;
import com.logica.*;

/*
 * 
 * FALTA LOGICA DE ENVIOS, NECESITAMOS ESO PARA TENER EN CUENTA EL PESOMAX
 * 
 * */
public class PanelGestionRutas {
	ArrayList<String> columnasTabla;
	
	JButton botonCalcularRutas;
	JComboBox<String> comboPlantaOrigen;
	JComboBox<String> comboPlantaDestino;
	JLabel plantaOrigen;
	JLabel plantaDestino;

	
	JTable tablaRutas;
	ModeloTablaRutas model;
	
	public PanelGestionRutas() {
		super();
		//inicializarComponentes();
		//armarPanel();
	} 
	
	public void inicializarComponentes() {
		//LABELS
		JLabel plantaOrigen= new JLabel("Planta de Origen: ");
		JLabel plantaDestino= new JLabel("Planta de Destino: ");
		
		//COMBOS
		Grafo g = new Grafo();
		String[] nomPlantas  = (new PlantaDAOImplSQL()).buscarNombresPlanta();
		for (String planta : nomPlantas) {
				g.agregarPlanta(new Planta(planta));
		}
		List<Tramo> tramos  = (new TramoDAOImplSQL().obtenerTramos());
		for (Tramo t : tramos) {
				g.agregarTramo(t);
		}
		
		comboPlantaOrigen = new JComboBox<String>();
		comboPlantaDestino = new JComboBox<String>();
		
		comboPlantaOrigen.setPreferredSize(new Dimension(100, 20));
		comboPlantaDestino.setPreferredSize(new Dimension(100, 20));
		for (String nomP : nomPlantas) {
			comboPlantaDestino.addItem(nomP);
			comboPlantaOrigen.addItem(nomP);
		}
	    //BUTTONS
		botonCalcularRutas = new JButton("Calcular rutas");
		botonCalcularRutas.setPreferredSize(new Dimension(200, 50));
		
		botonCalcularRutas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Ruta> rutasMasCortas = new ArrayList<Ruta>();
				List<Ruta> rutasMasRapidas = new ArrayList<Ruta>();
				RutaController contRuta=(new RutaController());
				rutasMasCortas=contRuta.calcularRutasCortas(g,new Planta((String) comboPlantaOrigen.getSelectedItem()),
						new Planta((String) comboPlantaDestino.getSelectedItem()));
				rutasMasRapidas =contRuta.calcularRutasRapidas(g,new Planta((String) comboPlantaOrigen.getSelectedItem()),
						new Planta((String) comboPlantaDestino.getSelectedItem()));
				if(!rutasMasCortas.isEmpty())
				JOptionPane.showMessageDialog(null,"Rutas calculadas con éxito!");
				else
				JOptionPane.showMessageDialog(null,"No hay rutas disponibles para realizar envios entre ambas plantas");
				
					
			}
			
		});
		
		
	}
		
	
	
	
}
