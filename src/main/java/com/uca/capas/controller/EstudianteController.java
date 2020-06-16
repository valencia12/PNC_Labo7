package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.EstudianteDao;
import com.uca.capas.domain.Estudiante;
import com.uca.capas.service.EstudianteService;

@Controller
public class EstudianteController {
	@Autowired
	private EstudianteService estudianteService;
	
	@RequestMapping("/inicio")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = new Estudiante();
		mav.addObject("estudiante", estudiante);
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/validacion")
	public ModelAndView validacion(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) 
		{
			mav.setViewName("index");
		}else
		{
			estudianteService.save(estudiante);
			Estudiante estudiante2 = new Estudiante();
			mav.addObject("estudiante", estudiante2);
			mav.addObject("success", true);
			mav.setViewName("index");

		}
		
		
		return mav;
	}
	
	
	@RequestMapping(value="/validacion2",params="action=buscar", method=RequestMethod.POST)
	public ModelAndView findOne(@RequestParam(value="codigo") int id) {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = null;
		try 
		{
			estudiante = estudianteService.findOne(id);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		mav.addObject("estudiante", estudiante);
		mav.setViewName("mostrarestudiante");
		
		return mav;
	}
	
	@RequestMapping(value="/validacion2",params="action=editar", method=RequestMethod.POST)
	public ModelAndView editar(@RequestParam(value="codigo") int id) {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = null;
		try 
		{
			estudiante = estudianteService.findOne(id);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		mav.addObject("estudiante", estudiante);
		mav.setViewName("editar");
		
		return mav;
	}
	
	@RequestMapping(value="/validacion2", params="action=eliminar", method=RequestMethod.POST)
	public ModelAndView delete(@RequestParam(value="codigo") int id) {
		ModelAndView mav = new ModelAndView();
		try 
		{
			estudianteService.delete(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Estudiante estudiante = new Estudiante();
		mav.addObject("estudiante", estudiante);
		mav.addObject("successDelete", true);
		mav.setViewName("index");
		
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		
		try {
			estudiantes = estudianteService.findAll();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("listado");
		
		return mav;
	}
	
	
	
}