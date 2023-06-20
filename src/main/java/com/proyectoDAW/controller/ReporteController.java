package com.proyectoDAW.controller;

import java.io.OutputStream;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyectoDAW.model.Libro;
import com.proyectoDAW.repository.ICategoriaRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ReporteController {
	@Autowired
	private ICategoriaRepository repoCat;
	@Autowired
	private DataSource dataSource; 
	
	@Autowired
	private ResourceLoader resourceLoader; 
	
	@GetMapping("/reportes")
	public String abrirPagReportes(Model model) {
		// enviar un "listado" para el combo
		// model.addAttribute("mensaje", "Éxito");
		model.addAttribute("lstCategorias", repoCat.findAll());
		// obj de tipo Producto para guardar los datos
		model.addAttribute("libro", new Libro());
		model.addAttribute("boton", "Reporte");
		return "reportes";
	}
	
	@GetMapping("/stock/listadopdf")
	public void reporteProductos(HttpServletResponse response) {
		// descargar directamente en un archivo
		// response.setHeader("Content-Disposition", "attachment; filename=\"reporte.pdf\";");
		
		// el pdf se muestre en pantalla
		response.setHeader("Content-Disposition", "inline;"); 
		// tipo de contenido
		response.setContentType("application/pdf");
		try {
			// obtener el recurso a utilizar -> jasper
			String ru = resourceLoader.getResource("classpath:reportes/reporte_stock.jasper").getURI().getPath();
			// combina el jasper + data / Ojo!!! null -> la conexión no tiene parámetros
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
			// genera un archivo temporal
			OutputStream outStream = response.getOutputStream();
			// muestra el archivo
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	@PostMapping("/reporte_cate")
	public void reporteFiltro(@ModelAttribute Libro libro, 
						HttpServletResponse response) {
		response.setHeader("Content-Disposition", "inline;"); 
	
		response.setContentType("application/pdf");
		try {
			String ru = resourceLoader.getResource("classpath:reportes/reporte_filtro_proyect.jasper").getURI().getPath();
			HashMap parametros = new HashMap();
			parametros.put("categoria", libro.getIdcategoria());
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, parametros, dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
}
