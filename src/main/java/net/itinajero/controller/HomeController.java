package net.itinajero.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.itinajero.model.Vacante;
import net.itinajero.service.IVacantesService;

@Controller
public class HomeController {
	@Autowired
	private IVacantesService serviceVacantes;
	
	@GetMapping("/detalle")
	public String Detalles(Model modelo){
		Vacante v = new Vacante();
		v.setNombre("Ingeniero de comunicaciones");
		v.setDescripcion("Se solicita Ing");
		v.setFecha(new Date());
		v.setSalario(1200000);
		modelo.addAttribute("vacante", v);
		return "detalle";
	}
	
	
	
	@GetMapping("/listado")
	public String mostrarListado(Model modelo) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ing. Sistemas");
		lista.add("Ausiliar de contabilidad");
		lista.add("Arquitecto");
		modelo.addAttribute("empleados", lista);
		return "listado";
	}
	
	@GetMapping("/")
	public String MostrarHome(Model modelo) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		modelo.addAttribute("vacantes", lista);
		return "home";	
	}
	
	
	@GetMapping("/tabla")
	public String MostrarTabla(Model modelo) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		modelo.addAttribute("vacantes", lista);
		return "tabla";		
	}
	
	
	
}
