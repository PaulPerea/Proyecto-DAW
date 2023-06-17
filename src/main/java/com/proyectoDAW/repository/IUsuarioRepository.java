package com.proyectoDAW.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoDAW.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	public Usuario findByCorreoAndClave(String usr_usua,String cla_usua);

}
