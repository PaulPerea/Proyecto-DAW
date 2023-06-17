package com.proyectoDAW.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyectoDAW.model.Usuario;
import com.proyectoDAW.repository.IUsuarioRepository;

@Controller
public class UsuarioController {
	
	@GetMapping("/")
	public String abrirPagPrincipal() {
		return "principal";
	}

		@GetMapping("/logout")
		public String login(Model model) {
			model.addAttribute("usuario",new Usuario());
			return"login";
		}
		
		@Autowired
		private IUsuarioRepository repoUsu;
				
		@PostMapping("/login")
		public String acceso(@ModelAttribute Usuario usuario, Model model) {
			
			Usuario u=repoUsu.findByCorreoAndClave(usuario.getCorreo(), usuario.getClave());
			if (u!=null) {
				return "principal";
			}else {
				model.addAttribute("mensaje","usuario o clave incorrecto ");
				model.addAttribute("clase","alert alert-danger");
				return "login";
			}
			//System.out.println(usuario);
			
		}
}
