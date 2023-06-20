package com.proyectoDAW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			model.addAttribute("boton2", "Recargar");
			return"crudlib";
		}
		
		@GetMapping("/cargarAutor")
		public String cargarautor(Model model) {
			model.addAttribute("lstAutores", repoAut.findAll());
			model.addAttribute("autor", new Autor());
			model.addAttribute("boton", "Registrar");
			model.addAttribute("boton2", "Recargar");
			return"crudaut";
		}
		
		@GetMapping("/cargarUsuario")
		public String cargarusuario(Model model) {
			model.addAttribute("lstUsuario", repoUsu.findAll());
			model.addAttribute("lstTipo", repoTipo.findAll());
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("boton", "Registrar");
			model.addAttribute("boton2", "Recargar");
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
		
		@PostMapping("/buscarUsu")
		public String buscarProducto(@RequestParam(name = "cod_usua") int cod_usua, Model model) {
				model.addAttribute("usuario", repoUsu.findById(cod_usua));
				model.addAttribute("lstTipo", repoTipo.findAll());
				model.addAttribute("lstUsuario", repoUsu.findAll());
				model.addAttribute("boton", "Actualizar");
				model.addAttribute("boton2", "Cancelar");
			return "crudusu";
		}
		
		@PostMapping("/buscarAut")
		public String buscarAutor(@RequestParam(name = "idautor") int idautor, Model model) {
				model.addAttribute("autor", repoAut.findById(idautor));
				model.addAttribute("lstAutores", repoAut.findAll());
				model.addAttribute("boton", "Actualizar");
				model.addAttribute("boton2", "Cancelar");
			return "crudaut";
		}
		
		@PostMapping("/buscarLib")
		public String buscarLibro(@RequestParam(name = "cod_lib") String cod_lib, Model model) {
				model.addAttribute("libro", repoLib.findById(cod_lib));
				model.addAttribute("lstLibros", repoLib.findAll());
				model.addAttribute("lstCategorias", repoCat.findAll());
				model.addAttribute("lstEditoriales", repoEdi.findAll());
				model.addAttribute("lstAutores", repoAut.findAll());
				model.addAttribute("boton", "Actualizar");
				model.addAttribute("boton2", "Cancelar");
			return "crudlib";
		}
		
		@PostMapping("/Usuarios/guardar")
		public String guardarProducto(Model model,@ModelAttribute Usuario usuario) {
			try {
				repoUsu.save(usuario);
				model.addAttribute("mensaje","La accion se realizó con éxito");
				model.addAttribute("clase","alert alert-success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				model.addAttribute("mensaje", "A ocrrido un error");
				model.addAttribute("clase", "alert alert-danger");
			}
			
			model.addAttribute("boton", "Registrar");
			model.addAttribute("boton2", "Continuar");
			return "crudusu";
		}
		
		@PostMapping("/Libros/guardar")
		public String guardarLibro(Model model,@ModelAttribute Libro libro) {
			try {
				repoLib.save(libro);
				model.addAttribute("mensaje","La accion se realizó con éxito");
				model.addAttribute("clase","alert alert-success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				model.addAttribute("mensaje", "A ocrrido un error");
				model.addAttribute("clase", "alert alert-danger");
			}
			
			model.addAttribute("boton", "Registrar");
			model.addAttribute("boton2", "Continuar");
			return "crudlib";
		}
		
		@PostMapping("/Autor/guardar")
		public String guardarAutor(Model model,@ModelAttribute Autor autor) {
			try {
				repoAut.save(autor);
				model.addAttribute("mensaje","La accion se realizó con éxito");
				model.addAttribute("clase","alert alert-success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				model.addAttribute("mensaje", "A ocrrido un error");
				model.addAttribute("clase", "alert alert-danger");
			}
			
			model.addAttribute("boton", "Registrar");
			model.addAttribute("boton2", "Continuar");
			return "crudaut";
		}
		
		@PostMapping("/eliminarL")
		public String eliminarLibro(@ModelAttribute Libro libro, Model model) {
				repoLib.deleteById(libro.getCod_lib());
				model.addAttribute("lstLibros", repoLib.findAll());
				model.addAttribute("lstCategorias", repoCat.findAll());
				model.addAttribute("lstEditoriales", repoEdi.findAll());
				model.addAttribute("lstAutores", repoAut.findAll());
				model.addAttribute("boton", "Registrar");
				model.addAttribute("boton2", "Recargar");
			return "crudlib";
		}
		
		@PostMapping("/eliminarU")
		public String eliminarUsuario(@ModelAttribute Usuario usuario, Model model) {
				repoUsu.deleteById(usuario.getCod_usua());
				model.addAttribute("lstTipo", repoTipo.findAll());
				model.addAttribute("lstUsuario", repoUsu.findAll());
				model.addAttribute("boton", "Registrar");
				model.addAttribute("boton2", "Recargar");
			return "crudusu";
		}
		
		@PostMapping("/eliminarA")
		public String eliminarAutor(@ModelAttribute Autor autor, Model model) {
				repoAut.deleteById(autor.getIdautor());
				model.addAttribute("lstAutores", repoAut.findAll());
				model.addAttribute("boton", "Registrar");
				model.addAttribute("boton2", "Recargar");
			return "crudaut";
		}
}
