package net.itinajero.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import net.itinajero.model.Vacante;


@Service
public class vacantesServiceImpl implements IVacantesService{
	private List<Vacante> lista = null;
	public vacantesServiceImpl() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		lista = new LinkedList<Vacante>();
		
		try {
			
			Vacante va1 = new Vacante();
			va1.setId(1);
			va1.setNombre("Ing. civil.");
			va1.setDescripcion("Solicitamos Ing. Civil.");
			va1.setFecha(sdf.parse("07-07-1996"));
			va1.setSalario(1200000);
			va1.setDestacado(1);
			va1.setImagen("logo15.png");
			
			Vacante va2 = new Vacante();
			va2.setId(2);
			va2.setNombre("Ing. Sistemas.");
			va2.setDescripcion("Solicitamos Ing. sistemas.");
			va2.setFecha(sdf.parse("08-02-2021"));
			va2.setSalario(1900000);
			va2.setDestacado(0);
			va2.setImagen("logo10.png");
			
			Vacante va3 = new Vacante();
			va3.setId(3);
			va3.setNombre("Ing. Eléctrico.");
			va3.setDescripcion("Solicitamos Ing. Eléctrico.");
			va3.setFecha(sdf.parse("08-02-2021"));
			va3.setSalario(1100000);
			va3.setDestacado(0);
			
			Vacante va4 = new Vacante();
			va4.setId(4);
			va4.setNombre("Ing. Grafico.");
			va4.setDescripcion("Solicitamos Ing. Grafico.");
			va4.setFecha(sdf.parse("08-02-2021"));
			va4.setSalario(1000000);
			va4.setDestacado(1);
			va4.setImagen("logo11.png");
			
			lista.add(va1);
			lista.add(va2);
			lista.add(va3);
			lista.add(va4);
			
		}catch(ParseException e) {
			System.out.println("Error: "+ e.getMessage());
		}
	}
	
	@Override
	public List<Vacante> buscarTodas() {		
		return lista;
	}

	@Override
	public Vacante buscarPorId(int idVacante) {
		for(Vacante v : lista) {
			if(v.getId()== idVacante) {
				return v;
			}
		}		
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);
		
	}
	
}
