package com.proyectoDAW.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoDAW.model.Libro;


public interface ILibroRepository extends JpaRepository<Libro, String>{
	
}
