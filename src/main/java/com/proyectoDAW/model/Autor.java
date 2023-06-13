package com.proyectoDAW.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_autores")
@Data
public class Autor {
	@Id
	private int idautor;
	private String nom_aut;
	private String apel_aut;
	private Date fch_nac;
	private String nacionalidad;
}
