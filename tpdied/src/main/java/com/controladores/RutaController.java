package com.controladores;

import java.util.ArrayList;
import java.util.List;

import com.logica.*;
//ver, se podria cambiar el int i = 1 y meterlo en el for al i
public class RutaController {
	
	public int altaRuta(Ruta r) {
		return (new RutaDAOImplSQL()).altaRuta(r);
	}
}
