package br.com.crux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.crux.dao.ExtratoDao;

@RestController
@RequestMapping(value = "extrato")
public class ExtratoService {
	
	@Autowired
	private ExtratoDao extratoDao;

	//@RequestMapping("/titulos/novo" )
	@GetMapping(path = "/conta")
	public ModelAndView novo() {
		
		ModelAndView mv = new ModelAndView("ExtratoContaBancaria");
		
		mv.addObject("extrato", extratoDao.getExtrato());
		return mv;
	}
}