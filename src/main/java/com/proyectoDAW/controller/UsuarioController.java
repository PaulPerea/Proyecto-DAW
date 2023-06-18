package com.proyectoDAW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyectoDAW.model.Autor;
import com.proyectoDAW.model.Libro;
import com.proyectoDAW.model.Usuario;
import com.proyectoDAW.repository.IAutorRepository;
import com.proyectoDAW.repository.ICategoriaRepository;
import com.proyectoDAW.repository.IEditorialRepository;
import com.proyectoDAW.repository.ILibroRepository;
import com.proyectoDAW.repository.ITipoRepository;
import com.proyectoDAW.repository.IUsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private ILibroRepository repoLib;
	@Autowired
	private IAutorRepository repoAut;
	@Autowired
	private IEditorialRepository repoEdi;
	@Autowired
	private ICategoriaRepository repoCat;
	@Autowired
	private ITipoRepository repoTipo;
	
	@GetMapping("/")
	public String abrirPagPrincipal() {
		return "principal";
	}
	@GetMapping("/salir")
	public String salirSesion() {
		return "principal";
	}
	@GetMapping("/menu")
	public String abrirMenu() {
		return "menu";
	}

		@GetMapping("/logout")
		public String login(Model model) {
			model.addAttribute("usuario",new Usuario());
			return"login";
		}
		
		@GetMapping("/cargarLibro")
		public String cargarlibro(Model model) {
			model.addAttribute("lstLibros", repoLib.findAll());
			model.addAttribute("lstCategorias", repoCat.findAll());
			model.addAttribute("lstEditoriales", repoEdi.findAll());
			model.addAttribute("lstAutores", repoAut.findAll());
			model.addAttribute("libro", new Libro());
			model.addAttribute("boton", "Registrar");
			return"crudlib";
		}
		
		@GetMapping("/cargarAutor")
		public String cargarautor(Model model) {
			model.addAttribute("lstAutores", repoAut.findAll());
			model.addAttribute("autor", new Autor());
			model.addAttribute("boton", "Registrar");
			return"crudaut";
		}
		
		@GetMapping("/cargarUsuario")
		public String cargarusuario(Model model) {
			model.addAttribute("lstUsuario", repoUsu.findAll());
			model.addAttribute("lstTipo", repoTipo.findAll());
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("boton", "Registrar");
			return"crudusu";
		}
		
		@Autowired
		private IUsuarioRepository repoUsu;
				
		@PostMapping("/login")
		public String acceso(@ModelAttribute Usuario usuario, Model model) {
			
			Usuario u=repoUsu.findByCorreoAndClave(usuario.getCorreo(), usuario.getClave());
			if (u!=null) {
				return "menu";
			}else {
				model.addAttribute("mensaje","usuario o clave incorrecto ");
				model.addAttribute("clase","alert alert-danger");
				return "login";
			}
			//System.out.println(usuario);
			
		}
}
