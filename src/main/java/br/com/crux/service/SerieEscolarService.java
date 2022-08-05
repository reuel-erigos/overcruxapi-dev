package br.com.crux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crux.cmd.GetSerieEscolarCmd;
import br.com.crux.to.SerieEscolarTO;

@RestController
@RequestMapping(value = "serieescolar")
public class SerieEscolarService {
	
	@Autowired
	private GetSerieEscolarCmd getCmd;
	
	@GetMapping
	public List<SerieEscolarTO> getAll() {
		return getCmd.getAll();
	}
	
}
