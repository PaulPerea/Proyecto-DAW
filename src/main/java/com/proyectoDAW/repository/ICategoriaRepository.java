package com.proyectoDAW.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyectoDAW.model.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {

}
