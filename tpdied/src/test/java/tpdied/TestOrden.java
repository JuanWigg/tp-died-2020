package tpdied;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.logica.DetalleEnvio;
import com.logica.DetalleItem;
import com.logica.EstadoOrden;
import com.logica.Grafo;
import com.logica.InsumoGeneral;
import com.logica.OrdenPedido;
import com.logica.Planta;
import com.logica.StockInsumo;
import com.logica.Tramo;
import com.logica.Unidad;

public class TestOrden {
	private Grafo g;
	private Planta p1;
	private Planta p2;
	private Planta p3;
	private Planta p4;
	private Planta p5;
	private Tramo t1;
	private Tramo t2;
	private Tramo t3;
	private Tramo t4;
	private Tramo t5;
	private Tramo t6;
	private Tramo t7;
	private OrdenPedido op;

	@Before
	public void init() {
	p1 = new Planta("p1");
	p2 = new Planta("p2");
	p3 = new Planta("p3");
	p4 = new Planta("p4");
	p5 = new Planta("p5");
	//tramo(IDTRAMO, PORIGEN PDESTINO, DISTANCIA, DURACION,PESOMAX,UNIDAD)
	t1 = new Tramo(1,p1,p2,100,10d,10d,"kg");
	t2 = new Tramo(2,p2,p3,100,10d,20d,"kg");
	t3 = new Tramo(3,p1,p3,200,10d,5d,"kg");
	t4 = new Tramo(4,p1,p4,30,10d,10d,"kg");	
	t5 = new Tramo(5,p4,p3,40,10d,10d,"kg");
    t6 = new Tramo(6,p1,p5,100,10d,30d,"kg");
	t7 = new Tramo(7,p5,p3,100,10d,25d,"kg");
	ArrayList<Tramo> tramosg= new ArrayList<Tramo>();
	ArrayList<Planta> plantasg= new ArrayList<Planta>();
	Grafo g = new Grafo(tramosg,plantasg);
	}
	@Test
	public void testCrearOrden() {
		
	}

}
