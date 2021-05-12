package net.itinajero.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.model.Vacante;
import net.itinajero.service.ICategoriasService;
import net.itinajero.service.IVacantesService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private IVacantesService serviceVacantes;
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
	
	
	
	@GetMapping("/view/{id}")
	public String verDetaller(@PathVariable("id") int idVacante, Model model) {//Parte dinamica de las variables en los enlaces
		
		Vacante v = serviceVacantes.buscarPorId(idVacante);
		
		System.out.print("Vacante: "+ v);	
		model.addAttribute("vacante", v);
		return "detalle";
	}
	
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.print("Borrando vacante con id: "+ idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}
	
	
	@GetMapping("/create")
	public String crear(Vacante vacante, Model modelo) {
		modelo.addAttribute("categorias", serviceCategorias.buscarTodas());
		return "vacantes/formVacante";
	}
	
	/*
	@PostMapping("/save")
	public String guardar(
			@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion,
			@RequestParam("estatus") String estatus,
			@RequestParam("fecha") String fecha,
			@RequestParam("destacado") int destacado,
			@RequestParam("salario") double salario,
			@RequestParam("detalles") String detalles) {
		
		System.out.println("Nombre vacante: "+nombre);
		System.out.println("Descripcion: "+descripcion);
		System.out.println("Estatus: "+estatus);
		System.out.println("Fecha de publicación: "+fecha);
		System.out.println("Destacado: "+destacado);
		System.out.println("Salario ofrecido: "+salario);
		System.out.println("Detalles: "+detalles);
		return "vacantes/listVacantes";
	}
	
	*/
	
	@PostMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			for(ObjectError error: result.getAllErrors()) {
				System.out.println("Error: "+error.getDefaultMessage());
			}
			return "vacantes/formVacante";
		}
		serviceVacantes.guardar(vacante);
		attr.addFlashAttribute("msg", "Registro guardado");
		System.out.println(vacante);
		return "redirect:/vacantes/index";
	}
	
	
	@InitBinder
	public void initBinder( WebDataBinder webDataBinder ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false) );
	}
	
	
	
	
}
