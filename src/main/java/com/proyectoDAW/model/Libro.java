package com.proyectoDAW.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_libros")
@Data
public class Libro {
	@Id
	private String cod_lib;
	private String nom_lib;
	private int ideditorial;
	private int idautor;
	private int idcategoria;
	private double prec_lib;
	private int stock_lib;
	private String fch_public;
	
	@ManyToOne
	@JoinColumn(name = "idautor", insertable = false, updatable = false)
	private Autor objAutor;
	
	@ManyToOne
	@JoinColumn(name = "ideditorial", insertable = false, updatable = false)
	private Editorial objEditorial;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria", insertable = false, updatable = false)
	private Categoria objCategoria;
}
